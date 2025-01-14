import { useState, useEffect } from "react";
import "./App.css";

function App() {
  const [counter, setCounter] = useState(0);
  const [isRunning, setIsRunning] = useState(false); // Track if counter should run

  // const addValue = () => {
  //   setCounter(counter + 1);
  // };

  // const removeValue = () => {
  //   if (counter <= 0) {
  //     console.log("Counter value is 0");
  //     return;
  //   }
  //   setCounter(counter - 1);
  // };

  const restValue = () => {
    setCounter(0);
  };

  const runValue = () => {
    setIsRunning(true); // Start the counter
  };

  const stopValue = () => {
    setIsRunning(false); // stop the counter
  };

  useEffect(() => {
    if (isRunning) {
      const interval = setInterval(() => {
        setCounter((prevCounter) => prevCounter + 1);
      }, 1000);

      // Cleanup: stop the interval when the component unmounts or isRunning changes
      return () => clearInterval(interval);
    }
  }, [isRunning]); // Run effect only when `isRunning` changes

  return (
    <>
      <h2>Timer: {" "}{counter}</h2>
      {/* <h2> {counter} seconds</h2> */}
      {/* <button onClick={addValue}>Add</button>{" "}
      <button onClick={removeValue}>Remove</button>{" "} */}
      <button onClick={runValue}>Run</button>{" "}
      <button onClick={stopValue}>Stop</button>{" "}
      <button onClick={restValue}>Reset</button>
    </>
  );
}

export default App;
