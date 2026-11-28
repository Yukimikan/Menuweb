-- SEQUENCE: public.restaurant_seq

-- DROP SEQUENCE IF EXISTS public.restaurant_seq;

CREATE SEQUENCE IF NOT EXISTS public.restaurant_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 10000
    CACHE 1;

ALTER SEQUENCE public.restaurant_seq
    OWNER TO postgres;