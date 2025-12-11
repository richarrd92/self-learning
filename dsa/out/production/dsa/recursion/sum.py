# Get sum recursively
def get_sum(num: int) -> int:
    if num == 0: return 0
    return num + get_sum(num-1)

# Get sum iteratively
def get_sum2(num: int) -> int:
    total = 0
    for i in range(num):
        total += num - i
        
    return total

# Get sum mathematically
def get_sum3(num: int) -> int:
    return num * (num + 1) // 2
        
print(get_sum3(4))