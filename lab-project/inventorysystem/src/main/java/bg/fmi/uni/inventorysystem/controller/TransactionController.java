package bg.fmi.uni.inventorysystem.controller;

import bg.fmi.uni.inventorysystem.model.Transaction;
import bg.fmi.uni.inventorysystem.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    public List<Transaction> getUpcomingDueTransactions() {
        return transactionService.getUpcomingDueTransactions();
    }
}