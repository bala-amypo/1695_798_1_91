-- Insert initial categories
INSERT INTO categories (category_name, default_urgency, created_at) VALUES 
('Electrical', 'HIGH', CURRENT_TIMESTAMP),
('Plumbing', 'MEDIUM', CURRENT_TIMESTAMP),
('HVAC', 'MEDIUM', CURRENT_TIMESTAMP),
('Structural', 'HIGH', CURRENT_TIMESTAMP),
('General Maintenance', 'LOW', CURRENT_TIMESTAMP);

-- Insert initial users
INSERT INTO users (email, password, role, created_at) VALUES 
('admin@demo.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iKq8SJx7O8YI8bS8vFJbV2z1V2Ry', 'ADMIN', CURRENT_TIMESTAMP), -- password: admin123
('technician@demo.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iKq8SJx7O8YI8bS8vFJbV2z1V2Ry', 'TECHNICIAN', CURRENT_TIMESTAMP), -- password: admin123
('user@demo.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iKq8SJx7O8YI8bS8vFJbV2z1V2Ry', 'USER', CURRENT_TIMESTAMP); -- password: admin123

-- Insert initial categorization rules
INSERT INTO categorization_rules (category_id, keyword, match_type, priority, created_at) VALUES 
(1, 'electrical', 'CONTAINS', 10, CURRENT_TIMESTAMP),
(1, 'power', 'CONTAINS', 9, CURRENT_TIMESTAMP),
(1, 'circuit', 'CONTAINS', 8, CURRENT_TIMESTAMP),
(2, 'water', 'CONTAINS', 10, CURRENT_TIMESTAMP),
(2, 'leak', 'CONTAINS', 9, CURRENT_TIMESTAMP),
(2, 'pipe', 'CONTAINS', 8, CURRENT_TIMESTAMP),
(3, 'ac', 'CONTAINS', 10, CURRENT_TIMESTAMP),
(3, 'heating', 'CONTAINS', 9, CURRENT_TIMESTAMP),
(3, 'cooling', 'CONTAINS', 8, CURRENT_TIMESTAMP),
(4, 'crack', 'CONTAINS', 10, CURRENT_TIMESTAMP),
(4, 'structural', 'CONTAINS', 9, CURRENT_TIMESTAMP),
(4, 'foundation', 'CONTAINS', 8, CURRENT_TIMESTAMP);

-- Insert initial urgency policies
INSERT INTO urgency_policies (policy_name, keyword, urgency_override, created_at) VALUES 
('Critical Electrical', 'fire', 'CRITICAL', CURRENT_TIMESTAMP),
('Critical Water', 'flood', 'CRITICAL', CURRENT_TIMESTAMP),
('High Priority Gas', 'gas', 'HIGH', CURRENT_TIMESTAMP),
('Emergency Safety', 'emergency', 'CRITICAL', CURRENT_TIMESTAMP),
('Major Leak', 'major leak', 'HIGH', CURRENT_TIMESTAMP);