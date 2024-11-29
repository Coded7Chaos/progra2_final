CREATE TABLE Lineas (
    id_linea INT NOT NULL AUTO_INCREMENT,
    id_ruta INT,
    nombre VARCHAR(20),
    
    PRIMARY KEY (id_linea),
    FOREIGN KEY (id_ruta) REFERENCES Rutas(id_ruta)
);


INSERT INTO Lineas (id_ruta, nombre) VALUES (1, "Roja");