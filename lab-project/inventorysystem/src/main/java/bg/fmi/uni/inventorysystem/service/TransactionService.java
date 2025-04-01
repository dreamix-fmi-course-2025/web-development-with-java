package bg.fmi.uni.inventorysystem.service;

import bg.fmi.uni.inventorysystem.config.AppConfig;
import bg.fmi.uni.inventorysystem.config.logger.Logger;
import bg.fmi.uni.inventorysystem.model.Transaction;
import bg.fmi.uni.inventorysystem.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final Logger logger;
    private final AppConfig appConfig;
    private final TransactionRepository transactionRepository;

    public List<Transaction> getUpcomingDueTransactions() {
        LocalDate today = LocalDate.now();
        int reminderWindow = appConfig.getTransaction().getReminderSafetyWindowDays();

        // usually you will prefer for the DB to calculate the result, for lab purposes we will query for all Transactions
        List<Transaction> soonDue = transactionRepository.getAllTransactions().stream()
                .filter(t -> !t.isReturned())
                .filter(t -> {
                    long daysUntilDue = ChronoUnit.DAYS.between(today, t.getDueDate().toLocalDate());
                    return daysUntilDue >= 0 && daysUntilDue <= reminderWindow;
                })
                .collect(Collectors.toList());

        if (soonDue.isEmpty()) {
            logger.info("No upcoming due transactions found.");
        } else {
            logger.info(String.format("[INFO] Found %d transactions due within %d days. Transaction IDs: %s%n",
                    soonDue.size(), reminderWindow,
                    soonDue.stream().map(t -> t.getId().toString()).collect(Collectors.joining(", ")))
            );
        }

        return soonDue;
    }
}
