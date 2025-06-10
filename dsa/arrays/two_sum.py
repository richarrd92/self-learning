nums = [2,7,11,15]
target = 26

# solution 1 time O(N^2) space O(1)
def sol1(arr: list, target: int) -> list:
    for i in range(len(arr)):
        num1 = arr[i]
        for j in range(len(arr)):
            num2 = arr[j]
            if (num1 + num2) == target:
                return [i, j]
    return [-1, -1]

# print(sol1(nums, target))

# solution 2 time  O(N) space O(N)
def sol2(arr: list, target: int) -> list:
    mp = dict()
    
    # iterate thru array to find match O(N)
    for i in range(len(arr)):
        diff = target - arr[i]

        # check for diff in mp
        if diff in mp:
            return [i, mp[diff]]
        
        # add diff to map
        mp[arr[i]] = i
    
    print(mp)

print(sol2(nums, target))