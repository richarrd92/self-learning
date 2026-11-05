from fastapi import Body, FastAPI

app = FastAPI()

# sample data -> mimics database
BOOKS = [
    {'title': 'Title One', 'author': 'Author One', 'category': 'science'},
    {'title': 'Title Two', 'author': 'Author Two', 'category': 'science'},
    {'title': 'Title Three', 'author': 'Author Three', 'category': 'history'},
    {'title': 'Title Four', 'author': 'Author Four', 'category': 'math'},
    {'title': 'Title Five', 'author': 'Author Five', 'category': 'math'},
    {'title': 'Title Six', 'author': 'Author Two', 'category': 'math'}
]

# ---- GET HTTP REQUEST METHODS ----
# this is the equivalent of the read functionality in CRUD operations

# returns all books
@app.get("/books")
async def read_all_books():
    return BOOKS

# returns specific book using path parameter
# ie Dynamic parameter
@app.get("/books/{book_title}")
async def read_book(book_title: str):
    for book in BOOKS:
        if book.get('title').casefold() == book_title.casefold(): # casefold() -> convert to lowercase
            return book

# get data by query parameter
@app.get("/books/")
async def read_category_by_query(category: str):
    books_to_return = []
    for book in BOOKS:
        if book.get('category').casefold() == category.casefold(): # finds match by category
            books_to_return.append(book)
    return books_to_return # returns list of books


# Get all books from a specific author using path or query parameters
@app.get("/books/byauthor/")
async def read_books_by_author_path(author: str):
    books_to_return = []
    for book in BOOKS:
        if book.get('author').casefold() == author.casefold():
            books_to_return.append(book)

    return books_to_return

# Get all books from a specific author and filter by category
@app.get("/books/{book_author}/")
async def read_author_category_by_query(book_author: str, category: str):
    books_to_return = []
    for book in BOOKS:
        # use \ tells python theres a new line continuation
        if book.get('author').casefold() == book_author.casefold() and \
                book.get('category').casefold() == category.casefold():
            books_to_return.append(book)

    return books_to_return

# ---- POST HTTP REQUEST METHODS ----
# this is the equivalent of the create functionality in CRUD operations
# data is passed in as a payload/body
# must import Body from fastapi


# create a new book 
# receives data to create a new book as payload
# Adds the new book to list of books
# Data/payload/body must be of the same structure
@app.post("/books/create_book")
async def create_book(new_book=Body()):
    BOOKS.append(new_book)


# ---- PUT HTTP REQUEST METHODS ----
# this is the equivalent of the update functionality in CRUD operations
# data is passed in as a payload/body similar to post method
# Difference is put updates already existing data

# updates already existing book
# receives data to create a new book as payload
# find matching book by title
# update the match with payload data
@app.put("/books/update_book")
async def update_book(updated_book=Body()):
    for i in range(len(BOOKS)):
        if BOOKS[i].get('title').casefold() == updated_book.get('title').casefold():
            BOOKS[i] = updated_book


# ---- DELETE HTTP REQUEST METHODS ----
# this is the equivalent of the delete functionality in CRUD operations
# finds match of data in the database or data structure
# deletes the data upon request


# find matching book by title
# deletes existing book
@app.delete("/books/delete_book/{book_title}")
async def delete_book(book_title: str):
    for i in range(len(BOOKS)):
        if BOOKS[i].get('title').casefold() == book_title.casefold():
            BOOKS.pop(i)
            break



























