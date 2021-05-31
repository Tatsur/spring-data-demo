create table categories(id bigserial primary key,name text);
insert into categories(name) values
('shoes'),('hats'),('t-shirts'),('coats'),('jeans'),('skirts');

create table products (id bigserial primary key,name text, price decimal, category_id bigserial);
insert into products (name, price, category_id) values
('Product1', 10, 1),
('Product2', 20, 2),
('Product3', 30, 3),
('Product4', 40, 4),
('Product5', 50, 4),
('Product6', 60, 3),
('Product7', 70, 1),
('Product8', 80, 2),
('Product9', 90, 2),
('Product10', 100, 1),
('Product11', 110, 4),
('Product12', 120, 4),
('Product13', 130, 5),
('Product14', 140, 4),
('Product15', 150, 1),
('Product16', 160, 1),
('Product17', 170, 1),
('Product18', 180, 2),
('Product19', 190, 1),
('Product20', 200, 2);