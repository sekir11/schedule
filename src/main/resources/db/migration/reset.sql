DROP TABLE IF EXISTS participants;
DROP TABLE IF EXISTS event_dates;
DROP TABLE IF EXISTS events;
DROP TABLE IF EXISTS users;


CREATE TABLE users (user_name varchar(20) PRIMARY KEY, address varchar(50), password varchar(20));
CREATE TABLE events (id int auto_increment PRIMARY KEY, event_name varchar(20), memo varchar(200), create_user varchar(20), FOREIGN KEY(create_user) REFERENCES users(user_name));
CREATE TABLE event_dates (id int auto_increment PRIMARY KEY, event_id int, candidate_date date, FOREIGN KEY(event_id) REFERENCES events(id));
CREATE TABLE participants (event_date_id int, name varchar(20), status varchar(1), FOREIGN KEY(event_date_id) REFERENCES event_dates(id), FOREIGN KEY(name) REFERENCES users(user_name), PRIMARY KEY(event_date_id, name));

INSERT INTO users VALUES("username", "seki.ryohei0721@gmail.com", "password");
INSERT INTO users VALUES("other", "seki.ryohei0721@gmail.com", "password");

INSERT INTO events(id, event_name, memo, create_user) VALUES(1, "歓迎会", "新人歓迎会", "username");
INSERT INTO events(id, event_name, memo, create_user) VALUES(2, "歓迎会2", "新人歓迎会2", "other");

INSERT INTO event_dates(id, event_id, candidate_date) VALUES(1, 1, "2022-06-20");

INSERT INTO event_dates(id, event_id, candidate_date) VALUES(2, 2, "2022-06-21");
INSERT INTO event_dates(id, event_id, candidate_date) VALUES(3, 2, "2022-06-22");

INSERT INTO participants(event_date_id, name, status) VALUES(1, "username", "0");

INSERT INTO participants(event_date_id, name, status) VALUES(2, "username", "0");
INSERT INTO participants(event_date_id, name, status) VALUES(2, "other", "0");
INSERT INTO participants(event_date_id, name, status) VALUES(3, "username", "0");
INSERT INTO participants(event_date_id, name, status) VALUES(3, "other", "0");