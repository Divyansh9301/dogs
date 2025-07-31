-- Initial schema for Doggo-Marketplace
CREATE TABLE IF NOT EXISTS users (
  id          BIGINT AUTO_INCREMENT PRIMARY KEY,
  email       VARCHAR(128) UNIQUE NOT NULL,
  password    CHAR(60)            NOT NULL, -- bcrypt
  role        ENUM('USER','ADMIN') NOT NULL DEFAULT 'USER',
  created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS pets (
  id            BIGINT AUTO_INCREMENT PRIMARY KEY,
  name          VARCHAR(64)  NOT NULL,
  breed         VARCHAR(64)  NOT NULL,
  father_breed  VARCHAR(64),
  mother_breed  VARCHAR(64),
  age_months    INT          NOT NULL,
  gender        ENUM('MALE','FEMALE') NOT NULL,
  vaccinated    BOOLEAN      NOT NULL,
  price_cents   INT          NOT NULL,
  seller_id     BIGINT,
  sold_out      BOOLEAN      DEFAULT FALSE,
  img_url       VARCHAR(256),
  created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (seller_id) REFERENCES users(id)
); 