package bg.fmi.uni.inventorysystem.model;

import bg.fmi.uni.inventorysystem.vo.Category;
import bg.fmi.uni.inventorysystem.vo.UnitOfMeasurement;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@Entity
/**
 * Unidirectional: InventoryItem does not reference Transaction.
 */
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private int quantity;
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private UnitOfMeasurement unitOfMeasurement;

    @Enumerated(EnumType.STRING)
    private Category category;

    private boolean borrowable;
    private LocalDateTime addedDate;

    public InventoryItem(String name, String description, int quantity, String serialNumber, UnitOfMeasurement unitOfMeasurement, Category category, boolean borrowable) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.serialNumber = serialNumber;
        this.unitOfMeasurement = unitOfMeasurement;
        this.category = category;
        this.borrowable = borrowable;
        this.addedDate = LocalDateTime.now();
    }

}
