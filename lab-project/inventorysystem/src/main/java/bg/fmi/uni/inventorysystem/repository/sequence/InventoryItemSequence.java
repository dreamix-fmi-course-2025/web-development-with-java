package bg.fmi.uni.inventorysystem.repository.sequence;

public class InventoryItemSequence {
    private static Integer sequence = 1000;

    public static Integer getNextValue() {
        return sequence++;
    }
}