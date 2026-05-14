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
| notification | push, SMS, email, in-app alerts |
| file | file metadata and storage ownership |
| complaint | complaints and disputes |
| admin | dashboard aggregation through service clients |

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
