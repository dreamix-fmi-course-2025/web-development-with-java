import controller.InventoryController;
import model.InventoryItem;
import repository.InventoryItemRepository;
import service.InventoryService;

import java.util.List;

/**
 * Entry point of the RC Club Inventory Management System.
 */
public class Main {
    public static void main(String[] args) {
        // Initialize repositories
        InventoryItemRepository itemRepository = new InventoryItemRepository();

        // Initialize services
        InventoryService inventoryService = new InventoryService(itemRepository);

        // Initialize controllers
        InventoryController inventoryController = new InventoryController(inventoryService);

        System.out.println("🚀 Application started successfully!");

        // Add Inventory Items
        inventoryService.addItem("RC Car", "High-speed remote control car", 5, "S123", "pcs", "Vehicles", true);
        inventoryService.addItem("Battery Pack", "Rechargeable battery", 10, "S1231", "pcs", "Accessories", true);

        try {
            inventoryService.addItem("Battery Pack Duplicate Serial Number", "Rechargeable battery", 10, "S1231", "pcs", "Accessories", true);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex);
        }

        System.out.println("---------------------------------------");
        System.out.println("✅ Inventory items added successfully!");
        System.out.println("---------------------------------------");

        // Display All Items
        System.out.println("📌 Displaying all inventory items:");
        inventoryController.displayAllItems();
        System.out.println("---------------------------------------");

        System.out.println("🔄 Updating 'RC Car' quantity to 8...");
        inventoryController.updateItem(1, "RC Car", "High-speed remote control car", 8, "Vehicles", true);

        System.out.println("---------------------------------------");
        System.out.println("📌 Displaying updated inventory items:");
        inventoryController.displayAllItems();

        System.out.println("---------------------------------------");
        System.out.println("📌 Displaying all low stock items:");
        int threshold = 10;
        List<InventoryItem> lowCost = inventoryController.getLowStockItems(threshold);
        lowCost.stream().forEach(System.out::println);
        System.out.println("---------------------------------------");
    }
}
