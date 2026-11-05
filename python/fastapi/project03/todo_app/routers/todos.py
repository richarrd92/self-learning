from typing import Annotated
from pydantic import BaseModel, Field
from sqlalchemy.orm import Session
from fastapi import APIRouter, Depends, HTTPException, Path
from starlette import status
from models import Todos
from database import SessionLocal
from .auth import get_current_user

# Initialize the API router for todo-related routes
router = APIRouter()

# Dependency function to create a new database session
# This will be injected into route handlers and automatically closed
def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

# Typed dependencies for database and authenticated user
db_dependency = Annotated[Session, Depends(get_db)]
user_dependency = Annotated[dict, Depends(get_current_user)]

# Pydantic model for validating and structuring incoming todo creation/update data
class TodoRequest(BaseModel):
    title: str = Field(min_length=3)
    description: str = Field(min_length=3, max_length=100)
    priority: int = Field(gt=0, lt=6)
    complete: bool

# Get all todos for the authenticated user
@router.get("/", status_code=status.HTTP_200_OK)
async def read_all(user: user_dependency, db: db_dependency):
    # Reject request if user is not authenticated
    if user is None:
        raise HTTPException(status_code=401, detail='Authentication Failed')
    # Return only todos owned by the currently authenticated user
    return db.query(Todos).filter(Todos.owner_id == user.get('id')).all()

# Get a specific todo by ID for the authenticated user
@router.get("/todo/{todo_id}", status_code=status.HTTP_200_OK)
async def read_todo(user: user_dependency, db: db_dependency, todo_id: int = Path(gt=0)):
    # Reject request if user is not authenticated 
    if user is None:
        raise HTTPException(status_code=401, detail='Authentication Failed')

    # Retrieve the todo by ID and ensure it belongs to the current user
    todo_model = db.query(Todos).filter(Todos.id == todo_id)\
        .filter(Todos.owner_id == user.get('id')).first()
    
    # Return the todo if found, else raise a 404
    if todo_model is not None:
        return todo_model
    raise HTTPException(status_code=404, detail='Todo not found.')

# Create a new todo item for the authenticated user
@router.post("/todo", status_code=status.HTTP_201_CREATED)
async def create_todo(user: user_dependency, db: db_dependency,
                      todo_request: TodoRequest):
    if user is None:
        raise HTTPException(status_code=401, detail='Authentication Failed')
    
    # Create a new Todos instance from the request and assign it to the current user
    todo_model = Todos(**todo_request.model_dump(), owner_id=user.get('id'))

    # Save the new todo to the database
    db.add(todo_model)
    db.commit()

# Update an existing todo item
@router.put("/todo/{todo_id}", status_code=status.HTTP_204_NO_CONTENT)
async def update_todo(user: user_dependency, db: db_dependency,
                      todo_request: TodoRequest,
                      todo_id: int = Path(gt=0)):
    
    # Reject request if user is not authenticated
    if user is None:
        raise HTTPException(status_code=401, detail='Authentication Failed')

    # Find the todo to update, ensuring it belongs to the current user
    todo_model = db.query(Todos).filter(Todos.id == todo_id)\
        .filter(Todos.owner_id == user.get('id')).first()
    
    # If not found, return 404
    if todo_model is None:
        raise HTTPException(status_code=404, detail='Todo not found.')

    # Update fields from the request body
    todo_model.title = todo_request.title
    todo_model.description = todo_request.description
    todo_model.priority = todo_request.priority
    todo_model.complete = todo_request.complete

    # Save the updated todo
    db.add(todo_model)
    db.commit()

# Delete a todo item for the current user
@router.delete("/todo/{todo_id}", status_code=status.HTTP_204_NO_CONTENT)
async def delete_todo(user: user_dependency, db: db_dependency, todo_id: int = Path(gt=0)):
    if user is None:
        raise HTTPException(status_code=401, detail='Authentication Failed')

    # Find the todo to delete, ensuring it belongs to the current user
    todo_model = db.query(Todos).filter(Todos.id == todo_id)\
        .filter(Todos.owner_id == user.get('id')).first()
    
    # If not found, return 404
    if todo_model is None:
        raise HTTPException(status_code=404, detail='Todo not found.')
    
    # Perform the delete operation and commit
    db.query(Todos).filter(Todos.id == todo_id).filter(Todos.owner_id == user.get('id')).delete()
    db.commit()












