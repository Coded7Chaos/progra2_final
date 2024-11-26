CREATE TABLE Paradas (
    id_parada INT NOT NULL AUTO_INCREMENT,
    id_ruta INT NOT NULL,
    id_zona INT NOT NULL,
    nombre VARCHAR(20) NOT NULL,
    longitud DECIMAL(10, 8) NOT NULL,
    latitud DECIMAL(10, 8) NOT NULL,
    direccion VARCHAR(30),
    color VARCHAR(15),
    estado TINYINT(1),
    
    PRIMARY KEY (id_parada),
    FOREIGN KEY (id_ruta) REFERENCES Rutas(id_ruta),
    FOREIGN KEY (id_zona) REFERENCES Zonas(id_zona)
);