CREATE TABLE Paradas (
    id_parada INT NOT NULL AUTO_INCREMENT,
    id_ruta INT NOT NULL,
    id_zona INT NOT NULL,
    latitud DECIMAL(10, 8) NOT NULL,
    longitud DECIMAL(10, 8) NOT NULL,
    color VARCHAR(15)

    PRIMARY KEY (id_parada),
    FOREIGN KEY (id_ruta) REFERENCES Rutas(id_ruta)
);

INSERT INTO Paradas (id_ruta, id_zona, latitud, longitud, color)
VALUES (1, 2, -16.53362900, -68.03659100, "#32a852");

INSERT INTO Paradas (id_ruta, id_zona, latitud, longitud, color)
VALUES (1, 1, -16.49326000, -68.15058900, "#32a852");

INSERT INTO Paradas (id_ruta, id_zona, latitud, longitud, color)
VALUES (2, 2, -16.53804700, -68.08740600, "#32a852");

INSERT INTO Paradas (id_ruta, id_zona, latitud, longitud, color)
VALUES (2, 2, -16.51865200, -68.11615100, "#32a852");