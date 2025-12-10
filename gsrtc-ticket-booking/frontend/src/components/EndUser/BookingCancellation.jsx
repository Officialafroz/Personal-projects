import { useState } from "react";
import "../styles/BookingCancellation.css";
import axios from "axios";

const BookingCancellation = () => {
  const [pnr, setPnr] = useState("");
  const [foundBooking, setFoundBooking] = useState(null);
  const [cancelledBookings, setCancelledBookings] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [booking, setBooking] = useState([]);

  // Mock bookings data
  // const bookings = [
  //   {
  //     pnr: "GS123456",
  //     source: "Ahmedabad",
  //     destination: "Surat",
  //     journeyDate: "2025-11-10",
  //     tripCode: "GJ-EXP123",
  //     busClass: "Express",
  //     seatNumbers: ["A1", "A2"],
  //     passengers: ["Afroz Mansuri", "Ali Khan"],
  //     totalFare: 650,
  //   },
  //   {
  //     pnr: "GS789012",
  //     source: "Rajkot",
  //     destination: "Vadodara",
  //     journeyDate: "2025-11-12",
  //     tripCode: "GJ-VOL999",
  //     busClass: "Volvo AC",
  //     seatNumbers: ["B2", "B3"],
  //     passengers: ["Afroz Mansuri", "Zara Sheikh"],
  //     totalFare: 1320,
  //   },
  // ];

  const handleSearch = async () => {
    try {
      const res = await axios.get("/api/ticket/requestedTicket", {
        params: { pnr }
      });

      if (!res.data) {
        alert("No booking found for this PNR.");
        setFoundBooking(null);
        return;
      }

      setBooking(res.data);
      setFoundBooking(res.data);

    } catch (error) {
      alert("Invalid PNR or server error.");
      setFoundBooking(null);
    }
  };


  const handleCancel = () => {
    setShowModal(true);
  };

  const confirmCancellation = () => {
    setCancelledBookings([...cancelledBookings, foundBooking]);
    setFoundBooking(null);
    setShowModal(false);
    alert("Booking successfully cancelled!");
  };

  return (
    <div className="booking-cancellation-container">
      <h2>Booking Cancellation</h2>

      <div className="pnr-search">
        <input
          type="text"
          placeholder="Enter your PNR number"
          value={pnr}
          onChange={(e) => setPnr(e.target.value)}
        />
        <button onClick={handleSearch}>Search</button>
      </div>

      {foundBooking && (
        <div className="booking-info">
          <h3>Booking Details</h3>
          <p><strong>Trip Code:</strong> {foundBooking.tripCode}</p>
          <p><strong>Route:</strong> {foundBooking.route}</p>
          {/* <p><strong>PNR:</strong> {foundBooking.pnr}</p> */}
          <p><strong>Date:</strong> {foundBooking.journeyDate}</p>
          <p><strong>Class:</strong> {foundBooking.classType}</p>
          <p><strong>Passengers:</strong> {foundBooking.passengers.join(", ")}</p>
          <p><strong>Seats:</strong> {foundBooking.seats.join(", ")}</p>
          <p><strong>Total Fare:</strong> ₹{foundBooking.totalFare}</p>

          <button className="cancel-btn" onClick={handleCancel}>
            Cancel Ticket
          </button>
        </div>
      )}

      {/* Confirmation Modal */}
      {showModal && (
        <div className="modall-overlay">
          <div className="modall">
            <h1></h1>
            <h3>Confirm Cancellation</h3>
            <p>Are you sure you want to cancel this ticket?</p>
            <div className="modall-actions">
              <button onClick={confirmCancellation} className="confirm">
                Yes, Cancel
              </button>
              <button onClick={() => setShowModal(false)} className="cancel">
                No, Go Back
              </button>
            </div>
          </div>
        </div>
      )}

      {/* Cancelled Bookings Record */}
      {cancelledBookings.length > 0 && (
        <div className="cancelled-section">
          <h3>Cancelled Bookings</h3>
          {cancelledBookings.map((b, index) => (
            <div key={index} className="cancelled-card">
              <p><strong>PNR:</strong> {b.pnr}</p>
              <p><strong>Route:</strong> {b.source} ➜ {b.destination}</p>
              <p><strong>Date:</strong> {b.journeyDate}</p>
              <p><strong>Total Fare:</strong> ₹{b.totalFare}</p>
              <p className="cancelled-status">Status: Cancelled</p>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default BookingCancellation;
