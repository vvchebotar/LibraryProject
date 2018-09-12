INSERT INTO db_books (name, author, publication_date, date_of_receipt, short_description, number_of_pages)
VALUES ('Патруль времени', 'Андерсон, Пол.', 2004, 10000, 'Фантастическая повесть', 333);

INSERT INTO db_books (name, author, publication_date, date_of_receipt, short_description, number_of_pages)
VALUES ('Восточный ветер', 'Абдуллаев, Чингиз.', 2008, 10000, 'роман', 314);

INSERT INTO db_books (name, author, publication_date, date_of_receipt, short_description, number_of_pages)
VALUES ('Ветер времени', 'Балашов, Дмитрий Михайлович.', 1994, 10000, 'роман', 493);

INSERT INTO db_books (name, author, publication_date, date_of_receipt, short_description, number_of_pages)
VALUES ('Ветер времени 2', 'Балашов, Дмитрий Михайлович.', 1995, 10000, 'роман', 493);

INSERT INTO db_books (name, author, publication_date, date_of_receipt, short_description, number_of_pages)
VALUES ('Ветер времени 3', 'Балашов, Дмитрий Михайлович.', 1996, 10000, 'роман', 493);

INSERT INTO db_users (login, password, administrator, registration_date, name, last_name, age, gender, favorite_books_bdname)
VALUES ('Victor', '1234', TRUE, 10000, 'Victor', 'Chebotar', 29, 'M', 'db_100005Victor');

INSERT INTO db_users (login, password, administrator, registration_date, name, last_name, age, gender, favorite_books_bdname)
VALUES ('Maksim', '1234', FALSE, 10000, 'Maksim', 'Bob', 29, 'M', 'db_100006Maksim');

INSERT INTO db_100006Maksim (id)
VALUES (100001);
INSERT INTO db_100006Maksim (id)
VALUES (100002);
INSERT INTO db_100006Maksim (id)
VALUES (100003);
INSERT INTO db_100005Victor (id)
VALUES (100004);
INSERT INTO db_100005Victor (id)
VALUES (100000);
INSERT INTO db_100005Victor (id)
VALUES (100003);