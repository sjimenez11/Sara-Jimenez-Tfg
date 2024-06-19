INSERT INTO _user (user_name, user_password, role) VALUES ('admin', 'admin', 'ADMIN');

INSERT INTO book (title, author, synopsis) VALUES ('Matar a un ruiseñor', 'Harper Lee', 'Una historia de injusticia racial y crecimiento moral en el sur de Estados Unidos.');
INSERT INTO book (title, author, synopsis) VALUES ('1984', 'George Orwell', 'Una novela distópica que representa un régimen totalitario y un estado de vigilancia.');
INSERT INTO book (title, author, synopsis) VALUES ('Orgullo y prejuicio', 'Jane Austen', 'Una novela romántica clásica que aborda temas de clase social y matrimonio en la Inglaterra del siglo XIX.');
INSERT INTO book (title, author, synopsis) VALUES ('Moby-Dick', 'Herman Melville', 'Una épica sobre obsesión y venganza ambientada en la caza de ballenas.');
INSERT INTO book (title, author, synopsis) VALUES ('Un mundo feliz', 'Aldous Huxley', 'Una novela futurista que explora una sociedad controlada por la tecnología y la ingeniería genética.');
INSERT INTO book (title, author, synopsis) VALUES ('El señor de los anillos', 'J.R.R. Tolkien', 'Una trilogía épica de fantasía sobre la búsqueda para destruir un anillo poderoso y salvar la Tierra Media.');
INSERT INTO book (title, author, synopsis) VALUES ('El hobbit', 'J.R.R. Tolkien', 'Una novela de aventuras fantásticas que sigue el viaje de Bilbo Bolsón.');
INSERT INTO book (title, author, synopsis) VALUES ('El cuento de la criada', 'Margaret Atwood', 'Una novela distópica que imagina una sociedad futura donde las mujeres son propiedad del estado.');
INSERT INTO book (title, author, synopsis) VALUES ('Harry Potter y la piedra filosofal', 'J.K. Rowling', 'El primer libro de la serie Harry Potter, que introduce el mundo mágico.');
INSERT INTO book (title, author, synopsis) VALUES ('El alquimista', 'Paulo Coelho', 'Una novela filosófica sobre el viaje de un pastor para encontrar su leyenda personal.');
INSERT INTO book (title, author, synopsis) VALUES ('Sapiens: De animales a dioses', 'Yuval Noah Harari', 'Un libro de no ficción que explora la historia y evolución de Homo sapiens.');
INSERT INTO book (title, author, synopsis) VALUES ('Los hombres que no amaban a las mujeres', 'Stieg Larsson', 'Un thriller de misterio que sigue al periodista Mikael Blomkvist y la hacker Lisbeth Salander.');
INSERT INTO book (title, author, synopsis) VALUES ('El nombre del viento', 'Patrick Rothfuss', 'El primer libro de la serie Crónica del asesino de reyes, que narra la vida de Kvothe.');
INSERT INTO book (title, author, synopsis) VALUES ('El marciano', 'Andy Weir', 'Una novela de ciencia ficción sobre un astronauta varado en Marte y su lucha por sobrevivir.');
INSERT INTO book (title, author, synopsis) VALUES ('Los juegos del hambre', 'Suzanne Collins', 'Una historia distópica que sigue la lucha de Katniss Everdeen en un mundo postapocalíptico.');
INSERT INTO book (title, author, synopsis) VALUES ('Trono de cristal', 'Sarah J. Maas', 'Primera entrega de la serie de fantasía sobre la asesina Celaena Sardothien.');
INSERT INTO book (title, author, synopsis) VALUES ('Toda la verdad de mis mentiras', 'Elisabet Benavent', 'Una novela que explora la amistad y el amor en la vida de una joven.');
INSERT INTO book (title, author, synopsis) VALUES ('El duque y yo', 'Julia Quinn', 'Primer libro de la serie Los Bridgerton, una historia de amor ambientada en la alta sociedad londinense.');


INSERT INTO reading_List (user_Id, name, description) VALUES (1, 'Summer Reads', 'Books to read during summer vacation');

INSERT INTO reading_List_Book (id_Book, id_Reading_List, status) VALUES (1, 1, 'LEYENDO');
