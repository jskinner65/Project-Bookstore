/** bid: unique identifier of Book (like ISBN)
* title: title of Book
* price: unit price WHEN ordered
* author: name of authors
* category: as specified
*/

CREATE TABLE Book (
bid VARCHAR(20) NOT NULL,
title VARCHAR(60) NOT NULL,
picture VARCHAR(60),
price float NOT NULL,
category ENUM('EECS1','EECS2','EECS3', 'EECS4') NOT NULL,
coursecode varchar(10),
coursetitle varchar(50),
description varchar(150),
PRIMARY KEY(bid),
FULLTEXT(title, description)
) ENGINE = InnoDB ;

INSERT INTO Book (bid, title, picture, price, category, coursecode, coursetitle, description) VALUES ('b001', 'How to use a keyboard', 0, 200, 'EECS1', '1001', 'Introduction to Computing', 'This book teaches you how to use keyboards for beginners.');
INSERT INTO Book (bid, title, picture, price, category, coursecode, coursetitle, description) VALUES ('b002', 'Computer Organization and Design', 0, 400, 'EECS2', '2021', 'Computer Organization', 'Fundamentals for EECS 2001 on computer basics.');
INSERT INTO Book (bid, title, picture, price, category, coursecode, coursetitle, description) VALUES ('b003', 'Introduction to Algorithms', 0, 25, 'EECS3', '3101', 'Design and Algorithms', 'Basics of computer algorithms.');
INSERT INTO Book (bid, title, picture, price, category, coursecode, coursetitle, description) VALUES ('b004', 'Understanding Machine Learning', 0, 220, 'EECS4', '4404', 'Introduction to Machine Learning', 'Introduction to artifical intelligence.');
INSERT INTO Book (bid, title, picture, price, category, coursecode, coursetitle, description) VALUES ('b005', 'Pattern Recognition and Machine Learning', 0, 221, 'EECS4', '4404', 'Introduction to Machine Learning', 'Expert guide on pattern recognition.');
INSERT INTO Book (bid, title, picture, price, category, coursecode, coursetitle, description) VALUES ('b006', 'How to use a Mouse', 0, 100, 'EECS1', '1012', 'Netcentric Intro to Computing', 'Yet another guide on how to use a fundamental mouse');

/* Address
* id: address id
*
*/
CREATE TABLE Address (
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
uid int unsigned NOT NULL,
street VARCHAR(100) NOT NULL,
province VARCHAR(20) NOT NULL,
country VARCHAR(20) NOT NULL,
zip VARCHAR(20) NOT NULL,
phone VARCHAR(20),
addresstype ENUM('Shipping','Billing') NOT NULL,
PRIMARY KEY(id)
/*FOREIGN KEY(email) REFERENCES User (email) ON DELETE CASCADE*/

);


/* Purchase Order
* lname: last name
* fname: first name
* id: purchase order id
* status: status of purchase
*/
CREATE TABLE PO (
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
uid int unsigned NOT NULL,
lname VARCHAR(20) NOT NULL,
fname VARCHAR(20) NOT NULL,
status ENUM('ORDERED','PROCESSED','DENIED') NOT NULL,
address INT UNSIGNED NOT NULL,
day varchar(8) NOT NULL,
PRIMARY KEY(id),
INDEX (address)
/*FOREIGN KEY (address) REFERENCES Address (id) ON DELETE CASCADE,
FOREIGN KEY (email) REFERENCES User (email) ON DELETE CASCADE */

);

INSERT INTO PO (id, uid, lname, fname, status, address, day) VALUES (1, 1, 'Test1', 'Admin', 'PROCESSED', '1', 12202015);
INSERT INTO PO (id, uid, lname, fname, status, address, day) VALUES (2, 1, 'Test1', 'Admin', 'ORDERED', '1', 12202015);
INSERT INTO PO (id, uid, lname, fname, status, address, day) VALUES (3, 2, 'Test2', 'Admin', 'DENIED', '3', 12202015);


/* Items on order
* id : purchase order id
* bid: unique identifier of Book
* price: unit price
*/
CREATE TABLE POItem (
id INT UNSIGNED NOT NULL,
bid VARCHAR(20) NOT NULL,
quantity INT NOT NULL,
price INT UNSIGNED NOT NULL,
PRIMARY KEY(id,bid),
INDEX (id),
/*FOREIGN KEY(id) REFERENCES PO(id) ON DELETE CASCADE,*/
INDEX (bid),
/*FOREIGN KEY(bid) REFERENCES Book(bid) ON DELETE CASCADE,*/
check (id > 0),
check (quantity > 0)
) ENGINE = InnoDB ;


INSERT INTO POItem (id, bid, quantity, price) VALUES (1, 'b001', 5,'20');
INSERT INTO POItem (id, bid, quantity, price) VALUES (2, 'b002', 2,'201');
INSERT INTO POItem (id, bid, quantity, price) VALUES (3, 'b003', 3,'100');


/* visit to website
* day: date
* bid: unique identifier of Book
* eventtype: status of purchase
*/
CREATE TABLE VisitEvent (
day varchar(8) NOT NULL,
uid INT UNSIGNED NOT NULL,
bid varchar(20) not null REFERENCES Book.bid,
eventtype ENUM('VIEW','CART','PURCHASE') NOT NULL,
quantity INT,
PRIMARY KEY(day, uid, bid)
/*FOREIGN KEY(bid) REFERENCES Book(bid)*/
) ENGINE = InnoDB ;

INSERT INTO VisitEvent (day, uid, bid, eventtype, quantity) VALUES ('12202015', 001, 'b001', 'VIEW', 1);
INSERT INTO VisitEvent (day, uid, bid, eventtype, quantity) VALUES ('12242015', 001, 'b001', 'CART', 1);
INSERT INTO VisitEvent (day, uid, bid, eventtype, quantity) VALUES ('12252015', 001, 'b001', 'PURCHASE', 1);

CREATE TABLE User (
uid INT UNSIGNED NOT NULL AUTO_INCREMENT,
fname VARCHAR(20) NOT NULL,
lname VARCHAR(20) NOT NULL,
email VARCHAR(30) NOT NULL UNIQUE,
password VARCHAR(80) NOT NULL,
privilege ENUM('Admin', 'General') NOT NULL,
PRIMARY KEY (uid)
/*FOREIGN KEY(email) REFERENCES Address (email) ON DELETE CASCADE*/

);

CREATE TABLE Review (
reviewID int NOT NULL,
rating int NOT NULL,
bid VARCHAR(20) NOT NULL,
uid INT UNSIGNED NOT NULL,
reviewtext VARCHAR (100),
PRIMARY KEY (reviewID, uid, bid)

/*FOREIGN KEY(bid) REFERENCES Book (bid) ON DELETE CASCADE,
FOREIGN KEY(uid) REFERENCES User (uid) ON DELETE CASCADE*/

) ENGINE = InnoDB ;


INSERT INTO User (uid, fname, lname, email, password, privilege) VALUES (001, 'Test1', 'Admin', 'test1@mailcatch.com', 'test', 'Admin');
INSERT INTO User (uid, fname, lname, email, password, privilege) VALUES (002, 'Test2', 'Admin', 'test2@mailcatch.com', 'test', 'Admin');

INSERT INTO Address (id, uid, street, province, country, zip, phone, addresstype) VALUES (1, 1,'123 Yonge St', 'ON', 'Canada', 'K1E 6T5' ,'647-123-4567', 'Billing');
INSERT INTO Address (id, uid, street, province, country, zip, phone, addresstype) VALUES (2, 1,'445 Avenue rd', 'ON', 'Canada', 'M1C 6K5' ,'416-123-8569', 'Shipping');
INSERT INTO Address (id, uid, street, province, country, zip, phone, addresstype) VALUES (3, 2,'789 Keele St.', 'ON', 'Canada', 'K3C 9T5' ,'416-123-9568', 'Billing');

ALTER TABLE Address
ADD FOREIGN KEY (uid) REFERENCES User (uid) ON DELETE CASCADE;

ALTER TABLE PO
ADD FOREIGN KEY (uid) REFERENCES User (uid) ON DELETE CASCADE;

ALTER TABLE POItem
ADD FOREIGN KEY(id) REFERENCES PO(id) ON DELETE CASCADE,
ADD FOREIGN KEY(bid) REFERENCES Book(bid);

ALTER TABLE VisitEvent
ADD FOREIGN KEY(bid) REFERENCES Book(bid),
ADD FOREIGN KEY(uid) REFERENCES User(uid);

ALTER TABLE Review
ADD FOREIGN KEY(bid) REFERENCES Book (bid) ON DELETE CASCADE,
ADD FOREIGN KEY(uid) REFERENCES User (uid) ON DELETE CASCADE;
alter table  visitevent add column price double precision (5,2);

/* 
 * ALTER TABLE User
 * ADD FOREIGN KEY(email) REFERENCES Address (email) ON DELETE CASCADE;
 * 
 * */

insert into visitevent (day, uid, bid, eventtype, quantity, price) values 
('20190408', 1, 'b004', 'cart', 2, '2.95'),
('20190408', 2, 'b002', 'cart', 2, '2.95'),
('20190408', 1, 'b005', 'cart', 2, '2.95'),
('20190408', 2, 'b003', 'cart', 2, '2.95'),
('20190408', 2, 'b005', 'cart', 2, '2.95'),
('20190408', 1, 'b006', 'cart', 2, '2.95');

/*
Changes for adding pictures and reviews - April 8th
*/

UPDATE Book
SET picture = './res/Images/keyboard.jpg'
WHERE bid = 'b001';

UPDATE Book
SET picture = './res/Images/2001.jpg'
WHERE bid = 'b002';

UPDATE Book
SET picture = './res/Images/algorithm.gif'
WHERE bid = 'b003';

UPDATE Book
SET picture = './res/Images/AI.jpg'
WHERE bid = 'b004';

UPDATE Book
SET picture = './res/Images/AI2.jpg'
WHERE bid = 'b005';

UPDATE Book
SET picture = './res/Images/mouse.jpg'
WHERE bid = 'b006';

INSERT INTO Review (reviewID, rating, bid, uid, reviewtext)
VALUES (1, 5, 'b001', '001', '10/10, great book would read again.');

INSERT INTO Review (reviewID, rating, bid, uid, reviewtext)
VALUES (2, 2, 'b001', '002', 'Did not get Chapter 5 properly.');

INSERT INTO Review (reviewID, rating, bid, uid, reviewtext)
VALUES (3, 4, 'b003', '001', 'Decent book.');

INSERT INTO Review (reviewID, rating, bid, uid, reviewtext)
VALUES (4, 5, 'b002', '002', 'Amazing, greatly written.');

/* - - - - - - - - - - - */

alter table address add column city Varchar(30) after street;
alter table visitevent drop foreign key visitevent_ibfk_2;
