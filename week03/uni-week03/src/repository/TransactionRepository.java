package repository;

import model.Transaction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
