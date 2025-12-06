# Currency Converter API - Postman Collection Guide

## Import Instructions

1. Open Postman
2. Click "Import" button
3. Select "Raw text" tab
4. Paste the JSON below
5. Click "Import"

## Environment Setup

Create a new environment in Postman with these variables:

```
base_url: http://localhost:8080/api
api_key: (will be set after registration)
username: testuser
password: test123
```

## Postman Collection

```json
{
  "info": {
    "name": "Currency Converter API",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
  },
  "item": [
    {
      "name": "Auth",
      "item": [
        {
          "name": "Health Check",
          "request": {
            "method": "GET",
            "header": [],
            "url": {
              "raw": "{{base_url}}/auth/health",
              "host": ["{{base_url}}"],
              "path": ["auth", "health"]
            }
          }
        },
        {
          "name": "Register User",
          "event": [
            {
              "listen": "test",
              "script": {
                "exec": [
                  "if (pm.response.code === 201) {",
                  "    var jsonData = pm.response.json();",
                  "    pm.environment.set('api_key', jsonData.apiKey);",
                  "}"
                ]
              }
            }
          ],
          "request": {
            "method": "POST",
            "header": [
              {
                "key": "Content-Type",
                "value": "application/json"
              }
            ],
            "body": {
              "mode": "raw",
              "raw": "{\n  \"username\": \"{{username}}\",\n  \"password\": \"{{password}}\",\n  \"email\": \"user@example.com\"\n}"
            },
            "url": {
              "raw": "{{base_url}}/auth/register",
              "host": ["{{base_url}}"],
              "path": ["auth", "register"]
            }
          }
        },
        {
          "name": "Regenerate API Key",
          "request": {
            "method": "POST",
            "header": [
              {
                "key": "X-API-Key",
                "value": "{{api_key}}"
              }
            ],
            "url": {
              "raw": "{{base_url}}/auth/regenerate-api-key",
              "host": ["{{base_url}}"],
              "path": ["auth", "regenerate-api-key"]
            }
          }
        }
      ]
    },
    {
      "name": "Currency Conversion",
      "item": [
        {
          "name": "Convert Currency (USD to INR)",
          "request": {
            "method": "POST",
            "header": [
              {
                "key": "Content-Type",
                "value": "application/json"
              },
              {
                "key": "X-API-Key",
                "value": "{{api_key}}"
              }
            ],
            "body": {
              "mode": "raw",
              "raw": "{\n  \"fromCurrency\": \"USD\",\n  \"toCurrency\": \"INR\",\n  \"amount\": 100\n}"
            },
            "url": {
              "raw": "{{base_url}}/convert",
              "host": ["{{base_url}}"],
              "path": ["convert"]
            }
          }
        },
        {
          "name": "Convert Currency (EUR to GBP)",
          "request": {
            "method": "POST",
            "header": [
              {
                "key": "Content-Type",
                "value": "application/json"
              },
              {
                "key": "X-API-Key",
                "value": "{{api_key}}"
              }
            ],
            "body": {
              "mode": "raw",
              "raw": "{\n  \"fromCurrency\": \"EUR\",\n  \"toCurrency\": \"GBP\",\n  \"amount\": 50\n}"
            },
            "url": {
              "raw": "{{base_url}}/convert",
              "host": ["{{base_url}}"],
              "path": ["convert"]
            }
          }
        },
        {
          "name": "Get Conversion History",
          "request": {
            "method": "GET",
            "header": [
              {
                "key": "X-API-Key",
                "value": "{{api_key}}"
              }
            ],
            "url": {
              "raw": "{{base_url}}/convert/history",
              "host": ["{{base_url}}"],
              "path": ["convert", "history"]
            }
          }
        },
        {
          "name": "Get History by Currency Pair",
          "request": {
            "method": "GET",
            "header": [
              {
                "key": "X-API-Key",
                "value": "{{api_key}}"
              }
            ],
            "url": {
              "raw": "{{base_url}}/convert/history/USD/INR",
              "host": ["{{base_url}}"],
              "path": ["convert", "history", "USD", "INR"]
            }
          }
        }
      ]
    },
    {
      "name": "Exchange Rates",
      "item": [
        {
          "name": "Get All Rates (USD)",
          "request": {
            "method": "GET",
            "header": [
              {
                "key": "X-API-Key",
                "value": "{{api_key}}"
              }
            ],
            "url": {
              "raw": "{{base_url}}/rates/USD",
              "host": ["{{base_url}}"],
              "path": ["rates", "USD"]
            }
          }
        },
        {
          "name": "Get All Rates (EUR)",
          "request": {
            "method": "GET",
            "header": [
              {
                "key": "X-API-Key",
                "value": "{{api_key}}"
              }
            ],
            "url": {
              "raw": "{{base_url}}/rates/EUR",
              "host": ["{{base_url}}"],
              "path": ["rates", "EUR"]
            }
          }
        },
        {
          "name": "Get Specific Rate (USD to EUR)",
          "request": {
            "method": "GET",
            "header": [
              {
                "key": "X-API-Key",
                "value": "{{api_key}}"
              }
            ],
            "url": {
              "raw": "{{base_url}}/rates/USD/EUR",
              "host": ["{{base_url}}"],
              "path": ["rates", "USD", "EUR"]
            }
          }
        },
        {
          "name": "Get Specific Rate (GBP to JPY)",
          "request": {
            "method": "GET",
            "header": [
              {
                "key": "X-API-Key",
                "value": "{{api_key}}"
              }
            ],
            "url": {
              "raw": "{{base_url}}/rates/GBP/JPY",
              "host": ["{{base_url}}"],
              "path": ["rates", "GBP", "JPY"]
            }
          }
        }
      ]
    }
  ]
}
```

## Quick Testing Flow

1. **Health Check**: Verify API is running
2. **Register User**: Create account and save API key
3. **Convert Currency**: Test conversion (automatically saves API key)
4. **Get History**: View your conversion history
5. **Get Rates**: Check current exchange rates

## Notes

- The "Register User" request automatically saves the API key to your environment
- All authenticated requests use the X-API-Key header
- You can also use Basic Auth with username/password if preferred
