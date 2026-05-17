# Payment APIs

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

The payment service supports three gateways: Razorpay, Paytm, and Cashfree.
Set the `paymentGateway` field to one of: `RAZORPAY`, `PAYTM`, `CASHFREE`.

Required environment variables per gateway:

| Gateway | Variables |
|---|---|
| Razorpay | `RAZORPAY_KEY_ID`, `RAZORPAY_KEY_SECRET` |
| Paytm | `PAYTM_MERCHANT_ID`, `PAYTM_MERCHANT_KEY`, `PAYTM_BASE_URL` |
| Cashfree | `CASHFREE_APP_ID`, `CASHFREE_SECRET_KEY`, `CASHFREE_BASE_URL` |

### Create Payment Order

Endpoint:

```http
POST /api/payments/create-order
```

API name: Create payment gateway order.

Curl:

```bash
curl -X POST http://localhost:8080/api/payments/create-order \
  -H "Authorization: Bearer <accessToken>" \
  -H "Content-Type: application/json" \
  -d '{
    "taskId": 1,
    "customerId": 1,
    "amount": 59,
    "paymentGateway": "RAZORPAY"
  }'
```

Full request:

```json
{
  "taskId": 1,
  "customerId": 1,
  "amount": 59,
  "paymentGateway": "RAZORPAY"
}
```

Full response:

```json
{
  "success": true,
  "message": "Payment order created successfully",
  "data": {
    "id": 1,
    "taskId": 1,
    "customerId": 1,
    "amount": 59,
    "paymentGateway": "RAZORPAY",
    "gatewayOrderId": "RAZORPAY_ORDER_1",
    "gatewayPaymentId": null,
    "paymentStatus": "INITIATED",
    "paidAt": null
  }
}
```

### Verify Payment

Endpoint:

```http
POST /api/payments/verify
```

API name: Verify payment gateway callback.

Curl:

```bash
curl -X POST http://localhost:8080/api/payments/verify \
  -H "Authorization: Bearer <accessToken>" \
  -H "Content-Type: application/json" \
  -d '{
    "paymentId": 1,
    "gatewayPaymentId": "pay_123456",
    "signature": "gateway_signature"
  }'
```

Full request:

```json
{
  "paymentId": 1,
  "gatewayPaymentId": "pay_123456",
  "signature": "gateway_signature"
}
```

Full response:

```json
{
  "success": true,
  "message": "Payment verified successfully",
  "data": {
    "id": 1,
    "taskId": 1,
    "customerId": 1,
    "amount": 59,
    "paymentGateway": "RAZORPAY",
    "gatewayOrderId": "RAZORPAY_ORDER_1",
    "gatewayPaymentId": "pay_123456",
    "paymentStatus": "SUCCESS",
    "paidAt": "2026-05-15T05:10:00Z"
  }
}
```

### Get Task Payments

Endpoint:

```http
GET /api/payments/task/{taskId}
```

API name: List payments for task.

Curl:

```bash
curl -X GET http://localhost:8080/api/payments/task/1 \
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
  "message": "Payments fetched successfully",
  "data": [
    {
      "id": 1,
      "taskId": 1,
      "customerId": 1,
      "amount": 59,
      "paymentGateway": "RAZORPAY",
      "paymentStatus": "SUCCESS",
      "paidAt": "2026-05-15T05:10:00Z"
    }
  ]
}
```

### Failed Payment Count

Endpoint:

```http
GET /api/payments/admin/counts/failed
```

API name: Count failed payments for admin dashboard.

Curl:

```bash
curl -X GET http://localhost:8080/api/payments/admin/counts/failed \
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
  "message": "Failed payment count fetched successfully",
  "data": 2
}
```
