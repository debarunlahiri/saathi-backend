# Complaint APIs

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

### Create Complaint

Endpoint:

```http
POST /api/complaints
```

API name: Create customer or partner complaint.

Curl:

```bash
curl -X POST http://localhost:8080/api/complaints \
  -H "Authorization: Bearer <accessToken>" \
  -H "Content-Type: application/json" \
  -d '{
    "taskId": 1,
    "raisedBy": 1,
    "complaintType": "PAYMENT_ISSUE",
    "description": "Payment was debited but task still shows pending"
  }'
```

Full request:

```json
{
  "taskId": 1,
  "raisedBy": 1,
  "complaintType": "PAYMENT_ISSUE",
  "description": "Payment was debited but task still shows pending"
}
```

Full response:

```json
{
  "success": true,
  "message": "Complaint created successfully",
  "data": {
    "id": 1,
    "taskId": 1,
    "raisedBy": 1,
    "complaintType": "PAYMENT_ISSUE",
    "description": "Payment was debited but task still shows pending",
    "status": "OPEN",
    "adminRemarks": null,
    "createdAt": "2026-05-15T05:45:00Z",
    "updatedAt": "2026-05-15T05:45:00Z"
  }
}
```

### List My Complaints

Endpoint:

```http
GET /api/complaints/users/{raisedBy}
```

API name: List complaints raised by user.

Curl:

```bash
curl -X GET http://localhost:8080/api/complaints/users/1 \
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
  "message": "Complaints fetched successfully",
  "data": [
    {
      "id": 1,
      "taskId": 1,
      "raisedBy": 1,
      "complaintType": "PAYMENT_ISSUE",
      "description": "Payment was debited but task still shows pending",
      "status": "OPEN",
      "adminRemarks": null
    }
  ]
}
```

### List All Complaints

Endpoint:

```http
GET /api/complaints
```

API name: Admin lists all complaints.

Curl:

```bash
curl -X GET http://localhost:8080/api/complaints \
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
  "message": "Complaints fetched successfully",
  "data": [
    {
      "id": 1,
      "taskId": 1,
      "raisedBy": 1,
      "complaintType": "PAYMENT_ISSUE",
      "description": "Payment was debited but task still shows pending",
      "status": "OPEN",
      "adminRemarks": null
    }
  ]
}
```

### Update Complaint Status

Endpoint:

```http
PUT /api/complaints/{id}/status
```

API name: Admin updates complaint status.

Curl:

```bash
curl -X PUT http://localhost:8080/api/complaints/1/status \
  -H "Authorization: Bearer <accessToken>" \
  -H "Content-Type: application/json" \
  -d '{
    "status": "RESOLVED",
    "adminRemarks": "Payment status was reconciled"
  }'
```

Full request:

```json
{
  "status": "RESOLVED",
  "adminRemarks": "Payment status was reconciled"
}
```

Full response:

```json
{
  "success": true,
  "message": "Complaint updated successfully",
  "data": {
    "id": 1,
    "taskId": 1,
    "raisedBy": 1,
    "complaintType": "PAYMENT_ISSUE",
    "description": "Payment was debited but task still shows pending",
    "status": "RESOLVED",
    "adminRemarks": "Payment status was reconciled"
  }
}
```

### Open Complaint Count

Endpoint:

```http
GET /api/complaints/admin/counts/open
```

API name: Count open complaints for admin dashboard.

Curl:

```bash
curl -X GET http://localhost:8080/api/complaints/admin/counts/open \
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
  "message": "Open complaint count fetched successfully",
  "data": 5
}
```
