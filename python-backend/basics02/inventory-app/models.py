# Import necessary SQLAlchemy modules for defining models
from sqlalchemy import Column, Integer, String, Float, TIMESTAMP, ForeignKey, func  # type: ignore 
from sqlalchemy.orm import relationship # type: ignore 
from database import Base, engine  # Import Base for model inheritance and engine for database connection

# Define the Item model, representing the 'items' table in the database
class Item(Base):
    __tablename__ = "items"  # Table name in the database

    id = Column(Integer, primary_key=True, index=True)  # Unique identifier for each item
    name = Column(String, index=True, unique=True, nullable=False)  # Item name (must be unique)
    description = Column(String, nullable=False)  # Detailed description of the item
    price = Column(Float, nullable=False)  # Price of the item
    quantity = Column(Integer, nullable=False)  # Available stock quantity
    category_id = Column(Integer, ForeignKey("categories.id"), nullable=False)  # Foreign key linking to Category

    # Relationship: Each item belongs to one category (one-to-many relationship)
    category = relationship("Category", back_populates="items")  

# Define the User model, representing the 'users' table in the database
class User(Base):
    __tablename__ = "users"  # Table name in the database

    id = Column(Integer, primary_key=True, autoincrement=True)  # Unique identifier, auto-increments
    first_name = Column(String(50), nullable=False)  # User's first name (required)
    last_name = Column(String(50), nullable=False)  # User's last name (required)
    email = Column(String(100), unique=True, nullable=False)  # Unique email for each user
    password_hash = Column(String(255), nullable=False)  # Hashed password for security
    created_at = Column(TIMESTAMP, server_default=func.now())  # Auto-set timestamp on creation

# Define the Category model, representing the 'categories' table in the database
class Category(Base):
    __tablename__ = "categories"  # Table name in the database

    id = Column(Integer, primary_key=True, autoincrement=True)  # Unique identifier for each category
    name = Column(String(255), unique=True, nullable=False)  # Category name (must be unique)

    # Relationship: A category can have multiple items (one-to-many relationship)
    items = relationship("Item", back_populates="category", cascade="all, delete")  

# If this script is executed directly, create all defined tables in the database
if __name__ == "__main__":
    Base.metadata.create_all(engine)  # Generate database tables if they do not exist
