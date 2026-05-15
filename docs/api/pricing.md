# Pricing APIs

Base URL through API gateway:

```text
http://localhost:8080
```

Protected APIs should be called with:

```http
Authorization: Bearer <accessToken>
Content-Type: application/json
```

Auth APIs are public. WebSocket endpoints are documented separately in `docs/architecture.md`.

### Estimate Price

Endpoint:

```http
POST /api/pricing/estimate
```

API name: Calculate task price estimate.

Curl:

```bash
curl -X POST http://localhost:8080/api/pricing/estimate \
  -H "Authorization: Bearer <accessToken>" \
  -H "Content-Type: application/json" \
  -d '{
    "serviceCategoryId": 1,
    "distanceKm": 2.5,
    "waitingMinutes": 20,
    "urgent": true,
    "discount": 10
  }'
```

Full request:

```json
{
  "serviceCategoryId": 1,
  "distanceKm": 2.5,
  "waitingMinutes": 20,
  "urgent": true,
  "discount": 10
}
```

Full response:

```json
{
  "success": true,
  "message": "Price estimated successfully",
  "data": {
    "basePrice": 19,
    "distanceCharge": 15,
    "waitingCharge": 10,
    "urgencyCharge": 20,
    "platformFee": 5,
    "discount": 10,
    "finalPrice": 59
  }
}
```

### List Pricing Rules

Endpoint:

```http
GET /api/pricing/rules
```

API name: List pricing rules.

Curl:

```bash
curl -X GET http://localhost:8080/api/pricing/rules \
  -H "Authorization: Bearer <accessToken>"
```

Full request:

```text
No request body.
```

Full response:

```json
{
  "success": true,
  "message": "Pricing rules fetched successfully",
  "data": [
    {
      "id": 1,
      "serviceCategoryId": 1,
      "basePrice": 19,
      "includedDistanceKm": 1,
      "perKmCharge": 10,
      "includedWaitingMinutes": 15,
      "perWaitingUnitMinutes": 10,
      "perWaitingUnitCharge": 5,
      "urgentCharge": 20,
      "platformFee": 5,
      "active": true
    }
  ]
}
```

### Create Pricing Rule

Endpoint:

```http
POST /api/pricing/rules
```

API name: Create pricing rule.

Curl:

```bash
curl -X POST http://localhost:8080/api/pricing/rules \
  -H "Authorization: Bearer <accessToken>" \
  -H "Content-Type: application/json" \
  -d '{
    "serviceCategoryId": 1,
    "basePrice": 19,
    "includedDistanceKm": 1,
    "perKmCharge": 10,
    "includedWaitingMinutes": 15,
    "perWaitingUnitMinutes": 10,
    "perWaitingUnitCharge": 5,
    "urgentCharge": 20,
    "platformFee": 5,
    "active": true
  }'
```

Full request:

```json
{
  "serviceCategoryId": 1,
  "basePrice": 19,
  "includedDistanceKm": 1,
  "perKmCharge": 10,
  "includedWaitingMinutes": 15,
  "perWaitingUnitMinutes": 10,
  "perWaitingUnitCharge": 5,
  "urgentCharge": 20,
  "platformFee": 5,
  "active": true
}
```

Full response:

```json
{
  "success": true,
  "message": "Pricing rule created successfully",
  "data": {
    "id": 1,
    "serviceCategoryId": 1,
    "basePrice": 19,
    "includedDistanceKm": 1,
    "perKmCharge": 10,
    "includedWaitingMinutes": 15,
    "perWaitingUnitMinutes": 10,
    "perWaitingUnitCharge": 5,
    "urgentCharge": 20,
    "platformFee": 5,
    "active": true
  }
}
```
