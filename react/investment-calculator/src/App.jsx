import { useState } from "react";
import Header from "./components/Header.jsx";
import UserInput from "./components/UserInput.jsx";
import Results from "./components/Results.jsx";

function App() {
  const [userInput, setUserInput] = useState({
    initialInvestment: 1000,
    annualInvestment: 1200,
    expectedReturn: 6,
    duration: 10,
  });

  function handleInputChange(inputIdentifier, newValue) {
    setUserInput((oldUserInput) => {
      return {
        ...oldUserInput,
        [inputIdentifier]: +newValue,
      };
    });
  }

  // validation
  const validInput = userInput.duration > 0;

  return (
    <>
      <Header />
      <UserInput onChange={handleInputChange} userInput={userInput} />
      {!validInput && <p className="center">Please enter a valid input.</p>}
      {validInput && <Results userInput={userInput} />}
    </>
  );
}

export default App;
