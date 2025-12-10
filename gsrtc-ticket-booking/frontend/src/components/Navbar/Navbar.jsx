import { NavLink, useNavigate } from "react-router-dom";
import "../styles/Navbar.css";
import { useEffect, useRef, useState } from "react";

const Navbar = ({ user, admin }) => {
  const navigate = useNavigate();
  const [showMenu, setShowMenu] = useState(false);
  const menuRef = useRef(null);

  useEffect(() => {
    const handleClickOutside = (e) => {
      if (menuRef.current && !menuRef.current.contains(e.target)) {
        setShowMenu(false);
      }
    };
    document.addEventListener("mousedown", handleClickOutside);
    return () => document.removeEventListener("mousedown", handleClickOutside);
  }, []);

  const handleLogout = () => {
    localStorage.removeItem("user");
    localStorage.removeItem("admin");
    navigate("/");
    window.location.reload();
  };

  return (
    <nav className="navbarr">
      <div className="upper-nav">
        <div
          className="navbar-logo"
          onClick={() => navigate("/")}
        >
          <h2>GSRTC</h2>
          <p>Gujarat State Road Transport Corporation</p>
        </div>

        <div className="navbar-actions">
          {!user && !admin ? (
            <>
              <button onClick={() => navigate("/admin-login")} className="btn btn-outline-primary">
                Admin Login
              </button>
              <button onClick={() => navigate("/signin")} className="btn btn-primary">
                Sign In
              </button>
              <button onClick={() => navigate("/signup")} className="btn btn-primary">
                Sign Up
              </button>
            </>
          ) : (
            <>
              <div className="avatar-container" ref={menuRef}>
                <div className="avatar" onClick={() => setShowMenu(!showMenu)}></div>

                {showMenu && (
                  <div className="user-dropdown">
                    <p className="user-name">{user?.name || admin?.name}</p>
                    <p className="user-email">{user?.email || admin?.email}</p>
                    <hr />
                    <button onClick={handleLogout} className="logout-btn">
                      Logout
                    </button>
                  </div>
                )}
              </div>
            </>
          )}
        </div>
      </div>

      {!user && !admin ? (
        <></>
      ) : (
        <div className="lower-nav">
          <ul className="navbar-links">
            <li>
              <NavLink to="/bus-booking" className={({ isActive }) => (isActive ? "active" : "")}>
                Home
              </NavLink>
            </li>
            <li>
              <NavLink to="/educational-booking">Educational Booking</NavLink>
            </li>
            <li>
              <NavLink to="/your-bookings">Your Bookings</NavLink>
            </li>
            <li>
              <NavLink to="/booking-cancellation">Booking Cancellation</NavLink>
            </li>
            <li>
              <NavLink to="/contact-us">Contact Us</NavLink>
            </li>
            <li>
              <NavLink to="/about-us">About Us</NavLink>
            </li>
          </ul>
        </div>
      )}
    </nav>
  );
};

export default Navbar;
