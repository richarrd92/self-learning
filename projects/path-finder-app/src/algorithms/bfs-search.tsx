
const breadthFirstSearch = (
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

  // Initialize visited and queue
  const visited: boolean[][] = Array.from({ length: rows }, () =>
    Array(cols).fill(false)
  );
  const queue: [number, number, number][] = []; // Stores [row, col, distance]
  const parent: Record<string, [number, number] | null> = {}; // To track the path

  // Start BFS
  const [startRow, startCol] = start;
  const [endRow, endCol] = end;

  // Add start to queue
  queue.push([startRow, startCol, 0]);
  visited[startRow][startCol] = true; // Mark start as visited
  parent[`${startRow}-${startCol}`] = null; // Set parent of start to null

  // BFS loop
  while (queue.length > 0) {
    const [row, col, dist] = queue.shift()!;

    // Mark current cell as explored
    explored.push([row, col]);

    // If we reach the end, stop BFS
    if (row === endRow && col === endCol) {
      let path = [];
      let current: [number, number] | null = [row, col];

      // Trace the path back from end to start
      while (current) {
        path.unshift(current);
        current = parent[`${current[0]}-${current[1]}`];
      }

      return { path, explored }; // Return the path and the explored cells
    }

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
        visited[newRow][newCol] = true;
        queue.push([newRow, newCol, dist + 1]);
        parent[`${newRow}-${newCol}`] = [row, col]; // Keep track of the path
      }
    }
  }

  return { path: [], explored }; // Return an empty path if no path is found, along with explored cells
};

export default breadthFirstSearch;
