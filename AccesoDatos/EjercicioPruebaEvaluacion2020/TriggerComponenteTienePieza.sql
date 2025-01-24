
--Trigger Total AFTER Insert on Productos

CREATE OR REPLACE FUNCTION fn_componente_tiene_pieza()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
COST 100
VOLATILE NOT LEAKPROOF
AS $body$
DECLARE


BEGIN
    IF EXISTS (SELECT 1 FROM componentes_contienen WHERE fk_nombre_componente = NEW.fk_nombre_producto)
    THEN 
        RETURN NEW;
    ELSE
        RAISE EXCEPTION 'No se puede insertar este registro porque se necesita que exista en piezas un registro con esta id';
        RETURN NULL;
    END IF;

END;
$body$;



CREATE CONSTRAINT TRIGGER tg_fn_componente_tiene_pieza
    AFTER INSERT 
    ON public.componentes
    DEFERRABLE INITIALLY DEFERRED
    FOR EACH ROW
    EXECUTE FUNCTION public.fn_componente_tiene_pieza();


