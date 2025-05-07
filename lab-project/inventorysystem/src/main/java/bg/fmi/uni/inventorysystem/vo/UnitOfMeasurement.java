package bg.fmi.uni.inventorysystem.vo;

public enum UnitOfMeasurement {
    PIECE,
    SET,
    GRAM,
    KILOGRAM,
    LITER,
    METER;

    public static UnitOfMeasurement parse(String value) {
        try {
            return UnitOfMeasurement.valueOf(value.trim().toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid unit of measurement: " + value);
        }
    }
}
