# Saathi Microservice Architecture

## Package Rule

Every service starts with `com.lambrk.saathi`.

Examples:

```text
com.lambrk.saathi.identity
com.lambrk.saathi.partner
com.lambrk.saathi.task
com.lambrk.saathi.chat
```

## Database Ownership

Each service owns its own schema or database. Services should not directly read or write another service's tables.

| Service | Data ownership |
| --- | --- |
| identity | users, roles, credentials, refresh tokens |
| customer | customer profiles, addresses, preferences |
| partner | partner profiles, KYC, availability |
| catalog | service categories |
| task | tasks, task assignment, task status history |
| pricing | pricing rules and estimates |
| payment | customer payment orders, refunds, webhooks |
| wallet | partner earnings, payouts |
| chat | chat rooms, chat messages |
| location | live location and location history |
| notification | push tokens, in-app alerts, email/SMS/WhatsApp delivery |
| file | file metadata and storage ownership |
| complaint | complaints and disputes |
| admin | dashboard aggregation through service clients |

## Service Communication

Services communicate via REST using `RestClient` with Eureka service discovery (`@LoadBalanced`).

External API calls (payment gateways, Firebase, SMS, WhatsApp) use a plain `RestClient.Builder`.

| Caller | Target | Protocol |
| --- | --- | --- |
| identity | customer-service, partner-service | REST (profile provisioning) |
| task | pricing, payment, partner, chat, notification, wallet | REST (orchestration) |
| notification | identity-service | REST (user email/mobile lookup) |
| admin | task, partner, complaint, payment | REST (dashboard counts) |

## Authentication

### JWT Token Flow
1. Client authenticates via `/api/auth/login` (password) or `/api/auth/firebase/*/login` (phone OTP)
2. Identity service returns `accessToken` (120 min) + `refreshToken` (30 days)
3. API gateway validates JWT on all requests except `/api/auth/**`, `/actuator/**`, `/ws/**`, `/ws-location/**`

### Firebase Phone Authentication
1. Client verifies phone number via Firebase Auth SDK (OTP)
2. Client sends Firebase `idToken` to `/api/auth/firebase/customer/login` or `/api/auth/firebase/partner/login`
3. Backend verifies token with Firebase Admin SDK, extracts `phone_number`
4. If user exists → returns JWT; if new → auto-registers and returns JWT

## Notification Delivery

When a notification is created via `POST /api/notifications`, it is delivered through all available channels:

| Channel | Provider | Config Required |
| --- | --- | --- |
| Push | Firebase FCM | `FIREBASE_SERVICE_ACCOUNT_PATH` |
| Email | SMTP (JavaMailSender) | `SMTP_HOST`, `SMTP_PORT`, `SMTP_USERNAME`, `SMTP_PASSWORD` |
| SMS | HTTP-based SMS gateway | `SMS_API_URL`, `SMS_API_KEY` |
| WhatsApp | Meta WhatsApp Cloud API | `WHATSAPP_PHONE_NUMBER_ID`, `WHATSAPP_ACCESS_TOKEN` |

## Payment Gateways

Three gateways supported, selectable via `paymentGateway` field:

| Gateway | Base URL |
| --- | --- |
| Razorpay | `https://api.razorpay.com/v1` |
| Paytm | `https://securegw-stage.paytm.in` (sandbox) |
| Cashfree | `https://sandbox.cashfree.com` (sandbox) |

## File Storage Providers

Three storage providers, auto-detected via `FileStorageRegistry`:

| Provider | Key | URL Pattern |
| --- | --- | --- |
| Local | `FileStorageRoot` | Writes to `./data/files/` |
| S3 | `S3Bucket` + `S3Region` | AWS SDK v2 |
| Cloudinary | `CloudName` + `API Key/Secret` | Cloudinary SDK |

## WebSocket Endpoints

### Chat Service (`/ws`)
| Destination | Direction | Description |
| --- | --- | --- |
| `/app/chat.send` | Client → Server | Send a message |
| `/app/chat.typing` | Client → Server | Typing indicator |
| `/app/chat.delivered` | Client → Server | Mark room messages as delivered |
| `/app/chat.read` | Client → Server | Mark a message as read (messageId in content) |
| `/topic/chat/{roomId}` | Server → Client | New messages broadcast |
| `/topic/chat/{roomId}/typing` | Server → Client | Typing indicator broadcast |
| `/topic/chat/{roomId}/delivered` | Server → Client | Delivery confirmation broadcast |
| `/topic/chat/{roomId}/read` | Server → Client | Read receipt broadcast |

### Location Service (`/ws-location`)
| Destination | Direction | Description |
| --- | --- | --- |
| `/app/location.update` | Client → Server | Partner sends location update |
| `/topic/location/{taskId}` | Server → Client | Customer receives location updates |

## Environment Variables

Key environment variables required for deployment:

```text
JWT_SECRET                         (required for JWT signing)
FIREBASE_SERVICE_ACCOUNT_PATH      (required for Firebase Auth and FCM)
SMTP_HOST, SMTP_PORT, SMTP_USERNAME, SMTP_PASSWORD   (email)
SMS_API_URL, SMS_API_KEY, SMS_SENDER_ID               (SMS)
WHATSAPP_PHONE_NUMBER_ID, WHATSAPP_ACCESS_TOKEN       (WhatsApp)
RAZORPAY_KEY_ID, RAZORPAY_KEY_SECRET                  (Razorpay)
PAYTM_MERCHANT_ID, PAYTM_MERCHANT_KEY                 (Paytm)
CASHFREE_APP_ID, CASHFREE_SECRET_KEY                  (Cashfree)
S3_BUCKET, S3_REGION                                  (AWS S3)
CLOUDINARY_CLOUD_NAME, CLOUDINARY_API_KEY, CLOUDINARY_API_SECRET (Cloudinary)
```

## Initial Delivery Recommendation

Build and stabilize six services first:

```text
saathi-api-gateway
saathi-service-discovery
saathi-identity-service
saathi-partner-service
saathi-task-service
saathi-chat-service
```

Then add pricing, payment, notification, file, wallet, location, complaint, and admin services.
