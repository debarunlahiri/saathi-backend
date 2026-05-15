# Notification APIs

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

### Create Notification

Endpoint:

```http
POST /api/notifications
```

API name: Create in-app notification.

Curl:

```bash
curl -X POST http://localhost:8080/api/notifications \
  -H "Authorization: Bearer <accessToken>" \
  -H "Content-Type: application/json" \
  -d '{
    "userId": 1,
    "title": "Partner assigned",
    "message": "A partner accepted your task",
    "notificationType": "TASK_ACCEPTED",
    "referenceId": "1"
  }'
```

Full request:

```json
{
  "userId": 1,
  "title": "Partner assigned",
  "message": "A partner accepted your task",
  "notificationType": "TASK_ACCEPTED",
  "referenceId": "1"
}
```

Full response:

```json
{
  "success": true,
  "message": "Notification created successfully",
  "data": {
    "id": 1,
    "userId": 1,
    "title": "Partner assigned",
    "message": "A partner accepted your task",
    "notificationType": "TASK_ACCEPTED",
    "referenceId": "1",
    "readStatus": false,
    "createdAt": "2026-05-15T05:35:00Z"
  }
}
```

### List User Notifications

Endpoint:

```http
GET /api/notifications/users/{userId}
```

API name: List notifications for user.

Curl:

```bash
curl -X GET http://localhost:8080/api/notifications/users/1 \
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
  "message": "Notifications fetched successfully",
  "data": [
    {
      "id": 1,
      "userId": 1,
      "title": "Partner assigned",
      "message": "A partner accepted your task",
      "notificationType": "TASK_ACCEPTED",
      "referenceId": "1",
      "readStatus": false,
      "createdAt": "2026-05-15T05:35:00Z"
    }
  ]
}
```

### Mark Notification Read

Endpoint:

```http
PUT /api/notifications/{id}/read
```

API name: Mark notification as read.

Curl:

```bash
curl -X PUT http://localhost:8080/api/notifications/1/read \
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
  "message": "Notification marked as read",
  "data": {
    "id": 1,
    "userId": 1,
    "title": "Partner assigned",
    "message": "A partner accepted your task",
    "notificationType": "TASK_ACCEPTED",
    "referenceId": "1",
    "readStatus": true,
    "createdAt": "2026-05-15T05:35:00Z"
  }
}
```
