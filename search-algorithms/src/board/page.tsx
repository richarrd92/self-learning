"use client";
import React, { useState, useEffect } from "react";
import breadthFirstSearch from "@/algorithms/breadth-first"; // Import the breadthFirstSearch function

// Board component
function Board() {
  const maxDimension = 70; // Maximum dimension for the grid
  const [dimension, setDimension] = useState<number>(70); // Initial dimension

  // Functions to increase and decrease dimension
  const handleIncreaseDimension = () => {
    setDimension((prev) => prev + 5);
    console.log(dimension);
  };

  const handleDecreaseDimension = () => {
    setDimension((prev) => prev - 5);
    console.log(dimension);
  };

  const [cellSize, setCellSize] = useState<number>(14); // Initial cell size

  // Functions to increase and decrease cell size
  const handleIncreaseCellSize = () => {
    setCellSize((prev) => prev + 2);
    console.log(cellSize);
  };

  const handleDecreaseCellSize = () => {
    setCellSize((prev) => prev - 2);
    console.log(cellSize);
  };

  const [cellShape, setCellShape] = useState<string>(""); // Initial cell shape

  // Function to toggle cell shape
  const handleSetCellShape = () => {
    setCellShape((prev) => (prev === "" ? "rounded-full" : ""));
    console.log(cellShape);
  };

  // State to track mouse holding and hovered cells
  const [hoveredCells, setHoveredCells] = useState<Record<string, boolean>>({});
  const [isHolding, setIsHolding] = useState(false);

  // Initialize grid[][] with all cells as all traversable -> (true)
  const [grid, setGrid] = useState<boolean[][]>(
    Array.from({ length: dimension }, () => Array(dimension).fill(true))
  );

  // State to track start, end points and path
  const [start, setStart] = useState<[number, number] | null>(null);
  const [end, setEnd] = useState<[number, number] | null>(null);
  const [path, setPath] = useState<[number, number][]>([]);

  // State to track if the user is placing obstacles
  const [isPlacingObstacles, setIsPlacingObstacles] = useState(true);
  const [pathStep, setPathStep] = useState(0); // Track the current step in the path display

  // State to track alerts
  const [alert, setAlert] = useState<{ message: string; type: string } | null>(
    null
  );

  // Handle mouse events for marking obstacles, start, and end points
  const handleMouseDown = (row: number, col: number) => {
    // Check if the user is currently placing obstacles
    if (isPlacingObstacles) {
      // non-continous placement
      const newGrid = [...grid];
      newGrid[row][col] = !newGrid[row][col]; // Toggle between walkable and obstacle
      setGrid(newGrid);
    }

    // Mark start point
    else if (start === null) {
      setStart([row, col]);
    }

    // Mark end point
    else if (end === null) {
      setEnd([row, col]);
    }

    // Toggle holding state
    setIsHolding(true);
  };

  // Handle mouse events for marking obstacles, start, and end points
  useEffect(() => {
    // Check if both start and end are set
    if (start && end) {
      const result = breadthFirstSearch(grid, start, end);
      setPath(result); // Set the path found by BFS

      // Check if a path was found
      if (result.length > 0) {
        console.log("Path found");
        // Display success message in UI
        setAlert({ message: "Path found", type: "success" });

        // Slow down the path display by updating one step at a time
        let step = 0;
        const interval = setInterval(() => {
          if (step < result.length) {
            setPathStep(step + 1);
            step++;
          } else {
            clearInterval(interval); // Stop when the full path is displayed
          }
        }, 100); // Delay of 100ms per step
      } else {
        console.log("No path found");
        setAlert({
          message: "No path found",
          type: "error",
        });
      }
    }

    // Log changes to cellShape, dimension, and cellSize
    console.log("Cell Shape:", cellShape);
    console.log("Dimension:", dimension);
    console.log("Cell Size:", cellSize);
  }, [start, end, grid, cellShape, dimension, cellSize]);

  // Handle mouse events for marking obstacles
  const handleMouseEnter = (row: number, col: number) => {
    if (isHolding && isPlacingObstacles) {
      // Mark the cell as hovered only if holding
      setHoveredCells((prev) => ({ ...prev, [`${row}-${col}`]: true }));
      const newGrid = [...grid];
      newGrid[row][col] = false; // Place obstacle
      setGrid(newGrid);
    }
  };

  const handleMouseUp = () => {
    setIsHolding(false); // Reset holding state
  };

  const gridRender = []; // Initialize the grid render array

  // Create a dimension x dimension grid
  for (let i = 0; i < dimension; i++) {
    for (let j = 0; j < dimension; j++) {
      const isHovered = hoveredCells[`${i}-${j}`]; // Check if the cell is hovered
      const isStart = start && start[0] === i && start[1] === j;
      const isEnd = end && end[0] === i && end[1] === j;
      const isPath = path
        .slice(0, pathStep)
        .some(([row, col]) => row === i && col === j); // Only show part of the path based on the step

      gridRender.push(
        <div
          onMouseDown={() => handleMouseDown(i, j)} // Mark start, end, or obstacle
          onMouseEnter={() => handleMouseEnter(i, j)} // Mark cell while dragging
          key={`${i}-${j}`} // Unique key for each cell
          style={{ width: `${cellSize}px`, height: `${cellSize}px` }} // Dynamically set size
          className={`border border-zinc-800 flex ${cellShape} items-center justify-center cursor-pointer 
          ${isStart ? "bg-green-500" : ""}
          ${isEnd ? "bg-red-500" : ""}
          ${isPath ? "bg-blue-500" : ""}
          ${isHovered ? "bg-yellow-500" : ""}
          ${!grid[i][j] ? "bg-yellow-500" : ""}`} // Highlight start, end, path, and obstacles
        >
          {/* Optionally render content */}
        </div>
      );
    }
  }

  // Render the grid
  return (
    <div className="flex flex-col items-center justify-center">
      <div className="mb-10 text-center px-5 py-2 rounded-[10px] border border-zinc-700 text-gray-300 ">
        <h1 className="text-2xl text-gray-200">
          Path Finder - Breadth First Search
        </h1>
      </div>
      {/* Grid rendering */}
      <div
        className="grid shadow-md shadow-black mb-10 hover:shadow-md hover:shadow-gray-700"
        onMouseUp={handleMouseUp} // End holding on mouse up
        style={{
          gridTemplateColumns: `repeat(${dimension}, minmax(0, 1fr))`,
          gridTemplateRows: `repeat(${dimension}, minmax(0, 1fr))`,
        }}
      >
        {gridRender} {/* Render grid cells */}
      </div>

      {/* Display Alert if it exists */}
      {alert ? (
        <div
          className={`alert ${alert.type} mb-4 p-2 rounded-md p-2 border border-gray-600  rounded-[7px]`}
        >
          {alert.type === "error" && (
            <span className="text-red-500 text-[20px]">{alert.message}</span>
          )}
          {alert.type === "success" && (
            <span className="text-green-500 text-[20px]">{alert.message}</span>
          )}
          <button
            className="ml-4 p-1  rounded-md px-2 border border-gray-600 hover:border-zinc-700 text-gray-300 bg-zinc-800 hover:bg-zinc-900 rounded-[7px]"
            onClick={() => {
              // Clear the alert and reset the grid state
              setAlert(null);
              Array.from({ length: dimension }, () =>
                Array(dimension).fill(true)
              );
              setGrid(
                Array.from({ length: dimension }, () =>
                  Array(dimension).fill(true)
                )
              );
              setHoveredCells({});
              setStart(null);
              setEnd(null);
              setPath([]);
              setPathStep(0);
            }}
          >
            Reset
          </button>
        </div>
      ) : (
        /* Button to toggle between placing obstacles or setting start/end points */
        <button
          id="toggle-button"
          className="flex items-center justify-center mb-4 rounded-md p-2 border border-gray-600 hover:border-zinc-700 text-gray-300 bg-zinc-800 hover:bg-zinc-900"
          onClick={() => {
            if (!alert) {
              setIsPlacingObstacles((prev) => !prev);
            }
          }}
        >
          {isPlacingObstacles ? (
            <>
              Toggle to place start{" "}
              <div
                className={`border border-zinc-800 flex ${cellShape} w-[14px] h-[14px] items-center justify-center cursor-pointer bg-green-500 mx-[5px]`}
              />
              and end
              <div
                className={`border border-zinc-800 flex ${cellShape} w-[14px] h-[14px] items-center justify-center cursor-pointer bg-red-500 mx-[5px]`}
              />{" "}
              points
            </>
          ) : (
            <>
              Toggle to place obstacles{" "}
              <div
                className={`border border-zinc-800 flex ${cellShape} w-[14px] h-[14px] items-center justify-center cursor-pointer bg-yellow-500 mx-[5px]`}
              />
            </>
          )}
        </button>
      )}
      <div className="flex justify-center items-center text-center text-gray-400 gap-5 flex-wrap">
        {/* Button to increase grid dimension */}
        {dimension < maxDimension && (
          <button
            className=" p-2 border border-gray-600 hover:border-zinc-700 text-gray-300 bg-zinc-800 hover:bg-zinc-900 rounded-[7px]"
            onClick={() => handleIncreaseDimension()} // Increase grid dimension
          >
            <span className="text-green-500">+</span>
            Dimension
          </button>
        )}

        {/* Button to decrease grid dimension */}
        {dimension > 6 && (
          <button
            className=" p-2 border border-gray-600 hover:border-zinc-700 text-gray-300 bg-zinc-800 hover:bg-zinc-900 rounded-[7px]"
            onClick={() => handleDecreaseDimension()} // Decrease grid dimension
          >
            <span className="text-red-500">-</span>Dimension
          </button>
        )}

        {/* Button to increase cell size */}
        <button
          className=" p-2 border border-gray-600 hover:border-zinc-700 text-gray-300 bg-zinc-800 hover:bg-zinc-900 rounded-[7px]"
          onClick={() => handleIncreaseCellSize()} // Increase cell size
        >
          <span className="text-green-500">+</span>Cell Size
        </button>

        {/* Button to decrease cell size */}
        {cellSize > 10 && (
          <button
            className=" p-2 border border-gray-600 hover:border-zinc-700 text-gray-300 bg-zinc-800 hover:bg-zinc-900 rounded-[7px]"
            onClick={() => handleDecreaseCellSize()} // Decrease cell size
          >
            <span className="text-red-500">-</span>Cell Size
          </button>
        )}

        {/* Button to change cell shape */}
        <button
          className=" p-2 border border-gray-600 hover:border-zinc-700 text-gray-300 bg-zinc-800 hover:bg-zinc-900 rounded-[7px] gap-1 flex justify-between items-center"
          onClick={handleSetCellShape}
        >
          {cellShape === "rounded-full" ? (
            <div
              className={`border border-zinc-800 flex ${cellShape} w-[14px] h-[14px] items-center justify-center cursor-pointer bg-yellow-500`}
            />
          ) : (
            <div
              className={`border border-zinc-800 flex ${cellShape} w-[14px] h-[14px] items-center justify-center cursor-pointer bg-yellow-500`}
            />
          )}
          Toggle Cell Shape
        </button>
      </div>
    </div>
  );
}
export default Board;
