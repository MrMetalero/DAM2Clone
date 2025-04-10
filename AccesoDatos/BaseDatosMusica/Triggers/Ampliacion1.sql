CREATE TABLE grupos_borrados(
    cod VARCHAR(3) PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    fecha DATE,
    pais VARCHAR(30)
)
;


CREATE OR REPLACE FUNCTION registro_borrado_grupos()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
COST 100
VOLATILE NOT LEAKPROOF
AS $body$
BEGIN
    INSERT INTO grupos_borrados (cod, nombre, fecha, pais)
    VALUES 
    (OLD.cod, OLD.nombre, OLD.fecha, OLD.pais);

    RETURN NEW;
END;
$body$;



CREATE OR REPLACE TRIGGER tg_registro_borrado_grupos
    AFTER DELETE
    ON public.grupo
    FOR EACH ROW
    EXECUTE FUNCTION public.registro_borrado_grupos();
