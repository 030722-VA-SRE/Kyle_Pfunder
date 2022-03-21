/*drop table grocery_list;

create table if not exists grocery_list(
id serial primary key,
item_name varchar(30) unique not null,
item_type varchar(20) not null,
date_added date not null,
is_purchased boolean default false,
user_id integer references users(id)
);

create table if not exists users(
id serial primary key,
username varchar(20) not null unique,
password varchar(30) not null
);

select * from grocery_list;
select * from users;*/

/*insert into users(username, password) values('Kyle', 'Password1234');*/

/*insert into grocery_list(item_name, item_type, date_added, user_id) values(
'milk', 'dairy', '03/19/2022',1);*/

select gl.id, gl.item_name, gl.item_type, gl.date_added, gl.is_purchased
from grocery_list gl join users u on gl.user_id = u.id;

