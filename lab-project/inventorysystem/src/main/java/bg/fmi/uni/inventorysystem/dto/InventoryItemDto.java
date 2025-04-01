package bg.fmi.uni.inventorysystem.dto;

import bg.fmi.uni.inventorysystem.model.InventoryItem;

import java.time.LocalDateTime;

/**
 * DTO for transferring inventory item data over REST.
 */
public record InventoryItemDto(
        Integer id,
        String name,
        String description,
        int quantity,
        String serialNumber,
        String unitOfMeasurement,
        String category,
        boolean borrowable,
        LocalDateTime addedDate
) {
    // Optional: static factory method for mapping from entity
    public static InventoryItemDto fromEntity(InventoryItem item) {
        return new InventoryItemDto(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getQuantity(),
                item.getSerialNumber(),
                item.getUnitOfMeasurement(),
                item.getCategory(),
                item.isBorrowable(),
                item.getAddedDate()
        );
    }
}
