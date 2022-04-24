DROP TABLE IF EXISTS USUARIO;
DROP TABLE IF EXISTS HOTEL;
DROP TABLE IF EXISTS RESERVA;
DROP TABLE IF EXISTS HABITACION;

CREATE TABLE USUARIO (
    NIF VARCHAR(9) PRIMARY KEY,
    NOMBRE VARCHAR(255) NOT NULL,
    APELLIDO1 VARCHAR(255) NOT NULL,
    APELLIDO2 VARCHAR(255) NOT NULL,
    CORREO VARCHAR(255) NOT NULL,
    CONTRASENA VARCHAR(255) NOT NULL,
    CUMPLEANOS DATE NOT NULL,
    ROL VARCHAR(255) NOT NULL
);

CREATE TABLE HOTEL(
    NOMBRE VARCHAR(255) PRIMARY KEY,
    DESTINO VARCHAR(255) NOT NULL,
    CAPACIDAD INTEGER NOT NULL,
    OCUPACION INTEGER NOT NULL,
    ESTADO BOOLEAN NOT NULL
);

CREATE TABLE RESERVA(
    ID INTEGER IDENTITY PRIMARY KEY,
    NIF VARCHAR(9) NOT NULL,
    HOTEL VARCHAR(255) NOT NULL,
    DESTINO VARCHAR(255) NOT NULL,
    HUESPEDES INTEGER NOT NULL,
    HABITACIONES INTEGER NOT NULL,
    FECHAENTRADA DATE NOT NULL,
    FECHASALIDA DATE NOT NULL
);

CREATE TABLE HABITACION (
    NUMERO INTEGER IDENTITY PRIMARY KEY,
    PLANTA INTEGER NOT NULL,
    HOTEL VARCHAR(255) NOT NULL,
    CAPACIDAD INTEGER NOT NULL,
    TIPO VARCHAR(255) NOT NULL,
    ESTADO BOOLEAN NOT NULL
);