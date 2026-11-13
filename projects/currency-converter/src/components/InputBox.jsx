import React, { useId } from "react";

function InputBox({
  label,
  amount,
  onAmountChange,
  onCurrencyChange,
  currencyOptions = [],
  selectedCurrency = "usd",
  amountDisabled = false,
  currencyDisabled = false,
  other = "", // Prop to apply custom classes to the input
}) {
  return (
    <div className={`bg-gray-800 p-3 rounded-lg text-md text-white`}>
      <div className="flex justify-between">
        <div className="flex flex-col item-start">
          <label id="label" className="text-white mb-2 inline-block">
            {label}
          </label>
          <input
            id="input"
            type="number"
            step="0.01" // Set step to 0.01 to increase by 0.01
            // min="0.01" // Minimum value
            className={`${
              other ? other : ""
            } rounded-lg h-9 py-1 px-2 outline-none bg-blue-900 text-white border border-gray-600 rounded-md hover:bg-blue-700`} // Apply "other" class here
            placeholder="Amount"
            disabled={amountDisabled}
            value={amount}
            onChange={(e) =>
              onAmountChange && onAmountChange(Number(e.target.value))
            }
            autoComplete="off" // Disable autocomplete
          />
        </div>

        <div className="flex flex-wrap justify-end text-right">
          <p className="text-white mb-2 w-full">Currency Type</p>
          <select
            id="select"
            className="rounded-lg px-1 py-1 cursor-pointer outline-none text-white bg-blue-900 border border-gray-600 rounded-md hover:bg-blue-700"
            value={selectedCurrency}
            onChange={(e) =>
              onCurrencyChange && onCurrencyChange(e.target.value)
            }
            disabled={currencyDisabled}
          >
            {currencyOptions.map((currency) => (
              <option key={currency} value={currency}>
                {currency.toUpperCase()}
              </option> // Ensure currency is uppercase
            ))}
          </select>
        </div>
      </div>
    </div>
  );
}

export default InputBox;
