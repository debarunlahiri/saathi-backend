# Customer APIs

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

### Save Customer Profile

Endpoint:

```http
POST /api/customers
```

API name: Create or update customer profile.

Curl:

```bash
curl -X POST http://localhost:8080/api/customers \
  -H "Authorization: Bearer <accessToken>" \
  -H "Content-Type: application/json" \
  -d '{
    "identityUserId": 1,
    "fullName": "Amit Sharma",
    "mobileNumber": "9876543210",
    "email": "amit@example.com",
    "defaultAddress": "A-101, Green Society, Noida",
    "defaultLatitude": 28.5355,
    "defaultLongitude": 77.3910,
    "emergencyContact": "9876501111"
  }'
```

Full request:

```json
{
  "identityUserId": 1,
  "fullName": "Amit Sharma",
  "mobileNumber": "9876543210",
  "email": "amit@example.com",
  "defaultAddress": "A-101, Green Society, Noida",
  "defaultLatitude": 28.5355,
  "defaultLongitude": 77.3910,
  "emergencyContact": "9876501111"
}
```

Full response:

```json
{
  "success": true,
  "message": "Customer profile saved successfully",
  "data": {
    "id": 1,
    "identityUserId": 1,
    "fullName": "Amit Sharma",
    "mobileNumber": "9876543210",
    "email": "amit@example.com",
    "defaultAddress": "A-101, Green Society, Noida",
    "defaultLatitude": 28.5355,
    "defaultLongitude": 77.3910,
    "emergencyContact": "9876501111",
    "createdAt": "2026-05-15T00:30:00Z",
    "updatedAt": "2026-05-15T00:30:00Z"
  }
}
```

### Get Customer Profile

Endpoint:

```http
GET /api/customers/{customerId}
```

API name: Get customer profile by customer id.

Curl:

```bash
curl -X GET http://localhost:8080/api/customers/1 \
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
  "message": "Customer profile fetched successfully",
  "data": {
    "id": 1,
    "identityUserId": 1,
    "fullName": "Amit Sharma",
    "mobileNumber": "9876543210",
    "email": "amit@example.com",
    "defaultAddress": "A-101, Green Society, Noida",
    "defaultLatitude": 28.5355,
    "defaultLongitude": 77.3910,
    "emergencyContact": "9876501111"
  }
}
```
