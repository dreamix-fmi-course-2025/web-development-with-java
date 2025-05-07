-- Flyway migration: Initial schema only

-- Create table for ClubMember
CREATE TABLE IF NOT EXISTS club_member (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone_number VARCHAR(32)
);

-- Create table for InventoryItem
CREATE TABLE IF NOT EXISTS inventory_item (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    quantity INTEGER NOT NULL,
    serial_number VARCHAR(255),
    unit_of_measurement VARCHAR(50) NOT NULL,
    category VARCHAR(50) NOT NULL,
    borrowable BOOLEAN NOT NULL,
    added_date TIMESTAMP NOT NULL
);

-- Create table for Transaction
CREATE TABLE IF NOT EXISTS transaction (
    id SERIAL PRIMARY KEY,
    member_id INTEGER NOT NULL REFERENCES club_member(id),
    item_id INTEGER NOT NULL REFERENCES inventory_item(id),
    transaction_date TIMESTAMP NOT NULL,
    due_date TIMESTAMP NOT NULL,
    quantity_used INTEGER NOT NULL,
    returned_date TIMESTAMP
);

-- Add indexes for performance
CREATE INDEX IF NOT EXISTS idx_transaction_member_id ON transaction(member_id);
CREATE INDEX IF NOT EXISTS idx_transaction_item_id ON transaction(item_id);
