--Insertamos algunos registros para que la base de datos no este vacia 

INSERT INTO HOTEL(NOMBRE, DESTINO, CAPACIDAD, OCUPACION, ESTADO) VALUES ('Melia-Madrid-Princesa','Madrid',274,190,1);
INSERT INTO HOTEL(NOMBRE, DESTINO, CAPACIDAD, OCUPACION, ESTADO) VALUES ('Gran-Melia-Palacio-de-los-Duques','Madrid',180,89,1);
INSERT INTO HOTEL(NOMBRE, DESTINO, CAPACIDAD, OCUPACION, ESTADO) VALUES ('Melia-White-House','Londres',581,492,1);
INSERT INTO HOTEL(NOMBRE, DESTINO, CAPACIDAD, OCUPACION, ESTADO) VALUES ('ME-London','Londres',157,100,1);
INSERT INTO HOTEL(NOMBRE, DESTINO, CAPACIDAD, OCUPACION, ESTADO) VALUES ('Innside-Paris-Charles-de-Gaulle','Paris',278,100,1);
INSERT INTO HOTEL(NOMBRE, DESTINO, CAPACIDAD, OCUPACION, ESTADO) VALUES ('Melia-Paris-La-Defense','Paris',369,322,1);
INSERT INTO HOTEL(NOMBRE, DESTINO, CAPACIDAD, OCUPACION, ESTADO) VALUES ('TRYP-New-York-Times-Square','Nueva-York',173,62,1);
INSERT INTO HOTEL(NOMBRE, DESTINO, CAPACIDAD, OCUPACION, ESTADO) VALUES ('Innside-New-York-Nomad','Nueva-York',313,183,1);

INSERT INTO USUARIO(NIF, NOMBRE, APELLIDO1, APELLIDO2, CORREO, CONTRASENA, CUMPLEANOS, ROL) VALUES ('41234189L','Sergio','Cuenca','Nunez','scuenca@melia.com','Melia','2001-12-12','admin');
INSERT INTO USUARIO(NIF, NOMBRE, APELLIDO1, APELLIDO2, CORREO, CONTRASENA, CUMPLEANOS, ROL) VALUES ('80762074N','Elena','Conderana','Medem','econderana@melia.com','Melia','2001-04-28','admin');
INSERT INTO USUARIO(NIF, NOMBRE, APELLIDO1, APELLIDO2, CORREO, CONTRASENA, CUMPLEANOS, ROL) VALUES ('68060671Z','Javier','Barneda','Carrion','javier_barneda@gmail.com','JavierCuenca','2000-02-8','cliente');
INSERT INTO USUARIO(NIF, NOMBRE, APELLIDO1, APELLIDO2, CORREO, CONTRASENA, CUMPLEANOS, ROL) VALUES ('35980493Y','Ana','Alvarez','Perez','aa@yahoo.com','AAP','2000-06-3','cliente');
INSERT INTO USUARIO(NIF, NOMBRE, APELLIDO1, APELLIDO2, CORREO, CONTRASENA, CUMPLEANOS, ROL) VALUES ('74575576P','Laura','Hernandez','Pardo','laura.h@gmail.com','Lauritaa','2000-08-27','cliente');
INSERT INTO USUARIO(NIF, NOMBRE, APELLIDO1, APELLIDO2, CORREO, CONTRASENA, CUMPLEANOS, ROL) VALUES ('73010655F','Gerardo','Fernandez','Gomez','ag@gmail.com','GerarF','2000-03-12','cliente');
INSERT INTO USUARIO(NIF, NOMBRE, APELLIDO1, APELLIDO2, CORREO, CONTRASENA, CUMPLEANOS, ROL) VALUES ('38421952S','Soraya','Conderana','Sicilia','sorayita@gmail.com','sorcorsic','2000-03-12','cliente');
INSERT INTO USUARIO(NIF, NOMBRE, APELLIDO1, APELLIDO2, CORREO, CONTRASENA, CUMPLEANOS, ROL) VALUES ('56808995F','Inés','Garcia','Santamaria','ele@gmail.com','ElenaGarcia829','2000-09-12','cliente');
INSERT INTO USUARIO(NIF, NOMBRE, APELLIDO1, APELLIDO2, CORREO, CONTRASENA, CUMPLEANOS, ROL) VALUES ('86104585Z','Isabel','Gonzalez','Casado','isaa@gmail.com','IsabelGlezz','2000-09-9','cliente');
INSERT INTO USUARIO(NIF, NOMBRE, APELLIDO1, APELLIDO2, CORREO, CONTRASENA, CUMPLEANOS, ROL) VALUES ('87504295J','Jaime','Lopez','Ayuso','jla@gmail.com','JaimeLo','2000-09-9','cliente');
INSERT INTO USUARIO(NIF, NOMBRE, APELLIDO1, APELLIDO2, CORREO, CONTRASENA, CUMPLEANOS, ROL) VALUES ('93528386J','Santiago','Pascual','Sanchez','g_pas@gmail.com','SantiPas','2000-11-9','cliente');
INSERT INTO USUARIO(NIF, NOMBRE, APELLIDO1, APELLIDO2, CORREO, CONTRASENA, CUMPLEANOS, ROL) VALUES ('48802294C','Dario','Quiroga','Iglesias','dario.q@gmail.com','SellesFerriz','2000-11-3','cliente');
INSERT INTO USUARIO(NIF, NOMBRE, APELLIDO1, APELLIDO2, CORREO, CONTRASENA, CUMPLEANOS, ROL) VALUES ('24215194N','Esteban','Selles','Ferriz','esteban.s.f@gmail.com','Esteban3393','2000-12-3','cliente');
INSERT INTO USUARIO(NIF, NOMBRE, APELLIDO1, APELLIDO2, CORREO, CONTRASENA, CUMPLEANOS, ROL) VALUES ('85630933R','Leo','Zamora','Mateos','xx_leo@yahoo.com','XXLEO','2000-12-6','cliente');
INSERT INTO USUARIO(NIF, NOMBRE, APELLIDO1, APELLIDO2, CORREO, CONTRASENA, CUMPLEANOS, ROL) VALUES ('67756676X','Pablo','Leon','Abella','pablox@yahoo.com','Pablito','2000-06-6','cliente');
INSERT INTO USUARIO(NIF, NOMBRE, APELLIDO1, APELLIDO2, CORREO, CONTRASENA, CUMPLEANOS, ROL) VALUES ('44203784S','Guillermo','Gutierrez','Vazquez','guti@yahoo.com','GuilleGuti532','2000-04-6','cliente');
INSERT INTO USUARIO(NIF, NOMBRE, APELLIDO1, APELLIDO2, CORREO, CONTRASENA, CUMPLEANOS, ROL) VALUES ('15979834D','Sara','Alamo','Carrasco','alamo.sara@yahoo.com','SaraAlamo9278','2000-03-4','cliente');

INSERT INTO RESERVA(ID, NIF, HOTEL, DESTINO, HUESPEDES, HABITACIONES, FECHAENTRADA, FECHASALIDA) VALUES (1,'68060671Z','Melia-Madrid-Princesa','Madrid',2,1,'2022-05-17','2022-05-25');
INSERT INTO RESERVA(ID, NIF, HOTEL, DESTINO, HUESPEDES, HABITACIONES, FECHAENTRADA, FECHASALIDA) VALUES (2,'35980493Y','Gran-Melia-Palacio-de-los-Duques','Madrid',2,1,'2022-05-17','2022-05-29');
INSERT INTO RESERVA(ID, NIF, HOTEL, DESTINO, HUESPEDES, HABITACIONES, FECHAENTRADA, FECHASALIDA) VALUES (3,'74575576P','Melia-White-House','Londres',4,2,'2022-05-17','2022-05-29');
INSERT INTO RESERVA(ID, NIF, HOTEL, DESTINO, HUESPEDES, HABITACIONES, FECHAENTRADA, FECHASALIDA) VALUES (4,'73010655F','ME-London','Londres',5,2,'2022-05-25','2022-05-20');
INSERT INTO RESERVA(ID, NIF, HOTEL, DESTINO, HUESPEDES, HABITACIONES, FECHAENTRADA, FECHASALIDA) VALUES (5,'38421952S','Innside-Paris-Charles-de-Gaulle','Paris',4,1,'2022-05-17','2022-05-31');
INSERT INTO RESERVA(ID, NIF, HOTEL, DESTINO, HUESPEDES, HABITACIONES, FECHAENTRADA, FECHASALIDA) VALUES (6,'56808995F','Melia-Paris-La-Defense','Paris',3,1,'2022-05-17','2022-05-25');
INSERT INTO RESERVA(ID, NIF, HOTEL, DESTINO, HUESPEDES, HABITACIONES, FECHAENTRADA, FECHASALIDA) VALUES (7,'86104585Z','TRYP-New-York-Times-Square','Nueva-York',5,1,'2022-05-17','2022-05-20');
INSERT INTO RESERVA(ID, NIF, HOTEL, DESTINO, HUESPEDES, HABITACIONES, FECHAENTRADA, FECHASALIDA) VALUES (8,'87504295J','Innside-New-York-Nomad','Nueva-York',3,2,'2022-05-17','2022-05-29');

INSERT INTO HABITACION(TIPO, NUMERO,PLANTA, HOTEL, CAPACIDAD, ESTADO) VALUES ('Premium', 123, 7, 'Melia-Madrid-Princesa',3,1);
INSERT INTO HABITACION(TIPO, NUMERO,PLANTA, HOTEL, CAPACIDAD, ESTADO) VALUES ('Suite', 208, 8, 'Melia-Madrid-Princesa',4,1);
INSERT INTO HABITACION(TIPO, NUMERO,PLANTA, HOTEL, CAPACIDAD, ESTADO) VALUES ('Grand-Suite-Presidencial', 273, 10, 'Melia-Madrid-Princesa',5,1);
INSERT INTO HABITACION(TIPO, NUMERO,PLANTA, HOTEL, CAPACIDAD, ESTADO) VALUES ('Supremme', 56, 3, 'Gran-Melia-Palacio-de-los-Duques',3,1);
INSERT INTO HABITACION(TIPO, NUMERO,PLANTA, HOTEL, CAPACIDAD, ESTADO) VALUES ('Suite-Deluxe', 162, 4, 'Gran-Melia-Palacio-de-los-Duques',4,1);
INSERT INTO HABITACION(TIPO, NUMERO,PLANTA, HOTEL, CAPACIDAD, ESTADO) VALUES ('Royal-Suite', 180, 5, 'Gran-Melia-Palacio-de-los-Duques',5,1);
INSERT INTO HABITACION(TIPO, NUMERO,PLANTA, HOTEL, CAPACIDAD, ESTADO) VALUES ('Premium-King', 302, 6, 'Melia-White-House',2,1);
INSERT INTO HABITACION(TIPO, NUMERO,PLANTA, HOTEL, CAPACIDAD, ESTADO) VALUES ('Junior-Suite', 420, 7, 'Melia-White-House',4,1);
INSERT INTO HABITACION(TIPO, NUMERO,PLANTA, HOTEL, CAPACIDAD, ESTADO) VALUES ('Marylebone-Suite', 557, 9, 'Melia-White-House',4,1);
INSERT INTO HABITACION(TIPO, NUMERO,PLANTA, HOTEL, CAPACIDAD, ESTADO) VALUES ('Mode', 37, 6, 'ME-London',2,1);
INSERT INTO HABITACION(TIPO, NUMERO,PLANTA, HOTEL, CAPACIDAD, ESTADO) VALUES ('Suite-Personality', 50, 7, 'ME-London',3,1);
INSERT INTO HABITACION(TIPO, NUMERO,PLANTA, HOTEL, CAPACIDAD, ESTADO) VALUES ('Penthouse-Suite', 149, 9, 'ME-London',4,1);
INSERT INTO HABITACION(TIPO, NUMERO,PLANTA, HOTEL, CAPACIDAD, ESTADO) VALUES ('Premium-Extra', 102, 5, 'Innside-Paris-Charles-de-Gaulle',3,1);
INSERT INTO HABITACION(TIPO, NUMERO,PLANTA, HOTEL, CAPACIDAD, ESTADO) VALUES ('Loft', 189, 6, 'Innside-Paris-Charles-de-Gaulle',4,1);
INSERT INTO HABITACION(TIPO, NUMERO,PLANTA, HOTEL, CAPACIDAD, ESTADO) VALUES ('Townhouse-Suite', 254, 8, 'Innside-Paris-Charles-de-Gaulle',4,1);
INSERT INTO HABITACION(TIPO, NUMERO,PLANTA, HOTEL, CAPACIDAD, ESTADO) VALUES ('Premium-Twin', 46, 5, 'Melia-Paris-La-Defense',3,1);
INSERT INTO HABITACION(TIPO, NUMERO,PLANTA, HOTEL, CAPACIDAD, ESTADO) VALUES ('Suite-Premium', 298, 6, 'Melia-Paris-La-Defense',4,1);
INSERT INTO HABITACION(TIPO, NUMERO,PLANTA, HOTEL, CAPACIDAD, ESTADO) VALUES ('Grand-Suite-Eiffel', 352, 8, 'Melia-Paris-La-Defense',4,1);
INSERT INTO HABITACION(TIPO, NUMERO,PLANTA, HOTEL, CAPACIDAD, ESTADO) VALUES ('Queen', 51, 2, 'TRYP-New-York-Times-Square',2,1);
INSERT INTO HABITACION(TIPO, NUMERO,PLANTA, HOTEL, CAPACIDAD, ESTADO) VALUES ('King', 136, 3, 'TRYP-New-York-Times-Square',3,1);
INSERT INTO HABITACION(TIPO, NUMERO,PLANTA, HOTEL, CAPACIDAD, ESTADO) VALUES ('Junior-Suite-Metro', 168, 4, 'TRYP-New-York-Times-Square',4,1);
INSERT INTO HABITACION(TIPO, NUMERO,PLANTA, HOTEL, CAPACIDAD, ESTADO) VALUES ('King-City', 184, 15, 'Innside-New-York-Nomad',3,1);
INSERT INTO HABITACION(TIPO, NUMERO,PLANTA, HOTEL, CAPACIDAD, ESTADO) VALUES ('Studio', 238, 18, 'Innside-New-York-Nomad',4,1);
INSERT INTO HABITACION(TIPO, NUMERO,PLANTA, HOTEL, CAPACIDAD, ESTADO) VALUES ('Townhouse-Junior-Suite', 305, 19, 'Innside-New-York-Nomad',4,1);
