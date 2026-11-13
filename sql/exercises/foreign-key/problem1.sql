create table post(
    id serial primary key,
    post varchar(225),
    user_fk int references site_user(id)
)