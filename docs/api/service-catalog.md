# Service Catalog APIs

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

### List Active Categories

Endpoint:

```http
GET /api/catalog/categories
```

API name: List active service categories.

Curl:

```bash
curl -X GET http://localhost:8080/api/catalog/categories \
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
  "message": "Categories fetched successfully",
  "data": [
    {
      "id": 1,
      "name": "Queue Standing",
      "code": "QUEUE_STANDING",
      "description": "Partner stands in queue for the customer",
      "iconUrl": "https://cdn.example.com/icons/queue.png",
      "basePrice": 19,
      "active": true
    }
  ]
}
```

### Create Category

Endpoint:

```http
POST /api/catalog/categories
```

API name: Create service category.

Curl:

```bash
curl -X POST http://localhost:8080/api/catalog/categories \
  -H "Authorization: Bearer <accessToken>" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Queue Standing",
    "code": "QUEUE_STANDING",
    "description": "Partner stands in queue for the customer",
    "iconUrl": "https://cdn.example.com/icons/queue.png",
    "basePrice": 19,
    "active": true
  }'
```

Full request:

```json
{
  "name": "Queue Standing",
  "code": "QUEUE_STANDING",
  "description": "Partner stands in queue for the customer",
  "iconUrl": "https://cdn.example.com/icons/queue.png",
  "basePrice": 19,
  "active": true
}
```

Full response:

```json
{
  "success": true,
  "message": "Category created successfully",
  "data": {
    "id": 1,
    "name": "Queue Standing",
    "code": "QUEUE_STANDING",
    "description": "Partner stands in queue for the customer",
    "iconUrl": "https://cdn.example.com/icons/queue.png",
    "basePrice": 19,
    "active": true
  }
}
```

### Update Category

Endpoint:

```http
PUT /api/catalog/categories/{id}
```

API name: Update service category.

Curl:

```bash
curl -X PUT http://localhost:8080/api/catalog/categories/1 \
  -H "Authorization: Bearer <accessToken>" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Queue Standing",
    "code": "QUEUE_STANDING",
    "description": "Queue service with partner tracking",
    "iconUrl": "https://cdn.example.com/icons/queue.png",
    "basePrice": 25,
    "active": true
  }'
```

Full request:

```json
{
  "name": "Queue Standing",
  "code": "QUEUE_STANDING",
  "description": "Queue service with partner tracking",
  "iconUrl": "https://cdn.example.com/icons/queue.png",
  "basePrice": 25,
  "active": true
}
```

Full response:

```json
{
  "success": true,
  "message": "Category updated successfully",
  "data": {
    "id": 1,
    "name": "Queue Standing",
    "code": "QUEUE_STANDING",
    "description": "Queue service with partner tracking",
    "iconUrl": "https://cdn.example.com/icons/queue.png",
    "basePrice": 25,
    "active": true
  }
}
```
