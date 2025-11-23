const BookingFilters = ({ setFilterClass, setFilterRevenue }) => {
  return (
    <div className="booking-filters">
      <select onChange={(e) => setFilterClass(e.target.value)}>
        <option value="">All Classes</option>
        <option value="Express">Express</option>
        <option value="Luxury">Luxury</option>
        <option value="Sleeper">Sleeper</option>
      </select>

      <select onChange={(e) => setFilterRevenue(e.target.value)}>
        <option value="">All Revenue</option>
        <option value="high">High (&gt; ₹20,000)</option>
        <option value="low">Low (≤ ₹20,000)</option>
      </select>
    </div>
  );
};

export default BookingFilters;
