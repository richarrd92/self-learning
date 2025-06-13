nums = [-2,1,-3,4,-1,2,1,-5,4]
# nums = [5,4,-1,7,8]

# given array -> return the max subarray
# n^2 
def bruteForce(nums: list) -> int:
    currSum = 0
    maxSum = -float("inf") # or First element

    # permutation
    for i in range(len(nums)):
        currSum = 0
        for j in range(i, len(nums)):
            currSum += nums[j]
            maxSum = max(maxSum, currSum)
    return maxSum

print(bruteForce(nums)) 