package bg.fmi.uni.inventorysystem.dto;

import lombok.Data;

import java.util.Optional;

@Data
public class InventoryItemPatchRequest {
    private Optional<String> name = Optional.empty();
    private Optional<String> description = Optional.empty();
    private Optional<Integer> quantity = Optional.empty();
    private Optional<String> unitOfMeasurement = Optional.empty();
    private Optional<String> category = Optional.empty();
    private Optional<Boolean> borrowable = Optional.empty();
}
