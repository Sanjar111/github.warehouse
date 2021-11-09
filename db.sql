
create table bin_child(
bin_child_id integer primary key not null,
subcategory_id integer not null,
size integer not null,
category_id integer not null,
product_id integer not null,
busy_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
free_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP;


create table bin(
bin_id integer primary key not null,
bin_number integer not null,
total_size integer not null,
busy_size integer not null,
free_size integer not null,
level_id integer not null,
status integer not null,
created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

create table level(
level_id integer primary key not null,
level_number integer not null,
row_id integer not null,
subcategory_id_list json not null,
status integer not null,
created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


create table row(
row_id integer primary key not null,
total_level integer not null,
block_id integer not null,
subcategory_id_list json not null,
status integer not null,
created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


create table block(
block_id integer primary key not null,
block_name varchar(300) not null,
warehouse_id integer not null,
subcategory_id_list json not null,
status integer not null,
created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


create table warehouse(
warehouse_id integer primary key not null,
warehouse_name varchar(300) not null,
subcategory_id_list json not null,
address varchar(200) not null,
location json not null,
phone varchar(200) not null,
status integer not null,
created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

create sequence warehouse_seq increment 1 start 1;
create sequence block_seq increment 1 start 1;
create sequence bin_child_seq increment 1 start 1;
create sequence bin_seq increment 1 start 1;
create sequence row_seq increment 1 start 1;
create sequence level_seq increment 1 start 1;