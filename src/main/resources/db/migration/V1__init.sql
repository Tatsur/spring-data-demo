create table categories(
    id      bigserial primary key    not null,
    name    text                     not null
);

insert into categories(name) values
('shoes'),('hats'),('t-shirts'),('coats'),('jeans'),('skirts');

create table products (
    id                  bigserial primary key   not null,
    name                varchar                 not null,
    price               decimal                 not null,
    category_id         bigserial,
    created_date_time   timestamp                not null,
    updated_date_time   timestamp               not null
);

insert into products (name, price, category_id,created_date_time,updated_date_time) values
('Product1', 10, 1, current_timestamp, current_timestamp),
('Product2', 20, 2, current_timestamp, current_timestamp),
('Product3', 30, 3, current_timestamp, current_timestamp),
('Product4', 40, 4, current_timestamp, current_timestamp),
('Product5', 50, 4, current_timestamp, current_timestamp),
('Product6', 60, 3, current_timestamp, current_timestamp),
('Product7', 70, 1, current_timestamp, current_timestamp),
('Product8', 80, 2, current_timestamp, current_timestamp),
('Product9', 90, 2, current_timestamp, current_timestamp),
('Product10', 100, 1, current_timestamp, current_timestamp),
('Product11', 110, 4, current_timestamp, current_timestamp),
('Product12', 120, 4, current_timestamp, current_timestamp),
('Product13', 130, 5, current_timestamp, current_timestamp),
('Product14', 140, 4, current_timestamp, current_timestamp),
('Product15', 150, 1, current_timestamp, current_timestamp),
('Product16', 160, 1, current_timestamp, current_timestamp),
('Product17', 170, 1, current_timestamp, current_timestamp),
('Product18', 180, 2, current_timestamp, current_timestamp),
('Product19', 190, 1, current_timestamp, current_timestamp),
('Product20', 200, 2, current_timestamp, current_timestamp);

create table order_items(
    id               serial  primary key  not null,
    productId        bigserial            not null,
    productPrice     decimal              not null,
    quantity         int                  not null
);

create table users (
    id                    bigserial primary key not null,
    username              varchar(30) unique    not null,
    password              varchar(80)           not null,
    email                 varchar               not null
);

create table roles (
    id                    serial primary key    not null,
    name                  varchar(50)           not null
);

create table users_roles (
  user_id               bigint      not null,
  role_id               int         not null,
  primary key (user_id, role_id),
  foreign key (user_id) references users (id),
  foreign key (role_id) references roles (id)
);

insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN');

insert into users (username, password, email)
values
('user1', '$2a$10$AL3BxKTjXlbu7.9NA5hRPutAw752Mo3pA8QnZV7FnGbxOxyHAuqcG', 'alpha@gmail.com'),
('user2', '$2a$10$AL3BxKTjXlbu7.9NA5hRPutAw752Mo3pA8QnZV7FnGbxOxyHAuqcG', 'beta@gmail.com');

insert into users_roles (user_id, role_id)
values
(1, 1),
(1, 2);