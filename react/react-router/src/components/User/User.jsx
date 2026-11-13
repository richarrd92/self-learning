import React from "react";
import { useParams } from "react-router-dom";

function User() {
  const { userId } = useParams();
  return (
    <div className="bg-orange-700 text-black text-3xl flex justify-center items-center text-center py-5">
      User: {userId}
    </div>
  );
}

export default User;
