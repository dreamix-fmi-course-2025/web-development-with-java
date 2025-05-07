-- Flyway migration: Seed initial data

-- Insert initial ClubMembers
INSERT INTO club_member (first_name, last_name, email, phone_number) VALUES
  ('John', 'Doe', 'john.doe@example.com', '+359888111222'),
  ('Jane', 'Smith', 'jane.smith@example.com', '+359888333444');

-- Insert initial InventoryItems
INSERT INTO inventory_item (name, description, quantity, serial_number, unit_of_measurement, category, borrowable, added_date) VALUES
  ('RC Airplane', 'Remote controlled airplane', 10, 'SN12345', 'PIECE', 'AIRPLANE', true, NOW()),
  ('Drone Set', 'Quadcopter drone set', 5, 'SN67890', 'SET', 'DRONE', true, NOW()),
  ('Propeller', 'Spare propeller for airplane', 100, 'SN54321', 'PIECE', 'ACCESSORY', false, NOW());

-- Insert initial Transactions
INSERT INTO transaction (member_id, item_id, transaction_date, due_date, quantity_used, returned_date) VALUES
  (1, 1, NOW(), NOW() + INTERVAL '7 days', 1, NULL),
  (2, 2, NOW(), NOW() + INTERVAL '7 days', 1, NULL),
  -- Consumable usage: member 1 uses 3 Propellers (item_id 3, which is not borrowable)
  (1, 3, NOW(), NOW(), 3, NULL);
