"use client";
import React, { useState, useEffect, useRef } from "react";
import breadthFirstSearch from "@/algorithms/bfs-search"; // Import the breadthFirstSearch function
import depthFirstSearch from "@/algorithms/dfs-search"; // Import the depthFirstSearch function
import aStarSearch from "@/algorithms/astar-search"; // Import the aStarSearch function

let exportedAlgorithm = "Breadth First";
// Board component
function Board() {
  const [algorithm, setAlgorithm] = useState<string>("Breadth First"); // Initial state

  // Function to toggle the algorithm
  const toggleAlgorithm = () => {
    setAlgorithm((prev) => {
      const nextAlgorithm =
        prev === "Breadth First"
          ? "Depth First"
          : prev === "Depth First"
          ? "A* Search"
          : "Breadth First";

      // Update the exported value
      exportedAlgorithm = nextAlgorithm;
      return nextAlgorithm;
    });
  };

  // Function to get the current algorithm value
  const getAlgorithm = () => {
    return algorithm;
  };

  let newDimension: number;
  const [exploredCells, setExploredCells] = useState<[number, number][]>([]);
  const boardRef = useRef<HTMLDivElement | null>(null);
  const maxDimension = 70; // Maximum dimension for the grid
  const [dimension, setDimension] = useState<number>(40); // Initial dimension
  const [pathInterval, setPathInterval] = useState<number>(100); // Initial interval


  // Functions to increase and decrease interval
  const handleIncreaseInterval = () => {
    setPathInterval((prev) => prev + 20);
    console.log("pathInterval: ", pathInterval);
  };

  const handleDecreaseInterval = () => {
    setPathInterval((prev) => prev - 20);
    console.log("pathInterval: ", pathInterval);
  };

  // Functions to increase and decrease dimension
  const handleIncreaseDimension = () => {
    newDimension = dimension + 5;
    setGrid(
      Array.from({ length: newDimension }, () => Array(newDimension).fill(true))
    );

    setDimension(newDimension);
    console.log("dimension: ", dimension);
  };

  const handleDecreaseDimension = () => {
    newDimension = dimension - 5;
    setGrid(
      Array.from({ length: newDimension }, () => Array(newDimension).fill(true))
    );

    setDimension(newDimension);
    console.log("dimension: ", dimension);
    // setDimension((prev) => prev - 5);
    // console.log("dimension: ", dimension);
  };

  const [cellSize, setCellSize] = useState<number>(14); // Initial cell size

  const [canIncrease, setCanIncrease] = useState(true); // State to control "+" button visibility

  useEffect(() => {
    if (boardRef.current) {
      const boardHeight = boardRef.current.clientHeight;
      setCanIncrease(boardHeight + 180 < window.innerHeight);
    }
    console.log("window height: ", window.innerHeight);
    console.log("board height: ", boardRef.current?.clientHeight);
  }, [cellSize, dimension]);

  // Functions to increase and decrease cell size
  const handleIncreaseCellSize = () => {
    setCellSize((prev) => prev + 2);
    console.log("cellSize: ", cellSize);
  };

  const handleDecreaseCellSize = () => {
    setCellSize((prev) => prev - 2);
    console.log("cellSize: ", cellSize);
  };

  const [cellShape, setCellShape] = useState<string>(""); // Initial cell shape

  // Function to toggle cell shape
  const handleSetCellShape = () => {
    setCellShape((prev) => (prev === "" ? "rounded-full" : ""));
    console.log(
      "Cell Shape:",
      cellShape === "rounded-full" ? "Circle" : "Square"
    );
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

  useEffect(() => {
    if (start && end) {
      let explored: [number, number][] = []; // Initialize an array to track explored cells
      let result: any;

      try {
        // Choose the appropriate algorithm based on the selected option
        result =
          algorithm === "Breadth First"
            ? breadthFirstSearch(grid, start, end, explored) // Pass explored array to BFS
            : algorithm === "Depth First"
            ? depthFirstSearch(grid, start, end, explored) // Pass explored array to DFS
            : aStarSearch(grid, start, end, explored); // Pass explored array to A* search

        setPath(result.path); // Set the path found by the algorithm
        setExploredCells(explored); // Update the explored cells state

        // Check if a path was found
        if (result.path.length > 0) {
          console.log("Path found");
          setAlert({
            message: `Path found`,
            type: "success",
          });

          // Display the path step-by-step
          let step = 0;
          const interval = setInterval(() => {
            if (step < result.path.length) {
              setPathStep(step + 1);
              step++;
            } else {
              clearInterval(interval);
            }
          }, pathInterval); // Delay of pathInterval per step
          return () => clearInterval(interval);
        } else {
          console.log("No path found");
          setAlert({
            message: "No path found",
            type: "error",
          });
        }
      } catch (error) {
        // Handle stack overflow
        if (error instanceof RangeError) {
          console.error("Stack overflow detected:", error);
          setAlert({
            message: "Decrease dimension. Try Different Algorithm",
            type: "error",
          });
        }

        // Handle other unexpected errors
        else {
          console.error("An unexpected error occurred:", error);
          setAlert({
            message: "An unexpected error occurred. Please try again.",
            type: "error",
          });
        }
      }
    }
  }, [
    start,
    end,
    grid,
    cellShape,
    dimension,
    cellSize,
    algorithm,
    pathInterval,
    cellSize,
    start,
    end,
  ]);

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

  // Updated gridRender logic to include the rendering of explored cells
  for (let i = 0; i < dimension; i++) {
    for (let j = 0; j < dimension; j++) {
      const isHovered = hoveredCells[`${i}-${j}`]; // Check if the cell is hovered
      const isStart = start && start[0] === i && start[1] === j;
      const isEnd = end && end[0] === i && end[1] === j;
      const isPath = path
        .slice(0, pathStep)
        .some(([row, col]) => row === i && col === j); // Only show part of the path based on the step
      const isExplored = exploredCells.some(
        ([row, col]) => row === i && col === j
      ); // Check if the cell is explored

      gridRender.push(
        <div
          onMouseDown={() => handleMouseDown(i, j)} // Mark start, end, or obstacle
          onMouseEnter={() => handleMouseEnter(i, j)} // Mark cell while dragging
          key={`${i}-${j}`} // Unique key for each cell
          style={{ width: `${cellSize}px`, height: `${cellSize}px` }} // Dynamically set size
          className={`border border-zinc-800 flex ${cellShape} items-center justify-center cursor-pointer 
          ${isStart ? "bg-green-500" : ""}
          ${isEnd ? "bg-red-500 " : ""}
          ${isPath && !isStart && !isEnd ? "bg-blue-600" : ""}
          ${
            !isPath && isExplored && !isStart && !isEnd
              ? "bg-zinc-800 border-zinc-900"
              : ""
          }
          ${(!grid[i][j] && isHovered) || !grid[i][j] ? "bg-yellow-500" : ""}`} // Highlight obstacles
        >
          {/* Optionally render content */}
        </div>
      );
    }
  }

  // Render the grid
  return (
    <div
      id="board"
      ref={boardRef} // Attach the ref here
      className="flex flex-col items-center justify-center "
    >
      {/* Grid rendering */}
      <div
        className="glowing-grid grid shadow-md shadow-black mb-6 glowing-grid:hover "
        onMouseUp={handleMouseUp} // End holding on mouse up
        style={{
          gridTemplateColumns: `repeat(${dimension}, minmax(0, 1fr))`,
          gridTemplateRows: `repeat(${dimension}, minmax(0, 1fr))`,
        }}
      >
        {gridRender} {/* Render grid cells */}
      </div>
      {/* <div> */} {/* Toggle Button */}
      <button
        onClick={toggleAlgorithm}
        className="flex items-center justify-center mb-3 rounded-md p-2 border border-gray-600 hover:border-zinc-700 text-gray-300 bg-zinc-800 hover:bg-zinc-900"
      >
        Switch to{" "}
        {algorithm === "Breadth First"
          ? "Depth First Search"
          : algorithm === "Depth First"
          ? "A* Search"
          : "Breadth First Search"}
      </button>
      {/* Display Alert if it exists */}
      {alert ? (
        <div
          className={`alert ${alert.type} mb-3 p-2 rounded-md border border-gray-600 rounded-[7px] flex justify-between items-center flex-wrap `}
        >
          <div>
            {alert.type === "error" && (
              <>
                <span className="text-red-500 text-[5px]">{alert.message}</span>
                <div className=" text-gray-300 text-[5px] mt-2 ">
                  <div className="flex flex-row gap-2">
                    <div
                      className={` flex ${cellShape} w-[14px] h-[14px] items-center justify-center cursor-pointer bg-blue-600`}
                    ></div>
                    <p className="transform -translate-y-1">
                      Path Steps: {pathStep}
                    </p>
                  </div>

                  <div className="flex flex-row gap-2">
                    <div
                      className={` flex ${cellShape} w-[14px] h-[14px] items-center justify-center cursor-pointer bg-zinc-800`}
                    ></div>
                    <p className="transform -translate-y-1 text-[5px]">
                      Explored Cells: {exploredCells.length}
                    </p>
                  </div>
                  <div className="flex flex-row gap-2">
                    <div
                      className={`border border-gray-600 flex w-[14px] h-[14px] items-center justify-center cursor-pointer bg-zinc-900 text-[5px]`}
                    />
                    <p className="transform -translate-y-1 text-[5px]">
                      Dimension: {dimension} x {dimension}
                    </p>
                  </div>
                </div>
              </>
            )}
            {alert.type === "success" && (
              <>
                <span className="text-green-500 text-[5px]">
                  {alert.message}
                </span>
                <div className="text-gray-300 text-[5px] mt-2">
                  <div className="flex flex-row gap-2">
                    <div
                      className={` flex ${cellShape} w-[14px] h-[14px] items-center justify-center cursor-pointer bg-blue-600`}
                    ></div>
                    <p className="transform -translate-y-1 text-[5px]">
                      Path Steps: {pathStep}
                    </p>
                  </div>

                  <div className="flex flex-row gap-2 text-[5px]">
                    <div
                      className={` flex ${cellShape} w-[14px] h-[14px] items-center justify-center cursor-pointer bg-zinc-800`}
                    ></div>
                    <p className="transform -translate-y-1 text-[5px]">
                      Explored Cells: {exploredCells.length}
                    </p>
                  </div>
                  <div className="flex flex-row gap-2">
                    <div
                      className={`border border-gray-600 flex w-[14px] h-[14px] items-center justify-center cursor-pointer bg-zinc-900 text-[5px]`}
                    />
                    <p className="transform -translate-y-1 text-[5px]">
                      Dimension: {dimension} x {dimension}
                    </p>
                  </div>
                </div>
              </>
            )}
          </div>
          <button
            className="ml-4 p-1 rounded-md px-2 border border-gray-600 hover:border-zinc-700 text-gray-300 bg-zinc-800 hover:bg-zinc-900 rounded-[7px] "
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
              setExploredCells([]);
              // setPathInterval(20);
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
              Click to place start{" "}
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
              Click to place obstacles{" "}
              <div
                className={`border border-zinc-800 flex ${cellShape} w-[14px] h-[14px] items-center justify-center cursor-pointer bg-yellow-500 mx-[5px]`}
              />
            </>
          )}
        </button>
      )}
      {!alert && (
        <div className="flex justify-center items-center text-center text-gray-400 gap-5 flex-wrap">
          {/* Button to increase grid dimension */}
          {canIncrease && dimension < maxDimension && (
            <button
              className="p-2 border border-gray-600 hover:border-zinc-700 text-gray-300 bg-zinc-800 hover:bg-zinc-900 rounded-[7px]"
              onClick={() => {
                // const newDimension = dimension + 1;
                // setGrid(
                //   Array.from({ length: newDimension }, () =>
                //     Array(newDimension).fill(true)
                //   )
                // );
                handleIncreaseDimension();
                setHoveredCells({});
                setStart(null);
                setEnd(null);
                setPath([]);
                setPathStep(0);
                setExploredCells([]);
                setPathInterval(20);
                setAlert(null);
                // setDimension(newDimension); // Update dimension last
              }}
            >
              <span className="text-green-500">+</span>Dimension
            </button>
          )}

          {dimension > 6 && (
            <button
              className="p-2 border border-gray-600 hover:border-zinc-700 text-gray-300 bg-zinc-800 hover:bg-zinc-900 rounded-[7px]"
              onClick={() => {
                // const newDimension = dimension - 1;
                // setGrid(
                //   Array.from({ length: newDimension }, () =>
                //     Array(newDimension).fill(true)
                //   )
                // );
                handleDecreaseDimension();
                setHoveredCells({});
                setStart(null);
                setEnd(null);
                setPath([]);
                setPathStep(0);
                setExploredCells([]);
                setPathInterval(20);
                setAlert(null);
                setDimension(newDimension); // Update dimension last
              }}
            >
              <span className="text-red-500">-</span>Dimension
            </button>
          )}

          {/* Button to increase cell size
        
          button is not working for increase dimension - not tracking screen height -- works well for increase cell size - too much rendering going on 
        
        
        
        */}
          {canIncrease && (
            <button
              className="p-2 border border-gray-600 hover:border-zinc-700 text-gray-300 bg-zinc-800 hover:bg-zinc-900 rounded-[7px]"
              onClick={handleIncreaseCellSize}
            >
              <span className="text-green-500">+</span> Cell Size
            </button>
          )}

          {/* Button to decrease cell size */}
          {cellSize > 10 && (
            <button
              className=" p-2 border border-gray-600 hover:border-zinc-700 text-gray-300 bg-zinc-800 hover:bg-zinc-900 rounded-[7px]"
              onClick={() => handleDecreaseCellSize()} // Decrease cell size
            >
              <span className="text-red-500">-</span>Cell Size
            </button>
          )}

          {/* Button to increase path interval */}
          {pathInterval > 1 && (
            <button
              className="p-2 border border-gray-600 hover:border-zinc-700 text-gray-300 bg-zinc-800 hover:bg-zinc-900 rounded-[7px]"
              onClick={() => handleDecreaseInterval()} // Reset the interval to default or a specific value
            >
              <span className="text-green-500">+</span>Speed
            </button>
          )}

          {/* Button to decrease path interval */}
          {pathInterval < 350 && (
            <button
              className="p-2 border border-gray-600 hover:border-zinc-700 text-gray-300 bg-zinc-800 hover:bg-zinc-900 rounded-[7px]"
              onClick={() => handleIncreaseInterval()} // Reset the interval to default or a specific value
            >
              <span className="text-red-500">-</span>Speed
            </button>
          )}

          {/* Button to change cell shape */}
          <button
            className=" p-2 border border-gray-600 hover:border-zinc-700 text-gray-300 bg-zinc-800 hover:bg-zinc-900 rounded-[7px] gap-1 flex justify-between items-center"
            onClick={handleSetCellShape}
          >
            {cellShape === "rounded-full" ? (
              <div
                className={`border border-gray-600 flex ${cellShape} w-[14px] h-[14px] items-center justify-center cursor-pointer bg-zinc-900`}
              />
            ) : (
              <div
                className={`border border-gray-600 flex ${cellShape} w-[14px] h-[14px] items-center justify-center cursor-pointer bg-zinc-900`}
              />
            )}
            Toggle Cell Shape
          </button>
        </div>
      )}
    </div>
  );
}

export { exportedAlgorithm };
export default Board;
