import { useContext } from "react";
import "../styles/Dashboard.css";
import { AppContext } from "../../store/AppContext";

const DashboardHeader = () => {
  const {admin} = useContext(AppContext)

  return (
    <div className="dashboard-header">
      <h1>Dashboard</h1>
      <p>Welcome back, {admin.name.toUpperCase()}</p>
    </div>
  );
};

export default DashboardHeader;
