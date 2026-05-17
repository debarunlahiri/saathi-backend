create table if not exists partner_profiles (
    id uuid primary key,
    identity_user_id uuid not null unique,
    full_name varchar(150) not null,
    mobile_number varchar(20) not null,
    email varchar(150),
    address varchar(500),
    kyc_status varchar(30) not null default 'PENDING',
    availability_status varchar(30) not null default 'OFFLINE',
    current_latitude numeric(10,7),
    current_longitude numeric(10,7),
    service_radius_km numeric(5,2) default 3,
    average_rating numeric(3,2) default 0,
    created_at timestamp with time zone not null default now(),
    updated_at timestamp with time zone not null default now()
);

create unique index if not exists idx_partner_identity_user on partner_profiles(identity_user_id);
create index if not exists idx_partner_availability_kyc on partner_profiles(availability_status, kyc_status);

create table if not exists partner_kyc (
    id uuid primary key,
    partner_id uuid not null unique references partner_profiles(id),
    aadhaar_url varchar(500),
    pan_url varchar(500),
    address_proof_url varchar(500),
    profile_photo_url varchar(500),
    bank_account_or_upi varchar(200),
    status varchar(30) not null default 'PENDING',
    rejection_reason varchar(500),
    created_at timestamp with time zone not null default now()
);
