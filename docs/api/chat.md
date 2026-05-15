# Chat APIs

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

### Create Chat Room

Endpoint:

```http
POST /api/chats/rooms
```

API name: Create task chat room.

Curl:

```bash
curl -X POST http://localhost:8080/api/chats/rooms \
  -H "Authorization: Bearer <accessToken>" \
  -H "Content-Type: application/json" \
  -d '{
    "taskId": 1,
    "customerId": 1,
    "partnerId": 1
  }'
```

Full request:

```json
{
  "taskId": 1,
  "customerId": 1,
  "partnerId": 1
}
```

Full response:

```json
{
  "success": true,
  "message": "Chat room created successfully",
  "data": {
    "id": "TASK_1",
    "roomId": "TASK_1",
    "taskId": 1,
    "customerId": 1,
    "partnerId": 1,
    "status": "ACTIVE",
    "createdAt": "2026-05-15T05:25:00Z"
  }
}
```

### Get Chat Room

Endpoint:

```http
GET /api/chats/rooms/{taskId}
```

API name: Get chat room by task id.

Curl:

```bash
curl -X GET http://localhost:8080/api/chats/rooms/1 \
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
  "message": "Chat room fetched successfully",
  "data": {
    "id": "TASK_1",
    "roomId": "TASK_1",
    "taskId": 1,
    "customerId": 1,
    "partnerId": 1,
    "status": "ACTIVE",
    "createdAt": "2026-05-15T05:25:00Z"
  }
}
```

### Get Chat Messages

Endpoint:

```http
GET /api/chats/messages/{roomId}
```

API name: Get messages for a room.

Curl:

```bash
curl -X GET http://localhost:8080/api/chats/messages/TASK_1 \
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
  "message": "Chat messages fetched successfully",
  "data": [
    {
      "id": "msg_1",
      "roomId": "TASK_1",
      "taskId": 1,
      "senderId": 1,
      "senderRole": "CUSTOMER",
      "messageType": "TEXT",
      "content": "Please reach by 10:30",
      "attachmentUrl": null,
      "status": "SENT",
      "sentAt": "2026-05-15T05:26:00Z",
      "deliveredAt": null,
      "readAt": null
    }
  ]
}
```
