-- AL ACTUALIZAR UN TECNICO_PERTENECE
CREATE OR REPLACE FUNCTION actualizar_tecnico_pertenece_minimo_un_equipo()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
AS $body$
BEGIN
    IF EXISTS (SELECT 1 FROM tecnico_pertenece WHERE tecnico_pertenece.fk_nombre_equipo = NEW.fk_nombre_equipo)
       THEN
        RETURN NEW;
       ELSE
       RAISE NOTICE 'NO SE PUEDE CAMBIAR ESTE TÉCNICO PORQUE ES EL ÚNICO QUE TIENE EL EQUIPO';
       RETURN NULL;
    END IF;

    RETURN NEW;
END;
$body$;

CREATE OR REPLACE TRIGGER tg_actualizar_tecnico_pertenece_minimo_un_equipo
    AFTER UPDATE ON public.tecnico_pertenece
    FOR EACH ROW
    EXECUTE FUNCTION public.actualizar_tecnico_pertenece_minimo_un_equipo();


-- AL BORRAR UN TECNICO_PERTENECE
CREATE OR REPLACE FUNCTION borrar_tecnico_pertenece_minimo_un_equipo()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
AS $body$
BEGIN
    IF EXISTS (SELECT 1 FROM tecnico_pertenece WHERE tecnico_pertenece.fk_nombre_equipo = NEW.fk_nombre_equipo)
       THEN
        RETURN NEW;
       ELSE
       RAISE NOTICE 'NO SE PUEDE CAMBIAR ESTE TÉCNICO PORQUE ES EL ÚNICO QUE TIENE EL EQUIPO';
       RETURN NULL;
    END IF;

    RETURN NEW;
END;
$body$;

CREATE OR REPLACE TRIGGER tg_borrar_tecnico_pertenece_minimo_un_equipo
    AFTER DELETE ON public.tecnico_pertenece
    FOR EACH ROW
    EXECUTE FUNCTION public.borrar_tecnico_pertenece_minimo_un_equipo();
