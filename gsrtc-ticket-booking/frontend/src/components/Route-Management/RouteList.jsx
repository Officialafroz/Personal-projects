import { useContext, useEffect } from "react";
import RouteItem from "./RouteItem";
import axios from "axios";
import { AppContext } from "../../store/AppContext";

const RouteList = ({ routes, setRoutes, deleteRoute, updateRoute }) => {
  const { admin } = useContext(AppContext);

  useEffect(() => {
    axios.get(`/api/busRoute/depotRoutes/${admin.depotId}`)
      .then(response => {
        setRoutes(response.data)
      })
      .catch(err => {
        console.log(err);
      })
  }, []);

  if (routes.length === 0) return <p className="route-filter-msg">No routes.</p>;



  return (
    <div className="route-list">
      {routes.map((route, index) => (
        <RouteItem
          key={index}
          route={route}
          deleteRoute={deleteRoute}
          updateRoute={updateRoute}
        />
      ))}
    </div>
  );
};

export default RouteList;
