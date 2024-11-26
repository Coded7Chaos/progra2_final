CREATE TABLE Transportes (
    id_transporte INT NOT NULL AUTO_INCREMENT,
    id_ruta INT NOT NULL,
    nombre VARCHAR(20),
    tipo VARCHAR(20),

    PRIMARY KEY (id_transporte),
    FOREIGN KEY (id_ruta) REFERENCES Rutas(id_ruta)
);