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

The file service supports three storage providers: Local, S3, and Cloudinary.
The default provider is **Local** (files stored in `./data/files`).

Required environment variables per provider:

| Provider | Variables |
|---|---|
| Local | `FILE_STORAGE_ROOT` (default: `./data/files`) |
| S3 | `S3_BUCKET`, `S3_REGION` |
| Cloudinary | `CLOUDINARY_CLOUD_NAME`, `CLOUDINARY_API_KEY`, `CLOUDINARY_API_SECRET` |

### Upload File

Endpoint:

```http
POST /api/files/upload
```

API name: Upload a file via multipart form data.

Curl:

```bash
curl -X POST http://localhost:8080/api/files/upload \
  -H "Authorization: Bearer <accessToken>" \
  -F "file=@/path/to/aadhaar.jpg" \
  -F "ownerUserId=2" \
  -F "referenceType=KYC" \
  -F "referenceId=1"
```

Full request:

```text
Multipart form data:
  file:            (binary)
  ownerUserId:     2
  referenceType:   KYC
  referenceId:     1
```

Full response:

```json
{
  "success": true,
  "message": "File uploaded successfully",
  "data": {
    "id": 1,
    "ownerUserId": 2,
    "referenceType": "KYC",
    "referenceId": "1",
    "fileName": "aadhaar.jpg",
    "contentType": "image/jpeg",
    "storageUrl": "/api/files/download/uuid_aadhaar.jpg",
    "createdAt": "2026-05-15T05:40:00Z"
  }
}
```

### Download File

Endpoint:

```http
GET /api/files/download/{fileName}
```

API name: Download an uploaded file by its storage file name.

Curl:

```bash
curl -X GET http://localhost:8080/api/files/download/uuid_aadhaar.jpg \
  -H "Authorization: Bearer <accessToken>" \
  -o aadhaar.jpg
```

Full request:

```text
No request body.
```

Full response:

```text
Binary file content with Content-Disposition: attachment header.
Returns HTTP 404 if file not found.
```

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
