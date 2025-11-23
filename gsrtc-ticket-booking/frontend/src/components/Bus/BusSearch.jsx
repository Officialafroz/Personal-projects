import React, { useState } from "react";
import BusResultCard from "./BusResultCard";
import SeatSelectionModal from "../Booking/SeatSelectionModal";
import "../../styles/bus/BusSearch.css";

const BusSearch = () => {
  const [form, setForm] = useState({ from: "", to: "", date: "" });
  const [buses, setBuses] = useState([
    { id: 1, name: "Express Line", source: "Ahmedabad", destination: "Surat", time: "10:00 AM", fare: 450 },
    { id: 2, name: "SuperFast", source: "Ahmedabad", destination: "Vadodara", time: "11:30 AM", fare: 550 },
    { id: 3, name: "Luxury Coach", source: "Surat", destination: "Ahmedabad", time: "2:00 PM", fare: 600 }
  ]);
  const [filtered, setFiltered] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [selectedBus, setSelectedBus] = useState(null);

  const handleSearch = (e) => {
    e.preventDefault();
    const results = buses.filter(
      (b) =>
        b.source.toLowerCase() === form.from.toLowerCase() &&
        b.destination.toLowerCase() === form.to.toLowerCase()
    );
    setFiltered(results);
  };

  const openSeatModal = (bus) => {
    setSelectedBus(bus);
    setShowModal(true);
  };

  const closeSeatModal = () => {
    setShowModal(false);
    setSelectedBus(null);
  };

  return (
    <div className="bus-search-container">
      <h2>Find Your Bus</h2>
      <form onSubmit={handleSearch} className="bus-search-form">
        <input
          type="text"
          placeholder="From"
          value={form.from}
          onChange={(e) => setForm({ ...form, from: e.target.value })}
        />
        <input
          type="text"
          placeholder="To"
          value={form.to}
          onChange={(e) => setForm({ ...form, to: e.target.value })}
        />
        <input
          type="date"
          value={form.date}
          onChange={(e) => setForm({ ...form, date: e.target.value })}
        />
        <button type="submit">Search Buses</button>
      </form>

      <div className="bus-results">
        {filtered.length === 0 ? (
          <p>No buses found.</p>
        ) : (
          filtered.map((bus) => (
            <BusResultCard key={bus.id} bus={bus} onSelectSeats={() => openSeatModal(bus)} />
          ))
        )}
      </div>

      <SeatSelectionModal
        isOpen={showModal}
        onClose={closeSeatModal}
        bus={selectedBus}
      />
    </div>
  );
};

export default BusSearch;
