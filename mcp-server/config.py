import os
from dotenv import load_dotenv
load_dotenv()

DB_HOST = os.getenv("DB_HOST", "localhost")
DB_NAME = os.getenv("DB_NAME", "finance")
DB_USERNAME = os.getenv("DB_USER", "postgres")
DB_PASSWORD = os.getenv("DB_PASS", "password")

OPENAI_API_KEY = os.getenv("OPENAI_API_KEY")