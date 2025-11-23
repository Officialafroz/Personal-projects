CREATE TABLE refund (
    refund_id INT PRIMARY KEY AUTO_INCREMENT,
    payment_id INT NOT NULL,
    refund_ref VARCHAR(50) UNIQUE,    -- Gateway or internal refund ID
    refund_amount DECIMAL(10,2) NOT NULL,          -- Refund amount (partial/full)
    status VARCHAR(20) DEFAULT 'PENDING',   -- PENDING, SUCCESS, FAILED
    refunded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    reason VARCHAR(255),
    FOREIGN KEY (payment_id) REFERENCES payment(payment_id) ON DELETE CASCADE
);

drop table refund;