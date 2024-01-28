-- DROP SCHEMA authentication;

CREATE SCHEMA authentication AUTHORIZATION postgres;
-- authentication."role" definition

-- Drop table

-- DROP TABLE authentication."role";

CREATE TABLE authentication."role" (
                                       id uuid NOT NULL,
                                       role_name text NOT NULL,
                                       CONSTRAINT role_pk PRIMARY KEY (id)
);


-- authentication."user" definition

-- Drop table

-- DROP TABLE authentication."user";

CREATE TABLE authentication."user" (
                                       id uuid NOT NULL,
                                       "name" text NOT NULL,
                                       email text NOT NULL,
                                       password_hash text NOT NULL,
                                       phone_number text NULL,
                                       address text NULL,
                                       CONSTRAINT user_pkey PRIMARY KEY (id)
);


-- authentication.user_roles definition

-- Drop table

-- DROP TABLE authentication.user_roles;

CREATE TABLE authentication.user_roles (
                                           user_id uuid NOT NULL,
                                           role_id uuid NOT NULL,
                                           CONSTRAINT user_roles_pk PRIMARY KEY (user_id, role_id),
                                           CONSTRAINT role_id_fk FOREIGN KEY (role_id) REFERENCES authentication."role"(id),
                                           CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES authentication."user"(id)
);
CREATE INDEX fki_role_id_fk ON authentication.user_roles USING btree (role_id);
CREATE INDEX fki_user_id_fk ON authentication.user_roles USING btree (user_id);
