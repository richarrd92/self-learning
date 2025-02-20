# simple program 

input_list = []
interrogatives = ("how", "when", "how", "what")

while True:
    user_input = input("Say something: ")
    if user_input == "\end":
        for sentence in input_list:
            print(sentence, end=" ")
        break
    else:
        if user_input.startswith(interrogatives):
            user_input += "?"
        else:
            user_input += "."

        input_list.append(user_input.capitalize())