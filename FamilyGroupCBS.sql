CREATE DATABASE IF NOT EXISTS `family_groupCBS`;
USE `family_groupCBS`;

CREATE TABLE CSR (
    csr_id INT PRIMARY KEY AUTO_INCREMENT,
    name_CSR VARCHAR(100) NOT NULL,
    `password` VARCHAR(100) NOT NULL,  -- Password for CSR authentication
    phone_number VARCHAR(15) NOT NULL,  -- Contact information for CSR
    available BOOLEAN DEFAULT TRUE  -- Availability status for CSR
);

CREATE TABLE CSR_authority (
    authority_id INT PRIMARY KEY AUTO_INCREMENT,
    `role` ENUM('ROLE_VIEW_ONLY', 'ROLE_OFFER_MANAGEMENT', 'ROLE_FAMILY_GROUP_MANAGEMENT', 'ROLE_CSR_MANAGEMENT') NOT NULL  -- Different permission levels
);

CREATE TABLE csr_authority_mapping (
    csr_id INT NOT NULL,
    authority_id INT NOT NULL,
    PRIMARY KEY (csr_id, authority_id),
    FOREIGN KEY (csr_id) REFERENCES CSR(csr_id),
    FOREIGN KEY (authority_id) REFERENCES CSR_authority(authority_id)
);

CREATE TABLE subscriber (
    subscriber_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_name VARCHAR(100) NOT NULL,
    msisdn VARCHAR(15) NOT NULL,
    subscriber_type ENUM('0', '1') NOT NULL,  -- 0 for prepaid, 1 for postpaid
    credit_limit DECIMAL(10, 2) DEFAULT NULL,  -- Applicable only for postpaid subscribers
    prepayment DECIMAL(10, 2) DEFAULT NULL   -- Applicable only for prepaid subscribers
);

CREATE TABLE family_group (
    group_id INT PRIMARY KEY AUTO_INCREMENT,
    group_name VARCHAR(100) NOT NULL,
    sponsor_id INT UNIQUE,
    FOREIGN KEY (sponsor_id) REFERENCES subscriber(subscriber_id)
);

CREATE TABLE group_member (
    member_id INT PRIMARY KEY AUTO_INCREMENT,
    group_id INT,
    quota DECIMAL(10, 2) NOT NULL,  -- Data quota assigned to the member in MB
    FOREIGN KEY (group_id) REFERENCES family_group(group_id),
    subscriber_id INT UNIQUE,
    FOREIGN KEY (subscriber_id) REFERENCES subscriber(subscriber_id)
);

CREATE TABLE offer (
    offer_id INT PRIMARY KEY AUTO_INCREMENT,
    offer_name VARCHAR(100) NOT NULL,
    data_limitMB DECIMAL(10, 2) NOT NULL,  -- Total data available in the offer in MB
    validity_days INT NOT NULL,             -- Validity period of the offer in days
    offer_type ENUM('PRIMARY', 'SECONDARY') NOT NULL,
    created_byCSRId INT,                    -- CSR who created the offer
    FOREIGN KEY (created_byCSRId) REFERENCES CSR(csr_id)
);

CREATE TABLE subscriber_offer (
    subscriber_offerId INT PRIMARY KEY AUTO_INCREMENT,
    subscriber_id INT,
    offer_id INT,
    data_usedMB DECIMAL(10, 2) DEFAULT 0,  -- Data usage tracked for the specific offer in MB
	data_unusedMB DECIMAL(10, 2) DEFAULT 0,
    create_time DATE,
    expiry_time DATE,
    FOREIGN KEY (subscriber_id) REFERENCES subscriber(subscriber_id),
    FOREIGN KEY (offer_id) REFERENCES offer(offer_id)
);

INSERT INTO family_groupcbs.CSR (name_CSR, `password`, phone_number, available)
VALUES 
    ('john', '{noop}password123', '1234567890', TRUE),
    ('jane', '{noop}password456', '0987654321', FALSE),
    ('alice', '{noop}password789', '1122334455', TRUE),
    ('bob', '{noop}test123', '2233445566', TRUE),
    ('charlie', '{noop}password654', '3344556677', FALSE);

INSERT INTO family_groupcbs.CSR_authority (`role`)
VALUES 
    ('ROLE_FAMILY_GROUP_MANAGEMENT'),
	('ROLE_VIEW_ONLY'),
    ('ROLE_CSR_MANAGEMENT'),
    ('ROLE_OFFER_MANAGEMENT');

INSERT INTO family_groupcbs.csr_authority_mapping (csr_id,authority_id)
VALUES 
    (1,1),
	(4,1),
    (4,2),
    (4,3),
    (4,4),
    (3,1),
    (2,2);