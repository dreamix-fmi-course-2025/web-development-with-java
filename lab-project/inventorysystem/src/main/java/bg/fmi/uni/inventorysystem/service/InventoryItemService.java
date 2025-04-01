package bg.fmi.uni.inventorysystem.service;


import bg.fmi.uni.inventorysystem.config.AppConfig;
import bg.fmi.uni.inventorysystem.config.logger.Logger;
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

    /**
     * Retrieves all inventory items.
     * @return List of all items in inventory.
     */
    public List<InventoryItem> getAllItems() {
        return itemRepository.getAllItems();
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

    /**
     * Retrieves an inventory item by its ID.
     * @param id The ID of the item to fetch.
     * @return Optional containing the item if found, or empty otherwise.
     */
    public Optional<InventoryItem> getItemById(Integer id) {
        logger.debug("getItemById called with ID: " + id);
        return itemRepository.getItemById(id);
    }
}
