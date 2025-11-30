// AdminLogin.jsx
import { useContext, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./styles/AuthForm.css"
import axios from "axios";
import { AppContext } from "../store/AppContext";

const AdminLogin = () => {
  const {setAdmin} = useContext(AppContext);
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

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
      <form className="login-form" onSubmit={handleLogin}>
        <h2>Admin Login</h2>
        <input type="email" placeholder="Email" onChange={(e) => setEmail(e.target.value)} />
        <input type="password" placeholder="Password" onChange={(e) => setPassword(e.target.value)} />
        <button type="submit">Login</button>
      </form>
    </>
  );
};

export default AdminLogin;
