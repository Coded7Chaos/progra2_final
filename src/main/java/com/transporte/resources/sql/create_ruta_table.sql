CREATE TABLE Rutas (
    id_ruta INT NOT NULL AUTO_INCREMENT,
    nombre_inicio VARCHAR(20),
    nombre_fin VARCHAR(20),
    horario_inicio VARCHAR(10),
    horario_fin VARCHAR(10),
    estado INT,
    
    PRIMARY KEY (id_ruta)
);