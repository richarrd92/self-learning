### Memoization Technique

**The Idea:**  
Treat the problem as a recursive tree, solve it, and then optimize using memoization.

1. Create a dictionary to store previously computed results.
2. If the result for a subproblem is already in the dictionary, return it.
3. If not, compute the result, store it in the dictionary, and return the result.

### Tabulation Technique    

**The Idea:**  
Treat the problem as a table, solve it, and then optimize using tabulation.

1. Create a table to store intermediate results.
2. Fill the table from left to right, top to bottom.
3. Return the desired result from the table.
