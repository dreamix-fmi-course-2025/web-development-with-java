package bg.fmi.uni.inventorysystem.exception;


public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(Integer id) {
        super("Item not found with ID: " + id);
    }
}
