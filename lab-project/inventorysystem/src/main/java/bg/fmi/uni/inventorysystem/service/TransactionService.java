package bg.fmi.uni.inventorysystem.service;

import bg.fmi.uni.inventorysystem.config.AppConfig;
import bg.fmi.uni.inventorysystem.config.logger.Logger;
import bg.fmi.uni.inventorysystem.dto.TransactionDto;
import bg.fmi.uni.inventorysystem.model.InventoryItem;
import bg.fmi.uni.inventorysystem.model.ClubMember;
import bg.fmi.uni.inventorysystem.repository.InventoryItemRepository;
import bg.fmi.uni.inventorysystem.repository.ClubMemberRepository;
import org.springframework.transaction.annotation.Transactional;
import bg.fmi.uni.inventorysystem.model.Transaction;
import bg.fmi.uni.inventorysystem.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final Logger logger;
    private final AppConfig appConfig;
    private final TransactionRepository transactionRepository;
    private final InventoryItemRepository inventoryItemRepository;
    private final ClubMemberRepository clubMemberRepository;

    public List<TransactionDto> getAllTransactions() {
        return transactionRepository.findAll().stream()
                .map(TransactionDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public TransactionDto createTransaction(Integer memberId, Integer itemId, int quantityUsed) {
        ClubMember member = clubMemberRepository.findById(memberId)
            .orElseThrow(() -> new IllegalStateException("Member not found"));
        InventoryItem item = inventoryItemRepository.findById(itemId)
            .orElseThrow(() -> new IllegalStateException("Item not found"));
        if (item.getQuantity() < quantityUsed) {
            throw new IllegalStateException("Not enough quantity for item: " + item.getName());
        }
        item.setQuantity(item.getQuantity() - quantityUsed);
        inventoryItemRepository.save(item);
        // Assume 7 days for borrowable, 0 for consumable
        int days = item.isBorrowable() ? 7 : 0;
        Transaction transaction = new Transaction(member, item, days, quantityUsed);
        transactionRepository.save(transaction);
        return TransactionDto.fromEntity(transaction);
    }

    @Transactional
    public TransactionDto returnTransaction(Integer transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
            .orElseThrow(() -> new IllegalStateException("Transaction not found"));
        if (transaction.getReturnedDate() != null) {
            throw new IllegalStateException("Transaction already marked as returned");
        }
        transaction.setReturnedDate(java.time.LocalDateTime.now());
        transactionRepository.save(transaction);
        return TransactionDto.fromEntity(transaction);
    }

    public Optional<TransactionDto> getTransactionById(Integer id) {
        return transactionRepository.findById(id).map(TransactionDto::fromEntity);
    }

    public TransactionDto createTransaction(Transaction transaction) {
        Transaction saved = transactionRepository.save(transaction);
        return TransactionDto.fromEntity(saved);
    }

    public Optional<TransactionDto> updateTransaction(Integer id, Transaction transaction) {
        Optional<Transaction> opt = transactionRepository.findById(id);
        if (opt.isPresent()) {
            Transaction existing = opt.get();
            // update fields as needed
            existing.setMember(transaction.getMember());
            existing.setItem(transaction.getItem());
            existing.setQuantityUsed(transaction.getQuantityUsed());
            existing.setTransactionDate(transaction.getTransactionDate());
            existing.setDueDate(transaction.getDueDate());
            if (transaction.getReturnedDate() != null) {
                existing.setReturnedDate(transaction.getReturnedDate());
            }
            transactionRepository.save(existing);
            return Optional.of(TransactionDto.fromEntity(existing));
        }
        return Optional.empty();
    }

    public boolean deleteTransaction(Integer id) {
        if (transactionRepository.existsById(id)) {
            transactionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<TransactionDto> getUpcomingDueTransactions() {
        LocalDate today = LocalDate.now();
        int reminderWindow = appConfig.getTransaction().getReminderSafetyWindowDays();

        Predicate<Transaction> notReturned     = t -> isNull(t.getReturnedDate());
        Predicate<Transaction> isBorrowable    = t -> t.getItem().isBorrowable();
        Predicate<Transaction> dueWithinWindow = t -> {
            long daysUntilDue = ChronoUnit.DAYS.between(today, t.getDueDate().toLocalDate());
            return daysUntilDue >= 0 && daysUntilDue <= reminderWindow;
        };

        // usually you will prefer for the DB to calculate the result, for lab purposes we will query for all Transactions
        List<TransactionDto> soonDue = transactionRepository.findAll().stream()
                .filter(notReturned.and(isBorrowable).and(dueWithinWindow))
                .map(TransactionDto::fromEntity)
                .toList();
        if (soonDue.isEmpty()) {
            logger.info("No upcoming due transactions found.");
        } else {
            logger.info(String.format("[INFO] Found %d transactions due within %d days. Transaction IDs: %s%n",
                    soonDue.size(), reminderWindow,
                    soonDue.stream().map(TransactionDto::id).map(Object::toString).collect(Collectors.joining(", ")))
            );
        }
        return soonDue;
    }
}
