package bg.uni.fmi.clubmanagementsystem;

import bg.uni.fmi.clubmanagementsystem.controller.InventoryController;
import bg.uni.fmi.clubmanagementsystem.model.InventoryItem;
import bg.uni.fmi.clubmanagementsystem.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class ClubManagementSystemApplication implements CommandLineRunner {
    @Autowired
    private InventoryController inventoryController;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(ClubManagementSystemApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

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

        System.out.println("List of Beans provided by Spring Boot:");

        String[] beanNames = context.getBeanDefinitionNames();
        List<String> beanClasses = Stream.of(beanNames)
            .map(el -> context.getBean(el).getClass().toString())
            .filter(el -> el.contains("bg.uni.fmi"))
            .toList();
        beanClasses.forEach(System.out::println);

        // uncomment to see all loaded beans
//        Arrays.sort(beanNames);
//        for (String beanName : beanNames) {
//            System.out.println(beanName + " - " + context.getBean(beanName).getClass());
//        }
    }
}
