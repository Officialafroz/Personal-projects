import { useState } from "react";
import "../styles/YourBookings.css";

const YourBookings = () => {
  const [filter, setFilter] = useState("");
  const [bookings] = useState([
    {
      id: 1,
      source: "Ahmedabad",
      destination: "Surat",
      journeyDate: "2025-11-10",
      tripCode: "GJ-EXP123",
      busClass: "Express",
      boardingPoint: "Nehrunagar",
      departureTime: "08:30 AM",
      seats: 3,
      seatNumbers: ["A1", "A2", "A3"],
      passengers: ["Afroz Mansuri", "Ali Khan", "Sara Patel"],
      fareDetails: {
        baseFare: 900,
        reservationFee: 50,
        gst: 45,
        discount: 30,
        total: 965,
      },
    },
    {
      id: 2,
      source: "Rajkot",
      destination: "Vadodara",
      journeyDate: "2025-11-12",
      tripCode: "GJ-VOL999",
      busClass: "Volvo AC",
      boardingPoint: "Rajkot Central Bus Stop",
      departureTime: "09:45 PM",
      seats: 2,
      seatNumbers: ["B2", "B3"],
      passengers: ["Afroz Mansuri", "Zara Sheikh"],
      fareDetails: {
        baseFare: 1200,
        reservationFee: 60,
        gst: 60,
        discount: 0,
        total: 1320,
      },
    },
  ]);

  const filteredBookings = bookings.filter((b) => {
    const search = filter.toLowerCase();
    return (
      b.source.toLowerCase().includes(search) ||
      b.destination.toLowerCase().includes(search) ||
      b.journeyDate.includes(search) ||
      b.tripCode.toLowerCase().includes(search)
    );
  });

  return (
    <div className="your-bookings-container">
      <div className="booking-filters">
        <input
          type="text"
          placeholder="Search by source, destination, date or tripcode..."
          value={filter}
          onChange={(e) => setFilter(e.target.value)}
        />
      </div>

      <div className="bookings-list">
        {filteredBookings.length > 0 ? (
          filteredBookings.map((booking) => (
            <div key={booking.id} className="booking-card">
              <div className="booking-header">
                <h3>
                  {booking.source} ➜ {booking.destination}
                </h3>
                <p>{booking.journeyDate}</p>
              </div>

              <div className="booking-details">
                <p><strong>Trip Code:</strong> {booking.tripCode}</p>
                <p><strong>Class:</strong> {booking.busClass}</p>
                <p><strong>Boarding Point:</strong> {booking.boardingPoint}</p>
                <p><strong>Departure Time:</strong> {booking.departureTime}</p>
                <p><strong>No. of Seats:</strong> {booking.seats}</p>
                <p><strong>Seat Numbers:</strong> {booking.seatNumbers.join(", ")}</p>
                <p><strong>Passengers:</strong> {booking.passengers.join(", ")}</p>
              </div>

              <div className="fare-details">
                <h4>Fare Details</h4>
                <p>Base Fare: ₹{booking.fareDetails.baseFare}</p>
                <p>Reservation Fee: ₹{booking.fareDetails.reservationFee}</p>
                <p>GST: ₹{booking.fareDetails.gst}</p>
                <p>Discount: -₹{booking.fareDetails.discount}</p>
                <hr />
                <p><strong>Total: ₹{booking.fareDetails.total}</strong></p>
              </div>
            </div>
          ))
        ) : (
          <p className="no-bookings">No bookings found.</p>
        )}
      </div>
    </div>
  );
};

export default YourBookings;
