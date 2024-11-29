CREATE TABLE Rutas (
    id_ruta INT NOT NULL AUTO_INCREMENT,
    nombre_inicio VARCHAR(30),
    nombre_fin VARCHAR(30),
    horario_inicio VARCHAR(10),
    horario_fin VARCHAR(10),
    estado INT,
    tipo_transporte INT,
    
    PRIMARY KEY (id_ruta)
);

INSERT INTO Rutas (nombre_inicio, nombre_fin, horario_inicio, horario_fin, estado, tipo_transporte)
VALUES ("Av. Apumalla", "14 De Septiembre", "06:00", "23:00", 1, 1);

INSERT INTO Rutas (nombre_inicio, nombre_fin, horario_inicio, horario_fin, estado, tipo_transporte)
VALUES ("Libertador", "Irpavi", "06:00", "22:00", 1, 3);