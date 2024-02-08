CREATE DATABASE IF NOT EXISTS cliente;

USE cliente;

CREATE TABLE persona (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    cedula VARCHAR(15),
    edad INT,
    direccion VARCHAR(255),
    telefono VARCHAR(20)
);

INSERT INTO persona (nombre, apellido, cedula, edad, direccion, telefono) VALUES ('Juan', 'Pérez', '123456789', 30, 'Calle 123, Ciudad', '123-456-7890');
INSERT INTO persona (nombre, apellido, cedula, edad, direccion, telefono) VALUES ('María', 'Gómez', '987654321', 28, 'Avenida ABC, Pueblo', '987-654-3210');
INSERT INTO persona (nombre, apellido, cedula, edad, direccion, telefono) VALUES ('Carlos', 'López', '555123456', 25, 'Carrera XYZ, Villa', '555-123-4567');
INSERT INTO persona (nombre, apellido, cedula, edad, direccion, telefono) VALUES ('Ana', 'Martínez', '222333444', 22, 'Callejón 456, Aldea', '222-333-4444');
INSERT INTO persona (nombre, apellido, cedula, edad, direccion, telefono) VALUES ('Pedro', 'Rodríguez', '777888999', 35, 'Avenida 789, Ciudad', '777-888-9999');


