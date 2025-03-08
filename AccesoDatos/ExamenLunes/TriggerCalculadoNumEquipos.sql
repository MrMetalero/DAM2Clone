


CREATE OR REPLACE FUNCTION calcular_numequipos()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
COST 100
VOLATILE NOT LEAKPROOF
AS $body$
DECLARE
   

BEGIN
    UPDATE ligas SET numequipos = (SELECT COUNT(*) FROM equipos WHERE equipos.fk_nivel_liga = ligas.nivel AND equipos.fk_nombre_pais_liga = ligas.fk_nombre_pais);
    RAISE NOTICE 'RECALCULADO numequipos';
    RETURN NEW;
    
    

END;
$body$;



CREATE OR REPLACE TRIGGER tg_calcular_numequipos
    AFTER INSERT OR UPDATE OR DELETE
    ON public.equipos
    FOR EACH ROW
    EXECUTE FUNCTION public.calcular_numequipos();

