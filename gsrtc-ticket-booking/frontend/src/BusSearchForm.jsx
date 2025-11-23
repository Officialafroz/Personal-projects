import { useState } from "react";
import "./../styles/BusSearchForm.css";

const BusSearchForm = () => {
  const [form, setForm] = useState({
    from: "",
    to: "",
    date: "",
    passengers: 1,
    singleLady: false,
    handicap: false,
  });

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setForm((prev) => ({
      ...prev,
      [name]: type === "checkbox" ? checked : value,
    }));
  };

  const handlePassengerChange = (delta) => {
    setForm((prev) => ({
      ...prev,
      passengers: Math.max(1, prev.passengers + delta),
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    alert(`Searching buses from ${form.from} to ${form.to} on ${form.date}`);
  };

  return (
    <form className="bus-search-form" onSubmit={handleSubmit}>
      <input
        type="text"
        name="from"
        placeholder="Source City"
        value={form.from}
        onChange={handleChange}
        required
      />

      <input
        type="text"
        name="to"
        placeholder="Destination City"
        value={form.to}
        onChange={handleChange}
        required
      />

      <input
        type="date"
        name="date"
        value={form.date}
        onChange={handleChange}
        required
      />

      <div className="passenger-control">
        <button type="button" onClick={() => handlePassengerChange(-1)}>
          -
        </button>
        <span>{form.passengers}</span>
        <button type="button" onClick={() => handlePassengerChange(1)}>
          +
        </button>
      </div>

      <div className="checkboxes">
        <label>
          <input
            type="checkbox"
            name="singleLady"
            checked={form.singleLady}
            onChange={handleChange}
          />
          Single Lady
        </label>
        <label>
          <input
            type="checkbox"
            name="handicap"
            checked={form.handicap}
            onChange={handleChange}
          />
          Handicap
        </label>
      </div>

      <button type="submit" className="search-btn">
        🔍 Search Buses
      </button>
    </form>
  );
};

export default BusSearchForm;
