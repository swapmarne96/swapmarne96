import pytest

from config.settings import PASSWORD
from pages.inventory_page import InventoryPage
from pages.login_page import LoginPage


@pytest.mark.smoke
def test_valid_user_can_log_in(page, app_url, credentials):
    username, password = credentials
    login = LoginPage(page)
    login.open(app_url)
    login.login(username, password)
    InventoryPage(page).assert_loaded()


@pytest.mark.regression
def test_invalid_password_shows_error(page, app_url, credentials):
    username, _ = credentials
    login = LoginPage(page)
    login.open(app_url)
    login.login(username, "incorrect-password")
    login.assert_error_contains("Username and password do not match")
