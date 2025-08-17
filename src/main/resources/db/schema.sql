-- Script SQL - DB CavoshCoffee
drop database IF EXISTS cavoshcoffee;
create DATABASE cavoshcoffee;
USE cavoshcoffee;

-- Tablas --
-- usuario
create TABLE Usuario (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombres VARCHAR(100) NOT NULL,
  correo VARCHAR(100) UNIQUE NOT NULL,
  passwordd VARCHAR(100) NOT NULL,
  login TINYINT NOT NULL DEFAULT 0
);

-- local
create TABLE Local (
  id INT AUTO_INCREMENT PRIMARY KEY,
  razonSocial VARCHAR(100) NOT NULL,
  direccion VARCHAR(150) NOT NULL,
  idDistrito INT NOT NULL,
  distrito VARCHAR(50) NOT NULL,
  horario VARCHAR(100) NOT NULL,
  latitud VARCHAR(20),
  longitud VARCHAR(20)
);

-- distrito
create TABLE Distrito (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(50) NOT NULL
);

-- favoritos
create TABLE Favoritos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  idUsuario INT NOT NULL,
  idLocal INT NOT NULL,
  FOREIGN KEY (idUsuario) REFERENCES Usuario(id) ON update CASCADE ON delete CASCADE,
  FOREIGN KEY (idLocal) REFERENCES Local(id) ON update CASCADE ON delete CASCADE
);

-- productos
create TABLE Producto (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  descripcion VARCHAR(255),
  precio DECIMAL(10,2) NOT NULL,
  imagen VARCHAR(255),
  idLocal INT NOT NULL,
  FOREIGN KEY (idLocal) REFERENCES Local(id) ON update CASCADE ON delete CASCADE
);