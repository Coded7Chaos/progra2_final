CREATE TABLE Zonas (
    id_zona INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(20) NOT NULL,
    
    PRIMARY KEY (id_zona)
);

INSERT INTO Zonas (nombre)
VALUES ("San Pedro");