# INVENTORY APP

## Database Setup

### Log into MariaDB:
```sh
mysql -u root -p
```

### Reload the privilege tables from disk into memory:
```sql
FLUSH PRIVILEGES;  -- Clear previous privileges
```

### Connect using localhost (Create a separate user for localhost):
```sql
CREATE USER 'inventory_db_users'@'localhost' IDENTIFIED BY 'inventory_app_1234';
GRANT ALL PRIVILEGES ON inventory_database.* TO 'inventory_db_users'@'localhost';
FLUSH PRIVILEGES;
```

### Restart MariaDB (macOS):
```sh
brew services restart mariadb
```

### Reload Database:
```sh
python3 database.py
```

### Create a new user with access from any remote IP:
```sql
CREATE USER 'username'@'%' IDENTIFIED BY 'password';
```

### Grant access to the store database:
```sql
GRANT ALL PRIVILEGES ON inventory_database.* TO 'inventory_db_users'@'%' WITH GRANT OPTION;
```

### MariaDB Connection String:
```plaintext
mysql+mariadb://DB_USER:DB_PASSWORD@DB_HOST/DB_NAME
```
Example:
```plaintext
DATABASE_STRING="mysql+pymysql://username:password@localhost/database_name"
```

---
## Tech Stack
- **Backend (API):** FastAPI + SQLAlchemy (Database ORM)
- **Frontend (UI):** React + TailwindCSS
- **Database:** PostgreSQL / MariaDB
- **Authentication:** JWT (for admin authentication)

---
## Backend Setup (FastAPI + PostgreSQL)

### Create a project folder and activate virtual environment:
```sh
mkdir inventory-app 
cd inventory-app
python3 -m venv venv
source venv/bin/activate  # Mac/Linux
venv\Scripts\activate   # Windows
```
### Install required dependencies:
```sh
pip install fastapi uvicorn sqlalchemy psycopg2-binary pydantic bcrypt jwt python-multipart python-dotenv mariadb pymysql
```

### Generate requirements.txt:
```sh
pip freeze > requirements.txt
```

### Install dependencies from requirements.txt:
```sh
pip install -r requirements.txt
```

### Keep dependencies updated:
```sh
pip install --upgrade -r requirements.txt
```


#### **Key Libraries and Their Purpose:**

- **FastAPI:** High-performance API framework for Python.
- **Uvicorn:** ASGI server to run FastAPI applications.
- **SQLAlchemy:** ORM for managing database interactions.
- **psycopg2-binary:** PostgreSQL database adapter for Python.
- **Pydantic:** Data validation and settings management.
- **bcrypt:** Password hashing for secure authentication.
- **PyJWT:** JSON Web Token implementation for authentication.

---
## Database Models

### Create `models.py`:
```python
from sqlalchemy import Column, Integer, String, Float
from database import Base

class Item(Base):
    __tablename__ = "items"
    id = Column(Integer, primary_key=True, index=True)
    name = Column(String, index=True, unique=True)
    description = Column(String)
    price = Column(Float)
    quantity = Column(Integer)
    category = Column(String)
```

### Create `database.py` (Database Connection):
```python
from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker

DATABASE_URL = "postgresql://username:password@localhost/inventory_db"

engine = create_engine(DATABASE_URL)
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)
Base = declarative_base()

def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()
```

---
## API Routes (FastAPI)

### Create `main.py`:
```python
from fastapi import FastAPI, Depends, HTTPException
from sqlalchemy.orm import Session
from models import Item
from database import get_db

app = FastAPI()

@app.get("/items")
def get_items(db: Session = Depends(get_db)):
    return db.query(Item).all()

@app.post("/admin/items")
def add_item(name: str, description: str, price: float, quantity: int, category: str, db: Session = Depends(get_db)):
    item = Item(name=name, description=description, price=price, quantity=quantity, category=category)
    db.add(item)
    db.commit()
    return {"message": "Item added!"}

@app.put("/admin/items/{item_id}")
def update_item(item_id: int, name: str, price: float, quantity: int, db: Session = Depends(get_db)):
    item = db.query(Item).filter(Item.id == item_id).first()
    if not item:
        raise HTTPException(status_code=404, detail="Item not found")
    
    item.name = name
    item.price = price
    item.quantity = quantity
    db.commit()
    return {"message": "Item updated!"}

@app.delete("/admin/items/{item_id}")
def delete_item(item_id: int, db: Session = Depends(get_db)):
    item = db.query(Item).filter(Item.id == item_id).first()
    if not item:
        raise HTTPException(status_code=404, detail="Item not found")
    
    db.delete(item)
    db.commit()
    return {"message": "Item deleted!"}
```

---
## Running the Application

### Start the FastAPI server:
```sh
uvicorn main:app --reload
```

### Access API Documentation:
- OpenAPI Docs: `http://127.0.0.1:8000/docs`
- Redoc UI: `http://127.0.0.1:8000/redoc`

---
## Notes
- Ensure MariaDB/PostgreSQL is running before launching the app.
- Use `.env` files to store sensitive credentials (DB passwords, JWT secrets).
- Implement authentication middleware to restrict admin routes.

---
## Future Enhancements
- Implement frontend with React & TailwindCSS.
- Add authentication (JWT-based login/logout).
- Introduce role-based access control (Admin vs. User).
- Deploy on cloud platforms (AWS, DigitalOcean, etc.).

