DROP TABLE IF EXISTS db_books;
DROP TABLE IF EXISTS db_users;
DROP TABLE IF EXISTS db_100005Victor;
DROP TABLE IF EXISTS db_100006Maksim;

DROP SEQUENCE IF EXISTS global_seq_library;

CREATE SEQUENCE global_seq_library
  START 100000;

CREATE TABLE db_books
(
  id                INTEGER PRIMARY KEY DEFAULT nextval('global_seq_library'),
  name              VARCHAR                           NOT NULL,
  author            VARCHAR                           NOT NULL,
  publication_date  INTEGER DEFAULT 0                 NOT NULL,
  date_of_receipt   BIGINT                            NOT NULL,
  short_description VARCHAR                           NOT NULL,
  number_of_pages   INTEGER DEFAULT 0                 NOT NULL
);

CREATE TABLE db_users
(
  id                    INTEGER PRIMARY KEY DEFAULT nextval('global_seq_library'),
  login                 VARCHAR                               NOT NULL,
  password              VARCHAR                               NOT NULL,
  administrator         BOOLEAN DEFAULT FALSE                 NOT NULL,
  registration_date     BIGINT                                NOT NULL,
  name                  VARCHAR                               NOT NULL,
  last_name             VARCHAR                               NOT NULL,
  age                   BIGINT DEFAULT 0                      NOT NULL,
  gender                VARCHAR                               NOT NULL,
  favorite_books_bdname VARCHAR                               
);
CREATE TABLE db_100005Victor (
  id VARCHAR(13)
);
CREATE TABLE db_100006Maksim (
  id VARCHAR(13)
);