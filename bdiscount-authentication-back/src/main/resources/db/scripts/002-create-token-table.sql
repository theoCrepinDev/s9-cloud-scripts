CREATE TABLE authentication."token" (
                                        id uuid NOT NULL,
                                        user_id uuid NOT NULL,
                                       creation_date int,
                                       expiration_date int,
                                       token text NOT NULL,
                                        CONSTRAINT token_pk PRIMARY KEY (id),
                                        CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES authentication."user"(id)
);