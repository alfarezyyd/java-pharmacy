SHOW DATABASES;
DROP DATABASE java_pharmacy;
CREATE DATABASE java_pharmacy;
USE java_pharmacy;

CREATE TABLE customers
(
    id            BIGINT PRIMARY KEY AUTO_INCREMENT,
    full_name     VARCHAR(255)          NOT NULL,
    date_of_birth DATE                  NOT NULL,
    gender        ENUM ('Man', 'Woman') NOT NULL,
    phone         VARCHAR(20)           NOT NULL,
    created_at    TIMESTAMP             NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP
);


CREATE TABLE addresses
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer_id BIGINT,
    street      VARCHAR(255) NOT NULL,
    city        VARCHAR(255) NOT NULL,
    state       VARCHAR(255) NOT NULL,
    country     VARCHAR(255) NOT NULL,
    postal_code VARCHAR(10)  NOT NULL,
    is_default  BOOLEAN      NOT NULL,
    description TEXT,
    created_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP,
    CONSTRAINT fk_customer_address
        FOREIGN KEY (customer_id) REFERENCES customers (id)
            ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE employees
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    full_name  VARCHAR(255)                                NOT NULL,
    gender     ENUM ('Man', 'Woman')                       NOT NULL,
    hire_date  DATE                                        NOT NULL,
    position   ENUM ('Programmer', 'Pharmacists') NOT NULL,
    start_date DATE                                        NOT NULL,
    end_date   DATE,
    created_at TIMESTAMP                                   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE users
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    employee_id BIGINT       NOT NULL UNIQUE,
    username    VARCHAR(255) NOT NULL,
    email       VARCHAR(255) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    last_login  TIMESTAMP,
    created_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP,
    CONSTRAINT fk_user_employee
        FOREIGN KEY (employee_id) REFERENCES employees (id)
            ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE medicines
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    full_name  VARCHAR(255) NOT NULL,
    brand      VARCHAR(255) NOT NULL,
    price      INT          NOT NULL,
    stock      INT          NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE medicines_information
(
    id                 BIGINT PRIMARY KEY                                                                                                                    NOT NULL,
    dosage_form        ENUM ('Tablet', 'Capsule', 'Caplet', 'Pill', 'Powder', 'Suppositoria', 'Rub', 'Liquid', 'Suspension', 'Injection', 'Drop', 'Inhaler') NOT NULL,
    strength           FLOAT(5)                                                                                                                              NOT NULL,
    indications        VARCHAR(255)                                                                                                                          NOT NULL,
    contraindications  VARCHAR(255)                                                                                                                          NOT NULL,
    side_effects       TEXT                                                                                                                                  NOT NULL,
    precautions        VARCHAR(255)                                                                                                                          NOT NULL,
    storage_conditions VARCHAR(255)                                                                                                                          NOT NULL,
    description        TEXT                                                                                                                                  NOT NULL,
    expiry_date        DATE                                                                                                                                  NOT NULL,
    country_of_origin  VARCHAR(255)                                                                                                                          NOT NULL
);


CREATE TABLE orders
(
    id              BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer_id     BIGINT                                  NOT NULL,
    total_amount    FLOAT,
    payment_method  ENUM ('Credit Card', 'Transfer', 'COD') NOT NULL,
    payment_status  ENUM ('Paid', 'Not Yet Paid'),
    order_status    ENUM ('On Process', 'Sent', 'Received', 'Cancelled'),
    shipping_method ENUM ('Regular', 'Express', 'One Day'),
    tracking_number VARCHAR(100),
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP,
    CONSTRAINT fk_orders_customers
        FOREIGN KEY (customer_id) REFERENCES customers (id)
            ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE orders_medicines
(
    medicine_id BIGINT NOT NULL,
    order_id    BIGINT NOT NULL,
    quantity    INT    NOT NULL,
    total_price INT    NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders (id),
    FOREIGN KEY (medicine_id) REFERENCES medicines (id),
    UNIQUE (order_id, medicine_id)
);


INSERT INTO employees(full_name, gender, hire_date, position, start_date) VALUE ('Ucup Martoto', 'Man', '2022-06-04', 'Programmer', '2022-06-04');
INSERT INTO users(employee_id, username, email, password) VALUE (1, 'ucupmartoto', 'ucupmartoto@gmail.com', SHA2('ucupmartoto', 256));

SELECT *
FROM employees;
SELECT *
FROM users;
