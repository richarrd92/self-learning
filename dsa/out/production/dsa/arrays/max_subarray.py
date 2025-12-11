# Kadanes algorithm
# key is comparing the nums[i] with curr sum + nums[i] -> curr sum is the max of the two


nums = [-2,1,-3,4,-1,2,1,-5,4]
# nums = [5,4,-1,7,8]

# given array -> return the max subarray
# n^2 
def findMax(nums: list) -> int:
    currSum = 0
    maxSum = -float("inf") # or First element

    # permutation
    for i in range(len(nums)):
        currSum = 0
        for j in range(i, len(nums)):
            currSum += nums[j]
            maxSum = max(maxSum, currSum)
    return maxSum

print(findMax(nums)) 

# optimized version -> time: n
def findMaxOptimized(nums) -> int:
    currSum = maxSum = nums[0]

    for i in range(1, len(nums)):
        currSum = max(nums[i], currSum + nums[i]) # update currSum -> window
        maxSum = max(currSum, maxSum) # store the highest sum

    return maxSum # first element is the max if no element greater is found

print(findMaxOptimized(nums))

