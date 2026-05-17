create table if not exists tasks (
    id uuid primary key,
    customer_id uuid not null,
    partner_id uuid,
    service_category_id uuid not null,
    title varchar(255) not null,
    description varchar(2000),
    pickup_address varchar(500) not null,
    pickup_latitude numeric(10,7),
    pickup_longitude numeric(10,7),
    drop_address varchar(500),
    task_date_time timestamp with time zone,
    estimated_price numeric(10,2) default 0,
    final_price numeric(10,2),
    task_status varchar(30) not null default 'CREATED',
    payment_status varchar(30) not null default 'PENDING',
    created_at timestamp with time zone not null default now(),
    updated_at timestamp with time zone not null default now()
);

create index if not exists idx_tasks_customer on tasks(customer_id);
create index if not exists idx_tasks_partner on tasks(partner_id);
create index if not exists idx_tasks_status on tasks(task_status);

create table if not exists task_status_history (
    id uuid primary key,
    task_id uuid not null,
    old_status varchar(30),
    new_status varchar(30) not null,
    changed_by uuid,
    remarks varchar(500),
    created_at timestamp with time zone not null default now()
);

create index if not exists idx_task_status_history_task on task_status_history(task_id);
