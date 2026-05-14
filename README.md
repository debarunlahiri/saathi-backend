# saathi-platform

Microservice backend platform for Saathi.

All services use the package root:

```text
com.lambrk.saathi
```

## Services

```text
saathi-api-gateway
saathi-service-discovery
saathi-config-server
saathi-identity-service
saathi-customer-service
saathi-partner-service
saathi-service-catalog-service
saathi-task-service
saathi-pricing-service
saathi-payment-service
saathi-wallet-service
saathi-chat-service
saathi-location-service
saathi-notification-service
saathi-file-service
saathi-complaint-service
saathi-admin-service
```

## Build

```bash
mvn test
```

## Local Infrastructure

```bash
docker compose up -d
```

The first development slice should focus on:

```text
saathi-api-gateway
saathi-service-discovery
saathi-identity-service
saathi-partner-service
saathi-task-service
saathi-chat-service
```

Then add pricing, payment, notification, and file services.
