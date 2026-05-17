create table if not exists pricing_rules (
    id uuid primary key,
    service_category_id uuid not null,
    base_price numeric(10,2) not null default 0,
    included_distance_km numeric(8,2) default 1,
    per_km_charge numeric(10,2) default 0,
    included_waiting_minutes int default 10,
    per_waiting_unit_minutes int default 10,
    per_waiting_unit_charge numeric(10,2) default 0,
    urgent_charge numeric(10,2) default 0,
    platform_fee numeric(10,2) default 0,
    active boolean not null default true,
    created_at timestamp with time zone not null default now()
);

create index if not exists idx_pricing_rule_category_active on pricing_rules(service_category_id, active);
