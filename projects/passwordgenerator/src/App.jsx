import { useState, useCallback, useEffect, useRef } from "react";
import "./App.css";

function App() {
  const [length, setLength] = useState(8); // default password length
  const [numberAllowed, setNumberAllowed] = useState(false); // include numbers
  const [charAllowed, setCharAllowed] = useState(false); // include characters
  const [password, setPassword] = useState(""); // store generated password

  const passwordRef = useRef(null); // reference to the password input

  const generatePassword = useCallback(() => {
    let pass = "";
    let str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    if (numberAllowed) str += "0123456789";
    if (charAllowed) str += "!@#$%&*()_+~`|}{[]:;?><,./";
    for (let i = 1; i <= length; i++) {
      let char = Math.floor(Math.random() * str.length + 1);
      pass += str.charAt(char);
    }
    setPassword(pass);
  }, [length, numberAllowed, charAllowed]);

useEffect(() => {
  generatePassword();
}, [length, numberAllowed, charAllowed, generatePassword]);
  
  const copyPasswordToClipboard = () => {
    navigator.clipboard.writeText(password); // Copy password to clipboard
    passwordRef.current?.select(); // Select the copied text
  };
  
  return (
    <div
      className="flex flex-col justify-center border border-gray-800 w-[490px] h-[300px] mx-auto shadow-lg rounded-lg px-4 py-3 my-8"
      style={{
        backgroundColor: "rgb(15, 25, 50)", // Dark blue background
        color: "rgb(200, 215, 255)", // Light text for readability
      }}
    >
      <h1 className="text-center mb-4 text-[25px] text-white">
        Password Generator
      </h1>

      <div className="flex rounded-lg overflow-hidden mb-4 shadow-md">
        <input
          type="text"
          value={password}
          className="outline-none w-full py-1 px-3 text-gray-200 bg-gray-800"
          placeholder="Password"
          readOnly
          ref={passwordRef} // Reference to the password input
        />
        <button
          onClick={copyPasswordToClipboard}
          className="outline-none w-[100px] py-2 px-4 text-white hover:bg-blue-700"
          style={{ backgroundColor: "rgb(29, 75, 192)" }} // Button blue
        >
          Copy
        </button>
      </div>

      <div className="flex flex-row text-sm gap-x-10">
        {/* Length slider */}
        <div className="flex items-center gap-x-1">
          <input
            type="range"
            min={6}
            max={20}
            value={length}
            className="cursor-pointer appearance-none"
            onChange={(e) => setLength(e.target.value)}
            name=""
            id=""
            style={{
              background: "rgb(29, 75, 192)", // Track blue
              height: "6px",
              borderRadius: "4px",
              outline: "none",
            }}
          />
          <label htmlFor="length" className="text-gray-200">
            Length: {length}
          </label>

          {/* Custom range slider styling */}
          <style jsx>
            {`
              input[type="range"]::-webkit-slider-thumb {
                -webkit-appearance: none;
                appearance: none;
                width: 16px;
                height: 16px;
                background: rgb(200, 215, 255); /* Light blue thumb */
                border-radius: 50%;
                cursor: pointer;
              }

              input[type="range"]:focus::-webkit-slider-thumb {
                box-shadow: 0 0 0 3px rgba(29, 75, 192, 0.5); /* Blue glow */
              }
            `}
          </style>
        </div>

        {/* Checkbox for Numbers */}
        <div className="flex items-center gap-x-1">
          <input
            type="checkbox"
            defaultChecked={numberAllowed}
            onChange={() => setNumberAllowed((prev) => !prev)}
            className="cursor-pointer accent-blue-700"
            name=""
            id="number"
          />
          <label htmlFor="number" className="text-gray-200">
            Numbers
          </label>
        </div>

        {/* Checkbox for Characters */}
        <div className="flex items-center gap-x-1">
          <input
            type="checkbox"
            defaultChecked={charAllowed}
            onChange={() => setCharAllowed((prev) => !prev)}
            className="cursor-pointer accent-blue-700"
            name=""
            id="charInput"
          />
          <label htmlFor="charInput" className="text-gray-200">
            Characters
          </label>
        </div>
      </div>
    </div>
  );
}

export default App;
