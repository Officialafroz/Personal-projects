// SignUp.jsx
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./styles/AuthForm.css";

const SignUp = ({ setUser }) => {
  const [form, setForm] = useState({
    name: "",
    email: "",
    phoneNumber: "",
    gender: "",
  });
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (Object.values(form).some((v) => v.trim() === "")) {
      console.log(form);
      alert("Please fill all fields");
      return;
    }

    try {
      const response = await axios.post("/api/user/add", form);

      setUser(response.data);
      alert("User registered successfully!");
      navigate("/bus-booking");
    } catch (error) {
      console.error("Error:", error);
      if (error.response) {
        alert(`Error: ${error.response.data.message || "Registration failed"}`);
      } else {
        alert("Failed to connect to the server");
      }
    }
  };

  return (
    <>
      <form className="signup-form" onSubmit={handleSubmit}>
        <h2>Sign Up</h2>

        {["name", "email", "phoneNumber"].map((f) => (
          <input
            key={f}
            type={f === "email" ? "email" : "text"}
            placeholder={`Enter ${f}`}
            value={form[f]}
            onChange={(e) => setForm({ ...form, [f]: e.target.value })}
          />
        ))}

        <select
          value={form.gender}
          onChange={(e) => setForm({ ...form, gender: e.target.value })}
        >
          <option value="">Select Gender</option>
          <option value="male">Male</option>
          <option value="female">Female</option>
        </select>

        <button type="submit">Register</button>
      </form>
    </>
  );
};

export default SignUp;
