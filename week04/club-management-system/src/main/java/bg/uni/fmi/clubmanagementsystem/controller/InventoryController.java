package bg.uni.fmi.clubmanagementsystem.controller;

import bg.uni.fmi.clubmanagementsystem.model.InventoryItem;
import bg.uni.fmi.clubmanagementsystem.service.InventoryService;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Controller for inventory operations.
 */
@Controller
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     * Displays all available inventory items.
     */
    public void displayAllItems() {
        List<InventoryItem> items = inventoryService.getAllItems();
        if (items.isEmpty()) {
            System.out.println("No inventory items available.");
        } else {
            items.forEach(item -> System.out.println("Item: " + item.getName() + ", Quantity: " + item.getQuantity()));
        }
    }

    public List<InventoryItem> getLowStockItems(int threshold) {
        return inventoryService.getLowStockItems(threshold);
    }

    public void updateItem(Integer id, String name, String description, int quantity, String category, boolean borrowable) {
        boolean success = inventoryService.updateItem(id, name, description, quantity, category, borrowable);
        if (success) {
            System.out.println("Item updated successfully.");
        } else {
            System.out.println("Update failed. Item not found.");
        }
    }
}
