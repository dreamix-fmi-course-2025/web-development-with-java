package bg.fmi.uni.inventorysystem.service;


import bg.fmi.uni.inventorysystem.config.AppConfig;
import bg.fmi.uni.inventorysystem.config.logger.Logger;
import bg.fmi.uni.inventorysystem.dto.InventoryItemDto;
import bg.fmi.uni.inventorysystem.dto.InventoryItemPatchRequest;
import bg.fmi.uni.inventorysystem.dto.InventoryItemRequest;
import bg.fmi.uni.inventorysystem.exception.ItemNotFoundException;
import bg.fmi.uni.inventorysystem.model.InventoryItem;
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

    @Deprecated
    public List<InventoryItem> getAllItemsEntity() {
        return itemRepository.getAllItems();
    }

    /**
     * Retrieves all inventory items.
     * @return List of all items in inventory.
     */
    public List<InventoryItemDto> getAllItems() {
        return itemRepository.getAllItems().stream()
                .map(InventoryItemDto::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * Adds a new item to the inventory.
     * @param name The name of the item.
     * @param description The item's description.
     * @param quantity The quantity available.
     * @param unit The unit of measurement.
     * @param category The category of the item.
     * @param borrowable Whether the item can be borrowed.
     */
    public void addItem(String name, String description, int quantity, String serialNumber, String unit, String category, boolean borrowable) {
        logger.trace("addItem functionality triggered");
        InventoryItem item = new InventoryItem(name, description, quantity, serialNumber, unit, category, borrowable);
        itemRepository.addItem(item);
    }

    /**
     * Retrieves all inventory items that are low in stock.
     * @return List of inventory items below the threshold.
     */
    public List<InventoryItem> getLowStockItems() {
        logger.info("Low-stock value used: lowStockThreshold -> " + lowStockThreshold);
        logger.info("Low-stock value used: appConfig.getInventory().getLowStockThreshold() -> " + lowStockThreshold);
        return itemRepository.getAllItems().stream()
//            .filter(item -> item.getQuantity() < lowStockThreshold)
            .filter(item -> item.getQuantity() < appConfig.getInventory().getLowStockThreshold())
            .collect(Collectors.toList());
    }

    /**
     * Updates an existing inventory item.
     * @param id The ID of the item to update.
     * @param name New name.
     * @param description New description.
     * @param quantity New quantity.
     * @param category New category.
     * @param borrowable Updated borrowable status.
     * @return true if updated successfully, false otherwise.
     */
    public boolean updateItem(Integer id, String name, String description, int quantity, String category, boolean borrowable) {
        Optional<InventoryItem> existingItem = itemRepository.getItemById(id);
        if (existingItem.isPresent()) {
            InventoryItem item = existingItem.get();
            item.setName(name);
            item.setDescription(description);
            item.setQuantity(quantity);
            item.setCategory(category);
            item.setBorrowable(borrowable);
            return itemRepository.updateItem(item);
        }
        return false;
    }

    @Deprecated
    public Optional<InventoryItem> getItemEntityById(Integer id) {
        return itemRepository.getItemById(id);
    }


    /**
     * Retrieves an inventory item by its ID.
     * @param id The ID of the item to fetch.
     * @return Optional containing the item if found, or empty otherwise.
     */
    public Optional<InventoryItemDto> getItemById(Integer id) {
        return itemRepository.getItemById(id)
                .map(InventoryItemDto::fromEntity);
    }


    public InventoryItemDto createItem(InventoryItemRequest request) {
        InventoryItem item = new InventoryItem(
                request.name(),
                request.description(),
                request.quantity(),
                request.serialNumber(),
                request.unitOfMeasurement(),
                request.category(),
                request.borrowable()
        );
        itemRepository.addItem(item);
        return InventoryItemDto.fromEntity(item);
    }

    public InventoryItemDto upsertItem(Integer id, InventoryItemRequest request) {
        Optional<InventoryItem> existing = itemRepository.getItemById(id);

        InventoryItem item;
        if (existing.isPresent()) {
            item = existing.get();
            item.setName(request.name());
            item.setDescription(request.description());
            item.setQuantity(request.quantity());
            item.setCategory(request.category());
            item.setBorrowable(request.borrowable());

            logger.debug("Existing item found with id " + id + ". Updating item based on the provided request");
            itemRepository.updateItem(item);
        } else {
            item = new InventoryItem(
                    request.name(), request.description(), request.quantity(),
                    request.serialNumber(), request.unitOfMeasurement(),
                    request.category(), request.borrowable()
            );
            logger.debug("No existing item found with id " + id + ". Creating new one");
            itemRepository.addItem(item);
        }
        return InventoryItemDto.fromEntity(item);
    }

    public Optional<InventoryItemDto> patchItem(Integer id, InventoryItemPatchRequest patchRequest) {
        Optional<InventoryItem> optionalItem = itemRepository.getItemById(id);
        if (optionalItem.isEmpty()) {
            throw new ItemNotFoundException(id);
        }

        InventoryItem item = optionalItem.get();

        patchRequest.getName().ifPresent(item::setName);
        patchRequest.getDescription().ifPresent(item::setDescription);
        patchRequest.getQuantity().ifPresent(item::setQuantity);
        patchRequest.getCategory().ifPresent(item::setCategory);
        patchRequest.getBorrowable().ifPresent(item::setBorrowable);

        itemRepository.updateItem(item);

        return Optional.of(InventoryItemDto.fromEntity(item));
    }

    // we can directly throw exception if element is not present or reuse the boolean flag in upper layer
    public boolean deleteItem(Integer id) {
        return itemRepository.deleteItemById(id);
    }
}
