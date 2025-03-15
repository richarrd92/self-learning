import React, { useState } from "react";

function Admin() {
  const [name, setName] = useState("no user");

  const users = [
    { name: "Alice", age: 25, email: "alice@example.com", role: "admin" },
    { name: "Bob", age: 30, email: "bob@example.com", role: "user" },
    { name: "Charlie", age: 22, email: "charlie@example.com", role: "user" },
    { name: "David", age: 28, email: "david@example.com", role: "admin" },
  ];

  return (
    <div>
      <h1>Admin</h1>
      <p>You are an admin: {name}</p>
      <input type="text" id="input-text" />
      <button
        onClick={() => {
          const input = document.getElementById("input-text");
          if (input.value) {
            setName(input.value);
            input.value = "";
          }
        }}
      >
        Login
      </button>
      <h2>Users List</h2>
      <ul>
        {users.map(({ name, age, email, role }) => (
          <li key={name}>
            {name} - {age} years old - {email} - {role}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default Admin;
