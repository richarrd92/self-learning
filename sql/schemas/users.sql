Show tables; -- No tables yet

-- create table
create table users(
    id int auto_increment primary key,
    first_name varchar(20) not null,
    last_name varchar(20) not null
);

-- show table structure
describe users;

-- insert into table
insert into users(first_name, last_name)
values("richard", "maliyetu");

-- show data in users table 
select * from users;

-- insert more data into users table 
insert into users(first_name, last_name)
values("alice", "bigaba");

-- show data in users table 
select * from users;

-- add column age to users table
alter table users
add column(age int check(age > 18));

-- show users table structure
describe users;

-- select user whos first name is alice 
select * from users where first_name = "alice";

-- alter age constraint to > 17
alter table users
modify column age int check (age > 17);

-- first must update all null values in age
update users 
set age = 18
where age is null;

-- modify the age column to have default age 18 and not be null
alter table users
modify column age int not null default 18 check(age > 17);

-- short form for describe
desc users;

-- show column
show columns from users; -- similar to desc or describe

-- show all databases
show databases;

-- show database table
show tables;

-- create a location table 
create table location(
    street varchar(100),
    city varchar(100),
    state varchar(100) not null,
    country varchar(100) not null
);

-- show location table details
desc location;

-- add one value in the location table 
insert into location(street, city, state, country)
values(
    '345 deed way',
    'essex',
    'maryland',
    'USA'
);

-- get all data from location table 
select * from location;








