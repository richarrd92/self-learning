import { useState } from "react";
import "./App.css";
import useCurrencyInfo from "./hooks/useCurrencyInfo";
import { InputBox } from "./components/index.js";

function App() {
  const [amount, setAmount] = useState();
  const [from, setFrom] = useState("usd");
  const [to, setTo] = useState("usd");
  const [convertedAmount, setConvertedAmount] = useState();

  const currencyInfo = useCurrencyInfo(from);
  const options = Object.keys(currencyInfo);

  const swap = () => {
    if (!from || !to) {
      console.error("Invalid currencies: Ensure both 'from' and 'to' are set.");
      return false;
    }

    if (from === to) {
      console.warn("Swapping the same currency is not allowed.");
      return false;
    }

    setFrom(to);
    setTo(from);
    setConvertedAmount(""); // Clear the converted amount
    setAmount(""); // Clear the amount
    return true;
  };
  const convert = () => {
    setConvertedAmount((amount * currencyInfo[to]).toFixed(2));
  };

  return (
    <div
      className="min-w-[700px] min-h-[400px] w-full h-screen absolute flex justify-center items-center bg-cover bg-no-repeat text-white bg-gray-900 font-medium"
      // style={{
      //   backgroundImage: `url(https://images.pexels.com/photos/534216/pexels-photo-534216.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1)`,
      // }}
    >
      <div className="w-full absolute">
        <div className="w-[700px] h-[400px] flex justify-center items-center flex-col mx-auto border border-gray-600 rounded-lg p-5  bg-gray-800 text-white shadow-black shadow-2xl">
          <div>
            <h1 className="text-4xl text-white mb-5 font-bold">
              Currency Converter
            </h1>
          </div>

          <form
            onSubmit={(e) => {
              e.preventDefault(); // Prevent form submission
              convert();
            }}
          >
            <div className="w-[550px] mb-1 text-white border border-gray-600 rounded-md ">
              <InputBox
                label="From:"
                amount={amount}
                currencyOptions={options}
                onCurrencyChange={(currency) => setFrom(currency)}
                onAmountChange={(amount) => setAmount(amount)}
                selectedCurrency={from}
              />
            </div>
            <div className="relative w-full h-0.5 text-white">
              <button
                className={`absolute left-1/2 -translate-y-1/2 -translate-x-1/2 rounded-md border px-2 py-0.5 font-bold outline-none text-white ${
                  from === to
                    ? "cursor-not-allowed bg-gray-900 hover:bg-gray-800 border-gray-600 text-gray-300" // Disabled styles (red background for error)
                    : "bg-blue-900 hover:bg-blue-700 border-gray-600" // Enabled styles
                }`}
                onClick={swap}
                disabled={from === to} // Disable button if 'from' and 'to' are the same
                title={
                  from === to ? "Swapping the same currency is not allowed" : ""
                } // Tooltip explaining why the button is disabled
              >
                ↓ Swap ↑
              </button>
            </div>

            <div className="w-[550px] mb-1 text-white border border-gray-600 rounded-md">
              <InputBox
                label="To: "
                amount={convertedAmount}
                currencyOptions={options}
                onCurrencyChange={(currency) => setTo(currency)}
                onAmountChange={(amount) => setAmount(amount)}
                selectedCurrency={to}
                other="pointer-events-none" // Disable the input
              />
            </div>
            <button
              type="submit"
              className={`w-[550px] px-4 py-3 mt-1 border rounded-lg font-bold bg-blue-900 text-white border-gray-600 hover:bg-blue-700 ${
                !(
                  (amount && parseFloat(amount) >= 0.01) ||
                  (convertedAmount && parseFloat(convertedAmount) >= 0.01)
                )
                  ? "cursor-not-allowed bg-gray-900 hover:bg-gray-800" // Disabled styles if either amount or convertedAmount is invalid
                  : "cursor-pointer" // Enabled styles when both are valid
              }`}
              disabled={
                !(
                  (amount && parseFloat(amount) >= 0.01) ||
                  (convertedAmount && parseFloat(convertedAmount) >= 0.01)
                )
              } // Disable button if neither amount nor convertedAmount is valid
              title={
                !(
                  (amount && parseFloat(amount) >= 0.01) ||
                  (convertedAmount && parseFloat(convertedAmount) >= 0.01)
                )
                  ? "Converting amount less than 0.01 is not allowed"
                  : ""
              } // Tooltip explaining why the button is disabled
            >
              {!(
                (amount && parseFloat(amount) >= 0.01) ||
                (convertedAmount && parseFloat(convertedAmount) >= 0.01)
              )
                ? "Enter a valid amount"
                : "Convert"}{" "}
              {/* Change button text based on validity of amount and convertedAmount */}
            </button>
          </form>
        </div>
      </div>
    </div>
  );
}

export default App;
