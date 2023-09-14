CREATE TABLE test
(
    id               integer         GENERATED ALWAYS AS IDENTITY,
    name             text            NOT NULL,
    age              integer         NOT NULL,
    rch              integer         NOT NULL,
    constraint pk_rch primary key ( rch )
);

create index ui_test_rch on test ( rch );