import { useState, useEffect } from "react"; // will need useEffect
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import "./App.css";

import axios from "axios"; // used to fetch data from api

function App() {
  const [productArray, setProduct] = useState([]);
  const [studentArray, setStudent] = useState([]);

  const fetchAPI = async () => {
    const products = await axios.get("http://localhost:8080/api/products");
    const students = await axios.get("http://localhost:8080/api/students");
    setProduct(products.data.product);
    setStudent(students.data.student);
    console.log(products.data.product);
    console.log(students.data.student);
  };

  useEffect(() => {
    fetchAPI();
  }, []);

  return (
    <>
      <p>
        {productArray.map((product, index) => (
          <>
            {" "}
            <span key={index}>{product}</span>
            <br />
          </>
        ))}
      </p>

      <p>
        {studentArray.map(
          (student, index) =>
            student?.name &&
            student.name
              .toLowerCase()
              .split(" ")
              .some((word) => word === "Alice Johnson") && (
              <React.Fragment key={index}>
                <span>{student.name}</span>
                <br />
              </React.Fragment>
            )
        )}
      </p>
    </>
  );
}

export default App;
