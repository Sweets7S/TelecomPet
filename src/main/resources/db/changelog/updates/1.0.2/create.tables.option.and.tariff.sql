CREATE TABLE tariff
(
    tariff_id        integer         GENERATED ALWAYS AS IDENTITY,
    name             text            NOT NULL,
    price_per_month  integer         NOT NULL,
    package_voice    integer         NOT NULL,
    package_data     integer         NOT NULL,
    package_sms      integer         NOT NULL,
    ACTIVE           BOOLEAN         default true,
    constraint tariff_name_unique UNIQUE (name)
);

CREATE TABLE options
(
    option_id        integer         GENERATED ALWAYS AS IDENTITY,
    name             text            NOT NULL,
    price_per_month  integer         NOT NULL,
    package_voice    integer,
    package_data     integer,
    package_sms      integer,
    spec_code        integer         NOT NULL,
    ACTIVE           BOOLEAN         default true,
    constraint spec_code_unique UNIQUE (spec_code),
    constraint option_name_unique UNIQUE (name)
);

ALTER TABLE tariff ADD PRIMARY KEY (tariff_id);
ALTER TABLE options ADD PRIMARY KEY (option_id);

ALTER TABLE sim ADD tariff_id integer NOT NULL;
ALTER TABLE sim ADD option_id integer;
ALTER TABLE sim ADD CONSTRAINT fk_tariff FOREIGN KEY(tariff_id)  REFERENCES tariff(tariff_id);
ALTER TABLE sim ADD CONSTRAINT fk_option FOREIGN KEY(option_id)  REFERENCES options(option_id);