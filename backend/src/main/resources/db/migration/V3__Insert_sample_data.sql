-- Insert a test user if not exists
INSERT INTO users (name, surname, email, password, age) 
SELECT 'Test', 'User', 'test@example.com', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.TVuHOnu', 25
WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = 'test@example.com');

-- Get the user ID (assuming it's the one we just inserted or already exists)
SET @userId = (SELECT id FROM users WHERE email = 'test@example.com' LIMIT 1);

-- Insert a test task
INSERT INTO tasks (user_id, name, waste, amount, completed) 
VALUES (@userId, 'Recoger botellas', 'Plástico', 10, false);

-- Insert a test animal status
INSERT INTO animal_status (description) 
VALUES ('Sano y salvo');

SET @statusId = (SELECT id FROM animal_status WHERE description = 'Sano y salvo' LIMIT 1);

-- Insert a test animal
INSERT INTO animals (user_id, animal_status_id, name, type) 
VALUES (@userId, @statusId, 'Tortuga Pepa', 'Tortuga Marina');

-- Insert a test notification
INSERT INTO notifications (user_id, message, date_notification) 
VALUES (@userId, 'Nueva misión disponible: Limpieza de Playa Blanca', CURDATE());
