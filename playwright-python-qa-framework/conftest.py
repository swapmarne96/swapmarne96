from pathlib import Path

import pytest
from playwright.sync_api import Page

from config.settings import BASE_URL, PASSWORD, USERNAME
from pages.login_page import LoginPage


@pytest.fixture
def app_url() -> str:
    return BASE_URL


@pytest.fixture
def credentials() -> tuple[str, str]:
    return USERNAME, PASSWORD


@pytest.fixture
def logged_in_page(page: Page, app_url: str, credentials: tuple[str, str]) -> Page:
    username, password = credentials
    login = LoginPage(page)
    login.open(app_url)
    login.login(username, password)
    return page


@pytest.hookimpl(hookwrapper=True)
def pytest_runtest_makereport(item, call):
    outcome = yield
    report = outcome.get_result()

    if report.when == "call" and report.failed:
        page = item.funcargs.get("page")
        if page is not None:
            artifact_dir = Path("artifacts")
            artifact_dir.mkdir(exist_ok=True)
            safe_name = item.nodeid.replace("/", "_").replace("::", "_")
            page.screenshot(path=str(artifact_dir / f"{safe_name}.png"), full_page=True)
