# Wallet APIs

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

### Get Partner Wallet

Endpoint:

```http
GET /api/wallets/partners/{partnerId}
```

API name: Get partner wallet balance.

Curl:

```bash
curl -X GET http://localhost:8080/api/wallets/partners/1 \
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
  "message": "Wallet fetched successfully",
  "data": {
    "id": 1,
    "partnerId": 1,
    "availableBalance": 0,
    "pendingBalance": 54,
    "totalEarnings": 54,
    "totalWithdrawn": 0,
    "updatedAt": "2026-05-15T05:20:00Z"
  }
}
```

### Get Partner Earnings

Endpoint:

```http
GET /api/wallets/partners/{partnerId}/earnings
```

API name: List partner earnings.

Curl:

```bash
curl -X GET http://localhost:8080/api/wallets/partners/1/earnings \
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
  "message": "Earnings fetched successfully",
  "data": [
    {
      "id": 1,
      "partnerId": 1,
      "taskId": 1,
      "grossAmount": 59,
      "platformCommission": 5,
      "netAmount": 54,
      "earningStatus": "PENDING",
      "createdAt": "2026-05-15T05:20:00Z"
    }
  ]
}
```

### Create Partner Earning

Endpoint:

```http
POST /api/wallets/earnings
```

API name: Add earning to partner wallet.

Curl:

```bash
curl -X POST http://localhost:8080/api/wallets/earnings \
  -H "Authorization: Bearer <accessToken>" \
  -H "Content-Type: application/json" \
  -d '{
    "partnerId": 1,
    "taskId": 1,
    "grossAmount": 59,
    "platformCommission": 5
  }'
```

Full request:

```json
{
  "partnerId": 1,
  "taskId": 1,
  "grossAmount": 59,
  "platformCommission": 5
}
```

Full response:

```json
{
  "success": true,
  "message": "Earning created successfully",
  "data": {
    "id": 1,
    "partnerId": 1,
    "taskId": 1,
    "grossAmount": 59,
    "platformCommission": 5,
    "netAmount": 54,
    "earningStatus": "PENDING",
    "createdAt": "2026-05-15T05:20:00Z"
  }
}
```
