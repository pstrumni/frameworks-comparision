CREATE DATABASE db;

CREATE TABLE db.users
(
    id         varchar(255) NOT NULL,
    email      varchar(255) DEFAULT NULL,
    first_name varchar(255) DEFAULT NULL,
    last_name  varchar(255) DEFAULT NULL,
    login      varchar(255) DEFAULT NULL,
    password   varchar(255) DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE db.authorities
(
    id          varchar(255) NOT NULL,
    description varchar(255) DEFAULT NULL,
    name        varchar(255) DEFAULT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE db.users_authorities
(
    user_id      varchar(255) NOT NULL,
    authority_id varchar(255) NOT NULL,

    PRIMARY KEY (user_id, authority_id),
    CONSTRAINT fk_users_authorities_authority FOREIGN KEY (authority_id) REFERENCES db.authorities (id),
    CONSTRAINT fk_users_authorities_user FOREIGN KEY (user_id) REFERENCES db.users (id)
);

CREATE TABLE db.addresses
(
    id               varchar(255) NOT NULL,
    apartment_number varchar(255) DEFAULT NULL,
    city             varchar(255) DEFAULT NULL,
    country          varchar(255) DEFAULT NULL,
    house_number     varchar(255) DEFAULT NULL,
    street_name      varchar(255) DEFAULT NULL,
    user_id          varchar(255) DEFAULT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_addresses_user FOREIGN KEY (user_id) REFERENCES db.users (id)
);


INSERT INTO db.authorities VALUES ('1','Użytkownik','Użytkownik'),('2','Administrator','Administrator');