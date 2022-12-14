create table access_level
(
    id    serial primary key not null,
    level varchar(60) unique not null
);

create table item_category
(
    id       serial primary key not null,
    category varchar(60) unique not null
);

create table country
(
    id      serial primary key not null,
    country varchar(60) unique not null
);

create table shop
(
    id          bigserial primary key not null,
    brand       varchar(60) unique    not null,
    description varchar(120)          not null
);

create table user_data
(
    id              bigserial primary key not null,
    is_verified     boolean default false,
    email           varchar(60) unique    not null,
    password        varchar(60) unique    not null,
    access_level_id serial,
    foreign key (access_level_id) references access_level (id) on delete set null
);

create table user_identity
(
    id         bigserial unique not null,
    shop_id    bigserial        not null,
    country_id serial,
    status     varchar(60)      not null default 'UNKNOWN',
    constraint fk_user_id foreign key (id) references user_data (id) on delete cascade,
    foreign key (shop_id) references shop (id) on delete set null,
    foreign key (country_id) references country (id) on delete set null
);

create table item_common_data
(
    id               bigserial primary key not null,
    shop_id          bigserial,
    item_category_id serial,
    is_available     boolean default false not null,
    price            money   default 0     not null,
    brand            varchar(60)           not null,
    label            varchar(60)           not null,
    description      varchar(120)          not null,
    foreign key (shop_id) references shop (id) on delete set null,
    foreign key (item_category_id) references item_category (id) on delete set null
);

create table box
(
    id                  bigserial primary key               not null,
    weight              double precision default 0.0        not null,
    code                varchar(16) unique                  not null,
    item_common_data_id bigserial                           not null,
    color_code          varchar(8)       default '0xffffff' not null,
    scale               double precision default 0.0        not null,
    width               double precision default 0.0        not null,
    length              double precision default 0.0        not null,
    color               varchar(60)      default 'white'    not null,
    constraint fk_item_common_data_id foreign key (item_common_data_id) references item_common_data (id) on delete cascade
);

create table component
(
    id   serial primary key not null,
    name varchar(60)        not null
);

create table chocolate
(
    id                  bigserial primary key            not null,
    weight              double precision default 0.0     not null,
    code                varchar(16) unique               not null,
    item_common_data_id bigserial                        not null,
    sugar               double precision default 0.0     not null,
    chocolate_type      varchar(60)      default 'black' not null,
    component_id        serial,
    constraint fk_item_common_data_id foreign key (item_common_data_id) references item_common_data (id) on delete cascade,
    foreign key (component_id) references component (id) on delete set null
);