import { NavLink, useNavigate } from "react-router-dom";
import "../styles/Sidebar.css";
import { useContext } from "react";
import { AppContext } from "../../store/AppContext";
import axios from "axios";

const Sidebar = () => {
  const { admin, setAdmin } = useContext(AppContext);
  const navigate = useNavigate();

  const handleLogout = async () => {
    try {
      await axios.post(`/api/depotAdmin/logout/${admin.depotId}`);

      setAdmin(null);
      navigate("/bus-booking");
    } catch (error) {
      console.error("Logout failed:", error);
      alert("Failed to logout. Please try again.");
    }
  };

  return (
    <div className="sidebar">
      <div className="top">
        <h2 className="sidebar-title">GSRTC Admin</h2>
        <nav className="sidebar-nav">
          <NavLink to="/admin/dashboard" className="sidebar-link">
            Dashboard
          </NavLink>
          <NavLink to="/admin/bus-management" className="sidebar-link">
            Bus Management
          </NavLink>
          <NavLink to="/admin/route-management" className="sidebar-link">
            Route Management
          </NavLink>
          <NavLink to="/admin/schedule-management" className="sidebar-link">
            Schedule Management
          </NavLink>
          <NavLink to="/admin/booking-management" className="sidebar-link">
            Booking Management
          </NavLink>
        </nav>
      </div>
      <div className="sidebar-footer">
        <button className="logout-btn" onClick={handleLogout}>Logout</button>
      </div>
    </div>
  );
};

export default Sidebar;
