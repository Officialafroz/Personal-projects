// UserProfile.jsx
import "./styles/Profile.css"

const UserProfile = ({ user }) => {
  if (!user) return <p>Please log in to view your profile.</p>;

  return (
    <div className="profile-container">
      <h2>User Profile</h2>
      <p><b>Name:</b> {user.name}</p>
      <p><b>Email:</b> {user.email}</p>
      <p><b>Address:</b> {user.address}</p>
      <p><b>Phone:</b> {user.phone}</p>
    </div>
  );
};  

export default UserProfile;
