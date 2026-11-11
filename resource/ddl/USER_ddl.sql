-- Table: public.USER

-- DROP TABLE IF EXISTS public."USER";

CREATE TABLE IF NOT EXISTS public."USER"
(
    name text COLLATE pg_catalog."default",
    password text COLLATE pg_catalog."default"
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."USER"
    OWNER to postgres;