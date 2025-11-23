import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import { AppProvider, AppContext } from "./store/AppContext";
import { useContext } from "react";

import Navbar from "./components/Navbar/Navbar";
import Home from "./pages/Home";
import UserLogin from "./components/UserLogin";
import SignUp from "./components/SignUp";
import UserProfile from "./components/UserProfile";
import YourBookings from "./components/EndUser/YourBookings";
import BookingCancellation from "./components/EndUser/BookingCancellation";
import Sidebar from "./components/Depot-Admin/Sidebar";
import Dashboard from "./components/Dashboard/Dashboard";
import BusManagement from "./components/Bus-Management/BusManagement";
import BookingManagement from "./components/Booking-Management/BookingManagement";
import AdminLogin from "./components/AdminLogin";
import RouteManagement from "./components/Route-Management/RouteManagement";
import ScheduleManagement from "./components/Schedule-Management/ScheduleManagement";
import EducationalTrip from "./components/Booking/EducationalTrip";
import ContactUs from "./components/EndUser/ContactUs";
import AboutUs from "./components/EndUser/AboutUs";

const AppContent = () => {
  const { user, setUser, admin, setAdmin } = useContext(AppContext);

  return (
    <>
      {admin ? (
        // -------- Admin Layout --------
        <div className="admin-layout">
          <Sidebar />
          <div className="admin-content">
            <Routes>
              <Route path="/admin/dashboard" element={<Dashboard />} />
              <Route path="/admin/bus-management" element={<BusManagement />} />
              <Route path="/admin/route-management" element={<RouteManagement />} />
              <Route path="/admin/schedule-management" element={<ScheduleManagement />} />
              <Route path="/admin/booking-management" element={<BookingManagement />} />
            </Routes>
          </div>
        </div>
      ) : (
        // -------- User Layout --------
        <>
          <Navbar user={user} admin={admin} />
          <Routes>
            <Route path="/" element={<Navigate to="/bus-booking" />} />
            <Route path="/bus-booking" element={<Home />} />
            <Route path="/your-bookings" element={<YourBookings />} />
            <Route path="/educational-booking" element={<EducationalTrip />} />
            <Route path="/booking-cancellation" element={<BookingCancellation />} />
            <Route path="/contact-us" element={<ContactUs />} />
            <Route path="/about-us" element={<AboutUs />} />
            <Route path="/signin" element={<UserLogin setUser={setUser} />} />
            <Route path="/signup" element={<SignUp setUser={setUser} />} />
            <Route path="/admin-login" element={<AdminLogin setAdmin={setAdmin} />} />
            <Route path="/profile" element={<UserProfile user={user} />} />
          </Routes>
        </>
      )}
    </>
  );
};

const App = () => {
  return (
    <Router>
      <AppProvider>
        <AppContent />
      </AppProvider>
    </Router>
  );
};

export default App;
