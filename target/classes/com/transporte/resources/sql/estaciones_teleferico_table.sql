CREATE TABLE Estaciones_teleferico (
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(80),
    direccion VARCHAR(80),
    parqueo TINYINT,
    idParada INT,

    PRIMARY KEY (id),
    FOREIGN KEY (idParada) REFERENCES Parada(id_parada);
);

INSERT INTO Estaciones_teleferico (nombre, direccion, parqueo, idParada)
VALUES ("Cementerio", "Av. Baptista", 1, 1);