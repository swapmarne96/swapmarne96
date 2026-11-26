# Playwright Python QA Framework

A practical UI automation framework built with **Python, Playwright, and Pytest**. It demonstrates Page Object Model design, reusable browser fixtures, smoke/regression markers, failure screenshots, HTML reporting, and CI execution with GitHub Actions.

> Independent portfolio project using the public SauceDemo demo application and demo credentials. No employer or client code or data is included.

## Tech stack

- Python 3.11+
- Playwright
- Pytest and pytest-playwright
- pytest-html
- pytest-xdist
- GitHub Actions

## Structure

```text
playwright-python-qa-framework/
├── config/settings.py
├── pages/
│   ├── login_page.py
│   └── inventory_page.py
├── tests/
│   ├── test_login.py
│   └── test_inventory.py
├── conftest.py
├── pytest.ini
└── requirements.txt
```

## Run locally

```bash
python -m venv .venv
# Windows: .venv\Scripts\activate
# macOS/Linux: source .venv/bin/activate
pip install -r requirements.txt
python -m playwright install chromium
pytest
```

HTML report:

```bash
pytest --html=reports/report.html --self-contained-html
```

Smoke tests: `pytest -m smoke`

Parallel execution: `pytest -n auto`

## Configuration

| Variable | Default | Purpose |
|---|---|---|
| BASE_URL | https://www.saucedemo.com | Demo application |
| APP_USERNAME | standard_user | Demo login |
| APP_PASSWORD | secret_sauce | Demo password |

## Scenarios

- Successful login
- Invalid password error
- Inventory visibility
- Add item to cart

## Next improvements

API setup/teardown, environment profiles, Allure reporting, accessibility checks, Docker execution, and combined API/UI workflows.
