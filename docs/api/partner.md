# Partner APIs

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

### Save Partner Profile

Endpoint:

```http
POST /api/partners
```

API name: Create or update partner profile.

Curl:

```bash
curl -X POST http://localhost:8080/api/partners \
  -H "Authorization: Bearer <accessToken>" \
  -H "Content-Type: application/json" \
  -d '{
    "identityUserId": 2,
    "fullName": "Rahul Verma",
    "mobileNumber": "9876500001",
    "email": "rahul@example.com",
    "address": "Sector 62, Noida",
    "serviceRadiusKm": 3
  }'
```

Full request:

```json
{
  "identityUserId": 2,
  "fullName": "Rahul Verma",
  "mobileNumber": "9876500001",
  "email": "rahul@example.com",
  "address": "Sector 62, Noida",
  "serviceRadiusKm": 3
}
```

Full response:

```json
{
  "success": true,
  "message": "Partner profile saved successfully",
  "data": {
    "id": 1,
    "identityUserId": 2,
    "fullName": "Rahul Verma",
    "mobileNumber": "9876500001",
    "email": "rahul@example.com",
    "address": "Sector 62, Noida",
    "kycStatus": "PENDING",
    "availabilityStatus": "OFFLINE",
    "currentLatitude": null,
    "currentLongitude": null,
    "serviceRadiusKm": 3,
    "averageRating": 0
  }
}
```

### Get Partner Profile

Endpoint:

```http
GET /api/partners/{partnerId}
```

API name: Get partner profile by partner id.

Curl:

```bash
curl -X GET http://localhost:8080/api/partners/1 \
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
  "message": "Partner fetched successfully",
  "data": {
    "id": 1,
    "identityUserId": 2,
    "fullName": "Rahul Verma",
    "mobileNumber": "9876500001",
    "email": "rahul@example.com",
    "address": "Sector 62, Noida",
    "kycStatus": "APPROVED",
    "availabilityStatus": "ONLINE",
    "currentLatitude": 28.5355,
    "currentLongitude": 77.3910,
    "serviceRadiusKm": 3,
    "averageRating": 4.8
  }
}
```

### Submit Partner KYC

Endpoint:

```http
POST /api/partners/{partnerId}/kyc
```

API name: Submit partner KYC documents.

Curl:

```bash
curl -X POST http://localhost:8080/api/partners/1/kyc \
  -H "Authorization: Bearer <accessToken>" \
  -H "Content-Type: application/json" \
  -d '{
    "aadhaarUrl": "https://files.example.com/kyc/aadhaar.jpg",
    "panUrl": "https://files.example.com/kyc/pan.jpg",
    "addressProofUrl": "https://files.example.com/kyc/address.jpg",
    "profilePhotoUrl": "https://files.example.com/kyc/photo.jpg",
    "bankAccountOrUpi": "rahul@upi"
  }'
```

Full request:

```json
{
  "aadhaarUrl": "https://files.example.com/kyc/aadhaar.jpg",
  "panUrl": "https://files.example.com/kyc/pan.jpg",
  "addressProofUrl": "https://files.example.com/kyc/address.jpg",
  "profilePhotoUrl": "https://files.example.com/kyc/photo.jpg",
  "bankAccountOrUpi": "rahul@upi"
}
```

Full response:

```json
{
  "success": true,
  "message": "Partner KYC submitted successfully",
  "data": {
    "id": 1,
    "aadhaarUrl": "https://files.example.com/kyc/aadhaar.jpg",
    "panUrl": "https://files.example.com/kyc/pan.jpg",
    "addressProofUrl": "https://files.example.com/kyc/address.jpg",
    "profilePhotoUrl": "https://files.example.com/kyc/photo.jpg",
    "bankAccountOrUpi": "rahul@upi",
    "status": "PENDING",
    "rejectionReason": null
  }
}
```

### Update Partner Availability

Endpoint:

```http
PUT /api/partners/{partnerId}/availability
```

API name: Set partner online or offline.

Curl:

```bash
curl -X PUT http://localhost:8080/api/partners/1/availability \
  -H "Authorization: Bearer <accessToken>" \
  -H "Content-Type: application/json" \
  -d '{
    "availabilityStatus": "ONLINE"
  }'
```

Full request:

```json
{
  "availabilityStatus": "ONLINE"
}
```

Full response:

```json
{
  "success": true,
  "message": "Partner availability updated successfully",
  "data": {
    "id": 1,
    "availabilityStatus": "ONLINE",
    "kycStatus": "APPROVED"
  }
}
```

### Update Partner Location

Endpoint:

```http
PUT /api/partners/{partnerId}/location
```

API name: Update partner current location.

Curl:

```bash
curl -X PUT http://localhost:8080/api/partners/1/location \
  -H "Authorization: Bearer <accessToken>" \
  -H "Content-Type: application/json" \
  -d '{
    "latitude": 28.5355,
    "longitude": 77.3910
  }'
```

Full request:

```json
{
  "latitude": 28.5355,
  "longitude": 77.3910
}
```

Full response:

```json
{
  "success": true,
  "message": "Partner location updated successfully",
  "data": {
    "id": 1,
    "currentLatitude": 28.5355,
    "currentLongitude": 77.3910,
    "availabilityStatus": "ONLINE"
  }
}
```

### Get Available Partners

Endpoint:

```http
GET /api/partners/available
```

API name: List online partners with approved KYC.

Curl:

```bash
curl -X GET http://localhost:8080/api/partners/available \
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
  "message": "Available partners fetched successfully",
  "data": [
    {
      "id": 1,
      "fullName": "Rahul Verma",
      "kycStatus": "APPROVED",
      "availabilityStatus": "ONLINE",
      "currentLatitude": 28.5355,
      "currentLongitude": 77.3910,
      "serviceRadiusKm": 3,
      "averageRating": 4.8
    }
  ]
}
```

### Approve Partner KYC

Endpoint:

```http
PUT /api/partners/admin/{partnerId}/kyc/approve
```

API name: Admin approves partner KYC.

Curl:

```bash
curl -X PUT http://localhost:8080/api/partners/admin/1/kyc/approve \
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
  "message": "Partner KYC approved successfully",
  "data": {
    "id": 1,
    "kycStatus": "APPROVED",
    "availabilityStatus": "OFFLINE"
  }
}
```

### Reject Partner KYC

Endpoint:

```http
PUT /api/partners/admin/{partnerId}/kyc/reject
```

API name: Admin rejects partner KYC.

Curl:

```bash
curl -X PUT http://localhost:8080/api/partners/admin/1/kyc/reject \
  -H "Authorization: Bearer <accessToken>" \
  -H "Content-Type: application/json" \
  -d '{
    "reason": "Address proof is not clear"
  }'
```

Full request:

```json
{
  "reason": "Address proof is not clear"
}
```

Full response:

```json
{
  "success": true,
  "message": "Partner KYC rejected successfully",
  "data": {
    "id": 1,
    "kycStatus": "REJECTED",
    "availabilityStatus": "OFFLINE"
  }
}
```

### Pending Partner KYC Count

Endpoint:

```http
GET /api/partners/admin/counts/pending-kyc
```

API name: Count partners waiting for KYC review.

Curl:

```bash
curl -X GET http://localhost:8080/api/partners/admin/counts/pending-kyc \
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
  "message": "Pending KYC count fetched successfully",
  "data": 7
}
```
