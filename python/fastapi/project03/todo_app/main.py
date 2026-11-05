from fastapi import FastAPI
import models
from database import engine
from routers import auth, todos, admin, users

# Initialize the FastAPI application instance
app = FastAPI()

# Create all tables in the database (based on the models defined using Base)
# This line reads all classes inheriting from Base in models.py and creates their corresponding tables in the DB.
# If the tables already exist, it does nothing — so it's safe to run on startup.
models.Base.metadata.create_all(bind=engine)

# Include the routers
app.include_router(auth.router)
app.include_router(todos.router)
app.include_router(admin.router)
app.include_router(users.router)