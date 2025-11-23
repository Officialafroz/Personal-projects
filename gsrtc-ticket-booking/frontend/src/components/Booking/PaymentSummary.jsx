import { useState, useEffect } from "react";
import "../styles/PaymentSummary.css";

const PaymentSummary = ({ totalAmount, onClose }) => {
  const [isPaid, setIsPaid] = useState(false);

  const handlePayment = () => {
    // Simulate payment process
    setTimeout(() => {
      setIsPaid(true);
    }, 800);
  };

  useEffect(() => {
    if (isPaid) {
      // Automatically close modal after 2 seconds
      const timer = setTimeout(() => {
        onClose?.(); // Close modal if function is provided
      }, 2000);
      return () => clearTimeout(timer);
    }
  }, [isPaid, onClose]);

  return (
    <div className="payment-summary">
      <h3>Payment Summary</h3>

      {!isPaid ? (
        <>
          <p>Total Fare: ₹{125}</p>
          <button className="pay-btn" onClick={handlePayment}>
            Pay ₹{125}
          </button>
        </>
      ) : (
        <div className="payment-success">
          <h4>✅ Payment Successful!</h4>
          <p>Your booking has been confirmed.</p>
        </div>
      )}
    </div>
  );
};

export default PaymentSummary;
