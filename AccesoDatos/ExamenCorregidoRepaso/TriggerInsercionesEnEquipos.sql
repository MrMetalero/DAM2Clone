-- AL INSERTAR UN EQUIPO
CREATE OR REPLACE FUNCTION insertar_equipos_necesita_tecnico()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
AS $body$
BEGIN
    IF EXISTS (SELECT 1 FROM tecnico_pertenece WHERE tecnico_pertenece.fk_nombre_equipo = NEW.nombre)
       THEN
        RETURN NEW;
       ELSE
       RAISE NOTICE 'NO SE PUEDE INSERTAR UN EQUIPO SIN ASIGNARLE AL MENOS UN TÉCNICO';
       RETURN NULL;
    END IF;

    RETURN NEW;
END;
$body$;

CREATE OR REPLACE TRIGGER tg_insertar_equipos_necesita_tecnico
    BEFORE INSERT ON public.equipos
    FOR EACH ROW
    EXECUTE FUNCTION public.insertar_equipos_necesita_tecnico();

