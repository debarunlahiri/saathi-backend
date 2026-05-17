create table if not exists location_events (
    id uuid primary key,
    task_id uuid not null,
    partner_id uuid not null,
    latitude numeric(10,7) not null,
    longitude numeric(10,7) not null,
    accuracy numeric(10,7),
    created_at timestamp with time zone not null default now()
);
create index if not exists idx_location_task on location_events(task_id);
