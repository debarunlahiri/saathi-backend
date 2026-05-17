create table if not exists service_categories (
    id uuid primary key,
    name varchar(200) not null,
    code varchar(100) not null unique,
    description varchar(500),
    icon_url varchar(500),
    base_price numeric(10,2) not null default 0,
    active boolean not null default true,
    created_at timestamp with time zone not null default now()
);

create unique index if not exists idx_service_category_code on service_categories(code);
