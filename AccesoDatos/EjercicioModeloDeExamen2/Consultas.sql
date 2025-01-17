BEGIN TRANSACTION;

-- Inserta en equipos primero
INSERT INTO equipos (id_equipo, nombre_equipo, marca, nacionalidad, presupuesto, lider)
VALUES ('1', 'Equipo Alpha', 'Marca X', 'España', 1000000.0, '1');

-- Inserta en participantes después
INSERT INTO participantes (id_participante, pertenece_a_equipo, apellidos, dorsal, nombre, fecha_nacimiento)
VALUES ('1', '1', 'Perez', '001', 'Juan', '1997-06-02');

COMMIT;




BEGIN TRANSACTION;

-- Inserta en la tabla `equipos`
INSERT INTO equipos (id_equipo, nombre_equipo, marca, nacionalidad, presupuesto, lider)
VALUES 
('2', 'Equipo Beta', 'Marca Y', 'Francia', 1500000.0, '3'),
('3', 'Equipo Gamma', 'Marca Z', 'Italia', 2000000.0, '5');

-- Inserta en la tabla `participantes`
INSERT INTO participantes (id_participante, pertenece_a_equipo, apellidos, dorsal, nombre, fecha_nacimiento)
VALUES 
('2', '1', 'Gomez', '002', 'Luis', '1995-04-18'),
('3', '2', 'Martinez', '003', 'Carlos', '1990-09-12'),
('4', '2', 'Lopez', '004', 'Ana', '1992-07-30'),
('5', '3', 'Hernandez', '005', 'Elena', '1998-03-25'),
('6', '3', 'Torres', '006', 'Miguel', '1996-11-15');

COMMIT;
ROLLBACK;




UPDATE participantes
SET pertenece_a_equipo = '2'
WHERE id_participante = '1';