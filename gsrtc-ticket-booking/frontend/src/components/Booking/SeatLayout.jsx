import React from "react";
import "../styles/SeatLayout.css";
import { MdEventSeat } from "react-icons/md";

const SeatLayout = ({ selectedSeats, toggleSeat }) => {
  const seats = Array.from({ length: 46 }, (_, i) => i + 1);
  // const layout = generateSeatLayout(seats)

  // function generateSeatLayout(totalSeats = 46) {
  //   const seatTypes = ['W', 'A', 'A', 'M', 'W']; // Window, Aisle, Aisle, Middle, Window
  //   const layout = [];

  //   for (let i = 0; i < totalSeats; i++) {
  //     const seatNumber = i + 1;
  //     const type = seatTypes[i % seatTypes.length];
  //     layout.push(`${seatNumber}${type}`);
  //   }

  //   return layout;
  // }

  return (
    <div className="seat-layout">
      {seats.map((seat, index) => (
        <div
          key={index}
          className={`seat ${selectedSeats.includes(seat) ? "selected" : ""}`}
          onClick={() => toggleSeat(seat)}
        >
          <MdEventSeat />
          {seat}
        </div>
      ))}
    </div>
  );
};

export default SeatLayout;
