package bg.fmi.uni.inventorysystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
/**
 * Transaction is the owning side of unidirectional relationships to ClubMember and InventoryItem.
 */
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private ClubMember member;

    @ManyToOne
    private InventoryItem item;

    private LocalDateTime transactionDate;
    private LocalDateTime dueDate;
    private Integer quantityUsed;
    private LocalDateTime returnedDate; // Nullable, only set for borrowables when returned

    public Transaction(ClubMember member, InventoryItem item, int days, int quantityUsed) {
        this.member = member;
        this.item = item;
        this.transactionDate = LocalDateTime.now();
        if (item.isBorrowable()) {
            this.dueDate = transactionDate.plusDays(days);
        } else {
            this.dueDate = null;
        }
        this.quantityUsed = quantityUsed;
        this.returnedDate = null;
    }

    public LocalDateTime getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(LocalDateTime returnedDate) {
        this.returnedDate = returnedDate;
    }

}
