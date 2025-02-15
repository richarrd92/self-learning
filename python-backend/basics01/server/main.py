import mariadb # database connector
import os # access system env variables
from flask import Flask, jsonify
from flask_cors import CORS # API access from different domains
from dotenv import load_dotenv 

# Load environment variables
load_dotenv()

app = Flask(__name__)
cors = CORS(app, origins='*')

# Database connection function
def get_db_connection():
    try:
        connection = mariadb.connect(
            user=os.getenv("DB_USER"),
            password=os.getenv("DB_PASSWORD"),
            host=os.getenv("DB_HOST"),
            database=os.getenv("DB_NAME")
        )
        return connection
    except mariadb.Error as e:
        print(f"Error connecting to MariaDB: {e}")
        return None

# API Route to Fetch Products
@app.route("/api/products", methods=['GET'])
def products():
    connection = get_db_connection()
    if connection is None:
        return jsonify({"error": "Database connection failed"}), 500

    cursor = connection.cursor()
    cursor.execute("SELECT product_name FROM products")  # Adjust table name if needed
    product_list = [row[0] for row in cursor.fetchall()]
    
    cursor.close()
    connection.close()

    return jsonify({"product": product_list})

# API Route to Fetch Students
@app.route("/api/students", methods=['GET'])
def students():
    connection = get_db_connection()
    if connection is None:
        return jsonify({"error": "Database connection failed"}), 500
    
    cursor = connection.cursor()
    cursor.execute("SELECT student_name FROM students")  # Adjust table name if needed
    product_list = [row[0] for row in cursor.fetchall()]
    
    cursor.close()
    connection.close()

    return jsonify({"student": product_list})
    

if __name__ == "__main__":
    app.run(debug=True, port=8080)
