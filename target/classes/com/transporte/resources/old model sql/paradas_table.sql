CREATE TABLE Paradas (
    id_parada INT NOT NULL AUTO_INCREMENT,
    id_ruta INT NOT NULL,
    id_zona INT NOT NULL,
    nombre VARCHAR(30) NOT NULL,
    latitud DECIMAL(10, 8) NOT NULL,
    longitud DECIMAL(10, 8) NOT NULL,
    direccion VARCHAR(30),
    color VARCHAR(15),
    estado TINYINT,
    
    PRIMARY KEY (id_parada),
    FOREIGN KEY (id_ruta) REFERENCES Rutas(id_ruta),
    FOREIGN KEY (id_zona) REFERENCES Zonas(id_zona)
);

INSERT INTO Paradas (id_ruta, id_zona, nombre, latitud, longitud, direccion, color, estado)
VALUES (1, 2, "14 de Septiembre", -16.533629, -68.036591, "Av. Muñoz Reyes", "#32a852", 1);

INSERT INTO Paradas (id_ruta, id_zona, nombre, latitud, longitud, direccion, color, estado)
VALUES (1, 1, "Av Apumalla", -16.493260, -68.150589, "Peñas", "#32a852", 1);


INSERT INTO Paradas (id_ruta, id_zona, nombre, latitud, longitud, direccion, color, estado)
VALUES (2, 2, "Irpavi", -16.538047, -68.087406, "Av. Fuerza Naval", "#32a852", 1);

INSERT INTO Paradas (id_ruta, id_zona, nombre, latitud, longitud, direccion, color, estado)
VALUES (2, 2, "Libertador", -16.518652, -68.116151, "Curva de Holguin", "#32a852", 1);
