package bg.fmi.uni.inventorysystem.repository;

import bg.fmi.uni.inventorysystem.model.InventoryItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Repository for managing inventory items.
 */
@Slf4j
@Repository
public class InventoryItemRepository {
    private static Map<Integer, InventoryItem> itemTable = new HashMap<>();

    /**
     * Adds a new inventory item to the repository.
     * @param item The item to be added.
     */
    public void addItem(InventoryItem item) {
        log.info("alabala");
        if (itemTable.values().stream().anyMatch(el -> el.getSerialNumber().equals(item.getSerialNumber()))) {
            throw new IllegalArgumentException(String.format("Serial number %s already in DB", item.getSerialNumber()));
        }
        itemTable.put(item.getId(), item);
    }

    /**
     * Deletes an inventory item by its ID.
     * @param id The ID of the item to be deleted.
     * @return true if item was successfully deleted, false otherwise.
     */
    public boolean deleteItemById(Integer id) {
        return itemTable.remove(id) != null;
    }

    /**
     * Retrieves an inventory item by its ID.
     * @param id The ID of the item.
     * @return An Optional containing the item if found.
     */
    public Optional<InventoryItem> getItemById(Integer id) {
        return Optional.ofNullable(itemTable.get(id));
    }

    /**
     * Returns all inventory items in the repository.
     * @return List of all inventory items.
     */
    public List<InventoryItem> getAllItems() {
        return new ArrayList<>(itemTable.values());
    }

    /**
     * Updates an existing inventory item.
     * @param updatedItem The updated item details.
     * @return true if update was successful, false otherwise.
     */
    public boolean updateItem(InventoryItem updatedItem) {
        if (itemTable.containsKey(updatedItem.getId())) {
            itemTable.put(updatedItem.getId(), updatedItem);
            return true;
        }
        return false;
    }
}
