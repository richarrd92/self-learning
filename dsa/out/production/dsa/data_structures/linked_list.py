# Singly linked list implementation

class Node:
    # initialize node of linked list
    def __init__(self, data=None, next=None):
        self.data = data # data of node
        self.next = next # pointer to next node

class LinkedList:
    # initialize empty linked list
    def __init__(self, node_count=0):
        self.head = None
        self.node_count = node_count # track number of nodes in linked list

    # insert node at the begining of linked list
    def insert_begin(self, data):
        # create new Node
        node = Node(data, self.head)  # new node points to head node
        self.head = node # reasign head pointer to new node
        self.node_count += 1
        return

    # insert node at the end of linked list
    def insert_end(self, data):
        # list is empty
        if self.empty():
            self.head = Node(data, self.head)
            self.node_count += 1
            return

        # list is not empty - must iterate to end
        temp_head = self.head
        while temp_head.next:
            temp_head = temp_head.next

        # end of list reached
        temp_head.next = Node(data, None) # insert node at the end
        self.node_count += 1
        return
    
    # insert node at given position of linked list
    def insert_position(self, data, position):
        # Validate position
        if position > self.node_count or position < 1:
            print("Invalid insertion index")
            return 

        # Special case: Insert at the beginning
        if position == 1:
            self.insert_begin(data)
            return

        # Iterate to the node just before the insertion position
        curr_node = self.head
        prev_node = None
        tracker = 1
        while tracker < position:  # Stop at the previous node
            prev_node = curr_node
            curr_node = curr_node.next
            tracker += 1

        # Insert node
        prev_node.next = Node(data, curr_node)
        self.node_count += 1  # Increment node count
    
    # Linked list is empty
    def empty(self) -> bool:
        return self.head is None
    
    # returns count of nodes in linked list
    def length(self) -> int:
        return self.node_count

    # remove node at the start of linked list
    def remove_start(self):
        # linked list is empty
        if self.empty():
            print("Linked List is Empty")
            return
        
        # linked list contains node(s)
        # case 1: contains one node 
        if self.node_count == 1:
            self.head.data == None # erase data
            self.node_count -= 1 # decrement linked list node count 

        # case 2: contains multiple nodes
        temp_head = self.head.next
        self.head.data = None # erase data
        self.node_count -= 1 # decrement linked list node count
        self.head = temp_head  # update the head of linked list

    # remove node at given position in the linked list
    def remove_position(self, position: int):
        # linked list is empty
        if self.empty():
            print("Linked List is Empty")
            return 
        
        # Validate position
        if position > self.node_count or position < 1:
            print("Invalid insertion index")
            return 
        
        # linked list contains node(s)
        # case 1: contains one node 
        if self.node_count == 1:
            self.head.data == None # erase data
            self.node_count -= 1 # decrement linked list node count 

        # case 2: contains multiple nodes
        curr_node = self.head
        prev_node = None
        tracker = 1
        # traverse to node
        while tracker < position:
            prev_node = curr_node
            curr_node = curr_node.next
            tracker += 1

        curr_node.data = None  # erase data
        prev_node.next = curr_node.next # form new link
        curr_node.next = None # break old link
        self.node_count -= 1 # decrement node count 


    # Reverse Linked list
    def reverse_list(self):
        if self.empty():
            print("Linked List is Empty")
            return
        
        # Reverse linked list
        prev_node = None
        curr_node = self.head

        # iterate until next node is None
        while curr_node:
            next_node = curr_node.next # store the remaining length of list
            curr_node.next = prev_node
            prev_node = curr_node
            curr_node = next_node

        # update head of linked list
        self.head = prev_node 
        print("Linked List Reversed")
        return
    

    # Search Linked List
    def search_list(self, data):
        if self.empty():
            print("Linked List is Empty")
            return

        # search for data in linked list
        temp_head = self.head
        position = 1
        while temp_head:
            if temp_head.data == data:
                print(f"Found {data} at position {position}")
                return
            temp_head = temp_head.next
            position += 1   

        print(f"{data} not found in Linked List")
        return
    
    
    # Print the Linked List
    def print_list(self):
        if self.empty():
            print("Linked List is empty")
            return 
        
        position = 1  # Track position
        temp_head = self.head
        while temp_head:
            print(f"({position}:{temp_head.data})", end=" --> ")
            temp_head = temp_head.next
            position += 1  # Increment position tracker
        print("None")  # Indicate the end of the list
        print()


if __name__ == '__main__':
    # Menu driven program 
    linked_list = LinkedList()

    while True:
        print("\n----- Linked List Menu -----")
        print("1. Insert at Beginning")
        print("2. Insert at End")
        print("3. Insert at Position")
        print("4. Get Linked list length")
        print("5. Remove from Start")
        print("6. Remove from Position")
        print("7. Print Linked List")
        print("8. Reverse Linked List")
        print("9. Search Linked List")
        print("0. Exit")
        
        choice = input("\nEnter your choice: ")

        if choice == '1':
            data = int(input("Enter value to insert at the beginning: "))
            linked_list.insert_begin(data)
        elif choice == '2':
            data = int(input("Enter value to insert at the end: "))
            linked_list.insert_end(data)
        elif choice == '3':
            data = int(input("Enter value to insert: "))
            position = int(input("Enter position: "))
            linked_list.insert_position(data, position)
        elif choice == '4':
            print("The length of linked list is: ", linked_list.length())
        elif choice == '5':
            linked_list.remove_start()
        elif choice == '6':
            position = int(input("Enter position: "))
            linked_list.remove_position(position)
        elif choice == '7':
            linked_list.print_list()
        elif choice == '8':
            linked_list.reverse_list()
        elif choice == '9':
            data = int(input("Enter value to search: "))
            linked_list.search_list(data)
        elif choice == '0':
            print("\nExiting program...")
            break
        else:
            print("Invalid choice! Please try again.")
