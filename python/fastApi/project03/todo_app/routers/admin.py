from typing import Annotated
from pydantic import BaseModel, Field
from sqlalchemy.orm import Session
from fastapi import APIRouter, Depends, HTTPException, Path
from starlette import status
from models import Todos
from database import SessionLocal
from .auth import get_current_user

# Create a router specifically for admin routes
router = APIRouter(
    prefix='/admin',
    tags=['admin']
)

# Dependency function to get a database session
# Ensures session is closed after request
def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

# Dependency annotations to inject database session and current user
db_dependency = Annotated[Session, Depends(get_db)]
user_dependency = Annotated[dict, Depends(get_current_user)]

# Route for admins to retrieve all todo items
# Only accessible if the authenticated user has the 'admin' role
@router.get("/todo", status_code=status.HTTP_200_OK)
async def read_all(user: user_dependency, db: db_dependency):
    # Verify that the user is authenticated and is an admin
    if user is None or user.get('user_role') != 'admin':
        raise HTTPException(status_code=401, detail='Authentication Failed')
    # Return all todo items from the database
    return db.query(Todos).all()

# Route for admins to delete a specific todo item by ID
# Only accessible if the authenticated user has the 'admin' role
@router.delete("/todo/{todo_id}", status_code=status.HTTP_204_NO_CONTENT)
async def delete_todo(user: user_dependency, db: db_dependency, todo_id: int = Path(gt=0)):
    # Verify that the user is authenticated and is an admin
    if user is None or user.get('user_role') != 'admin':
        raise HTTPException(status_code=401, detail='Authentication Failed')
    
    # Attempt to find the todo item in the database
    todo_model = db.query(Todos).filter(Todos.id == todo_id).first()
    if todo_model is None:
        raise HTTPException(status_code=404, detail='Todo not found.')
    
    # Delete the todo item and commit the change to the database
    db.query(Todos).filter(Todos.id == todo_id).delete()
    db.commit()







