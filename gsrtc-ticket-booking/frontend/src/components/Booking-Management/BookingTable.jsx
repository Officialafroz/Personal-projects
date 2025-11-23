const BookingTable = ({ buses }) => {
  return (
    <table className="booking-table">
      <thead>
        <tr>
          <th>Bus Number</th>
          <th>Route</th>
          <th>Class</th>
          <th>Trip Code</th>
          <th>Total Seats</th>
          <th>Booked Seats</th>
          <th>Remaining Seats</th>
          <th>Revenue (₹)</th>
        </tr>
      </thead>
      <tbody>
        {buses.map((bus) => (
          <tr key={bus.id}>
            <td>{bus.busNumber}</td>
            <td>{bus.route}</td>
            <td>{bus.class}</td>
            <td>{bus.tripCode}</td>
            <td>{bus.totalSeats}</td>
            <td>{bus.bookedSeats}</td>
            <td>{bus.totalSeats - bus.bookedSeats}</td>
            <td>{bus.revenue.toLocaleString()}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default BookingTable;
