package bg.fmi.uni.inventorysystem.controller;


import bg.fmi.uni.inventorysystem.config.logger.Logger;
import bg.fmi.uni.inventorysystem.dto.InventoryItemDto;
import bg.fmi.uni.inventorysystem.model.InventoryItem;
import bg.fmi.uni.inventorysystem.service.InventoryItemService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for inventory operations.
 */
@RestController
@RequestMapping("inventory")
public class InventoryItemController {
    private final InventoryItemService inventoryItemService;
    private final Logger logger;

    public InventoryItemController(InventoryItemService inventoryItemService, Logger logger) {
        this.inventoryItemService = inventoryItemService;
        this.logger = logger;
    }

    /**
     * Displays all available inventory items.
     */
    @GetMapping("items")
    public List<InventoryItemDto> getAllItems() {
        List<InventoryItem> items = inventoryItemService.getAllItems();
        if (items.isEmpty()) {
            logger.info("No inventory items available.");
        } else {
            items.forEach(item -> logger.info("Item: " + item.getName() + ", Quantity: " + item.getQuantity()));
        }
        return items.stream().map(InventoryItemDto::fromEntity).toList();
    }

    @Deprecated
    public void displayAllItems() {
        List<InventoryItem> items = inventoryItemService.getAllItems();
        if (items.isEmpty()) {
            logger.info("No inventory items available.");
        } else {
            items.forEach(item -> logger.info("Item: " + item.getName() + ", Quantity: " + item.getQuantity()));
        }
    }

    public List<InventoryItem> getLowStockItems() {
        return inventoryItemService.getLowStockItems();
    }

    public void updateItem(Integer id, String name, String description, int quantity, String category, boolean borrowable) {
        boolean success = inventoryItemService.updateItem(id, name, description, quantity, category, borrowable);
        if (success) {
            logger.debug("Item updated successfully.");
        } else {
            logger.error("Update failed. Item not found.");
        }
    }
}
