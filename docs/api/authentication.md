# Authentication APIs

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

### Customer Registration

Endpoint:

```http
POST /api/auth/customer/register
```

API name: Register customer and create customer profile.

Curl:

```bash
curl -X POST http://localhost:8080/api/auth/customer/register \
  -H "Content-Type: application/json" \
  -d '{
    "fullName": "Amit Sharma",
    "mobileNumber": "9876543210",
    "email": "amit@example.com",
    "password": "secret123"
  }'
```

Full request:

```json
{
  "fullName": "Amit Sharma",
  "mobileNumber": "9876543210",
  "email": "amit@example.com",
  "password": "secret123"
}
```

Full response:

```json
{
  "success": true,
  "message": "Customer registered successfully",
  "data": {
    "userId": 1,
    "role": "CUSTOMER",
    "accessToken": "eyJhbGciOiJIUzI1NiJ9.access.token",
    "refreshToken": "eyJhbGciOiJIUzI1NiJ9.refresh.token"
  }
}
```

### Partner Registration

Endpoint:

```http
POST /api/auth/partner/register
```

API name: Register partner and create partner profile.

Curl:

```bash
curl -X POST http://localhost:8080/api/auth/partner/register \
  -H "Content-Type: application/json" \
  -d '{
    "fullName": "Rahul Verma",
    "mobileNumber": "9876500001",
    "email": "rahul@example.com",
    "password": "secret123"
  }'
```

Full request:

```json
{
  "fullName": "Rahul Verma",
  "mobileNumber": "9876500001",
  "email": "rahul@example.com",
  "password": "secret123"
}
```

Full response:

```json
{
  "success": true,
  "message": "Partner registered successfully",
  "data": {
    "userId": 2,
    "role": "PARTNER",
    "accessToken": "eyJhbGciOiJIUzI1NiJ9.access.token",
    "refreshToken": "eyJhbGciOiJIUzI1NiJ9.refresh.token"
  }
}
```

### Login

Endpoint:

```http
POST /api/auth/login
```

API name: Login with mobile number and password.

Curl:

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "mobileNumber": "9876543210",
    "password": "secret123"
  }'
```

Full request:

```json
{
  "mobileNumber": "9876543210",
  "password": "secret123"
}
```

Full response:

```json
{
  "success": true,
  "message": "Login successful",
  "data": {
    "userId": 1,
    "role": "CUSTOMER",
    "accessToken": "eyJhbGciOiJIUzI1NiJ9.access.token",
    "refreshToken": "eyJhbGciOiJIUzI1NiJ9.refresh.token"
  }
}
```

### Firebase Phone Authentication - Customer Login
Endpoint:

```http
POST /api/auth/firebase/customer/login
```

API name: Login or auto-register as customer using Firebase phone auth ID token.

Curl:

```bash
curl -X POST http://localhost:8080/api/auth/firebase/customer/login \
  -H "Content-Type: application/json" \
  -d '{
    "idToken": "eyJhbGciOiJSUzI1NiIsImtpZCI6...firebase_id_token...",
    "fullName": "Amit Sharma",
    "email": "amit@example.com"
  }'
```

Full request:

```json
{
  "idToken": "eyJhbGciOiJSUzI1NiIsImtpZCI6...firebase_id_token...",
  "fullName": "Amit Sharma",
  "email": "amit@example.com"
}
```

Full response:

```json
{
  "success": true,
  "message": "Login successful",
  "data": {
    "userId": 1,
    "role": "CUSTOMER",
    "accessToken": "eyJhbGciOiJIUzI1NiJ9.access.token",
    "refreshToken": "eyJhbGciOiJIUzI1NiJ9.refresh.token"
  }
}
```

Notes:
- `idToken` is the Firebase ID token obtained after phone OTP verification on the client.
- If the phone number is not registered, a new user is auto-created. `fullName` and `email` are optional for new users.

### Firebase Phone Authentication - Partner Login

Endpoint:

```http
POST /api/auth/firebase/partner/login
```

API name: Login or auto-register as partner using Firebase phone auth ID token.

Curl:

```bash
curl -X POST http://localhost:8080/api/auth/firebase/partner/login \
  -H "Content-Type: application/json" \
  -d '{
    "idToken": "eyJhbGciOiJSUzI1NiIsImtpZCI6...firebase_id_token...",
    "fullName": "Rahul Verma",
    "email": "rahul@example.com"
  }'
```

Full request:

```json
{
  "idToken": "eyJhbGciOiJSUzI1NiIsImtpZCI6...firebase_id_token...",
  "fullName": "Rahul Verma",
  "email": "rahul@example.com"
}
```

Full response:

```json
{
  "success": true,
  "message": "Login successful",
  "data": {
    "userId": 2,
    "role": "PARTNER",
    "accessToken": "eyJhbGciOiJIUzI1NiJ9.access.token",
    "refreshToken": "eyJhbGciOiJIUzI1NiJ9.refresh.token"
  }
}
```

### Refresh Token

Endpoint:

```http
POST /api/auth/refresh-token
```

API name: Create a new access token from refresh token.

Curl:

```bash
curl -X POST http://localhost:8080/api/auth/refresh-token \
  -H "Content-Type: application/json" \
  -d '{
    "refreshToken": "eyJhbGciOiJIUzI1NiJ9.refresh.token"
  }'
```

Full request:

```json
{
  "refreshToken": "eyJhbGciOiJIUzI1NiJ9.refresh.token"
}
```

Full response:

```json
{
  "success": true,
  "message": "Token refreshed successfully",
  "data": {
    "userId": 1,
    "role": "CUSTOMER",
    "accessToken": "eyJhbGciOiJIUzI1NiJ9.new.access.token",
    "refreshToken": "eyJhbGciOiJIUzI1NiJ9.refresh.token"
  }
}
```
