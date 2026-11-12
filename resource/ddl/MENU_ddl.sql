-- Table: public.MENU

-- DROP TABLE IF EXISTS public."MENU";

CREATE TABLE IF NOT EXISTS public."MENU"
(
    no integer,
    type text COLLATE pg_catalog."default",
    restaurant_name text COLLATE pg_catalog."default",
    singlemenu_flg text COLLATE pg_catalog."default",
    menu text COLLATE pg_catalog."default",
    price integer,
    tax integer,
    total integer,
    update_user text COLLATE pg_catalog."default",
    update_date timestamp without time zone
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."MENU"
    OWNER to postgres;