package bg.fmi.uni.inventorysystem.model;

import lombok.ToString;

import java.time.LocalDateTime;

@ToString
public class Transaction {
    private static int idCounter = 1;

    private Integer id;
    private ClubMember member;
    private InventoryItem item;
    private LocalDateTime borrowedDate;
    private LocalDateTime dueDate;
    private Integer quantityUsed;
    private boolean returned;

    public Transaction(ClubMember member, InventoryItem item, int days, int quantityUsed, boolean returned) {
        this.id = idCounter++;
        this.member = member;
        this.item = item;
        this.borrowedDate = LocalDateTime.now();
        this.dueDate = borrowedDate.plusDays(days);
        this.quantityUsed = quantityUsed;
        this.returned = returned;
    }

    public Integer getId() {
        return id;
    }

    public ClubMember getMember() {
        return member;
    }

    public InventoryItem getItem() {
        return item;
    }

    public LocalDateTime getBorrowedDate() {
        return borrowedDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }
}
