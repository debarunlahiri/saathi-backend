# File APIs

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

### Register File

Endpoint:

```http
POST /api/files
```

API name: Register an uploaded file URL against a reference.

Curl:

```bash
curl -X POST http://localhost:8080/api/files \
  -H "Authorization: Bearer <accessToken>" \
  -H "Content-Type: application/json" \
  -d '{
    "ownerUserId": 2,
    "referenceType": "KYC",
    "referenceId": "1",
    "fileName": "aadhaar.jpg",
    "contentType": "image/jpeg",
    "storageUrl": "https://files.example.com/kyc/aadhaar.jpg"
  }'
```

Full request:

```json
{
  "ownerUserId": 2,
  "referenceType": "KYC",
  "referenceId": "1",
  "fileName": "aadhaar.jpg",
  "contentType": "image/jpeg",
  "storageUrl": "https://files.example.com/kyc/aadhaar.jpg"
}
```

Full response:

```json
{
  "success": true,
  "message": "File registered successfully",
  "data": {
    "id": 1,
    "ownerUserId": 2,
    "referenceType": "KYC",
    "referenceId": "1",
    "fileName": "aadhaar.jpg",
    "contentType": "image/jpeg",
    "storageUrl": "https://files.example.com/kyc/aadhaar.jpg",
    "createdAt": "2026-05-15T05:40:00Z"
  }
}
```

### Get Files By Reference

Endpoint:

```http
GET /api/files/references/{referenceType}/{referenceId}
```

API name: List files attached to a reference.

Curl:

```bash
curl -X GET http://localhost:8080/api/files/references/KYC/1 \
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
  "message": "Files fetched successfully",
  "data": [
    {
      "id": 1,
      "ownerUserId": 2,
      "referenceType": "KYC",
      "referenceId": "1",
      "fileName": "aadhaar.jpg",
      "contentType": "image/jpeg",
      "storageUrl": "https://files.example.com/kyc/aadhaar.jpg",
      "createdAt": "2026-05-15T05:40:00Z"
    }
  ]
}
```
