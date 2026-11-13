import React, { useContext } from "react";
import UserContext from "../context/UserContext";
function Profile() {
  const { user } = useContext(UserContext);
  if (!user) {
    return <div>Please login</div>;
  }
  return (
    <div>
      <h1>Profile: {user.username}</h1>
    </div>
  );
}

export default Profile;
