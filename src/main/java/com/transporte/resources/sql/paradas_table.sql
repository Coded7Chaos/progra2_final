CREATE TABLE Paradas (
    id_parada INT NOT NULL AUTO_INCREMENT,
    id_ruta INT NOT NULL,
    id_zona INT NOT NULL,
    nombre VARCHAR(20) NOT NULL,
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
VALUES (1, 1, "San Pedro", -16.503172, -68.132334, "Cañada Stronger", "#32a852", 0);

INSERT INTO Paradas (id_ruta, id_zona, nombre, latitud, longitud, direccion, color, estado)
VALUES (1, 1, "Campo Verde", -16.507371, -68.052672, "Achumani", "#32a852", 0);