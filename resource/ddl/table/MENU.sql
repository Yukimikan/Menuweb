-- Table: public.MENU

-- DROP TABLE IF EXISTS public."MENU";

CREATE TABLE IF NOT EXISTS public."MENU"
(
    no integer,
    restaurant_id integer,
    restaurant_name text COLLATE pg_catalog."default",
    menu_name text COLLATE pg_catalog."default",
    category text COLLATE pg_catalog."default",
    singlemenu_flg text COLLATE pg_catalog."default",
    eat_in_price integer,
    takeout_price integer,
    tax integer,
    total integer,
    update_user text COLLATE pg_catalog."default",
    update_date timestamp without time zone
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."MENU"
    OWNER to postgres;