# React Application Overview

## Project Description
This React application fetches and displays data from an API consisting of users, categories, and items. It utilizes Axios for making HTTP requests and React hooks like `useState` and `useEffect` for state management and lifecycle handling.

## Application Flow
1. **Import Dependencies**
   - `useState` and `useEffect` from React for state and lifecycle management.
   - `axios` for making API requests.
   - `App.css` for styling.

2. **State Initialization**
   - Three state variables are declared:
     - `user_array` for storing user data.
     - `category_array` for storing category data.
     - `item_array` for storing item data.

3. **Fetching API Data**
   - The `fetchAPI` function:
     - Uses `axios.get` to fetch users, items, and categories from the backend API.
     - Logs the fetched data for debugging purposes.
     - Updates the state with the retrieved data.
     - Implements error handling with `try-catch`.

4. **Component Lifecycle**
   - `useEffect` ensures `fetchAPI` runs once when the component mounts.

5. **Rendering UI**
   - Displays fetched data in structured sections:
     - **Users Section:** Displays each user's name and email.
     - **Categories Section:** Lists categories in a responsive grid.
     - **Items Section:** Shows item details (name, description, and price) in a grid format.
   - Provides a loading message if data is unavailable.

## Logic Behind Each Line
- Every function and component is structured to ensure a clean and maintainable flow.
- Conditional rendering is used to handle loading states.
- Each mapped data array dynamically creates UI elements.


