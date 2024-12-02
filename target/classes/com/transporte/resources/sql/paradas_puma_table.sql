CREATE TABLE Paradas_pumakatari (
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(80),
    direccion VARCHAR(80),
    estado TINYINT,
    trayectoria TINYINT,
    tiempo_espera INT,
    idParada INT,

    PRIMARY KEY (id),
    FOREIGN KEY (idParada) REFERENCES Paradas(id_parada)
);