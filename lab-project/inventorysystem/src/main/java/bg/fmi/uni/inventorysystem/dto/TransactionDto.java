package bg.fmi.uni.inventorysystem.dto;

import bg.fmi.uni.inventorysystem.model.Transaction;
import java.time.LocalDateTime;

public record TransactionDto(
    Integer id,
    Integer itemId,
    Integer memberId,
    int quantityUsed,
    LocalDateTime transactionDate,
    LocalDateTime dueDate,
    LocalDateTime returnedDate,
    String itemType // "BORROWABLE" or "CONSUMABLE"
) {
    public static TransactionDto fromEntity(Transaction transaction) {
        String itemType = transaction.getItem().isBorrowable() ? "BORROWABLE" : "CONSUMABLE";
        return new TransactionDto(
            transaction.getId(),
            transaction.getItem().getId(),
            transaction.getMember().getId(),
            transaction.getQuantityUsed(),
            transaction.getTransactionDate(),
            transaction.getDueDate(),
            transaction.getReturnedDate(),
            itemType
        );
    }
}
