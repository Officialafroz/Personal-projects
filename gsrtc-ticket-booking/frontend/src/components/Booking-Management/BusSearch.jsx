const BusSearch = ({ setSearchTerm }) => {
  return (
    <div className="bus-search">
      <input
        type="text"
        placeholder="Search by route or bus number..."
        onChange={(e) => setSearchTerm(e.target.value)}
      />
    </div>
  );
};

export default BusSearch;
