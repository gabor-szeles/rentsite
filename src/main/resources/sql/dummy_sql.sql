INSERT INTO users VALUES (1, 'matyko@matyimail.com', 'Matyi', 'Gyuli', '12345', 'Matyko');
INSERT INTO users VALUES (2, 'gyuli@gymail.com', 'Gyuli', 'Matyi', '12345', 'Gyuli');
INSERT INTO users VALUES (3, 'hmiyazaki@ghibli.jp', 'Hayao', 'Miyazaki', '12345', 'hmiyazaki');
INSERT INTO users VALUES (4, 'meszarosl@freemail.hu', 'Lőrinc', 'Mészáros', '12345', 'lolo_m');

INSERT INTO itemcategory VALUES (1, 'Electronics');
INSERT INTO itemcategory VALUES (2, 'Vehicles');

INSERT INTO servicecategory VALUES (3, 'Cleaning');
INSERT INTO servicecategory VALUES (4, 'Special');
INSERT INTO tag VALUES (1, 'Bicycle');
INSERT INTO tag VALUES (2, 'Catbus');
INSERT INTO tag VALUES (3, 'Laptop');
INSERT INTO tag VALUES (4, '4fun');

INSERT INTO item VALUES (1, TRUE, 'MSI EX600', 'its a good lappy', '1000', 'HUF', 1, 1);
INSERT INTO item VALUES (2, TRUE, 'Catbus', 'Its the catbus from totoro', '9999', 'JPY', 3, 2);

INSERT INTO service VALUES (3, TRUE, 'SWATting', 'Swat your enemies lol', '1000', 'USD', 2, 4);
INSERT INTO service VALUES (4, TRUE, 'Maid service', 'U know what I mean lel', '999999', 'HUF', 4, 3);

INSERT INTO tag_rentable VALUES (2, 2);
INSERT INTO tag_rentable VALUES (3, 1);
INSERT INTO tag_rentable VALUES (4, 3);
INSERT INTO tag_rentable VALUES (4, 4);
INSERT INTO tag_rentable VALUES (4, 2);

INSERT INTO reservation VALUES (1, '2017-01-01', '2017-02-01', 4, 2);
INSERT INTO reservation VALUES (2, '2017-01-01', '2017-02-01', 2, 3);
INSERT INTO reservation VALUES (3, '2017-01-01', '2017-02-01', 3, 4);

INSERT INTO userreview VALUES (1, 'Its great user', 5, 1, 2);
INSERT INTO userreview VALUES (2, 'Is okay', 3, 2, 3);

INSERT INTO reservationreview VALUES (3, 'Its good item', 4, 1, 1);
INSERT INTO reservationreview VALUES (4, 'Its awesome item', 5, 2, 2);
