DROP TABLE gln."user";

CREATE TABLE gln."user"
(
    id integer NOT NULL DEFAULT nextval('gln.user_id_seq'::regclass) ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    email character varying(255) COLLATE pg_catalog."default",
    first_name character varying(255) COLLATE pg_catalog."default",
    last_name character varying(255) COLLATE pg_catalog."default",
    avatar character varying(500) COLLATE pg_catalog."default",
    job character varying(50) COLLATE pg_catalog."default",
    dt_create timestamp(4) without time zone,
    dt_update timestamp without time zone,
    CONSTRAINT user_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE gln."user"
    OWNER to postgres;

-- Index: user_id_uindex

-- DROP INDEX gln.user_id_uindex;

CREATE UNIQUE INDEX user_id_uindex
    ON gln."user" USING btree
    (id)
    TABLESPACE pg_default;