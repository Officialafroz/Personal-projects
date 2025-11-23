import { useState } from "react";
import BusSearch from "./BusSearch";
import BookingTable from "./BookingTable";
import BookingFilters from "./BookingFilters";
import "../styles/BookingManagement.css";

const BookingManagement = () => {
  const [searchTerm, setSearchTerm] = useState("");
  const [filterClass, setFilterClass] = useState("");
  const [filterRevenue, setFilterRevenue] = useState("");

  const [buses, setBuses] = useState([
    { id: 1, busNumber: "GJ05AB1234", route: "Ahmedabad - Surat", class: "Express", tripCode: "EXP-1123", totalSeats: 50, bookedSeats: 42, revenue: 17800 },
    { id: 2, busNumber: "GJ10CD9876", route: "Rajkot - Vadodara", class: "Luxury", tripCode: "LUX-2299", totalSeats: 45, bookedSeats: 40, revenue: 30000 },
    { id: 3, busNumber: "GJ02PQ4455", route: "Surat - Junagadh", class: "Sleeper", tripCode: "SLP-3301", totalSeats: 40, bookedSeats: 25, revenue: 16000 },
  ]);

  const filteredBuses = buses.filter(bus => {
    const matchesSearch = bus.route.toLowerCase().includes(searchTerm.toLowerCase());
    const matchesClass = filterClass ? bus.class === filterClass : true;
    const matchesRevenue =
      filterRevenue === "high"
        ? bus.revenue > 20000
        : filterRevenue === "low"
          ? bus.revenue <= 20000
          : true;

    return matchesSearch && matchesClass && matchesRevenue;
  });

  return (
    <div className="booking-management">
      <h1>Booking Management</h1>
      <div className="search-filter">
        <BusSearch setSearchTerm={setSearchTerm} />
        <BookingFilters setFilterClass={setFilterClass} setFilterRevenue={setFilterRevenue} />
      </div>
      <BookingTable buses={filteredBuses} />
    </div>
  );
};

export default BookingManagement;
