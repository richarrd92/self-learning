# Reading content of a file

with open("file.txt", "a+") as my_file: 
    my_file.write("\nthis text is appended")
    my_file.seek(0) # returns cursor to 0
    print(my_file.read())