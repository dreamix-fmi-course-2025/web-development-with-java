# Inventory Management System - Spring Boot Integration

## Task 1 - migrate your Inventory Management System to a Spring Boot application
1. To achieve this task you will need to create a Spring Boot application with IntelliJ Ultimate or [spring-initializr](https://start.spring.io/) to create a Spring Boot application.

<big><pre>
If you are having trouble with the starter you can check the following [documentation](https://github.com/dreamix-fmi-course-2025/web-development-with-java/tree/main/week04/spring-initializr-tutorial.md)
</pre></big>

<big><pre>
In case you need additional information about Spring/Spring Boot/Maven, use the following [documentation](https://github.com/dreamix-fmi-course-2025/web-development-with-java/tree/main/week04/basic-guide.md)
</pre></big>


2. Create the proper Bean definition and connect all project dependencies
<details>
<summary>Hints</summary>

```md
- make use of @Autowired and @Component annotation
- Connect the respectful classes (e.g Service is containing Repository, Service can contain one or more Services)
```

</details>

If you don't have the code for the InventoryManagementSystem project you can use the code from [week03](https://github.com/dreamix-fmi-course-2025/web-development-with-java/tree/main/week03/uni-week03).
For the purpose of this lab you can start only with the Racer domain logic (model, repository and service)

## Task 2 - showcase the usage of the beans in together with CommandLineRunner interface
For this test we will use the InventoryService and InventoryController to showcase the autowire and the usage of beans (and as it's funtionalities are simple to manipulate).

In your main class implement the `CommandLineRunner` interface. This will give you the abbility to play with your Spring Boot application

1. Add Inventory Items
2. Display all items.
3. Update Item with ID 1
4. Display all low stock items
Example:
```java
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
    }

```
5. Use ApplicationContext to print all loaded beans

<details>
<summary>Hints</summary>

```java
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

```

</details>


## Task 3 - add lombok dependency and remove System print logs if any
1. Search for lombok inside [mvn-repository](https://mvnrepository.com/)
2. Copy the `dependency`
3. Add it inside `pom.xml`
4. Create verion property instead of the hardcoded one (e.g 1.18.32)

<details>
<summary>Hints</summary>

```md
- [lombok-repo](https://mvnrepository.com/artifact/org.projectlombok/lombok)
- modify `properties` in order to introduce `lombok.version`
```

</details>

## Task 4 - Ensure that you have basic CRUD operations written for all services
Implement all Services connecting them to Repositories using the Spring approach