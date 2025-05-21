CREATE DATABASE PELICULAS;
USE PELICULAS;

CREATE TABLE Directores (
    id_director INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    nacionalidad VARCHAR(30),
    fecha_nacimiento DATE,
    numero_peliculas INT,
    retirado BOOLEAN DEFAULT TRUE,
    imagen VARCHAR(50)
    );

CREATE TABLE Actores (
    id_actor INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    nacionalidad VARCHAR(30),
    fecha_nacimiento DATE,
    numero_peliculas INT,
    retirado BOOLEAN DEFAULT FALSE,
    imagen VARCHAR(50)
    );

CREATE TABLE Peliculas (
    id_pelicula INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) UNIQUE NOT NULL,
    sinopsis VARCHAR(500),
    duracion INT,
    fecha_estreno DATE,
    disponible_streaming BOOLEAN DEFAULT FALSE,
    imagen VARCHAR(50),
    id_director INT UNSIGNED NOT NULL,
    FOREIGN KEY (id_director) REFERENCES Directores(id_director)
    );

CREATE TABLE Usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    email VARCHAR(100) UNIQUE,
    contraseña VARCHAR(255) NOT NULL,
    rol VARCHAR(50) NOT NULL,
    fecha_registro DATE,
    activo BOOLEAN DEFAULT TRUE
    );

INSERT INTO Directores (nombre, nacionalidad, fecha_nacimiento, numero_peliculas, retirado, imagen)
VALUES ('Christopher Nolan', 'UK', '1977-02-08', 23, 0, 'director1.jpg');
INSERT INTO Directores (nombre, nacionalidad, fecha_nacimiento, numero_peliculas, retirado, imagen)
VALUES ('Quentin Tarantino', 'USA', '1963-11-03', 19, 0, 'director2.jpg');
INSERT INTO Directores (nombre, nacionalidad, fecha_nacimiento, numero_peliculas, retirado, imagen)
VALUES ('Pedro Almodóvar', 'Spain', '1958-06-27', 22, 1, 'director3.jpg');
INSERT INTO Directores (nombre, nacionalidad, fecha_nacimiento, numero_peliculas, retirado, imagen)
VALUES ('Greta Gerwig', 'USA', '1983-08-04', 8, 0, 'director4.jpg');
INSERT INTO Directores (nombre, nacionalidad, fecha_nacimiento, numero_peliculas, retirado, imagen)
VALUES ('Hayao Miyazaki', 'Japan', '1941-01-05', 12, 1, 'director5.jpg');
INSERT INTO Directores (nombre, nacionalidad, fecha_nacimiento, numero_peliculas, retirado, imagen)
VALUES ('Sofia Coppola', 'USA', '1971-05-14', 10, 1, 'director6.jpg');
INSERT INTO Directores (nombre, nacionalidad, fecha_nacimiento, numero_peliculas, retirado, imagen)
VALUES ('Bong Joon-ho', 'South Korea', '1969-09-14', 15, 0, 'director7.jpg');
INSERT INTO Directores (nombre, nacionalidad, fecha_nacimiento, numero_peliculas, retirado, imagen)
VALUES ('Alfonso Cuarón', 'Mexico', '1961-11-28', 18, 0, 'director8.jpg');
INSERT INTO Directores (nombre, nacionalidad, fecha_nacimiento, numero_peliculas, retirado, imagen)
VALUES ('Denis Villeneuve', 'Canada', '1967-10-03', 14, 0, 'director9.jpg');
INSERT INTO Directores (nombre, nacionalidad, fecha_nacimiento, numero_peliculas, retirado, imagen)
VALUES ('Jordan Peele', 'USA', '1979-02-21', 9, 0, 'director10.jpg');
INSERT INTO Directores (nombre, nacionalidad, fecha_nacimiento, numero_peliculas, retirado, imagen)
VALUES ('Jane Campion', 'New Zealand', '1954-04-30', 11, 0, 'director11.jpg');
INSERT INTO Directores (nombre, nacionalidad, fecha_nacimiento, numero_peliculas, retirado, imagen)
VALUES ('Taika Waititi', 'New Zealand', '1975-08-16', 7, 0, 'director12.jpg');


INSERT INTO Actores (nombre, nacionalidad, fecha_nacimiento, numero_peliculas, retirado, imagen)
VALUES ('Leonardo DiCaprio', 'USA', '1974-11-11', 52, 0, 'actor1.jpg');
INSERT INTO Actores (nombre, nacionalidad, fecha_nacimiento, numero_peliculas, retirado, imagen)
VALUES ('Penélope Cruz', 'Spain', '1976-04-28', 60, 0, 'actor2.jpg');
INSERT INTO Actores (nombre, nacionalidad, fecha_nacimiento, numero_peliculas, retirado, imagen)
VALUES ('Brad Pitt', 'USA', '1963-12-18', 70, 0, 'actor3.jpg');
INSERT INTO Actores (nombre, nacionalidad, fecha_nacimiento, numero_peliculas, retirado, imagen)
VALUES ('Tom Hanks', 'USA', '1956-07-09', 85, 0, 'actor4.jpg');
INSERT INTO Actores (nombre, nacionalidad, fecha_nacimiento, numero_peliculas, retirado, imagen)
VALUES ('Emma Stone', 'USA', '1988-11-06', 32, 0, 'actor5.jpg');
INSERT INTO Actores (nombre, nacionalidad, fecha_nacimiento, numero_peliculas, retirado, imagen)
VALUES ('Antonio Banderas', 'Spain', '1960-08-10', 55, 0, 'actor6.jpg');
INSERT INTO Actores (nombre, nacionalidad, fecha_nacimiento, numero_peliculas, retirado, imagen)
VALUES ('Scarlett Johansson', 'USA', '1984-11-22', 48, 0, 'actor7.jpg');
INSERT INTO Actores (nombre, nacionalidad, fecha_nacimiento, numero_peliculas, retirado, imagen)
VALUES ('Chris Hemsworth', 'Australia', '1983-08-11', 31, 0, 'actor8.jpg');
INSERT INTO Actores (nombre, nacionalidad, fecha_nacimiento, numero_peliculas, retirado, imagen)
VALUES ('Zendaya', 'USA', '1996-09-01', 20, 0, 'actor9.jpg');
INSERT INTO Actores (nombre, nacionalidad, fecha_nacimiento, numero_peliculas, retirado, imagen)
VALUES ('Daniel Brühl', 'Germany', '1978-06-16', 37, 0, 'actor10.jpg');
INSERT INTO Actores (nombre, nacionalidad, fecha_nacimiento, numero_peliculas, retirado, imagen)
VALUES ('Florence Pugh', 'UK', '1996-01-03', 20, 0, 'actor11.jpg');
INSERT INTO Actores (nombre, nacionalidad, fecha_nacimiento, numero_peliculas, retirado, imagen)
VALUES ('Rami Malek', 'USA', '1981-05-12', 18, 0, 'actor12.jpg');


INSERT INTO Peliculas (titulo, sinopsis, duracion, fecha_estreno, disponible_streaming, imagen, id_director)
VALUES ('Origen', 'Un ladrón entra en tus sueños… y encima te deja confundido por 10 años. ¡Nadie sabe si siguen soñando!', 148, '2010-07-16', 1, 'origen.jpg', 1);
INSERT INTO Peliculas (titulo, sinopsis, duracion, fecha_estreno, disponible_streaming, imagen, id_director)
VALUES ('Pulp Fiction', 'Balazos, maletines misteriosos y conversaciones profundas sobre hamburguesas. Todo muy Tarantino.', 154, '1994-10-14', 1, 'pulpfiction.jpg', 2);
INSERT INTO Peliculas (titulo, sinopsis, duracion, fecha_estreno, disponible_streaming, imagen, id_director)
VALUES ('Dolor y Gloria', 'Un director deprimido recuerda su vida, se droga un poco y encuentra inspiración. Muy meta y muy Almodóvar.', 113, '2019-03-22', 0, 'dolorygloria.jpg', 3);
INSERT INTO Peliculas (titulo, sinopsis, duracion, fecha_estreno, disponible_streaming, imagen, id_director)
VALUES ('Barbie', 'Barbie descubre que el mundo real no es rosa. Ken tampoco ayuda mucho. Feminismo con purpurina.', 114, '2023-07-21', 1, 'barbie.jpg', 4);
INSERT INTO Peliculas (titulo, sinopsis, duracion, fecha_estreno, disponible_streaming, imagen, id_director)
VALUES ('El viaje de Chihiro', 'Tus padres se convierten en cerdos, trabajas en un balneario para dioses raros y todo parece normal. En Japón todo vale.', 125, '2001-07-20', 0, 'chihiro.jpg', 5);
INSERT INTO Peliculas (titulo, sinopsis, duracion, fecha_estreno, disponible_streaming, imagen, id_director)
VALUES ('Lost in Translation', 'Dos personas tristes se encuentran en Tokio y no hacen nada emocionante, pero lo hacen con estilo.', 102, '2003-09-12', 1, 'lost.jpg', 6);
INSERT INTO Peliculas (titulo, sinopsis, duracion, fecha_estreno, disponible_streaming, imagen, id_director)
VALUES ('Parásitos', 'Una familia se infiltra en otra como si fuera Among Us en versión Oscar. Sospechosamente genial.', 132, '2019-05-30', 1, 'parasitos.jpg', 7);
INSERT INTO Peliculas (titulo, sinopsis, duracion, fecha_estreno, disponible_streaming, imagen, id_director)
VALUES ('Roma', 'Una sirvienta lo hace todo: cuida niños, limpia, sobrevive y rompe tu corazón en blanco y negro.', 135, '2018-08-30', 1, 'roma.jpg', 8);
INSERT INTO Peliculas (titulo, sinopsis, duracion, fecha_estreno, disponible_streaming, imagen, id_director)
VALUES ('Dune', 'Arena, gusanos gigantes, profecías, más arena… y un tío guapo sufriendo en cámara lenta.', 155, '2021-10-22', 0, 'dune.jpg', 9);
INSERT INTO Peliculas (titulo, sinopsis, duracion, fecha_estreno, disponible_streaming, imagen, id_director)
VALUES ('Déjame salir', 'Conoces a los suegros y terminas hipnotizado, atrapado y con ganas de salir corriendo, literalmente.', 104, '2017-02-24', 1, 'getout.jpg', 10);
INSERT INTO Peliculas (titulo, sinopsis, duracion, fecha_estreno, disponible_streaming, imagen, id_director)
VALUES ('La lección de piano', 'Una mujer muda llega a Nueva Zelanda con un piano y una hija repelente. Se comunica golpeando teclas y enamora a un tío... sin decir una palabra.', 121, '1993-11-19', 0, 'piano.jpg', 11);
INSERT INTO Peliculas (titulo, sinopsis, duracion, fecha_estreno, disponible_streaming, imagen, id_director)
VALUES ('Jojo Rabbit', 'Un niño nazi tiene como mejor amigo a Hitler... pero versión imaginaria y payasa. Satírica, tierna y más rara que un bocadillo de pepinillos con Nutella.', 108, '2019-10-18', 1, 'jojo.jpg', 12);

