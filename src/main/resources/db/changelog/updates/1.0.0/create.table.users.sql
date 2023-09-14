CREATE TABLE users
(
    id               integer         GENERATED ALWAYS AS IDENTITY,
    login            text            NOT NULL,
    password         text            NOT NULL,
    fio              text            NOT NULL,
    document         text            NOT NULL,
    number           text            NOT NULL,
    ACTIVE           BOOLEAN                 default true,
    constraint pk_number primary key ( number )
);

create index ui_users_number on users (number);