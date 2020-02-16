DROP SEQUENCE gln.user_id_seq;

CREATE SEQUENCE gln.user_id_seq
    INCREMENT 1
    START 29
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE gln.user_id_seq
    OWNER TO postgres;