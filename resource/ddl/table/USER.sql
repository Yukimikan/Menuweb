-- Table: public.USER

-- DROP TABLE IF EXISTS public."USER";

CREATE TABLE IF NOT EXISTS public."USER"
(
    user_id integer NOT NULL,
    name text COLLATE pg_catalog."default",
    password text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "USER_pkey" PRIMARY KEY (user_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."USER"
    OWNER to postgres;