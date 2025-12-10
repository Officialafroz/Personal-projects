import React, { useState } from "react";
import SeatLayout from "./SeatLayout";
import PassengerForm from "./PassengerForm";
import PaymentSummary from "./PaymentSummary";
import "../styles/SeatSelectionModal.css";

const SeatSelectionModal = ({ stops, isOpen, onClose, bus }) => {
  const [selectedSeats, setSelectedSeats] = useState([]);
  const [passengerData, setPassengerData] = useState([]);
  const [total, setTotal] = useState(0)

  if (isOpen) return null;

  const handleStops = () => {
    console.log(result.stops);
  }

  const toggleSeat = (seat) => {
    setSelectedSeats((prev) =>
      prev.includes(seat)
        ? prev.filter((s) => s !== seat)
        : [...prev, seat]
    );
  };

  const handlePassengerData = (data) => {
    setPassengerData(data);
  };

  return (
    <div className="modal-overlay">
      <div className="modal-container">
        <div className="modal-header">
          {/* <h2>Seat Selection</h2> */}
          <button onClick={onClose} className="close-btn">✕</button>
        </div>

        <div className="modal-body">
          <SeatLayout selectedSeats={selectedSeats} toggleSeat={toggleSeat} />
          <PassengerForm
            stops={stops}
            total={total}
            setTotal={setTotal}
            selectedSeats={selectedSeats}
            onPassengerDataChange={handlePassengerData}
          />
          <PaymentSummary totalAmount={total} />
        </div>
      </div>
    </div>
  );
};

export default SeatSelectionModal;
