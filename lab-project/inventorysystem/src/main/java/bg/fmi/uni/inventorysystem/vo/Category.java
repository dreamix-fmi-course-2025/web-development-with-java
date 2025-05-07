package bg.fmi.uni.inventorysystem.vo;

public enum Category {
    AIRPLANE,
    HELICOPTER,
    DRONE,
    CAR,
    BOAT,
    TOOL,
    ACCESSORY,
    POWER_SOURCE;

    public static Category parse(String category) {
        try {
            return Category.valueOf(category.trim().toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid category: " + category);
        }
    }
}
