-- Enable foreign key checks
SET FOREIGN_KEY_CHECKS = 0;

-- Drop tables if they exist (in reverse order of dependencies)
DROP TABLE IF EXISTS categorization_logs;
DROP TABLE IF EXISTS category_urgency_policies;
DROP TABLE IF EXISTS categorization_rules;
DROP TABLE IF EXISTS urgency_policies;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS tickets;
DROP TABLE IF EXISTS users;

-- Enable foreign key checks
SET FOREIGN_KEY_CHECKS = 1;

-- Create users table
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) DEFAULT 'USER',
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    INDEX idx_user_email (email)
);

-- Create categories table
CREATE TABLE categories (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    category_name VARCHAR(100) NOT NULL UNIQUE,
    default_urgency VARCHAR(20) DEFAULT 'MEDIUM',
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

-- Create tickets table
CREATE TABLE tickets (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    description TEXT NOT NULL,
    category_id BIGINT,
    urgency_level VARCHAR(20) DEFAULT 'LOW',
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    INDEX idx_ticket_created_at (created_at),
    INDEX idx_ticket_urgency (urgency_level),
    CONSTRAINT fk_ticket_category FOREIGN KEY (category_id) REFERENCES categories(id)
);

-- Create urgency_policies table
CREATE TABLE urgency_policies (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    policy_name VARCHAR(200) NOT NULL,
    keyword VARCHAR(100) NOT NULL,
    urgency_override VARCHAR(20) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

-- Create categorization_rules table
CREATE TABLE categorization_rules (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    category_id BIGINT NOT NULL,
    keyword VARCHAR(100) NOT NULL,
    match_type VARCHAR(20) DEFAULT 'CONTAINS',
    priority INT DEFAULT 1,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT fk_rule_category FOREIGN KEY (category_id) REFERENCES categories(id)
);

-- Create category_urgency_policies junction table
CREATE TABLE category_urgency_policies (
    category_id BIGINT NOT NULL,
    policy_id BIGINT NOT NULL,
    PRIMARY KEY (category_id, policy_id),
    CONSTRAINT fk_category_policy_category FOREIGN KEY (category_id) REFERENCES categories(id),
    CONSTRAINT fk_category_policy_policy FOREIGN KEY (policy_id) REFERENCES urgency_policies(id)
);

-- Create categorization_logs table
CREATE TABLE categorization_logs (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    ticket_id BIGINT NOT NULL,
    rule_id BIGINT,
    category_name VARCHAR(100),
    urgency_level VARCHAR(20),
    categorized_at TIMESTAMP,
    CONSTRAINT fk_log_ticket FOREIGN KEY (ticket_id) REFERENCES tickets(id),
    CONSTRAINT fk_log_rule FOREIGN KEY (rule_id) REFERENCES categorization_rules(id)
);