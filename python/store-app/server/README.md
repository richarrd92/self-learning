# Backend Project Flow Guide

## **Introduction**
This document serves as a step-by-step guide to building a backend project using SQLAlchemy, ensuring that all essential components are properly structured. The guide assumes no prior knowledge and provides explanations for every stage.

---

## **1. Project Setup**
Before writing any code, we need to set up the development environment properly.

### **Step 1: Create a Virtual Environment**
A virtual environment isolates dependencies and prevents conflicts between projects.
```bash
python -m venv venv
source venv/bin/activate  # On macOS/Linux
venv\Scripts\activate  # On Windows
```

### **Step 2: Install Dependencies**
We need SQLAlchemy and other essential packages for database interaction.
```bash
pip install sqlalchemy psycopg2  # For PostgreSQL (replace with mysqlclient for MySQL)
pip install alembic  # For database migrations
```

---

## **2. Project Structure**
To maintain a clean structure, organize files as follows:
```
backend_project/
│── database.py
│── models.py
│── main.py
│── config.py
│── requirements.txt
│── .env
```
- `database.py`: Manages database connection
- `models.py`: Defines database tables
- `main.py`: Entry point for running the backend
- `config.py`: Stores configuration settings (e.g., database URL)
- `requirements.txt`: Lists all dependencies
- `.env`: Stores environment variables securely

---

## **3. Database Connection**
### **Step 1: Define Database Configuration**
Create a `config.py` file to store the database URL.
```python
import os
from dotenv import load_dotenv

load_dotenv()
DATABASE_URL = os.getenv("DATABASE_URL")
```

### **Step 2: Establish Database Connection**
Create a `database.py` file to manage the connection.
```python
from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker
from config import DATABASE_URL

engine = create_engine(DATABASE_URL)
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)
Base = declarative_base()
```

---

## **4. Creating Models (Tables)**
We define our database tables using SQLAlchemy ORM in `models.py`.

```python
from sqlalchemy import Column, Integer, String, Float, TIMESTAMP, ForeignKey, func
from sqlalchemy.orm import relationship
from database import Base

class Item(Base):
    __tablename__ = "items"
    id = Column(Integer, primary_key=True, index=True)
    name = Column(String, unique=True, nullable=False)
    description = Column(String, nullable=False)
    price = Column(Float, nullable=False)
    quantity = Column(Integer, nullable=False)
    category_id = Column(Integer, ForeignKey("categories.id"), nullable=False)
    category = relationship("Category", back_populates="items")

class User(Base):
    __tablename__ = "users"
    id = Column(Integer, primary_key=True, autoincrement=True)
    first_name = Column(String(50), nullable=False)
    last_name = Column(String(50), nullable=False)
    email = Column(String(100), unique=True, nullable=False)
    password_hash = Column(String(255), nullable=False)
    created_at = Column(TIMESTAMP, server_default=func.now())

class Category(Base):
    __tablename__ = "categories"
    id = Column(Integer, primary_key=True, autoincrement=True)
    name = Column(String(255), unique=True, nullable=False)
    items = relationship("Item", back_populates="category", cascade="all, delete")
```
---

## **5. Creating Tables in the Database**
After defining models, we need to create the tables in the database.

Modify `main.py` to include:
```python
from database import engine, Base
from models import Item, User, Category

if __name__ == "__main__":
    Base.metadata.create_all(engine)  # Creates tables if they don't exist
    print("Database tables created successfully!")
```
Run the script:
```bash
python main.py
```

---

## **6. Running the Project**
### **Step 1: Start the Database Server**
If using PostgreSQL, start the service:
```bash
sudo service postgresql start  # Linux/macOS
net start postgresql  # Windows
```

### **Step 2: Set Environment Variables**
Add the database URL to `.env`:
```ini
DATABASE_URL=postgresql://user:password@localhost/dbname
```

### **Step 3: Run the Backend**
Execute the main script:
```bash
python main.py
```
This will initialize the database and ensure everything is working correctly.

---

## **7. Next Steps**
- Implement API routes using FastAPI or Flask
- Add authentication (JWT, OAuth, etc.)
- Implement database migrations with Alembic
- Deploy to a cloud service (Heroku, AWS, etc.)


