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
PRIMARY KEY(bid)
);

INSERT INTO Book (bid, title, picture, price, category, coursecode, coursetitle) VALUES ('b001', 'How to use a keyboard', 0, 200, 'EECS1', '1001', 'Introduction to Computing');
INSERT INTO Book (bid, title, picture, price, category, coursecode, coursetitle) VALUES ('b002', 'Computer Organization and Design', 0, 400, 'EECS2', '2021', 'Computer Organization');
INSERT INTO Book (bid, title, picture, price, category, coursecode, coursetitle) VALUES ('b003', 'Introduction to Algorithms', 0, 25, 'EECS3', '3101', 'Design and Algorithms');
INSERT INTO Book (bid, title, picture, price, category, coursecode, coursetitle) VALUES ('b004', 'Understanding Machine Learning', 0, 220, 'EECS4', '4404', 'Introduction to Machine Learning');
INSERT INTO Book (bid, title, picture, price, category, coursecode, coursetitle) VALUES ('b005', 'Pattern Recognition and Machine Learning', 0, 221, 'EECS4', '4404', 'Introduction to Machine Learning');
INSERT INTO Book (bid, title, picture, price, category, coursecode, coursetitle) VALUES ('b006', 'How to use a Mouse', 0, 100, 'EECS1', '1012', 'Netcentric Intro to Computing');

/* Address
* id: address id
*
*/
CREATE TABLE Address (
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
email VARCHAR(30) NOT NULL,
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
email VARCHAR(20) NOT NULL,
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

INSERT INTO PO (id, email, lname, fname, status, address, day) VALUES (1, 'test1@mailcatch.com', 'Test1', 'Admin', 'PROCESSED', '1', 12202015);
INSERT INTO PO (id, email, lname, fname, status, address, day) VALUES (2, 'test1@mailcatch.com', 'Test1', 'Admin', 'ORDERED', '1', 12202015);
INSERT INTO PO (id, email, lname, fname, status, address, day) VALUES (3, 'test2@mailcatch.com', 'Test2', 'Admin', 'DENIED', '3', 12202015);


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
);


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
PRIMARY KEY(day, bid, eventtype)
/*FOREIGN KEY(bid) REFERENCES Book(bid)*/
);

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

);

INSERT INTO User (uid, fname, lname, email, password, privilege) VALUES (001, 'Test1', 'Admin', 'test1@mailcatch.com', 'test', 'Admin');
INSERT INTO User (uid, fname, lname, email, password, privilege) VALUES (002, 'Test2', 'Admin', 'test2@mailcatch.com', 'test', 'Admin');

INSERT INTO Address (id, email, street, province, country, zip, phone, addresstype) VALUES (1, 'test1@mailcatch.com','123 Yonge St', 'ON', 'Canada', 'K1E 6T5' ,'647-123-4567', 'Billing');
INSERT INTO Address (id, email, street, province, country, zip, phone, addresstype) VALUES (2, 'test1@mailcatch.com','445 Avenue rd', 'ON', 'Canada', 'M1C 6K5' ,'416-123-8569', 'Shipping');
INSERT INTO Address (id, email, street, province, country, zip, phone, addresstype) VALUES (3, 'test2@mailcatch.com','789 Keele St.', 'ON', 'Canada', 'K3C 9T5' ,'416-123-9568', 'Billing');

ALTER TABLE Address
ADD FOREIGN KEY (email) REFERENCES User (email) ON DELETE CASCADE;

ALTER TABLE PO
ADD FOREIGN KEY (address) REFERENCES Address (id) ON DELETE CASCADE,
ADD FOREIGN KEY (email) REFERENCES User (email) ON DELETE CASCADE;

ALTER TABLE POItem
ADD FOREIGN KEY(id) REFERENCES PO(id) ON DELETE CASCADE,
ADD FOREIGN KEY(bid) REFERENCES Book(bid) ON DELETE CASCADE;

ALTER TABLE VisitEvent
ADD FOREIGN KEY(bid) REFERENCES Book(bid),
ADD FOREIGN KEY(uid) REFERENCES User(uid);

ALTER TABLE User
ADD FOREIGN KEY(email) REFERENCES Address (email) ON DELETE CASCADE;

ALTER TABLE Review
ADD FOREIGN KEY(bid) REFERENCES Book (bid) ON DELETE CASCADE,
ADD FOREIGN KEY(uid) REFERENCES User (uid) ON DELETE CASCADE;
