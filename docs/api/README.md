# Saathi Platform API Documentation

Base URL through API gateway:

```text
http://localhost:8080
```

Protected APIs should be called with:

```http
Authorization: Bearer <accessToken>
Content-Type: application/json
```

Public paths (no auth): `/api/auth/**`, `/actuator/**`, `/ws/**`, `/ws-location/**`

## Category Files

- [Authentication APIs](./authentication.md) — Login, register, Firebase phone auth, token refresh
- [Customer APIs](./customer.md) — Customer profile CRUD
- [Partner APIs](./partner.md) — Partner profile, KYC, availability, location
- [Service Catalog APIs](./service-catalog.md) — Service categories
- [Pricing APIs](./pricing.md) — Pricing rules and estimates
- [Task APIs](./task.md) — Task lifecycle, assignment, status updates
- [Payment APIs](./payment.md) — Payment orders (Razorpay/Paytm/Cashfree), verification
- [Wallet APIs](./wallet.md) — Partner earnings, payouts, settlement
- [Chat APIs](./chat.md) — Chat rooms, messages, WebSocket delivery/read status
- [Location APIs](./location.md) — Location updates, history, WebSocket tracking
- [Notification APIs](./notification.md) — In-app notifications, device tokens, push/email/SMS/WhatsApp
- [File APIs](./file.md) — File upload, download, metadata (Local/S3/Cloudinary)
- [Complaint APIs](./complaint.md) — Complaints, status updates, admin counts
- [Admin APIs](./admin.md) — Dashboard aggregation

## Quick Reference: New Endpoints

| Method | Endpoint | Description |
| --- | --- | --- |
| POST | `/api/auth/firebase/customer/login` | Firebase phone auth login/register as customer |
| POST | `/api/auth/firebase/partner/login` | Firebase phone auth login/register as partner |
| POST | `/api/device-tokens` | Register FCM device token for push notifications |
| DELETE | `/api/device-tokens` | Unregister device token |
| GET | `/api/device-tokens/users/{userId}` | List user's device tokens |
| POST | `/api/files/upload` | Upload file (multipart) |
| GET | `/api/files/download/{fileName}` | Download uploaded file |
| POST | `/api/wallets/payouts` | Initiate payout from available balance |
| GET | `/api/wallets/partners/{partnerId}/payouts` | List payout history |
| POST | `/api/wallets/partners/{partnerId}/settle` | Settle pending earnings to available balance |
