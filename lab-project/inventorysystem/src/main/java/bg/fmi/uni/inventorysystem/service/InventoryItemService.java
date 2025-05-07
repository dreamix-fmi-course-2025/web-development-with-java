package bg.fmi.uni.inventorysystem.service;


import bg.fmi.uni.inventorysystem.config.AppConfig;
import bg.fmi.uni.inventorysystem.config.logger.Logger;
import bg.fmi.uni.inventorysystem.dto.InventoryItemDto;
import bg.fmi.uni.inventorysystem.dto.InventoryItemPatchRequest;
import bg.fmi.uni.inventorysystem.dto.InventoryItemRequest;
import bg.fmi.uni.inventorysystem.exception.ItemNotFoundException;
import bg.fmi.uni.inventorysystem.model.InventoryItem;
import bg.fmi.uni.inventorysystem.vo.UnitOfMeasurement;
import bg.fmi.uni.inventorysystem.vo.Category;
import bg.fmi.uni.inventorysystem.repository.InventoryItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service layer for handling inventory operations.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryItemService {

    private final Logger logger;
    private final AppConfig appConfig;
    private final InventoryItemRepository itemRepository;

    @Value("${config.inventory.low-stock-threshold:10}")
    private Integer lowStockThreshold;

    /**
     * Retrieves all inventory items.
     * @return List of all items in inventory.
     */
    public List<InventoryItemDto> getAllItems() {
        return itemRepository.findAll().stream()
                .map(InventoryItemDto::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all inventory items that are low in stock.
     * @return List of inventory items below the threshold.
     */
    public List<InventoryItem> getLowStockItems() {
        logger.info("Low-stock value used: lowStockThreshold -> " + lowStockThreshold);
        logger.info("Low-stock value used: appConfig.getInventory().getLowStockThreshold() -> " + lowStockThreshold);
        return itemRepository.findAll().stream()
            .filter(item -> item.getQuantity() < appConfig.getInventory().getLowStockThreshold())
            .collect(Collectors.toList());
    }

    /**
     * Retrieves an inventory item by its ID.
     * @param id The ID of the item to fetch.
     * @return Optional containing the item if found, or empty otherwise.
     */
    public Optional<InventoryItemDto> getItemById(Integer id) {
        return itemRepository.findById(id)
                .map(InventoryItemDto::fromEntity);
    }


    public InventoryItemDto createItem(InventoryItemRequest request) {
        try {
            InventoryItem item = new InventoryItem(
                request.name(),
                request.description(),
                request.quantity(),
                request.serialNumber(),
                UnitOfMeasurement.parse(request.unitOfMeasurement()),
                Category.parse(request.category()),
                request.borrowable()
            );
            itemRepository.save(item);
            return InventoryItemDto.fromEntity(item);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid unit of measurement or category: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public InventoryItemDto upsertItem(Integer id, InventoryItemRequest request) {
        Optional<InventoryItem> existing = itemRepository.findById(id);

        InventoryItem item;
        if (existing.isPresent()) {
            item = existing.get();
            item.setName(request.name());
            item.setDescription(request.description());
            item.setQuantity(request.quantity());
            item.setSerialNumber(request.serialNumber());
            item.setUnitOfMeasurement(UnitOfMeasurement.parse(request.unitOfMeasurement()));
            item.setCategory(Category.parse(request.category()));
            item.setBorrowable(request.borrowable());

            logger.debug("Existing item found with id " + id + ". Updating item based on the provided request");
            itemRepository.save(item);
        } else {
            item = new InventoryItem(
                    request.name(), request.description(), request.quantity(),
                    request.serialNumber(),
                    UnitOfMeasurement.parse(request.unitOfMeasurement()),
                    Category.parse(request.category()),
                    request.borrowable()
            );
            logger.debug("No existing item found with id " + id + ". Creating new one");
            itemRepository.save(item);
        }
        return InventoryItemDto.fromEntity(item);
    }

    public Optional<InventoryItemDto> patchItem(Integer id, InventoryItemPatchRequest patchRequest) {
        Optional<InventoryItem> optionalItem = itemRepository.findById(id);
        if (optionalItem.isEmpty()) {
            throw new ItemNotFoundException(id);
        }

        InventoryItem item = optionalItem.get();

        patchRequest.getName().ifPresent(item::setName);
        patchRequest.getDescription().ifPresent(item::setDescription);
        patchRequest.getQuantity().ifPresent(item::setQuantity);
        patchRequest.getUnitOfMeasurement().ifPresent(unitStr -> item.setUnitOfMeasurement(UnitOfMeasurement.parse(unitStr)));
        patchRequest.getCategory().ifPresent(catStr -> item.setCategory(Category.parse(catStr)));
        patchRequest.getBorrowable().ifPresent(item::setBorrowable);

        itemRepository.save(item);

        return Optional.of(InventoryItemDto.fromEntity(item));
    }

    // we can directly throw exception if element is not present or reuse the boolean flag in upper layer
    public boolean deleteItem(Integer id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
