ALTER TABLE users DROP CONSTRAINT icc_unique;
ALTER TABLE users DROP CONSTRAINT pk_number;
ALTER TABLE users DROP COLUMN IF EXISTS number;
ALTER TABLE users DROP COLUMN IF EXISTS icc;
ALTER TABLE users ADD PRIMARY KEY (id);

CREATE TABLE msisdn
(
    msisdn_id        integer         GENERATED ALWAYS AS IDENTITY,
    user_id          integer         NOT NULL,
    msisdn           text            NOT NULL,
    icc              text            NOT NULL,
    ACTIVE           BOOLEAN         default true,
    CONSTRAINT fk_user FOREIGN KEY(user_id)  REFERENCES users(id),
    constraint msisdn_unique UNIQUE (msisdn),
    constraint icc_unique UNIQUE (icc)
);

create index ui_msisdns_msisdn on msisdn (msisdn);
create index ui_msisdns_icc on msisdn (icc);
