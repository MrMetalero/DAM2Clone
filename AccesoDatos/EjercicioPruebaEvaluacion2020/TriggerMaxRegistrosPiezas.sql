


--Trigger Total AFTER Insert on Productos

CREATE OR REPLACE FUNCTION fn_total_entre_componentes_funciones()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
COST 100
VOLATILE NOT LEAKPROOF
AS $body$
DECLARE


BEGIN
    IF EXISTS (SELECT * FROM formulas WHERE fk_nombre_producto = NEW.nombre_producto) OR EXISTS (SELECT * FROM componentes WHERE fk_nombre_producto = NEW.nombre_producto)
    THEN 
    
        RETURN NEW;
    ELSE
        RAISE EXCEPTION 'No se puede insertar este registro porque se necesita que exista en componentes o en formulas ';
        RETURN NULL;
    END IF;

END;
$body$;



CREATE OR REPLACE TRIGGER tg_total_entre_componentes_funciones
    BEFORE INSERT
    ON public.productos
    DEFERRABLE INITIALLY DEFERRED
    FOR EACH ROW
    EXECUTE FUNCTION public.fn_total_entre_componentes_funciones();
