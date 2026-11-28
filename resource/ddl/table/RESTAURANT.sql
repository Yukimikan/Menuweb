-- Table: public.RESTAURANT

-- DROP TABLE IF EXISTS public."RESTAURANT";

CREATE TABLE IF NOT EXISTS public."RESTAURANT"
(
    restaurant_id integer NOT NULL,
    restaurant_name text COLLATE pg_catalog."default",
    type text COLLATE pg_catalog."default",
    update_user text COLLATE pg_catalog."default",
    update_date timestamp without time zone,
    CONSTRAINT "RESTAURANT_pkey" PRIMARY KEY (restaurant_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."RESTAURANT"
    OWNER to postgres;