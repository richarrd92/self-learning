
const aStarSearch = (
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

  // Heuristic function (Manhattan distance)
  const heuristic = (row: number, col: number) =>
    Math.abs(row - end[0]) + Math.abs(col - end[1]);

  // Priority queue for A* (min-heap based on f-cost)
  const pq: [number, number, number, number][] = []; // [row, col, g-cost, f-cost]

  // To track visited nodes
  const visited: boolean[][] = Array.from({ length: rows }, () =>
    Array(cols).fill(false)
  );

  // To track the shortest path
  const parent: Record<string, [number, number] | null> = {};

  // Add the start node to the priority queue
  const [startRow, startCol] = start;
  pq.push([startRow, startCol, 0, heuristic(startRow, startCol)]);
  parent[`${startRow}-${startCol}`] = null;

  // A* search loop
  while (pq.length > 0) {
    // Sort by f-cost and take the node with the smallest f-cost
    pq.sort((a, b) => a[3] - b[3]);
    const [row, col, gCost] = pq.shift()!;

    // Mark the node as visited
    if (visited[row][col]) continue;
    visited[row][col] = true;

    // Mark current cell as explored
    explored.push([row, col]);

    // If we reach the end node, construct the path
    if (row === end[0] && col === end[1]) {
      let path = [];
      let current: [number, number] | null = [row, col];

      while (current) {
        path.unshift(current);
        current = parent[`${current[0]}-${current[1]}`];
      }

      return { path, explored }; // Return the path and explored cells
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
        grid[newRow][newCol] && // Check if the cell is walkable
        !visited[newRow][newCol]
      ) {
        const newGCost = gCost + 1;
        const fCost = newGCost + heuristic(newRow, newCol);

        pq.push([newRow, newCol, newGCost, fCost]);
        parent[`${newRow}-${newCol}`] = [row, col];
      }
    }
  }

  return { path: [], explored }; // Return an empty path if no path is found, along with explored cells
};

export default aStarSearch;
