package bg.fmi.uni.inventorysystem.repository;

import bg.fmi.uni.inventorysystem.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Integer> {
}

