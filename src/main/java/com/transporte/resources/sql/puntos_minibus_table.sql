CREATE TABLE Puntos_minibus (
    id INT NOT NULL AUTO_INCREMENT,
    trayectoria TINYINT,
    idParada INT,

    PRIMARY KEY (id),
    FOREIGN KEY (idParada) REFERENCES Paradas(id_parada)
);

