-- Script SQL - DB CavoshCoffee
--drop database IF EXISTS cavoshcoffee;
--create DATABASE cavoshcoffee;
--USE cavoshcoffee;

-- Tablas --
-- usuario
create TABLE usuario (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombres VARCHAR(100) NOT NULL,
  correo VARCHAR(100) UNIQUE NOT NULL,
  passwordd VARCHAR(100) NOT NULL,
  login TINYINT NOT NULL DEFAULT 0
);

-- distrito
create TABLE distrito (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(50) NOT NULL
);

-- producto
create TABLE producto (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  descripcion VARCHAR(255),
  precio DECIMAL(10,2) NOT NULL,
  detalle VARCHAR(255),
  categoria VARCHAR(50) NOT NULL,
  imagen VARCHAR(255) NOT NULL,
  esNuevo TINYINT NOT NULL DEFAULT 1
);

-- local
create TABLE local (
  id INT AUTO_INCREMENT PRIMARY KEY,
  razonSocial VARCHAR(100) NOT NULL,
  direccion VARCHAR(150) NOT NULL,
  idDistrito INT NOT NULL,
  horario VARCHAR(100) NOT NULL,
  latitud VARCHAR(20) NOT NULL,
  longitud VARCHAR(20) NOT NULL,
  FOREIGN KEY (idDistrito) REFERENCES distrito(id) ON update CASCADE ON delete CASCADE
);

-- favorito
create TABLE favorito (
  id INT AUTO_INCREMENT PRIMARY KEY,
  idUsuario INT NOT NULL,
  idProducto INT NOT NULL,
  FOREIGN KEY (idUsuario) REFERENCES usuario(id) ON update CASCADE ON delete CASCADE,
  FOREIGN KEY (idProducto) REFERENCES producto(id) ON update CASCADE ON delete CASCADE
);