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
GET /api/chats/messages/{roomId}?page=0&size=50
```

API name: Get paginated messages for a room (most recent first).

Curl:

```bash
curl -X GET "http://localhost:8080/api/chats/messages/TASK_1?page=0&size=50" \
  -H "Authorization: Bearer <accessToken>"
```

Query parameters:

| Param | Type | Default | Description |
|---|---|---|---|
| page | int | 0 | Zero-based page number |
| size | int | 50 | Messages per page |

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

### WebSocket Events

The chat service uses STOMP over WebSocket. Connect to `ws://localhost:8080/ws` with SockJS.

| Destination | Direction | Description |
|---|---|---|
| `/app/chat.send` | Client -> Server | Send a message |
| `/app/chat.typing` | Client -> Server | Typing indicator |
| `/app/chat.delivered` | Client -> Server | Mark messages as delivered |
| `/app/chat.read` | Client -> Server | Mark a message as read |
| `/topic/chat/{roomId}` | Server -> Client | New messages broadcast |
| `/topic/chat/{roomId}/typing` | Server -> Client | Typing indicator broadcast |
| `/topic/chat/{roomId}/delivered` | Server -> Client | Delivery confirmation broadcast |
| `/topic/chat/{roomId}/read` | Server -> Client | Read receipt broadcast |

Mark delivered payload:

```json
{
  "roomId": "TASK_1",
  "senderId": 1
}
```

Mark read payload (messageId in `content` field):

```json
{
  "roomId": "TASK_1",
  "content": "msg_1"
}
```

Delivery and read statuses are persisted to MongoDB (`deliveredAt`, `readAt` timestamps).
```
