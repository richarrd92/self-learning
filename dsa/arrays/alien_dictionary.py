# Given a sequence of words written in the alien language, and the order of the alphabet
# return true if and only if the given words are sorted lexicographically in this alien language.

# test 1 : should return true
words = ["hello","leetcode"]
order = "hlabcdefgijkmnopqrstuvwxyz"

# test. 2 : Should return false
# words = ["word","world","row"]
# order = "worldabcefghijkmnpqstuvxyz"

def is_sorted(words: list, order: str) -> bool:
    # map the order chars 

    # order_map = {}
    # for i in range(len(order)):
    #     order_map[order[i]] = i

    # using comprehension list
    order_map = { char: idx for idx, char in enumerate(order)}

    # compare adjacent words
    for i in range(len(words)-1):
        word1 = words[i]
        word2 = words[i+1]

        # use min word len to prevent index error
        for j in range(min(len(word1), len(word2))):
            # compare char in adjancent words
            if word1[j] != word2[j]:
                # use the order list to validate
                if order_map[word1[j]] > order_map[word2[j]]:
                    return False
                break

        else:
            # chars matched until shortest word but longer word comes after shorter word
            if len(word1) > len(word2):
                return False
            
    return True # is sorted

# validate result
if is_sorted(words, order):
    print("the words are sorted based on the alien dictionary")
else:
    print("the words are not sorted based on the alien dictionary")