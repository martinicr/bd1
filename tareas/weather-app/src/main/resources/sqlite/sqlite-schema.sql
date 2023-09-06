-- Table forecast
CREATE TABLE forecast(
  forecast_id INTEGER PRIMARY KEY AUTOINCREMENT,
  country_name TEXT NOT NULL,
  city_name TEXT NOT NULL,
  zip_code TEXT NOT NULL,
  forecast_date DATE NOT NULL,
  temperature REAL NOT NULL
);