package bg.fmi.uni.inventorysystem.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record InventoryItemRequest(
        @NotBlank(message = "Name is required")
        String name,

        @Size(max = 128, message = "Description must be under 128 characters")
        String description,

        @Min(value = 0, message = "Quantity must be 0 or greater")
        int quantity,

        @NotBlank(message = "Serial number is required")
        String serialNumber,

        @NotBlank(message = "Unit of measurement is required")
        String unitOfMeasurement,

        @NotBlank(message = "Category is required")
        String category,

        boolean borrowable
) {}
