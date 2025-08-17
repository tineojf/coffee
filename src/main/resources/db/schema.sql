-- Script MySQL - Base de Datos CavoshCoffee
DROP DATABASE IF EXISTS CavoshCoffee;
CREATE DATABASE CavoshCoffee;
USE CavoshCoffee;


-- Tablas --

CREATE TABLE Usuario (
  id INT AUTO_INCREMENT PRIMARY KEY,
  Nombres VARCHAR(100) NOT NULL,
  Correo VARCHAR(100) UNIQUE NOT NULL,
  Passwordd VARCHAR(100) NOT NULL,
  Login TINYINT NOT NULL DEFAULT 0
);

CREATE TABLE Local (
  id INT AUTO_INCREMENT PRIMARY KEY,
  RazonSocial VARCHAR(100) NOT NULL,
  Direccion VARCHAR(150) NOT NULL,
  idDistrito INT NOT NULL,
  Distrito VARCHAR(50) NOT NULL,
  Horario VARCHAR(100) NOT NULL,
  Latitud VARCHAR(20),
  Longitud VARCHAR(20)
);


-- Procedimientos almacenados --

-- Registrar Usuario --
DELIMITER $$
CREATE PROCEDURE sp_registrarUsuario(
    IN pNombres VARCHAR(100),
    IN pCorreo VARCHAR(100),
    IN pPasswordd VARCHAR(100)
)
BEGIN
    INSERT INTO Usuario (Nombres, Correo, Passwordd, Login)
    VALUES (pNombres, pCorreo, pPasswordd, 0);
END $$
DELIMITER ;

-- Login Usuario --
DELIMITER $$
CREATE PROCEDURE sp_loginUsuario(
    IN pCorreo VARCHAR(100),
    IN pPasswordd VARCHAR(100)
)
BEGIN
    SELECT id, Nombres, Correo
    FROM Usuario
    WHERE Correo = pCorreo AND Passwordd = pPasswordd;
END $$
DELIMITER ;

-- Listar Usuarios --
DELIMITER $$
CREATE PROCEDURE sp_getUsuarios()
BEGIN
    SELECT id, Nombres, Correo, Login FROM Usuario;
END $$
DELIMITER ;

-- Registrar Local --
DELIMITER $$
CREATE PROCEDURE sp_registrarLocal(
    IN pRazonSocial VARCHAR(100),
    IN pDireccion VARCHAR(150),
    IN pIdDistrito INT,
    IN pDistrito VARCHAR(50),
    IN pHorario VARCHAR(100),
    IN pLatitud VARCHAR(20),
    IN pLongitud VARCHAR(20)
)
BEGIN
    INSERT INTO Local(RazonSocial, Direccion, idDistrito, Distrito, Horario, Latitud, Longitud)
    VALUES (pRazonSocial, pDireccion, pIdDistrito, pDistrito, pHorario, pLatitud, pLongitud);
END $$
DELIMITER ;

-- Listar Locales --
DELIMITER $$
CREATE PROCEDURE sp_getLocales()
BEGIN
    SELECT * FROM Local;
END $$
DELIMITER ;

-- Obtener Local por ID --
DELIMITER $$
CREATE PROCEDURE sp_getLocalById(IN pId INT)
BEGIN
    SELECT * FROM Local WHERE id = pId;
END $$
DELIMITER ;