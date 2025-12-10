// AdminLogin.jsx
import { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "../components/styles/AuthForm.css";
import axios from "axios";
import { AppContext } from "../store/AppContext";
import AdminPages from "./AdminPages";

const AdminLogin = () => {
  const { admin, setAdmin } = useContext(AppContext);
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    setAdmin(null);  // ensures login page always shows first
  }, []);

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const verifyRes = await axios.post("/api/depotAdmin/login/verify", { email, password });
      const admin = await axios.get(`/api/depotAdmin/getAdmin/${email}`);

      const userData = verifyRes.data;
      console.log(userData + ' verification msg.');
      setAdmin(admin.data);
      navigate("/admin/dashboard");
    } catch (err) {
      console.error(err);
      alert("Incorrect email or password");
    }
  };

  return (
    <>
      {!admin ? (
        <form className="login-form" onSubmit={handleLogin}>
          <h2>Admin Login</h2>
          <input type="email" placeholder="Email" onChange={(e) => setEmail(e.target.value)} />
          <input type="password" placeholder="Password" onChange={(e) => setPassword(e.target.value)} />
          <button type="submit">Login</button>
        </form>
      ) : (
        <AdminPages />
      )}
    </>
  );
};

export default AdminLogin;
