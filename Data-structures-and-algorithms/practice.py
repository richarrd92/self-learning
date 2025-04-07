# Practice file
nums = [2,0,2,1,1,0]

# helper function
def merge(arr: list, left: int, mid: int, right: int):
    temp_arr = [] # store temp sorted values

    left_ptr = left
    right_ptr = mid +1 

    # swap loop
    while left_ptr <= mid and right_ptr <= right:
        
        # left is smaller
        if arr[left_ptr] <= arr[right_ptr]:
            temp_arr.append(arr[left_ptr])
            left_ptr += 1

        # right is smaller
        else:
            temp_arr.append(arr[right_ptr])
            right_ptr += 1

    # remaining left values
    while left_ptr <= mid:
        temp_arr.append(arr[left_ptr])
        left_ptr += 1

    # remaining right values
    while right_ptr <= right:
        temp_arr.append(arr[right_ptr])
        right_ptr += 1


    # merge sorted values in the temp into main array 
    for i in range(len(temp_arr)):
        arr[left + i] = temp_arr[i]
    

# merge_sort algorithm
def merge_sort(arr: list, left: int, right: int) -> None:
    if left >= right:
       return 
    
    mid = (left + right) // 2 # integer division
    merge_sort(arr, left, mid)
    merge_sort(arr, mid+1, right)
    merge(arr, left, mid, right)

# should return sorted values
merge_sort(nums, 0, len(nums)-1)
print(nums)