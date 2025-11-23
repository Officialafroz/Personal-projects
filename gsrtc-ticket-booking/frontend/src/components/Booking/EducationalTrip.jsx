import { useState } from "react";
import "../styles/EducationalTrip.css";

const EducationalTrip = () => {
  const [formData, setFormData] = useState({
    code: "",
    instituteName: "",
    type: "",
    ownership: "",
    location: "",
    phone: "",
    email: "",
    source: "",
    destinations: [""],
    numStudents: "",
    numFaculties: "",
    numBuses: "",
    busClass: "",
    budget: "",
    date: "",
    time: "",
  });

  const [isRegistered, setIsRegistered] = useState(false);
  const [requestSent, setRequestSent] = useState(false);

  // Handle input
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  // Add multiple destinations
  const addDestination = () => {
    setFormData({ ...formData, destinations: [...formData.destinations, ""] });
  };

  const handleDestinationChange = (index, value) => {
    const updated = [...formData.destinations];
    updated[index] = value;
    setFormData({ ...formData, destinations: updated });
  };

  // Mock: check institute registry
  const checkRegistry = () => {
    if (formData.code.trim() === "") {
      alert("Please enter UDISE or AISHE code");
      return;
    }

    // Simulated registry check
    if (formData.code === "UDISE123" || formData.code === "AISHE987") {
      setFormData({
        ...formData,
        instituteName: "Bright Future College",
        type: "College",
        ownership: "Private",
        location: "Ahmedabad, Gujarat",
        phone: "9876543210",
        email: "info@brightfuture.edu.in",
      });
      setIsRegistered(true);
    } else {
      alert("Institute not registered!");
    }
  };

  // Submit request
  const handleSubmit = (e) => {
    e.preventDefault();
    setRequestSent(true);
  };

  return (
    <div className="educational-trip-container">

      {!requestSent ? (
        <form onSubmit={handleSubmit} className="educational-trip-form">
          {/* Step 1: Registry check */}
          <div className="form-section">
            <h3>Institute Verification</h3>
            <label>
              UDISE / AISHE Code:
              <input
                type="text"
                name="code"
                value={formData.code}
                onChange={handleChange}
                placeholder="Enter your institute code"
              />
            </label>
            <button type="button" className="register-btn" onClick={checkRegistry}>
              Check Registry
            </button>
          </div>

          {/* Step 2: Institute Details */}
          {isRegistered && (
            <>
              <div className="form-section">
                <h3>Institute Details</h3>
                <p><strong>Name:</strong> {formData.instituteName}</p>
                <p><strong>Type:</strong> {formData.type}</p>
                <p><strong>Ownership:</strong> {formData.ownership}</p>
                <p><strong>Location:</strong> {formData.location}</p>
                <p><strong>Phone:</strong> {formData.phone}</p>
                <p><strong>Email:</strong> {formData.email}</p>
              </div>

              {/* Step 3: Trip Details */}
              <div className="form-section">
                <h3>Trip Details</h3>

                <label>
                  Source:
                  <input
                    type="text"
                    name="source"
                    value={formData.source}
                    onChange={handleChange}
                    placeholder="Enter starting point"
                  />
                </label>

                <label>Destinations:</label>
                {formData.destinations.map((dest, idx) => (
                  <input
                    key={idx}
                    type="text"
                    value={dest}
                    placeholder={`Destination ${idx + 1}`}
                    onChange={(e) => handleDestinationChange(idx, e.target.value)}
                  />
                ))}
                <button type="button" onClick={addDestination}>
                  + Add Destination
                </button>

                <label>
                  Number of Students:
                  <input
                    type="number"
                    name="numStudents"
                    value={formData.numStudents}
                    onChange={handleChange}
                  />
                </label>

                <label>
                  Number of Faculties:
                  <input
                    type="number"
                    name="numFaculties"
                    value={formData.numFaculties}
                    onChange={handleChange}
                  />
                </label>

                <label>
                  Number of Buses Required:
                  <input
                    type="number"
                    name="numBuses"
                    value={formData.numBuses}
                    onChange={handleChange}
                  />
                </label>

                <label>
                  Bus Class:
                  <select
                    name="busClass"
                    value={formData.busClass}
                    onChange={handleChange}
                  >
                    <option value="">Select Class</option>
                    <option value="Express">Express</option>
                    <option value="Sleeper">Sleeper</option>
                    <option value="Volvo">Volvo</option>
                  </select>
                </label>

                <label>
                  Estimated Budget (₹):
                  <input
                    type="number"
                    name="budget"
                    value={formData.budget}
                    onChange={handleChange}
                  />
                </label>

                <label>
                  Trip Date:
                  <input
                    type="date"
                    name="date"
                    value={formData.date}
                    onChange={handleChange}
                  />
                </label>

                <label>
                  Departure Time:
                  <input
                    type="time"
                    name="time"
                    value={formData.time}
                    onChange={handleChange}
                  />
                </label>
              </div>

              <button type="submit" className="submit-btn">
                Submit Request
              </button>
            </>
          )}
        </form>
      ) : (
        <div className="success-message">
          <h3>Trip Request Sent Successfully!</h3>
          <p>
            Your request has been submitted to the depot admin for approval.
            Once approved, you will receive a notification to pay 50% of the
            total budget. The remaining 50% must be paid on the day of the trip.
          </p>
        </div>
      )}
    </div>
  );
};

export default EducationalTrip;
