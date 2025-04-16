### Memoization Technique

**Concept:**  
Think of the problem as a recursive process. Solve it naturally using recursion, then add memoization to cache results and avoid redundant work.

**Steps:**
1. Initialize a dictionary (or array) to store solutions to subproblems.
2. Before solving a subproblem, check if it's already been solved.
3. If yes, return the stored value.  
4. If no, compute it, store the result, and return it.


### Tabulation Technique

**Concept:**  
Visualize the problem as a table. Solve all smaller subproblems iteratively and build up to the final solution.

**Steps:**
1. Create a table (usually an array) for storing subproblem results.
2. Solve subproblems in order, from smallest to largest.
3. Fill in each entry using the results of previously solved subproblems.
4. The final answer is found in the last cell of the table.


### Comparison Table

| Feature            | Top-Down (Memoization)                   | Bottom-Up (Tabulation)                 |
|--------------------|------------------------------------------|----------------------------------------|
| **Approach**       | Recursive + memoization                  | Iterative                              |
| **Direction**      | Solves big problem first, breaks it down | Solves small problems first, builds up |
| **Storage**        | Memo table (dict or array)               | Table/array filled step-by-step        |
| **Function Calls** | Uses recursion                           | Uses loops                             |
| **Stack Usage**    | Uses call stack (risk of overflow)       | No call stack usage                    |
| **Ease of Writing**| Often more intuitive                     | Often more efficient                   |