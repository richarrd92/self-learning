
const depthFirstSearch = (
  grid: boolean[][],
  start: [number, number],
  end: [number, number],
  explored: [number, number][] // Add explored parameter
) => {
  // Get grid dimensions
  const rows = grid.length;
  const cols = grid[0].length;

  // Directions for moving up, down, left, right
  const directions = [
    [-1, 0], // Up
    [1, 0], // Down
    [0, 1], // Right
    [0, -1], // Left
  ];

  // Initialize visited
  const visited: boolean[][] = Array.from({ length: rows }, () =>
    Array(cols).fill(false)
  );
  const parent: Record<string, [number, number] | null> = {}; // To track the path

  // Start DFS
  const [startRow, startCol] = start;
  const [endRow, endCol] = end;

  // Helper function for DFS
  const dfs = (row: number, col: number): boolean => {
    // If we reach the end, return true
    if (row === endRow && col === endCol) {
      return true;
    }

    // Mark current cell as visited
    visited[row][col] = true;
    explored.push([row, col]); // Add the current cell to explored

    // Explore neighbors
    for (let [dx, dy] of directions) {
      const newRow = row + dx;
      const newCol = col + dy;

      if (
        newRow >= 0 &&
        newRow < rows &&
        newCol >= 0 &&
        newCol < cols &&
        !visited[newRow][newCol] &&
        grid[newRow][newCol]
      ) {
        parent[`${newRow}-${newCol}`] = [row, col]; // Keep track of the path

        // Recurse into the neighbor
        if (dfs(newRow, newCol)) {
          return true; // Stop recursion if the path to the end is found
        }
      }
    }

    return false; // No valid path from this cell
  };

  // Start DFS from the start cell
  parent[`${startRow}-${startCol}`] = null; // Set parent of start to null
  if (!dfs(startRow, startCol)) {
    return { path: [], explored }; // Return empty path if no path is found, along with explored cells
  }

  // Trace the path back from end to start
  let path: [number, number][] = [];
  let current: [number, number] | null = [endRow, endCol];
  while (current) {
    path.unshift(current);
    current = parent[`${current[0]}-${current[1]}`];
  }

  return { path, explored }; // Return both the path and the explored cells
};

export default depthFirstSearch;
