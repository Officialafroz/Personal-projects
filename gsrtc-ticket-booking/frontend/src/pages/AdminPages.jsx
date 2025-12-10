import Sidebar from '../components/Depot-Admin/Sidebar.jsx'
import Dashboard from '../components/Dashboard/Dashboard.jsx'
import BusManagement from '../components/Bus-Management/BusManagement.jsx'
import RouteManagement from '../components/Route-Management/RouteManagement.jsx'
import ScheduleManagement from '../components/Schedule-Management/ScheduleManagement.jsx'
import BookingManagement from '../components/Booking-Management/BookingManagement.jsx'
import { Route, Routes } from 'react-router-dom'


const AdminPages = () => {
  return (
    <div className="admin-layout">
      <Sidebar />
      <div className="admin-content">
        <Routes>
          <Route path="dashboard" element={<Dashboard />} />
          <Route path="bus-management" element={<BusManagement />} />
          <Route path="route-management" element={<RouteManagement />} />
          <Route path="schedule-management" element={<ScheduleManagement />} />
          <Route path="booking-management" element={<BookingManagement />} />
        </Routes>
      </div>
    </div>
  )
}

export default AdminPages