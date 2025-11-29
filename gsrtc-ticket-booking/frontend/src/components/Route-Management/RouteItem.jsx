import { useState } from "react";
import StopManagement from "./Stop-Management/StopManagement";

const RouteItem = ({ route, deleteRoute, updateRoute }) => {
  const [showStops, setShowStops] = useState(false);
  console.log(route);

  return (
    <div className="route-item">
      <div className="route-info">
        <div className="route-info-wrapper">
          <p>{route.routeName}</p>
          <p>{route.classType}</p>
          <div className="route-actions">
            <button onClick={() => setShowStops(!showStops)}>
              {showStops ? "Hide Stops" : "Manage Stops"}
            </button>
            <button onClick={() => deleteRoute(route.routeId)}>Delete</button>
          </div>
        </div>

      </div>

      {showStops && (
        <StopManagement route={route} updateRoute={updateRoute} />
      )}
    </div>
  );
};

export default RouteItem;
