import pytest

from pages.inventory_page import InventoryPage


@pytest.mark.smoke
def test_inventory_is_displayed_after_login(logged_in_page):
    InventoryPage(logged_in_page).assert_loaded()


@pytest.mark.regression
def test_user_can_add_item_to_cart(logged_in_page):
    inventory = InventoryPage(logged_in_page)
    inventory.assert_loaded()
    inventory.add_first_item_to_cart()
    inventory.assert_cart_count("1")
