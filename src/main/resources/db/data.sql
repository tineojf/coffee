insert into usuario (nombres, correo, passwordd) values
('admin', 'admin@mail.com', 'admin123'),
('user', 'user@gmail.com', 'user123');

-- insert distrito
insert into distrito (nombre) values
('Miraflores'),
('San Isidro'),
('San Borja'),
('Santiago de Surco'),
('La Molina'),
('San Miguel'),
('Magdalena del Mar'),
('Pueblo Libre'),
('Jesús María'),
('Lince'),
('Barranco'),
('Chorrillos'),
('Villa El Salvador'),
('San Juan de Lurigancho'),
('Comas');

-- insert producto
insert into producto (nombre, descripcion, precio, detalle, categoria, imagen, esNuevo) values
-- Cafés
('Café Americano', 'Café filtrado estilo americano', 6.50, 'Taza 8oz', 'Café', 'https://www.shutterstock.com/image-photo/coffee-cup-saucer-teaspoon-pack-600nw-2337186769.jpg', 1),
('Espresso', 'Café concentrado servido en taza pequeña', 5.00, 'Shot simple', 'Café', 'https://www.shutterstock.com/image-photo/coffee-cup-saucer-teaspoon-pack-600nw-2337186769.jpg', 0),
('Cappuccino', 'Café espresso con leche vaporizada y espuma', 8.50, 'Taza 12oz', 'Café', 'https://www.shutterstock.com/image-photo/coffee-cup-saucer-teaspoon-pack-600nw-2337186769.jpg', 1),
('Latte', 'Café espresso con leche cremosa', 8.00, 'Taza 12oz', 'Café', 'https://www.shutterstock.com/image-photo/coffee-cup-saucer-teaspoon-pack-600nw-2337186769.jpg', 0),
('Mocha', 'Latte con chocolate y crema batida', 9.50, 'Taza 12oz', 'Café', 'https://www.shutterstock.com/image-photo/coffee-cup-saucer-teaspoon-pack-600nw-2337186769.jpg', 0),

-- Fríos
('Frappé Vainilla', 'Bebida fría a base de café y vainilla', 11.00, 'Vaso 16oz', 'Bebida fría', 'https://img.freepik.com/psd-gratis/refrescante-bebida-cola-fria-vaso-salpicaduras_632498-25634.jpg', 1),
('Iced Latte', 'Café espresso con leche y hielo', 9.00, 'Vaso 16oz', 'Bebida fría', 'https://img.freepik.com/psd-gratis/refrescante-bebida-cola-fria-vaso-salpicaduras_632498-25634.jpg', 0),

-- Pastelería
('Croissant', 'Hojaldre de mantequilla horneado', 4.50, 'Unidad', 'Pastelería', 'https://www.shutterstock.com/image-photo/assorted-fancy-cakes-shopwindow-260nw-2189877387.jpg', 0),
('Muffin de Arándanos', 'Muffin suave con trozos de arándanos', 5.00, 'Unidad', 'Pastelería', 'https://www.shutterstock.com/image-photo/assorted-fancy-cakes-shopwindow-260nw-2189877387.jpg', 1),
('Brownie', 'Brownie de chocolate con nueces', 6.00, 'Porción', 'Pastelería', 'https://www.shutterstock.com/image-photo/assorted-fancy-cakes-shopwindow-260nw-2189877387.jpg', 0),

-- Sándwiches
('Sándwich de Jamón y Queso', 'Pan artesanal con jamón y queso fundido', 10.00, 'Unidad', 'Sándwich', 'https://bimbousa.com/sites/default/files/2022-07/Receta-Sandwich-de-pollo-a-la-mostaza-Bimbo.jpg', 0),
('Sándwich de Pollo', 'Pan ciabatta con pollo a la parrilla y lechuga', 12.00, 'Unidad', 'Sándwich', 'https://bimbousa.com/sites/default/files/2022-07/Receta-Sandwich-de-pollo-a-la-mostaza-Bimbo.jpg', 1),

-- Extras
('Té Verde', 'Infusión de té verde natural', 7.00, 'Taza 8oz', 'Infusiones', 'https://static.vecteezy.com/system/resources/thumbnails/040/174/391/small/ai-generated-pictures-of-delicious-and-beautiful-drinks-photo.jpg', 0),
('Limonada Frozen', 'Refrescante limonada frappé', 8.50, 'Vaso 16oz', 'Bebida fría', 'https://static.vecteezy.com/system/resources/thumbnails/040/174/391/small/ai-generated-pictures-of-delicious-and-beautiful-drinks-photo.jpg', 1),
('Agua Mineral', 'Botella de agua sin gas', 3.50, '500ml', 'Otros', 'https://static.vecteezy.com/system/resources/thumbnails/040/174/391/small/ai-generated-pictures-of-delicious-and-beautiful-drinks-photo.jpg', 0);


-- Favoritos de admin
insert into favorito(idUsuario, idProducto) values
(1, 1),  -- Café Americano
(1, 3),  -- Cappuccino
(1, 7),  -- Iced Latte
(1, 10); -- Sándwich de Pollo

-- Favoritos de Maria Lopez
insert into favorito(idUsuario, idProducto) values
(2, 2),  -- Espresso
(2, 4),  -- Latte
(2, 8),  -- Croissant
(2, 12); -- Limonada Frozen

-- Insertar locales
INSERT INTO local (razonSocial, direccion, idDistrito, horario, latitud, longitud) VALUES
('Cavosh Coffee Miraflores', 'Av. Larco 1234', 1, 'Lun-Dom 7:00am - 10:00pm', '-12.123456', '-77.032123'),
('Cavosh Coffee San Isidro', 'Calle Los Sauces 567', 2, 'Lun-Vie 7:30am - 9:00pm', '-12.099876', '-77.036543'),
('Cavosh Coffee Barranco', 'Av. Grau 890', 3, 'Lun-Dom 8:00am - 11:00pm', '-12.144321', '-77.022345'),
('Cavosh Coffee Surco', 'Av. Primavera 456', 4, 'Lun-Sab 7:00am - 9:30pm', '-12.145678', '-77.012987');
