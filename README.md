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
mvn package -DskipTests
```

## Docker Compose

```bash
docker compose config
docker compose up -d
```

`docker compose up -d` starts PostgreSQL, MongoDB, Eureka service discovery, config server, API gateway, and every Saathi service. Run `mvn package -DskipTests` first because each service Dockerfile copies the packaged jar from its local `target` directory.

Docker Desktop or another Docker daemon must be running before image build or startup commands will work.

The PostgreSQL container initializes separate service databases on first volume creation:

```text
saathi_identity
saathi_customer
saathi_partner
saathi_catalog
saathi_task
saathi_pricing
saathi_payment
saathi_wallet
saathi_location
saathi_notification
saathi_file
saathi_complaint
```

If the Postgres volume already exists from an older run, recreate it to rerun the init script:

```bash
docker compose down -v
docker compose up -d
```

## Runtime Ports

```text
8080  saathi-api-gateway
8081  saathi-identity-service
8082  saathi-customer-service
8083  saathi-partner-service
8084  saathi-service-catalog-service
8085  saathi-task-service
8086  saathi-pricing-service
8087  saathi-payment-service
8088  saathi-wallet-service
8089  saathi-chat-service
8090  saathi-location-service
8091  saathi-notification-service
8092  saathi-file-service
8093  saathi-complaint-service
8094  saathi-admin-service
8761  saathi-service-discovery
8888  saathi-config-server
```

## Suggested Local Run Order

Start the platform infrastructure first:

```bash
mvn -pl saathi-service-discovery spring-boot:run
mvn -pl saathi-config-server spring-boot:run
```

Then start business services as needed. For the first task flow:

```bash
mvn -pl saathi-identity-service spring-boot:run
mvn -pl saathi-customer-service spring-boot:run
mvn -pl saathi-partner-service spring-boot:run
mvn -pl saathi-service-catalog-service spring-boot:run
mvn -pl saathi-pricing-service spring-boot:run
mvn -pl saathi-task-service spring-boot:run
mvn -pl saathi-chat-service spring-boot:run
mvn -pl saathi-api-gateway spring-boot:run
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
