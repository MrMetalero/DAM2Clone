-- LA TABLA ESTABA COMO PERTENCE XD

--SE DEBERÁ HACER EN UNA TRANSACCIÓN LOS INSERTS DE ARTISTAs, METIENDO UN REGISTRO EN PERTENECE CON EL DNI DEL GRUPO ARTISTA


CREATE OR REPLACE FUNCTION no_permitir_grupos_sin_artistas() 
RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM pertence 
        WHERE grupo_id = OLD.grupo_id AND dni <> OLD.dni
    ) THEN
        RAISE EXCEPTION 'NO SE PUEDE ELIMINAR EL ÚLTIMO ARTISTA DE UN GRUPO';
    END IF;

    RETURN OLD;
END;
$$;

CREATE OR REPLACE TRIGGER tg_no_permitir_grupos_sin_artistas
    BEFORE DELETE
    ON public.pertence
    FOR EACH ROW
    EXECUTE FUNCTION no_permitir_grupos_sin_artistas();


--TRANSACCIÓN 
BEGIN;

INSERT INTO artista VALUES ('24124112','ARTISTAPRUEBA2');

INSERT INTO pertence  VALUES ('24124112','2','test');


COMMIT;

ROLLBACK;





CREATE OR REPLACE FUNCTION max_5_artistas_por_grupo() 
RETURNS TRIGGER
LANGUAGE 'plpgsql'
COST 100
VOLATILE NOT LEAKPROOF
AS $body$
BEGIN


    IF (SELECT COUNT(*) FROM pertence WHERE public.pertence.cod = NEW.cod) >= 5 
    THEN
        RAISE EXCEPTION 'NO PUEDES TENER MAS DE 5 ARTISTAS EN UN GRUPO';
        RETURN NULL;
    ELSE
        RETURN NEW;
    END IF;

END;
$body$;




CREATE OR REPLACE TRIGGER tg_max_5_artistas_por_grupo
    BEFORE INSERT
    ON public.pertence
    FOR EACH ROW
    EXECUTE FUNCTION public.max_5_artistas_por_grupo();



BEGIN;
-- Insertar grupos
INSERT INTO grupo (cod, nombre, fecha, pais) VALUES 
('G01', 'Rockstars', '2000-01-01', 'USA');

-- Insertar artistas
INSERT INTO artista (dni, nombre) VALUES 
('A001', 'John Doe'),
('A002', 'Jane Smith'),
('A003', 'Mike Johnson'),
('A004', 'Sarah Connor'),
('A005', 'Chris Evans'),
('A006', 'Emma Watson'); -- Este será rechazado

-- Insertar 5 artistas en el grupo G01 (debe funcionar)
INSERT INTO pertence (dni, cod, funcion) VALUES 
('A001', 'G01', 'Vocalista'),
('A002', 'G01', 'Guitarrista'),
('A003', 'G01', 'Bajista'),
('A004', 'G01', 'Tecladista'),
('A005', 'G01', 'Baterista');

-- Intentar agregar un sexto artista al grupo G01 (debe fallar)
INSERT INTO pertence (dni, cod, funcion) VALUES 
('A006', 'G01', 'Corista'); -- ERROR esperado: "NO PUEDES TENER MÁS DE 5 ARTISTAS EN UN GRUPO"

COMMIT;

ROLLBACK;