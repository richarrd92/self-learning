from sqlalchemy import create_engine 
from sqlalchemy.orm import sessionmaker
from sqlalchemy.ext.declarative import declarative_base

# This is the database connection URL.
# We're using SQLite, and the database file will be created in the current directory as 'todosapp.db'.
SQLALCHEMY_DATABASE_URL = 'sqlite:///./todosapp.db'

# Create the SQLAlchemy engine which manages the actual connection to the database.
# 'check_same_thread=False' allows SQLite to be accessed from different threads (important for FastAPI's async handling).
engine = create_engine(
    SQLALCHEMY_DATABASE_URL, 
    connect_args={'check_same_thread': False}  # Required only for SQLite
)

# Create a configured "Session" class.
# Each instance of SessionLocal is a database session, used to interact with the database.
# - autocommit=False: We control when changes are committed.
# - autoflush=False: We control when data is sent to the database.
# - bind=engine: This binds the session to the engine we created.
SessionLocal = sessionmaker(
    autocommit=False,
    autoflush=False,
    bind=engine
)

# Base class for all ORM models.
# Any class that inherits from Base will be treated as a table in the database.
# You use this to define models like 'User', 'Todo', etc.
Base = declarative_base()
