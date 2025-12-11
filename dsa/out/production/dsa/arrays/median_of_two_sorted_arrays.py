# test 1
nums1 = [1,3]
nums2 = [2]

# test 2
# nums1 = [1,2]
# nums2 = [3,4]

# test 3
# nums1 = [1,2,3,4,5,8,9,34,56]
# nums2 = [1,1,3,4,5,99]

# test 4
# nums1 = [1,2,3,4,5,8,9,34,56,56]
# nums2 = [1,1,3,4,5,99,100,100,100]

# Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
# The overall run time complexity should be O(log (m+n)).

# Brute force: time and space: O(m+n) 
# Could use python libraries to sort concatenated arrays for concise code
def brute_force(arr1, arr2) -> float:
    m, n = len(arr1), len(arr2)
    res = []

    arr1_pointer, arr2_pointer = 0, 0

    # merge into res array
    while arr1_pointer < m and arr2_pointer < n:
        if arr1[arr1_pointer] <= arr2[arr2_pointer]:
            res.append(arr1[arr1_pointer])
            arr1_pointer += 1
        else:
            res.append(arr2[arr2_pointer])
            arr2_pointer += 1

    # remaining values in arr1
    while arr1_pointer < m:
        res.append(arr1[arr1_pointer])
        arr1_pointer += 1

    # remaining values in arr12
    while arr2_pointer < n:
        res.append(arr2[arr2_pointer])
        arr2_pointer += 1

    # find the median 
    # 2 pointer approach
    # if even or odd egde cases
    answer = 0.0
    L = 0
    R = len(res)-1
    if len(res) % 2 == 0:
        while L < R:
            L += 1
            R -= 1
        answer = (res[L] + res[R]) / 2
        print("the formula:", (res[L] + res[R]) / 2)
        print("L: ", L, " and R: ", R)
        print("from if", answer)
    else:
        answer = res[((R+L)//2)]
        print("the formula:", ((R+L)//2)+1)
        print("L: ", L, " and R: ", R)
        print("from else:" , answer)

    print("\nThe merged array: ", res)
    return answer

print(brute_force(nums1, nums2))


### CAN BE IMPLEMENTED IN 0(LOG (M+N)) OR 0(LOG min(M+N)) USING BINARY SEARCH
### I WILL COME BACK TO IT IN THE FUTURE -> (Not on that level yet)

