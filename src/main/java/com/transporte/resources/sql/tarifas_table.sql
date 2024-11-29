CREATE TABLE Tarifas (
    id_tarifa INT NOT NULL AUTO_INCREMENT,
    id_ruta INT,
    nombre VARCHAR(20),
    costo DECIMAL(10, 8),
    
    PRIMARY KEY (id_tarifa),
    FOREIGN KEY (id_ruta) REFERENCES Rutas(id_ruta)
);

INSERT INTO Tarifas (id_ruta, nombre, costo)
VALUES (1, "Regular", 2.0);

INSERT INTO Tarifas (id_ruta, nombre, costo)
VALUES (1, "Nocturna", 2.2);

INSERT INTO Tarifas (id_ruta, nombre, costo)
VALUES (2, "Regular", 2.0);

INSERT INTO Tarifas (id_ruta, nombre, costo)
VALUES (2, "Tercera edad", 1.8);