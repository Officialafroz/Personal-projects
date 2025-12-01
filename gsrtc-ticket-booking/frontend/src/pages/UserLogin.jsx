// UserLogin.jsx
import { useContext, useState } from "react";
import { useNavigate } from "react-router-dom";
import "../components/styles/AuthForm.css";
import axios from "axios";
import { AppContext } from "../store/AppContext";

const UserLogin = () => {
  const { setUser } = useContext(AppContext);
  const [email, setEmail] = useState("");
  const [otp, setOtp] = useState("");
  const [showOtpField, setShowOtpField] = useState(false);
  const navigate = useNavigate();

  const handleSendOtp = async (e) => {
    console.log(email);
    e.preventDefault();
    if (!email.trim()) return alert("Enter your email");

    try {
      const res = await axios.post("/api/user/login/processEmail", { email });
      alert(res.data.message || "OTP sent to your email");
      setShowOtpField(true);
    } catch (err) {
      console.error(err);
      alert("Failed to send OTP. Try again.");
    }
  };

  const handleVerify = async (e) => {
    e.preventDefault();
    if (!otp.trim()) return alert("Enter the OTP");

    try {
      const res = await axios.post("/api/user/login/verify", { email, otp });
      const userData = res.data;
      console.log(userData + ' verification msg.');
      const user = { email };
      setUser(user);
      navigate("/");
    } catch (err) {
      console.error(err);
      alert("Invalid or expired OTP");
    }
  };

  return (
    <>
      <form className="login-form">
        {!showOtpField ? (
          <>
            <h2>User Login</h2>
            <input
              type="email"
              placeholder="Enter email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
            <button onClick={handleSendOtp}>Send OTP</button>
          </>
        ) : (
          <>
            <h2>Enter OTP</h2>
            <input
              type="text"
              placeholder="Enter OTP"
              value={otp}
              onChange={(e) => setOtp(e.target.value)}
            />
            <button type="button" onClick={handleVerify}>Verify</button>
          </>
        )}
      </form>
    </>
  );
};

export default UserLogin;
