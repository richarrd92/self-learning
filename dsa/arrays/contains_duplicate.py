# Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.

nums = [1,2,3,1]
k = 3

# nums = [1,0,1,1]
# k = 1

# nums = [1,2,3,1,2,3]
# k = 2

# time O(n^2)
def contains_duplicate(nums: int, k: int) -> bool:
    for i in range(len(nums)):
        for j in range(i+1, len(nums)):
            if (nums[i] == nums[j]) and (abs(i - j) <= k):
                return True
    return False
print(contains_duplicate(nums, k))

# can be done in nlogn time using sorting and binary search 


# sliding window technique
# time O(n) space O(n)
def contains_duplicate2(nums: int, k: int) -> bool:
    left = 0
    window = set()

    for right in range(len(nums)):
        # validate window size
        if right - left > k:
            window.remove(nums[left])
            left += 1

        # found duplicate
        if nums[right] in window:
            return True
        window.add(nums[right])

    return False



