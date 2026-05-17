create table if not exists file_assets (
    id uuid primary key,
    owner_user_id uuid,
    reference_type varchar(50),
    reference_id varchar(100),
    file_name varchar(255) not null,
    content_type varchar(100),
    storage_url varchar(500) not null,
    created_at timestamp with time zone not null default now()
);
