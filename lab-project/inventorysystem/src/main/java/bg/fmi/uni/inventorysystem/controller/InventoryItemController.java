package bg.fmi.uni.inventorysystem.controller;


import bg.fmi.uni.inventorysystem.config.logger.Logger;
import bg.fmi.uni.inventorysystem.dto.InventoryItemDto;
import bg.fmi.uni.inventorysystem.dto.InventoryItemPatchRequest;
import bg.fmi.uni.inventorysystem.dto.InventoryItemRequest;
import bg.fmi.uni.inventorysystem.exception.ItemNotFoundException;
import bg.fmi.uni.inventorysystem.service.InventoryItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import java.util.List;



/**
 * Controller for inventory operations.
 */
@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class InventoryItemController {
    private final InventoryItemService inventoryItemService;
    private final Logger logger;

    /**
     * Displays all available inventory items.
     */
    @GetMapping
    public List<InventoryItemDto> getAllItems() {
        List<InventoryItemDto> items = inventoryItemService.getAllItems();
        if (items.isEmpty()) {
            logger.info("No inventory items available.");
        } else {
            items.forEach(item -> logger.info("Item: " + item.name() + ", Quantity: " + item.quantity()));
        }
        return items;
    }

    @GetMapping("/{id}")
    public InventoryItemDto getItemById(@PathVariable Integer id) {
        logger.info("Get Item by id: " + id);
        return inventoryItemService.getItemById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }

    // Week 8 CRUD implementation
    @PostMapping // ADDED
    public ResponseEntity<InventoryItemDto> createItem(@Valid @RequestBody InventoryItemRequest request) {
        logger.info("Create Item API POST");
        InventoryItemDto created = inventoryItemService.createItem(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryItemDto> upsertItem(@PathVariable Integer id,
                                                       @Valid @RequestBody InventoryItemRequest request) {
        logger.info("Update Item with id: " + id);
        InventoryItemDto result = inventoryItemService.upsertItem(id, request);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<InventoryItemDto> patchItem(@PathVariable Integer id,
                                                      @RequestBody InventoryItemPatchRequest patchRequest) {
        return inventoryItemService.patchItem(id, patchRequest)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Integer id) {
        if (inventoryItemService.deleteItem(id)) {
            return ResponseEntity.noContent().build();
        } else {
            throw new ItemNotFoundException(id);
        }
    }
}
