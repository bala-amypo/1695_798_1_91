-- Insert initial categories
INSERT INTO categories (category_name, default_urgency, created_at, updated_at) VALUES 
('Electrical', 'HIGH', NOW(), NOW()),
('Plumbing', 'MEDIUM', NOW(), NOW()),
('HVAC', 'MEDIUM', NOW(), NOW()),
('Structural', 'HIGH', NOW(), NOW()),
('General Maintenance', 'LOW', NOW(), NOW())
ON DUPLICATE KEY UPDATE updated_at = NOW();

-- Insert initial users (passwords are 'admin123' encoded with BCrypt)
INSERT INTO users (email, password, role, created_at, updated_at) VALUES 
('admin@demo.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iKq8SJx7O8YI8bS8vFJbV2z1V2Ry', 'ADMIN', NOW(), NOW()),
('technician@demo.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iKq8SJx7O8YI8bS8vFJbV2z1V2Ry', 'TECHNICIAN', NOW(), NOW()),
('user@demo.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iKq8SJx7O8YI8bS8vFJbV2z1V2Ry', 'USER', NOW(), NOW())
ON DUPLICATE KEY UPDATE updated_at = NOW();

-- Insert initial categorization rules
INSERT INTO categorization_rules (category_id, keyword, match_type, priority, created_at, updated_at) VALUES 
((SELECT id FROM categories WHERE category_name = 'Electrical'), 'electrical', 'CONTAINS', 10, NOW(), NOW()),
((SELECT id FROM categories WHERE category_name = 'Electrical'), 'power', 'CONTAINS', 9, NOW(), NOW()),
((SELECT id FROM categories WHERE category_name = 'Electrical'), 'circuit', 'CONTAINS', 8, NOW(), NOW()),
((SELECT id FROM categories WHERE category_name = 'Plumbing'), 'water', 'CONTAINS', 10, NOW(), NOW()),
((SELECT id FROM categories WHERE category_name = 'Plumbing'), 'leak', 'CONTAINS', 9, NOW(), NOW()),
((SELECT id FROM categories WHERE category_name = 'Plumbing'), 'pipe', 'CONTAINS', 8, NOW(), NOW()),
((SELECT id FROM categories WHERE category_name = 'HVAC'), 'ac', 'CONTAINS', 10, NOW(), NOW()),
((SELECT id FROM categories WHERE category_name = 'HVAC'), 'heating', 'CONTAINS', 9, NOW(), NOW()),
((SELECT id FROM categories WHERE category_name = 'HVAC'), 'cooling', 'CONTAINS', 8, NOW(), NOW()),
((SELECT id FROM categories WHERE category_name = 'Structural'), 'crack', 'CONTAINS', 10, NOW(), NOW()),
((SELECT id FROM categories WHERE category_name = 'Structural'), 'structural', 'CONTAINS', 9, NOW(), NOW()),
((SELECT id FROM categories WHERE category_name = 'Structural'), 'foundation', 'CONTAINS', 8, NOW(), NOW())
ON DUPLICATE KEY UPDATE updated_at = NOW();

-- Insert initial urgency policies
INSERT INTO urgency_policies (policy_name, keyword, urgency_override, created_at, updated_at) VALUES 
('Critical Electrical', 'fire', 'CRITICAL', NOW(), NOW()),
('Critical Water', 'flood', 'CRITICAL', NOW(), NOW()),
('High Priority Gas', 'gas', 'HIGH', NOW(), NOW()),
('Emergency Safety', 'emergency', 'CRITICAL', NOW(), NOW()),
('Major Leak', 'major leak', 'HIGH', NOW(), NOW())
ON DUPLICATE KEY UPDATE updated_at = NOW();