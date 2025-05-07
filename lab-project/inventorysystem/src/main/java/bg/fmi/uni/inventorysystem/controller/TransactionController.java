package bg.fmi.uni.inventorysystem.controller;

import bg.fmi.uni.inventorysystem.dto.TransactionDto;
import bg.fmi.uni.inventorysystem.service.TransactionService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionRequest request) {
        TransactionDto dto = transactionService.createTransaction(request.memberId(), request.itemId(), request.quantityUsed());
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public List<TransactionDto> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/upcoming-due")
    public List<TransactionDto> getUpcomingDueTransactions() {
        return transactionService.getUpcomingDueTransactions();
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<TransactionDto> returnTransaction(@PathVariable Integer id) {
        TransactionDto dto = transactionService.returnTransaction(id);
        return ResponseEntity.ok(dto);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalState(IllegalStateException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    // Request DTO
    public record TransactionRequest(Integer memberId, Integer itemId, int quantityUsed) {}
}