from typing import Annotated
from pydantic import BaseModel, Field
from sqlalchemy.orm import Session
from fastapi import APIRouter, Depends, HTTPException, Path
from starlette import status
from models import Users
from database import SessionLocal
from .auth import get_current_user
from passlib.context import CryptContext

# Create a router for user-specific endpoints
router = APIRouter(
    prefix='/user',
    tags=['user']
)

# Dependency function to get a database session
# Ensures session is closed properly after request
def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

# Dependency annotations for injecting the DB session and current user
db_dependency = Annotated[Session, Depends(get_db)]
user_dependency = Annotated[dict, Depends(get_current_user)]

# Password hashing context using bcrypt
bcrypt_context = CryptContext(schemes=['bcrypt'], deprecated='auto')

# Pydantic model to validate the password change request payload
class UserVerification(BaseModel):
    password: str
    new_password: str = Field(min_length=6)

# Route to get the currently authenticated user's details
@router.get('/', status_code=status.HTTP_200_OK)
async def get_user(user: user_dependency, db: db_dependency):
    # Ensure the request is authenticated
    if user is None:
        raise HTTPException(status_code=401, detail='Authentication Failed')
    
    # Fetch and return the user record based on the user ID from the token
    return db.query(Users).filter(Users.id == user.get('id')).first()

# Route to allow a user to change their password
@router.put("/password", status_code=status.HTTP_204_NO_CONTENT)
async def change_password(user: user_dependency, db: db_dependency,
                          user_verification: UserVerification):
    # Ensure the request is authenticated
    if user is None:
        raise HTTPException(status_code=401, detail='Authentication Failed')
    
    # Fetch the user from the database using the ID from the token
    user_model = db.query(Users).filter(Users.id == user.get('id')).first()
    
    # Verify the current password is correct
    if not bcrypt_context.verify(user_verification.password, user_model.hashed_password):
        raise HTTPException(status_code=401, detail='Error on password change')
    
    # Hash and update the user's password with the new one
    user_model.hashed_password = bcrypt_context.hash(user_verification.new_password)
    db.add(user_model)
    db.commit()







