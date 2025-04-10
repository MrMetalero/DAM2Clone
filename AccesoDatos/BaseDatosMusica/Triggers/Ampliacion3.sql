CREATE OR REPLACE FUNCTION tg_impedir_nuevas_canciones()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
COST 100
VOLATILE NOT LEAKPROOF
AS $body$
BEGIN
    RAISE NOTICE 'NO SE PUEDEN INSERTAR NUEVAS CANCIONES';
    RETURN NULL;
END;
$body$;



CREATE OR REPLACE TRIGGER tg_impedir_nuevas_canciones
    BEFORE INSERT
    ON public.cancion
    FOR EACH ROW
    EXECUTE FUNCTION public.tg_impedir_nuevas_canciones();
