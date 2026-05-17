create table if not exists payments (
    id uuid primary key,
    task_id uuid not null,
    customer_id uuid not null,
    amount numeric(10,2) not null,
    payment_gateway varchar(30),
    gateway_order_id varchar(255),
    gateway_payment_id varchar(255),
    payment_status varchar(30) not null default 'INITIATED',
    paid_at timestamp with time zone,
    created_at timestamp with time zone not null default now()
);

create index if not exists idx_payments_task on payments(task_id);
create index if not exists idx_payments_status on payments(payment_status);
