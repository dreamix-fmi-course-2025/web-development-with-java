package bg.uni.fmi.clubmanagementsystem.service;

import bg.uni.fmi.clubmanagementsystem.model.InventoryItem;
import bg.uni.fmi.clubmanagementsystem.repository.InventoryItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service layer for handling inventory operations.
 */
@Service
public class InventoryService {
    private final InventoryItemRepository itemRepository;

    public InventoryService(InventoryItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

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
        InventoryItem item = new InventoryItem(name, description, quantity, serialNumber, unit, category, borrowable);
        itemRepository.addItem(item);
    }

    /**
     * Retrieves all inventory items that are low in stock.
     * @param threshold The threshold quantity to determine low stock.
     * @return List of inventory items below the threshold.
     */
    public List<InventoryItem> getLowStockItems(int threshold) {
        return itemRepository.getAllItems().stream()
            .filter(item -> item.getQuantity() < threshold)
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
}
