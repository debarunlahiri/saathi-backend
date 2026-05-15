# Task APIs

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

### Create Task

Endpoint:

```http
POST /api/tasks
```

API name: Customer creates a new task.

Curl:

```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Authorization: Bearer <accessToken>" \
  -H "Content-Type: application/json" \
  -d '{
    "customerId": 1,
    "serviceCategoryId": 1,
    "title": "Stand in queue at clinic",
    "description": "Collect token for doctor appointment",
    "pickupAddress": "City Clinic, Sector 62, Noida",
    "pickupLatitude": 28.5355,
    "pickupLongitude": 77.3910,
    "dropAddress": null,
    "taskDateTime": "2026-05-15T10:30:00+05:30",
    "estimatedPrice": null
  }'
```

Full request:

```json
{
  "customerId": 1,
  "serviceCategoryId": 1,
  "title": "Stand in queue at clinic",
  "description": "Collect token for doctor appointment",
  "pickupAddress": "City Clinic, Sector 62, Noida",
  "pickupLatitude": 28.5355,
  "pickupLongitude": 77.3910,
  "dropAddress": null,
  "taskDateTime": "2026-05-15T10:30:00+05:30",
  "estimatedPrice": null
}
```

Full response:

```json
{
  "success": true,
  "message": "Task created successfully",
  "data": {
    "id": 1,
    "customerId": 1,
    "partnerId": null,
    "serviceCategoryId": 1,
    "title": "Stand in queue at clinic",
    "description": "Collect token for doctor appointment",
    "pickupAddress": "City Clinic, Sector 62, Noida",
    "pickupLatitude": 28.5355,
    "pickupLongitude": 77.3910,
    "dropAddress": null,
    "taskDateTime": "2026-05-15T10:30:00+05:30",
    "estimatedPrice": 59,
    "finalPrice": null,
    "taskStatus": "SEARCHING_PARTNER",
    "paymentStatus": "PENDING",
    "createdAt": "2026-05-15T05:00:00Z"
  }
}
```

### Get Task

Endpoint:

```http
GET /api/tasks/{taskId}
```

API name: Get task by id.

Curl:

```bash
curl -X GET http://localhost:8080/api/tasks/1 \
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
  "message": "Task fetched successfully",
  "data": {
    "id": 1,
    "customerId": 1,
    "partnerId": 1,
    "serviceCategoryId": 1,
    "title": "Stand in queue at clinic",
    "taskStatus": "PARTNER_ASSIGNED",
    "paymentStatus": "PENDING",
    "estimatedPrice": 59,
    "finalPrice": null
  }
}
```

### List Customer Tasks

Endpoint:

```http
GET /api/tasks/customer/{customerId}
```

API name: List tasks created by customer.

Curl:

```bash
curl -X GET http://localhost:8080/api/tasks/customer/1 \
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
  "message": "Customer tasks fetched successfully",
  "data": [
    {
      "id": 1,
      "customerId": 1,
      "partnerId": 1,
      "title": "Stand in queue at clinic",
      "taskStatus": "PARTNER_ASSIGNED",
      "estimatedPrice": 59
    }
  ]
}
```

### List Partner Tasks

Endpoint:

```http
GET /api/tasks/partner/{partnerId}
```

API name: List tasks accepted by partner.

Curl:

```bash
curl -X GET http://localhost:8080/api/tasks/partner/1 \
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
  "message": "Partner tasks fetched successfully",
  "data": [
    {
      "id": 1,
      "customerId": 1,
      "partnerId": 1,
      "title": "Stand in queue at clinic",
      "taskStatus": "PARTNER_ASSIGNED",
      "estimatedPrice": 59
    }
  ]
}
```

### List Nearby Tasks

Endpoint:

```http
GET /api/tasks/nearby
```

API name: List tasks searching for partner.

Curl:

```bash
curl -X GET http://localhost:8080/api/tasks/nearby \
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
  "message": "Nearby tasks fetched successfully",
  "data": [
    {
      "id": 2,
      "customerId": 1,
      "partnerId": null,
      "title": "Medicine pickup",
      "taskStatus": "SEARCHING_PARTNER",
      "pickupLatitude": 28.5355,
      "pickupLongitude": 77.3910
    }
  ]
}
```

### Accept Task

Endpoint:

```http
POST /api/tasks/{taskId}/accept
```

API name: Partner accepts a task.

Curl:

```bash
curl -X POST http://localhost:8080/api/tasks/1/accept \
  -H "Authorization: Bearer <accessToken>" \
  -H "Content-Type: application/json" \
  -d '{
    "partnerId": 1
  }'
```

Full request:

```json
{
  "partnerId": 1
}
```

Full response:

```json
{
  "success": true,
  "message": "Task accepted successfully",
  "data": {
    "id": 1,
    "customerId": 1,
    "partnerId": 1,
    "taskStatus": "PARTNER_ASSIGNED",
    "estimatedPrice": 59
  }
}
```

### Auto Assign Task

Endpoint:

```http
POST /api/tasks/{taskId}/assign
```

API name: Assign nearest eligible online approved partner.

Curl:

```bash
curl -X POST http://localhost:8080/api/tasks/1/assign \
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
  "message": "Task assigned successfully",
  "data": {
    "id": 1,
    "customerId": 1,
    "partnerId": 1,
    "taskStatus": "PARTNER_ASSIGNED",
    "estimatedPrice": 59
  }
}
```

### Update Task Status

Endpoint:

```http
PUT /api/tasks/{taskId}/status
```

API name: Update task lifecycle status.

Curl:

```bash
curl -X PUT http://localhost:8080/api/tasks/1/status \
  -H "Authorization: Bearer <accessToken>" \
  -H "Content-Type: application/json" \
  -d '{
    "status": "TASK_COMPLETED",
    "changedBy": 1,
    "remarks": "Task completed with proof uploaded"
  }'
```

Full request:

```json
{
  "status": "TASK_COMPLETED",
  "changedBy": 1,
  "remarks": "Task completed with proof uploaded"
}
```

Full response:

```json
{
  "success": true,
  "message": "Task status updated successfully",
  "data": {
    "id": 1,
    "customerId": 1,
    "partnerId": 1,
    "taskStatus": "TASK_COMPLETED",
    "estimatedPrice": 59,
    "finalPrice": 59
  }
}
```

### Active Task Count

Endpoint:

```http
GET /api/tasks/admin/counts/active
```

API name: Count active tasks for admin dashboard.

Curl:

```bash
curl -X GET http://localhost:8080/api/tasks/admin/counts/active \
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
  "message": "Active task count fetched successfully",
  "data": 12
}
```
