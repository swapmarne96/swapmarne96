import os

BASE_URL = os.getenv("BASE_URL", "https://www.saucedemo.com").rstrip("/")
USERNAME = os.getenv("APP_USERNAME", "standard_user")
PASSWORD = os.getenv("APP_PASSWORD", "secret_sauce")
