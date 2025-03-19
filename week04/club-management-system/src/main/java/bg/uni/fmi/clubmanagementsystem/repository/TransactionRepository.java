package bg.uni.fmi.clubmanagementsystem.repository;

import bg.uni.fmi.clubmanagementsystem.model.Transaction;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class TransactionRepository {
    private static Map<Integer, Transaction> transactionTable = new HashMap<>();

    public void addTransaction(Transaction transaction) {
        transactionTable.put(transaction.getId(), transaction);
    }

    public List<Transaction> getAllTransactions() {
        return new ArrayList<>(transactionTable.values());
    }

    public List<Transaction> getOverdueTransactions() {
        return transactionTable.values().stream()
            .filter(tr -> !tr.isReturned() && tr.getDueDate().isBefore(LocalDateTime.now()))
            .collect(Collectors.toList());
    }
}
