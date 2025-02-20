# *********** COMMON BUILT-IN FUNCTIONS ********************
# isdigit() - returns true if char is digit
# str() - converts to string 
# reversed() - reverses variable but returns iterator object
# [::-1] - reverses using splie method


# *********** LISTS ********************
# List x = [] is zero indexed, and mutable
# x is storing a reference not a copy
# x = ['a', 3 , 4 , 5]
# y = x[:] makes a copy - changes dont translate
# x.pop() removes 5 also prints it
# x.pop(index) removes value at given index
# x[index] accesses value at given index

# *********** TUPLES   *********************
# Tuple x = () uses brackets instead
# works almost the same as list
# are immutable - cant be changed
# cant append, remove or change

# ************* FOR LOOP ****************
# for i in range(stop) 
# for i in range(start, stop) 
# for i in range(start, stop, increment/step) 

# ************* WHILE LOOP **************
# while(condition) - very basic

# ************ SLICE OPERATOR ***********
# x[start:stop:step]
# x[start:]
# x[:stop]
# it picks a section of a data structure eg List
# can flip to print in reverse or x[::-1]

# *********** SET ****************
# equivalent to unordered_set in c++
# removes duplicates 
# stores elements in order
# print(element in set)
# you can do union, intersection, difference etc

# ********** DICTIONARY ***********
# equivalent to unordered_map in c++
# x = {key : value}
# to add x[key] = value
# print keys - x.keys()
# print values -  x.values()
# remove or delete - del x[key] or del x[value]

# *********** COMPREHENSIONS ***************
# basically initialization list on lists
# x = [i for i in range(5) for i in range(5)] 
# x = [i for i in range(5) for  in range(5)] 

# ********** FUNCTIONS ********************
# use def to define a function
# def func()
# the rest is pretty explanatory 

# ********************************************
# use dir to print all functions of data type
# print(dir(str))

# ********************************************
# use help to print what the function does
# print(help(str.upper))

# ********************************************
# ways to format strings 
# message = "Hello %s" % user_input
# message = f"Hello {user_input}"

# ********** ITERATOR CONCATINATION ***************
# to join the strings in a list or tuple 
# use "".join(list_or_tuple)


# ************  ARGUMENTS ****************
# functions with multiple arguments
# use * 
# def mean(*args) returns a tuple
# if dealing with keyword arguments 
# use **
# def mean(**kwargs) returns a dictionary

# ************** FILES *********************

# read a file
# my_file = open(file.txt) # must be in the same directory
# print content of the file
# print(my_file.read())

# always close a file
# my_file.close() # erases the content from RAM

# handle opening and closing at once
# use with _____ as ______
# with open("my_file.txt") as my_file
#   content = my_file.read()
# print(content)

# read a file
# with open("my_file.txt", "w") as my_file
# my_file.write("snail")

# open, write and append to a file
# with open("file.txt", "a+") as my_file: 
#     my_file.write("\nthis text is appended")
#     my_file.seek(0) # returns cursor to 0
#     print(my_file.read())

