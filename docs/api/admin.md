# Admin APIs

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

### Dashboard

Endpoint:

```http
GET /api/admin/dashboard
```

API name: Get admin dashboard counts.

Curl:

```bash
curl -X GET http://localhost:8080/api/admin/dashboard \
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
  "message": "Dashboard fetched successfully",
  "data": {
    "activeTasks": 12,
    "pendingPartners": 7,
    "openComplaints": 5,
    "paymentIssues": 2
  }
}
```

### Reports

Endpoint:

```http
GET /api/admin/reports
```

API name: Generate admin report counts.

Curl:

```bash
curl -X GET http://localhost:8080/api/admin/reports \
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
  "message": "Report generated successfully",
  "data": {
    "activeTasks": 12,
    "pendingPartners": 7,
    "openComplaints": 5,
    "paymentIssues": 2
  }
}
```

