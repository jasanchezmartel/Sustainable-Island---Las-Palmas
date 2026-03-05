-- Initial migration to verify Flyway setup
CREATE TABLE IF NOT EXISTS flyway_test (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO flyway_test (name) VALUES ('Flyway is working!');
