from database import Base
from sqlalchemy import Column, Integer, String, Boolean, ForeignKey

# Users table definition
# This class defines the structure of the 'users' table in the database.
class Users(Base):
    # Sets the actual table name in the database to 'users'
    __tablename__ = 'users'

    id = Column(Integer, primary_key=True, index=True) # Primary key column with index for faster lookup
    email = Column(String, unique=True) # Email must be unique for each user
    username = Column(String, unique=True) # Username must be unique for each user
    first_name = Column(String) # Optional personal information
    last_name = Column(String) # Optional personal information
    hashed_password = Column(String) # Stores hashed password (never store raw passwords)
    is_active = Column(Boolean, default=True) # Boolean flag to indicate if the user account is active
    role = Column(String) # User role (e.g., "admin", "user", etc.)

# Todos table definition
# This class defines the structure of the 'todos' table in the database.
class Todos(Base):
    
    __tablename__ = 'todos' # Sets the actual table name in the database to 'todos'

    id = Column(Integer, primary_key=True, index=True) # Primary key column with index for faster lookup
    title = Column(String) # Title of the task or to-do item
    description = Column(String) # Detailed description of the task
    priority = Column(Integer) # Integer representing priority (e.g., 1 = high, 3 = low)
    complete = Column(Boolean, default=False) # Boolean flag indicating whether the task is complete

    # Foreign key linking the todo item to a user
    # 'users.id' means this references the 'id' column in the 'users' table
    owner_id = Column(Integer, ForeignKey("users.id"))
