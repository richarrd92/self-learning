# FUNCTION BackTrack(n):
#     SET k = 1                     // Start with the first position
#     CREATE array X[1..n]          // This holds the current permutation
#     SET X[k] = 0                  // Initialize the first value

#     WHILE k > 0:
#         X[k] = X[k] + 1           // Try the next possible value for position k

#         WHILE X[k] <= n AND NOT isLegal(X, k):
#             X[k] = X[k] + 1       // Keep trying next values until it's legal

#         IF X[k] <= n:
#             IF k == n:
#                 PRINT X[1..n]     // Found a complete valid permutation!
#             ELSE:
#                 k = k + 1         // Move to next position
#                 X[k] = 0          // Start trying from 1 again for new position
#         ELSE:
#             k = k - 1             // Backtrack to previous position


# FUNCTION isLegal(X, k):
#     FOR i FROM 1 TO k - 1:
#         IF X[i] == X[k]:
#             RETURN false          // Duplicate found, not legal
#     RETURN true                   // No duplicates, it's legal


def is_legal(current_permutation, current_position):
    # Check if the current value has already been used earlier in the permutation
    for previous_position in range(1, current_position):
        if current_permutation[previous_position] == current_permutation[current_position]:
            return False
    return True


def generate_permutations(n):
    permutation = [0] * (n + 1)  # Index 1 to n will be used
    position = 1                 # Start filling from the first position

    while position > 0:
        # Try the next possible value at the current position
        permutation[position] += 1

        # Keep increasing the value until it's legal or we go beyond n
        while permutation[position] <= n and not is_legal(permutation, position):
            permutation[position] += 1

        if permutation[position] <= n:
            if position == n:
                # Found a complete and valid permutation
                print(permutation[1:])  # Ignore index 0
            else:
                # Move forward to fill the next position
                position += 1
                permutation[position] = 0  # Start from 1 again at new position
        else:
            # Backtrack to the previous position
            position -= 1
