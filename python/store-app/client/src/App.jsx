// Importing necessary hooks and dependencies from React and external libraries
import { useState } from "react"; // useState hook for managing state
import "./App.css"; // Importing CSS file for styling
import { useEffect } from "react"; // useEffect hook for handling side effects like API calls
import axios from "axios"; // Importing axios for making HTTP requests

function App() {
  // State variables to store data from API responses
  const [user_array, setUser] = useState([]); // Stores list of users
  const [category_array, setCategory] = useState([]); // Stores list of categories
  const [item_array, setItem] = useState([]); // Stores list of items

  // Function to fetch data from API asynchronously
  const fetchAPI = async () => {
    try {
      // Sending GET requests to fetch data from the backend
      const users = await axios.get("http://127.0.0.1:8000/users"); // Fetch users
      const items = await axios.get("http://127.0.0.1:8000/items"); // Fetch items
      const categories = await axios.get("http://127.0.0.1:8000/categories"); // Fetch categories

      // Logging fetched data for debugging purposes
      console.log("Users:", users.data);
      console.log("Items:", items.data);
      console.log("Categories:", categories.data);

      // Updating state variables with fetched data
      setUser(users.data);
      setItem(items.data);
      setCategory(categories.data);

      console.log("Data fetched successfully"); // Log success message
    } catch (error) {
      console.error("Error fetching data:", error); // Log errors if the request fails
    }
  };

  // useEffect hook to call fetchAPI when the component mounts
  useEffect(() => {
    fetchAPI(); // Fetch data when component loads
  }, []); // Empty dependency array ensures it runs only once

  return (
    <>
      <div className="p-4">
        {" "}
        {/* Main container with padding */}
        {/* Users Section */}
        <div className="mb-1">
          {" "}
          {/* Adds bottom margin */}
          <h2 className="text-lg font-semibold">Users:</h2>{" "}
          {/* Section heading */}
          {user_array.length > 0 ? (
            user_array.map((user) => (
              <div
                key={user.id} // Unique key for each user
                className="border rounded-lg shadow-sm bg-white m-0" // Styling
              >
                <p className="text-gray-600 m-0">
                  <strong>Name: </strong>
                  {user.first_name}, <strong>Email: </strong>
                  {user.email}
                </p>
              </div>
            ))
          ) : (
            <p className="text-gray-500">Loading...</p> // Display message while loading
          )}
        </div>
        {/* Categories Section */}
        <div className="mb-4">
          {" "}
          {/* Adds bottom margin */}
          <h2 className="text-lg font-semibold">Categories:</h2>{" "}
          {/* Section heading */}
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
            {" "}
            {/* Responsive grid layout */}
            {category_array.length > 0 ? (
              category_array.map((category) => (
                <div
                  key={category.id} // Unique key for each category
                  className="p-4 border rounded-lg shadow-sm bg-white" // Styling
                >
                  <p className="text-gray-600">{category.name}</p>{" "}
                  {/* Display category name */}
                </div>
              ))
            ) : (
              <p className="text-gray-500">No categories found or loading...</p> // Display message while loading
            )}
          </div>
        </div>
        {/* Items Section */}
        <div>
          <h2 className="text-lg font-semibold">Items:</h2>{" "}
          {/* Section heading */}
          {Array.isArray(item_array) && item_array.length > 0 ? (
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
              {" "}
              {/* Responsive grid layout */}
              {item_array.map((item) => (
                <div
                  key={item.id} // Unique key for each item
                  className="p-4 border rounded-lg shadow-sm bg-white" // Styling
                >
                  <p className="text-gray-600">
                    {item.name} : {item.description} : ${item.price}{" "}
                    {/* Display item details */}
                  </p>
                </div>
              ))}
            </div>
          ) : (
            <p className="text-gray-500">No items found or loading...</p> // Display message while loading
          )}
        </div>
      </div>
    </>
  );
}

export default App; // Export App component for use in other files
