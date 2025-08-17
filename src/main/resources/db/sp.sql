-- Procedimientos almacenados --

-- Registrar Usuario --
DELIMITER $$
create procedure sp_registrarUsuario(
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
create procedure sp_loginUsuario(
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
create procedure sp_getUsuarios()
BEGIN
    SELECT id, Nombres, Correo, Login FROM Usuario;
END $$
DELIMITER ;

-- Registrar Local --
DELIMITER $$
create procedure sp_registrarLocal(
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
create procedure sp_getLocales()
BEGIN
    SELECT * FROM Local;
END $$
DELIMITER ;

-- Obtener Local por ID --
DELIMITER $$
create procedure sp_getLocalById(IN pId INT)
BEGIN
    SELECT * FROM Local WHERE id = pId;
END $$
DELIMITER ;


DELIMITER $$
-- Usuario
CREATE PROCEDURE sp_get_usuarios()
BEGIN
    SELECT * FROM usuario;
END $$

-- Distrito
CREATE PROCEDURE sp_get_distritos()
BEGIN
    SELECT * FROM distrito;
END $$

-- Producto
CREATE PROCEDURE sp_get_productos()
BEGIN
    SELECT * FROM producto;
END $$

-- Local
CREATE PROCEDURE sp_get_locales()
BEGIN
    SELECT l.id, l.razonSocial, l.direccion, d.nombre AS distrito, l.horario, l.latitud, l.longitud
    FROM local l
    JOIN distrito d ON l.idDistrito = d.id;
END $$

-- Favorito
CREATE PROCEDURE sp_get_favoritos()
BEGIN
    SELECT f.id, u.nombres AS usuario, p.nombre AS producto
    FROM favorito f
    JOIN usuario u ON f.idUsuario = u.id
    JOIN producto p ON f.idProducto = p.id;
END $$

CREATE PROCEDURE sp_get_favoritos_by_usuario(
    IN p_idUsuario INT
)
BEGIN
    SELECT f.id, u.nombres AS usuario, p.nombre AS producto
    FROM favorito f
    JOIN usuario u ON f.idUsuario = u.id
    JOIN producto p ON f.idProducto = p.id
    WHERE f.idUsuario = p_idUsuario;
END $$

DELIMITER ;
