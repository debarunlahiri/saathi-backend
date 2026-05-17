create table if not exists complaints (
    id uuid primary key,
    task_id uuid,
    raised_by uuid not null,
    complaint_type varchar(50) not null,
    description varchar(2000) not null,
    status varchar(30) not null default 'OPEN',
    admin_remarks varchar(2000),
    created_at timestamp with time zone not null default now(),
    updated_at timestamp with time zone not null default now()
);
