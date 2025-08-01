-- Initial schema for Doggo-Marketplace
CREATE TABLE IF NOT EXISTS users (
  id          BIGINT AUTO_INCREMENT PRIMARY KEY,
  email       VARCHAR(128) UNIQUE NOT NULL,
  password    CHAR(60)            NOT NULL, -- bcrypt
  name        VARCHAR(100)        NOT NULL,
  role        ENUM('USER','ADMIN') NOT NULL DEFAULT 'USER',
  created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS pets (
  id              BIGINT AUTO_INCREMENT PRIMARY KEY,
  name            VARCHAR(64)  NOT NULL,
  breed           VARCHAR(64)  NOT NULL,
  father_breed    VARCHAR(64),
  mother_breed    VARCHAR(64),
  age_months      INT          NOT NULL,
  gender          ENUM('MALE','FEMALE') NOT NULL,
  vaccinated      BOOLEAN      NOT NULL,
  price_cents     INT          NOT NULL,
  seller_id       BIGINT,
  sold_out        BOOLEAN      DEFAULT FALSE,
  img_url         VARCHAR(256),
  medical_history TEXT,
  description     TEXT,
  location        VARCHAR(255),
  created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (seller_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS orders (
  id                    BIGINT AUTO_INCREMENT PRIMARY KEY,
  buyer_id              BIGINT NOT NULL,
  pet_id                BIGINT NOT NULL,
  total_amount_cents    INT NOT NULL,
  processing_fee_cents  INT NOT NULL DEFAULT 5000,
  status                ENUM('PENDING','PAID','COMPLETED','CANCELLED') NOT NULL DEFAULT 'PENDING',
  payment_method        VARCHAR(50),
  transaction_id        VARCHAR(255),
  created_at            TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at            TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (buyer_id) REFERENCES users(id),
  FOREIGN KEY (pet_id) REFERENCES pets(id)
);

-- Insert demo data
INSERT INTO users (email, password, name, role) VALUES
('user@example.com', 'password123', 'Demo User', 'USER'),
('admin@example.com', 'password123', 'Demo Admin', 'ADMIN')
ON DUPLICATE KEY UPDATE name = VALUES(name); 