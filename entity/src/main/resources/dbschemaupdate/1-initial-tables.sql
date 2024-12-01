CREATE TABLE IF NOT EXISTS app_user(
    id serial constraint user_pk primary key,
    name varchar(50) not null,
    surname varchar(50) not null,
    email varchar(50) not null
);

CREATE INDEX IF NOT EXISTS idx_app_user_email
    ON app_user(email) INCLUDE (name, surname);