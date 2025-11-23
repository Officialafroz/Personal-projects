import { createContext, useState } from "react";

export const AppContext = createContext();

export const AppProvider = ({ children }) => {
  const [buses, setBuses] = useState([]);
  const [routes, setRoutes] = useState([]);
  const [schedules, setSchedules] = useState([]);
  const [user, setUser] = useState(null);
  const [admin, setAdmin] = useState(null);

  return (
    <AppContext.Provider
      value={{
        buses, setBuses,
        routes, setRoutes,
        schedules, setSchedules,
        user, setUser,
        admin, setAdmin,
      }}
    >
      {children}
    </AppContext.Provider>
  );
};
