CREATE TABLE Transportes (
    id_transporte INT NOT NULL AUTO_INCREMENT,
    id_ruta INT NOT NULL,
    nombre VARCHAR(20),
    tipo VARCHAR(20),

    PRIMARY KEY (id_transporte),
    FOREIGN KEY (id_ruta) REFERENCES Rutas(id_ruta)
);

INSERT INTO Transportes (id_ruta, nombre, tipo)
VALUES (1, "San Pedro - Achumani", "Pumakatari");

INSERT INTO Transportes (id_ruta, nombre, tipo)
VALUES (2, "Plaza Camacho - Chasquipampa", "Pumakatari");