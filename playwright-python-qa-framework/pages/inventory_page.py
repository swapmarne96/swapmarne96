from playwright.sync_api import Page, expect


class InventoryPage:
    def __init__(self, page: Page) -> None:
        self.page = page
        self.title = page.locator('[data-test="title"]')
        self.inventory_items = page.locator('[data-test="inventory-item"]')
        self.cart_badge = page.locator('[data-test="shopping-cart-badge"]')
        self.first_add_to_cart_button = page.locator('[data-test^="add-to-cart-"]').first

    def assert_loaded(self) -> None:
        expect(self.title).to_have_text("Products")
        expect(self.inventory_items.first).to_be_visible()

    def add_first_item_to_cart(self) -> None:
        self.first_add_to_cart_button.click()

    def assert_cart_count(self, count: str) -> None:
        expect(self.cart_badge).to_have_text(count)
