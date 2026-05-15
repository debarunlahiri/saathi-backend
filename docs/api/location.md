# Location APIs

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

### Update Location

Endpoint:

```http
POST /api/locations/update
```

API name: Save partner location update.

Curl:

```bash
curl -X POST http://localhost:8080/api/locations/update \
  -H "Authorization: Bearer <accessToken>" \
  -H "Content-Type: application/json" \
  -d '{
    "taskId": 1,
    "partnerId": 1,
    "latitude": 28.5355,
    "longitude": 77.3910,
    "accuracy": 10
  }'
```

Full request:

```json
{
  "taskId": 1,
  "partnerId": 1,
  "latitude": 28.5355,
  "longitude": 77.3910,
  "accuracy": 10
}
```

Full response:

```json
{
  "success": true,
  "message": "Location updated successfully",
  "data": {
    "id": 1,
    "taskId": 1,
    "partnerId": 1,
    "latitude": 28.5355,
    "longitude": 77.3910,
    "accuracy": 10,
    "createdAt": "2026-05-15T05:30:00Z"
  }
}
```

### Get Latest Location

Endpoint:

```http
GET /api/locations/tasks/{taskId}/latest
```

API name: Get latest partner location for task.

Curl:

```bash
curl -X GET http://localhost:8080/api/locations/tasks/1/latest \
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
  "message": "Latest location fetched successfully",
  "data": {
    "id": 1,
    "taskId": 1,
    "partnerId": 1,
    "latitude": 28.5355,
    "longitude": 77.3910,
    "accuracy": 10,
    "createdAt": "2026-05-15T05:30:00Z"
  }
}
```

### Get Location History

Endpoint:

```http
GET /api/locations/tasks/{taskId}/history
```

API name: Get location history for task.

Curl:

```bash
curl -X GET http://localhost:8080/api/locations/tasks/1/history \
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
  "message": "Location history fetched successfully",
  "data": [
    {
      "id": 1,
      "taskId": 1,
      "partnerId": 1,
      "latitude": 28.5355,
      "longitude": 77.3910,
      "accuracy": 10,
      "createdAt": "2026-05-15T05:30:00Z"
    }
  ]
}
```
