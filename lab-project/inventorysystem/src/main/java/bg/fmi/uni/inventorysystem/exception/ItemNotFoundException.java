package bg.fmi.uni.inventorysystem.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(Integer id) {
        super("Item not found with ID: " + id);
    }
}
