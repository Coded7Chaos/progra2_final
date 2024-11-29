CREATE TABLE Carteles (
    id_cartel INT NOT NULL AUTO_INCREMENT,
    id_ruta INT,
    nombre VARCHAR(20),
    
    PRIMARY KEY (id_cartel),
    FOREIGN KEY (id_ruta) REFERENCES Rutas(id_ruta)
);

INSERT INTO Carteles (id_ruta, nombre)
VALUES (1, "ILLAMPU");

INSERT INTO Carteles (id_ruta, nombre)
VALUES (1, "RODRIGUEZ");

INSERT INTO Carteles (id_ruta, nombre)
VALUES (1, "OBRAJES");