UPDATE grupo 
SET n_participantes = (
    SELECT COUNT(*) FROM pertence  WHERE pertence.cod = grupo.cod
);


CREATE OR REPLACE FUNCTION actualizar_n_participantes() 
RETURNS TRIGGER 
LANGUAGE plpgsql 
AS $$
BEGIN
    IF TG_OP = 'INSERT' THEN
        UPDATE grupo 
        SET n_participantes = n_participantes + 1
        WHERE cod = NEW.cod;
    ELSIF TG_OP = 'DELETE' THEN
        UPDATE grupo 
        SET n_participantes = n_participantes - 1
        WHERE cod = OLD.cod;
    END IF;
    
    RETURN NULL;
END;
$$;

--Mas facilillo detectando la operación y au
CREATE OR REPLACE TRIGGER tg_actualizar_n_participantes_insert
AFTER INSERT ON pertence
FOR EACH ROW 
EXECUTE FUNCTION actualizar_n_participantes();

CREATE OR REPLACE TRIGGER tg_actualizar_n_participantes_delete
AFTER DELETE ON pertence
FOR EACH ROW 
EXECUTE FUNCTION actualizar_n_participantes();


INSERT INTO pertence (dni, cod, funcion) VALUES ('A007', 'G01', 'Bajista');

DELETE FROM pertence WHERE dni = 'A002' AND cod = 'G01';

SELECT * FROM grupo;