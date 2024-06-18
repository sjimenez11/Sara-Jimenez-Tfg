INSERT INTO books (title, author, synopsis) VALUES ('Title1', 'Author1', 'Synopsis1');
INSERT INTO books (title, author, synopsis) VALUES ('Title2', 'Author2', 'Synopsis1');

--TODO no puede ser el user_Id null, hacer comprobación o dar por hecho que este caso nunca va a suceder??
INSERT INTO reading_Lists (user_Id, name, description) VALUES (null, 'Summer Reads', 'Books to read during summer vacation');

INSERT INTO reading_List_Books (id_Book, id_Reading_List, status) VALUES (1, 1, 'LEYENDO');

INSERT INTO users (user_name, user_password, role) VALUES ('admin', 'admin', 'ADMIN');