CREATE TABLE Ruta_Numeros (
    id_ruta_numero INT NOT NULL AUTO_INCREMENT,
    id_ruta INT,
    numero VARCHAR(10),
    
    PRIMARY KEY (id_ruta_numero),
    FOREIGN KEY (id_ruta) REFERENCES Rutas(id_ruta)
);

INSERT INTO Ruta_Numeros (id_ruta, numero)
VALUES (1, "220");