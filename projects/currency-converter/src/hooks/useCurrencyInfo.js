import { use } from "react";
import { useEffect, useState } from "react";
// // import { useState, useEffect } from "react";

// const useCurrencyInfo = () => {
//   const [currencyData, setCurrencyData] = useState(null);
//   const [error, setError] = useState(null);
//   const API_KEY = "ed2429034fe2b0d65674f1b299e176db"; // Replace with your actual API key

//   useEffect(() => {
//     const fetchCurrencyData = async () => {
//       const url = `https://api.currencylayer.com/live?access_key=${API_KEY}`;

//       try {
//         const response = await fetch(url);

//         if (!response.ok) {
//           throw new Error(`HTTP error! status: ${response.status}`);
//         }

//         const data = await response.json();

//         if (!data.success) {
//           throw new Error(`API error: ${data.error?.info || "Unknown error"}`);
//         }

//         setCurrencyData(data.quotes);
//       } catch (err) {
//         console.error("Failed to fetch data:", err);
//         setError(err.message);
//       }
//     };

//     fetchCurrencyData();
//   }, []);

//   return { currencyData, error };
// };

// export default useCurrencyInfo;

// function useCurrencyInfo(currency) {
//   const [data, setData] = useState({});
//   const date = "latest"; // Use 'latest' or a specific date in YYYY-MM-DD format

//   useEffect(() => {
//     const endpoint = `currencies/${currency.toLowerCase()}`; // Ensure currency is lowercase
//     const url = `https://cdn.jsdelivr.net/npm/@fawazahmed0/currency-api@${date}/${endpoint}.json`;

//     fetch(url)
//       .then((res) => {
//         if (!res.ok) {
//           throw new Error(`HTTP error! status: ${res.status}`);
//         }
//         return res.json();
//       })
//       .then((data) => {
//         if (data) {
//           setData(data);
//         } else {
//           console.error("Invalid response structure:", data);
//           setData({});
//         }
//       })
//       .catch((err) => console.error("Failed to fetch data:", err));
//   }, [currency, date]);

//   console.log(data);
//   return data;
// }

// export default useCurrencyInfo;

// import { use } from "react";
// import { useEffect, useState } from "react";

// function useCurrencyInfo(currency) {
//   const [data, setData] = useState({});
//   const apiVersion = "1"; // Specify the API version if required
//   const date = "latest"; // Use 'latest' for daily updates, or a specific date in YYYY-MM-DD format

//   useEffect(() => {
//     const endpoint = `currencies/${currency}`;
//     const url = `https://cdn.jsdelivr.net/npm/@fawazahmed0/currency-api@${date}/${apiVersion}/${endpoint}.json`;

//     fetch(url)
//       .then((res) => {
//         if (!res.ok) {
//           throw new Error(`HTTP error! status: ${res.status}`);
//         }
//         return res.json();
//       })
//       .then((data) => {
//         if (data[currency]) {
//           setData(data[currency]);
//         } else {
//           console.error(`Invalid response structure:`, data);
//           setData({});
//         }
//       })
//       .catch((err) => console.error("Failed to fetch data:", err));
//   }, [currency, apiVersion, date]);

//   console.log(data);
//   return data;
// }

// export default useCurrencyInfo;

function useCurrencyInfo(currency) {
  const [data, setData] = useState({});
  useEffect(() => {
    fetch(
      `https://cdn.jsdelivr.net/npm/@fawazahmed0/currency-api@latest/v1/currencies/${currency}.json`
      //   `https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/${currency}.json`
    )
      .then((res) => res.json())
      .then((res) => setData(res[currency]))
      .catch((err) => console.error("Failed to fetch data:", err));
  }, [currency]);
//   console.log(typeof data);
  return data;
}

export default useCurrencyInfo;
