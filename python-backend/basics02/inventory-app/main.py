# Import necessary modules from FastAPI
from fastapi import FastAPI, Depends, HTTPException  # FastAPI framework and dependencies handling # type: ignore 
from sqlalchemy.orm import Session  # SQLAlchemy session management # type: ignore 
from models import Item, User, Category  # Import database models
from database import get_db  # Import database session dependency
import bcrypt  # For handling password hashing and security # type: ignore 

# Initialize FastAPI application
app = FastAPI()

# Landing page endpoint
@app.get("/")
def read_root():
    """Root endpoint that returns a welcome message."""
    return {"message": "Welcome to the Inventory API!"}

# Retrieve all users
@app.get("/users")
def get_users(db: Session = Depends(get_db)):
    """
    Fetch all users from the database.
    Uses dependency injection to get a database session.
    """
    try:
        users = db.query(User).all()  # Fetch all user records
        return users
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))  # Handle unexpected errors

# Retrieve all items
@app.get("/items")
def get_items(db: Session = Depends(get_db)):
    """
    Fetch all items from the database.
    """
    try:
        items = db.query(Item).all()
        return items
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))

# Retrieve all categories
@app.get("/categories")
def get_categories(db: Session = Depends(get_db)):
    """
    Fetch all categories from the database.
    """
    try:
        categories = db.query(Category).all()
        return categories
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))

# Add a new item (Admin Only)
@app.post("/admin/items")
def add_item(name: str, description: str, price: float, quantity: int, category: str, db: Session = Depends(get_db)):
    """
    Add a new item to the inventory.
    Only accessible to admins.
    """
    item = Item(name=name, description=description, price=price, quantity=quantity, category=category)
    db.add(item)  # Add item to the database session
    db.commit()  # Commit transaction to save changes
    db.refresh(item)  # Refresh to get the latest state from the database
    return {"message": "Item added!", "item": item}

# Update an existing item
@app.put("/admin/items/{item_id}")
def update_item(item_id: int, name: str, description: str, price: float, quantity: int, category: str, db: Session = Depends(get_db)):
    """
    Update an item's details by its ID.
    """
    item = db.query(Item).filter(Item.id == item_id).first()  # Find item by ID
    if not item:
        raise HTTPException(status_code=404, detail="Item not found")  # Handle item not found
    
    # Update item fields
    item.name = name
    item.description = description
    item.price = price
    item.quantity = quantity
    item.category = category
    db.commit()  # Commit the changes
    db.refresh(item)  # Ensure updated values are returned
    return {"message": "Item updated!", "item": item}

# Delete an item
@app.delete("/admin/items/{item_id}")
def delete_item(item_id: int, db: Session = Depends(get_db)):
    """
    Delete an item from the inventory by its ID.
    """
    item = db.query(Item).filter(Item.id == item_id).first()
    if not item:
        raise HTTPException(status_code=404, detail="Item not found")  # Handle item not found
    
    db.delete(item)  # Remove item from the database
    db.commit()  # Commit the deletion
    return {"message": "Item deleted!"}

# Run the FastAPI app when executed directly
if __name__ == "__main__":
    import uvicorn  # ASGI server for running FastAPI # type: ignore 
    uvicorn.run("main:app", host="127.0.0.1", port=8000, reload=True)

# Notes:
# - The API will be accessible at http://127.0.0.1:8000
# - The "reload=True" option allows automatic reloading when code changes.
# - This script sets up routes for managing users, items, and categories.
# - Admin routes should ideally have authentication for security.
