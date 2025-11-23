import React, { useState } from "react";
import "../styles/PassengerForm.css";

const PassengerForm = ({ selectedSeats, onPassengerDataChange }) => {
  const [passengers, setPassengers] = useState([]);
  const [multiBoarding, setMultiBoarding] = useState(false);

  const handleInputChange = (index, field, value) => {
    const updated = [...passengers];
    updated[index] = { ...updated[index], [field]: value };
    setPassengers(updated);
    onPassengerDataChange(updated);
  };

  const handleToggle = () => {
    setMultiBoarding(!multiBoarding);
  };

  return (
    <div className="passenger-form">
      <h3>Fill Details</h3>

      {selectedSeats.map((seat, index) => (
        <div key={seat} className="passenger-entry">
          <label>Passenger for Seat {seat}</label>
          <input
            type="text"
            placeholder="Full Name"
            onChange={(e) =>
              handleInputChange(index, "name", e.target.value)
            }
          />
          <input
            type="number"
            placeholder="Age"
            onChange={(e) =>
              handleInputChange(index, "age", e.target.value)
            }
          />
          <select
            onChange={(e) =>
              handleInputChange(index, "gender", e.target.value)
            }
          >
            <option value="">Gender</option>
            <option>Male</option>
            <option>Female</option>
          </select>

          <div className="boarding-toggle">
            <label>
              <input type="checkbox" checked={multiBoarding} onChange={handleToggle} /> 
              Add Boarding Point
            </label>
          </div>

          {multiBoarding && (
            <select
              onChange={(e) =>
                handleInputChange(index, "boardingPoint", e.target.value)
              }
            >
              <option value="">Select Boarding Point</option>
              <option>City Center</option>
              <option>Main Bus Stop</option>
              <option>Highway Pickup</option>
            </select>
          )}
          <button className="btn btn-primary">Add Passenger</button>
        </div>
      ))}
    </div>
  );
};

export default PassengerForm;
