ALTER TABLE users add icc text;
ALTER TABLE users ADD CONSTRAINT icc_unique UNIQUE (icc)