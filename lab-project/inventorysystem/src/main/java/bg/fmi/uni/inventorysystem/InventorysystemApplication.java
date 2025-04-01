package bg.fmi.uni.inventorysystem;

import bg.fmi.uni.inventorysystem.config.logger.Logger;
import bg.fmi.uni.inventorysystem.controller.InventoryItemController;
import bg.fmi.uni.inventorysystem.model.ClubMember;
import bg.fmi.uni.inventorysystem.model.InventoryItem;
import bg.fmi.uni.inventorysystem.model.Transaction;
import bg.fmi.uni.inventorysystem.repository.ClubMemberRepository;
import bg.fmi.uni.inventorysystem.repository.TransactionRepository;
import bg.fmi.uni.inventorysystem.service.InventoryItemService;
import bg.fmi.uni.inventorysystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class InventorysystemApplication implements CommandLineRunner {

	@Autowired
	private Logger logger;
	
	@Autowired
	private InventoryItemService inventoryItemService;

	@Autowired
	private InventoryItemController inventoryController;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private ClubMemberRepository clubMemberRepository;

	public static void main(String[] args) {
		SpringApplication.run(InventorysystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("🚀 Application started successfully!");

		// Add Inventory Items
		inventoryItemService.addItem("RC Car", "High-speed remote control car", 5, "S123", "pcs", "Vehicles", true);
		inventoryItemService.addItem("Battery Pack", "Rechargeable battery", 10, "S1231", "pcs", "Accessories", true);

		try {
			inventoryItemService.addItem("Battery Pack Duplicate Serial Number", "Rechargeable battery", 10, "S1231", "pcs", "Accessories", true);
		} catch (IllegalArgumentException ex) {
			logger.info(ex);
		}

		logger.info("---------------------------------------");
		logger.info("✅ Inventory items added successfully!");
		logger.info("---------------------------------------");

		// Display All Items
		logger.info("📌 Displaying all inventory items:");
		inventoryController.displayAllItems();
		logger.info("---------------------------------------");

		logger.info("🔄 Updating 'RC Car' quantity to 8...");
		inventoryController.updateItem(1, "RC Car", "High-speed remote control car", 8, "Vehicles", true);

		logger.info("---------------------------------------");
		logger.info("📌 Displaying updated inventory items:");
		inventoryController.displayAllItems();

		logger.info("---------------------------------------");
		logger.info("📌 Displaying all low stock items:");
		List<InventoryItem> lowCost = inventoryController.getLowStockItems();
		lowCost.forEach(el -> logger.info(el));
		logger.info("---------------------------------------");


		// Add Club Member
		ClubMember member = new ClubMember("Pesho", "Ivanov", "pesho@example.com");
		clubMemberRepository.addMember(member);

		// Fetch item for transaction
		Optional<InventoryItem> item1 = inventoryItemService.getItemById(1);
		Optional<InventoryItem> item2 = inventoryItemService.getItemById(2);

		// Create upcoming due transaction (e.g., due in 2 days)
		if (item1.isPresent()) {
			Transaction tx1 = new Transaction(member, item1.get(), 2, 1, false);
			transactionRepository.addTransaction(tx1);
		}

		// Create transaction due in 6 days (should not show up if window < 6)
		if (item2.isPresent()) {
			Transaction tx2 = new Transaction(member, item2.get(), 6, 1, false);
			transactionRepository.addTransaction(tx2);
		}

		// Create already overdue transaction
		if (item2.isPresent()) {
			Transaction tx3 = new Transaction(member, item2.get(), -1, 1, true);
			transactionRepository.addTransaction(tx3);
		}

		logger.info("---------------------------------------");
		logger.info("📢 Checking for upcoming due transactions:");
		List<Transaction> soonToOverdue = transactionService.getUpcomingDueTransactions();
		soonToOverdue.forEach(el -> logger.info(el));
		logger.info("---------------------------------------");
	}
}
