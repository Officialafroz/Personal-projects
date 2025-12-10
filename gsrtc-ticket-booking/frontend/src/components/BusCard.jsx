import { useContext, useState } from "react";
import SeatSelectionModal from "./Booking/SeatSelectionModal";
import "./styles/BusCard.css";
import { AppContext } from "../store/AppContext";
import axios from "axios";

const BusCard = ({ result }) => {
  const [showModal, setShowModal] = useState(false);
  const { setTripCode } = useContext(AppContext);
  const tripCode = result.tripCode;

  const handleSeatSelect = () => {
    setShowModal(true);
    setTripCode(tripCode);
  };

  const handleCloseModal = () => {
    setShowModal(false);
  };

  return (
    <div className="bus-card">
      <div className="bus-details">
        <div className="left-cont">
          <h3>{tripCode}</h3>
          <p>{result.classType}</p>
        </div>

        <div className="bus-desc">
          <h3>{result.routeName}</h3>
          <p className="route-via">via</p>
          <span>[</span>
          {result.stops.map((stop, index) => (
            <span key={index}>{stop.stopName} </span>
          ))}
          <span>]</span>
        </div>

        <p>{result.departureTime}{" - "}{result.destinationTime}</p>
        <p>Fare: ₹{result.seatRate}</p>
      </div>

      <button className="select-seat-btn" onClick={handleSeatSelect}>
        {result.vacantSeats} Seats
      </button>

      {showModal && <SeatSelectionModal
        stops={result.stops}
        onClose={handleCloseModal}
      />}
    </div>
  );
};

export default BusCard;
