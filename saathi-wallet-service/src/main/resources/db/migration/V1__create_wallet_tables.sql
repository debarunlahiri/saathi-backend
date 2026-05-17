create table if not exists partner_wallets (
    id uuid primary key,
    partner_id uuid not null unique,
    available_balance numeric(12,2) default 0,
    pending_balance numeric(12,2) default 0,
    total_earnings numeric(12,2) default 0,
    total_withdrawn numeric(12,2) default 0,
    updated_at timestamp with time zone not null default now()
);

create unique index if not exists idx_wallet_partner on partner_wallets(partner_id);

create table if not exists partner_earnings (
    id uuid primary key,
    partner_id uuid not null,
    task_id uuid not null,
    gross_amount numeric(12,2) not null,
    platform_commission numeric(12,2) not null,
    net_amount numeric(12,2) not null,
    created_at timestamp with time zone not null default now()
);

create index if not exists idx_earnings_partner on partner_earnings(partner_id);

create table if not exists partner_payouts (
    id uuid primary key,
    partner_id uuid not null,
    amount numeric(12,2) not null,
    payout_method varchar(30) not null,
    payout_reference varchar(255),
    status varchar(30) not null default 'INITIATED',
    created_at timestamp with time zone not null default now()
);

create index if not exists idx_payouts_partner on partner_payouts(partner_id);
