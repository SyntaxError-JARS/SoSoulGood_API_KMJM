create table Customer (
username varchar(25) primary key,
fname varchar(20) not null,
lname varchar(20) not null,
"password" varchar(30) not null,
balance numeric(12,2) not null,
isAdmin boolean default false
-- isAdmin bit default 0
);

create table Menu (
menuItem varchar(40) primary key,
"price" numeric(12,2) not null,
protein varchar(20) not null,
isSubstitutable boolean default false
-- isSubstitutable bit default 0
);

create table "Order" (
id serial primary key,
menuItem varchar(40) not null,
"comment" text,
isFavorite boolean default false
--isFavorite bit default 0
orderDate varchar(15) not null,
username varchar(25)
);

create table CreditCard (
Cc_No varchar(16) primary key,
Cc_Name varchar(30) not null,
cvv int not null,
expDate varchar(15) not null,
Zip int not null,
"limit" numeric(15,2) not null,
username varchar(25) not null
);


-----------------------------------------
-------------CR(ead)UD-----------------

select * from customer ;

select * from credit_card;

select * from "order";

select * from menu;





-----------------------------------------
-------------CRU(pdate)D-----------------

alter table credit_card
add constraint fk_cm_customer
foreign key(username) references customer(username)
--ON DELETE CASCADE
;


alter table "order"
add constraint fk_customer
foreign key(username) references customer(username)
;

alter table "order"
add constraint fk_menu
foreign key(menuItem) references menu(menuItem)
;