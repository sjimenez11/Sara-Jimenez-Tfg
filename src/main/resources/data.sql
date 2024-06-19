INSERT INTO book (title, author, synopsis) VALUES ('Title1', 'Author1', 'Synopsis1');
INSERT INTO book (title, author, synopsis) VALUES ('Title2', 'Author2', 'Synopsis1');

INSERT INTO _user (user_name, user_password, role) VALUES ('admin', 'admin', 'ADMIN');

INSERT INTO reading_List (user_Id, name, description) VALUES (1, 'Summer Reads', 'Books to read during summer vacation');

INSERT INTO reading_List_Book (id_Book, id_Reading_List, status) VALUES (1, 1, 'LEYENDO');
