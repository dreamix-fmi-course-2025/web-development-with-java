package bg.fmi.uni.inventorysystem.model;

import java.time.LocalDateTime;

public class InventoryItem {
    private static int idCounter = 1;

    private Integer id;
    private String name;
    private String description;
    private int quantity;

    private String serialNumber;
    private String unitOfMeasurement;
    private String category;
    private boolean borrowable;
    private LocalDateTime addedDate;

    public InventoryItem(String name, String description, int quantity, String serialNumber, String unit, String category, boolean borrowable) {
        this.id = idCounter++;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.serialNumber = serialNumber;
        this.unitOfMeasurement = unit;
        this.category = category;
        this.borrowable = borrowable;
        this.addedDate = LocalDateTime.now();
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getQuantity() { return quantity; }
    public String getSerialNumber() { return serialNumber; }
    public String getUnitOfMeasurement() { return unitOfMeasurement; }
    public String getCategory() { return category; }
    public boolean isBorrowable() { return borrowable; }
    public LocalDateTime getAddedDate() { return addedDate; }

    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setCategory(String category) { this.category = category; }
    public void setBorrowable(boolean borrowable) { this.borrowable = borrowable; }

    @Override
    public String toString() {
        return "InventoryItem{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", quantity=" + quantity +
            ", unitOfMeasurement='" + unitOfMeasurement + '\'' +
            ", category='" + category + '\'' +
            ", borrowable=" + borrowable +
            ", addedDate=" + addedDate +
            '}';
    }
}
