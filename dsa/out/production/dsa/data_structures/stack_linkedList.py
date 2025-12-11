# stack implementation using linked list
 
class Node:
    # initialize node 
    def __init__(self, data=None, next=None):
        self.data = data 
        self.next = next # pointer to next node

class Stack:
    # initialize stack
    def __init__(self, count=0):
        self.top = None # top of stack
        self.count = count

    # stack is empty
    def empty(self) -> bool:
        return self.top is None
    
    # push data on stack
    def push(self, data):
        node = Node(data, self.top)
        self.top = node 
        self.count += 1

    # get size of stack
    def getSize(self):
        return self.count

    # print stack
    def print_stack(self):
        if self.empty():
            print("Stack is Empty")
            return

        current = self.top
        print("Stack contents:\n")
        print("---")
        while current:
            print(current.data)
            current = current.next
        print("---")  # End of stack
    
    # pop data from stack
    def pop(self):
        if self.empty():
            print("Stack is empty")
            return

        data = self.top.data
        self.top = self.top.next
        self.count -= 1
        return data
    
    # peek at top of stack
    def peek(self):
        if self.empty():
            print("Stack is empty")
            return

        return self.top.data
    
    # clear stack
    def clear(self):
        self.top = None
        self.count = 0
        print("Stack cleared")
        return

    # search for data in stack  
    def search(self, data):
        current = self.top
        while current:
            if current.data == data:
                return True
            current = current.next
        return False    
    
    # reverse stack
    def reverse(self):
        prev = None
        current = self.top
        
        # reverse linked list
        while current:
            next_node = current.next
            current.next = prev
            prev = current
            current = next_node

        # update top
        self.top = prev
        print("Stack reversed")
        return

if __name__ == '__main__':
    # Menu driven program 
    stack = Stack()

    while True:
        print("\n----- Stack Menu -----")
        print("1. Push item")
        print("2. Print stack")
        print("3. Get size of stack")
        print("4. Pop item")
        print("5. Peek at top of stack")
        print("6. Clear stack")
        print("7. Search for item")
        print("8. Reverse stack")
        print("0. Exit")

        choice = input("Enter your choice: ")

        if choice == "1":
            item = input("Enter item to push: ")
            stack.push(item)
        elif choice == "2":
            stack.print_stack()
        elif choice == "3":
            print("Size of stack:", stack.getSize())
        elif choice == "4":
            stack.pop()
        elif choice == "5":
            print("Top of stack:", stack.peek())
        elif choice == "6":
            stack.clear()
        elif choice == "7":
            item = input("Enter item to search for: ")
            if stack.search(item):
                print(item, "found in stack")
            else:
                print(item, "not found in stack")
        elif choice == "8":
            stack.reverse()
        elif choice == "0":
            print("\nExiting program...")
            break
        else:
            print("Invalid choice! Please try again.")

