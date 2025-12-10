import { useContext, useEffect, useState } from "react";
import RouteForm from "./RouteForm";
import RouteList from "./RouteList";
import RouteFilters from "./RouteFilters";
import "../styles/RouteManagement.css";
import { AppContext } from "../../store/AppContext";
import axios from "axios";

const RouteManagement = () => {
  const { routes, setRoutes } = useContext(AppContext);
  const [searchTerm, setSearchTerm] = useState("");
  const [filterClass, setFilterClass] = useState("");

  const addRoute = async (route, duration) => {
    await axios.post("/api/busRoute/addRoute", route, { params: { duration } });

    setRoutes([...routes, route]);
  };

  const deleteRoute = (id) => {
    setRoutes(routes.filter((r) => r.id !== id));
  };

  const updateRoute = (updatedRoute) => {
    setRoutes(routes.map((r) => (r.id === updatedRoute.id ? updatedRoute : r)));
  };

  const filteredRoutes = routes.filter((r) => {
    const matchesSearch = r.routeName.toLowerCase().includes(searchTerm.toLowerCase());
    const matchesClass = filterClass ? r.classType === filterClass : true;
    return matchesSearch && matchesClass;
  });

  return (
    <div className="route-management">
      <div className="route-header">
        <h1>Route Management System</h1>
        <RouteForm addRoute={addRoute} />
      </div>
      <RouteFilters setSearchTerm={setSearchTerm} setFilterClass={setFilterClass} />
      <RouteList
        routes={filteredRoutes}
        setRoutes={setRoutes}
        deleteRoute={deleteRoute}
        updateRoute={updateRoute}
      />
    </div>
  );
};

export default RouteManagement;
