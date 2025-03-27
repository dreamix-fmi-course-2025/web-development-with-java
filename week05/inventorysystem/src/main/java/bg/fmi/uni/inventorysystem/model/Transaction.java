package bg.fmi.uni.inventorysystem.model;

import java.time.LocalDateTime;

public class Transaction {
    private static int idCounter = 1;

    private Integer id;
    private ClubMember member;
    private InventoryItem item;
    private LocalDateTime borrowedDate;
    private LocalDateTime dueDate;
    private boolean returned;

    public Transaction(ClubMember member, InventoryItem item, int days) {
        this.id = idCounter++;
        this.member = member;
        this.item = item;
        this.borrowedDate = LocalDateTime.now();
        this.dueDate = borrowedDate.plusDays(days);
        this.returned = false;
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
