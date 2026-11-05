import { useState, useEffect } from "react"; // useState for state management, useEffect to trigger side effects
import "./App.css"; // importing CSS for styling

import axios from "axios"; // Axios is used for making HTTP requests

function App() {
  // State variables to store fetched data
  const [productArray, setProduct] = useState([]);
  const [studentArray, setStudent] = useState([]);

  // Function to fetch product and student data from the API
  const fetchAPI = async () => {
    try {
      const products = await axios.get("http://localhost:8080/api/products");
      const students = await axios.get("http://localhost:8080/api/students");

      // Updating state with fetched data
      setProduct(products.data.product);
      setStudent(students.data.student);

      // Logging the data for debugging
      console.log(products.data.product);
      console.log(students.data.student);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  // useEffect to call fetchAPI once when the component mounts
  useEffect(() => {
    fetchAPI();
  }, []);

  return (
    <>
      {/* Displaying products from the fetched data */}
      <p>
        {productArray.map((product, index) => (
          <span key={index}>{product}</span>
        ))}
      </p>

      {/* Displaying students whose names include "Alice Johnson" */}
      <p>
        {studentArray.map((student, index) =>
          student?.name &&
          student.name
            .toLowerCase()
            .split(" ")
            .some((word) => word === "Alice Johnson") ? (
            <span key={index}>{student.name}</span>
          ) : null
        )}
      </p>
    </>
  );
}

export default App;
