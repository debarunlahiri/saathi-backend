create table if not exists notifications (
    id uuid primary key,
    user_id uuid not null,
    title varchar(255) not null,
    message varchar(1000) not null,
    notification_type varchar(50),
    reference_id varchar(100),
    read_status boolean not null default false,
    created_at timestamp with time zone not null default now()
);
create index if not exists idx_notification_user on notifications(user_id);

create table if not exists device_tokens (
    id uuid primary key,
    user_id uuid not null,
    device_token varchar(500) not null,
    created_at timestamp with time zone not null default now(),
    updated_at timestamp with time zone not null default now(),
    unique(user_id, device_token)
);
