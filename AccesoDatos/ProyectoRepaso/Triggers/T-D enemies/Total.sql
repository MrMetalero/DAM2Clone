-- Active: 1727983525436@@127.0.0.1@5432@proyecto_repaso@public

CREATE OR REPLACE FUNCTION herencia_total_enemies()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
COST 100
VOLATILE NOT LEAKPROOF
AS $body$
DECLARE

BEGIN
    IF EXISTS(SELECT 1 FROM basic_enemies) OR EXISTS(SELECT 1 FROM elite_enemies)
    THEN
    RETURN NEW;
    ELSE
    RAISE EXCEPTION 'No se pueden insertar/borrar/actualizar enemigos que no estén en la tabla basic_enemies o elite_enemies';
    RETURN NULL;
    END IF;
END;
$body$;



CREATE CONSTRAINT TRIGGER tg_herencia_total_enemies
    AFTER INSERT OR UPDATE
    ON enemies
    DEFERRABLE INITIALLY DEFERRED
    FOR EACH ROW
    EXECUTE FUNCTION public.herencia_total_enemies();
