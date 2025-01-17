

/*
    PARTICIPANTES            EQUIPO
    Nombre  Equipo       Nombre  Lider
    p1      e2            e1      p1
    p2      e2            e2      p3
    p3      e3            e3      p2

    No dejar cambiar Participantes.equipo si el lider de el OLD.equipo.lider = New.Participante.Nombre 

    Que equipo no tenga lider es imposible porque es VNN


    Para introducir un equipo hace falta una transacción,
    la transacción necesita comrobar que el participante que se pone como lider
    pertenezca al equipo que se quiere liderar. Esto se tiene que comprobar en la tabla participantes

    BUP
    El momento que intento actualizar el equipo de un participante que en la tabla equipo es LIDER
    Debo comprobar si existe en equipo un lider con la id del participante que estamos intentando cambiar.
    Si lo hay, no se deja cambiar de equipo.


    AUP DD
    Cambiamos a un participante de equipo.
    Si ese participante era el lider de un equipo, tenemos que asignarle al equipo afectado, otro lider
    teniendo en cuenta que tendremos los check de que haya un menor de 18 y al menos 3 registros en participantes
    con un equipo.




*/



CREATE OR REPLACE FUNCTION comprobar_lider()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
COST 100
VOLATILE NOT LEAKPROOF
AS $body$
BEGIN
    -- Comprobar si el participante que intenta cambiar de equipo es líder de un equipo
    IF EXISTS (
        SELECT 1 
        FROM equipos 
        WHERE equipos.lider = OLD.id_participante
    ) THEN
        -- Si es líder, lanzar una excepción para evitar el cambio
        RAISE EXCEPTION 'NO PUEDES CAMBIAR EL EQUIPO DE ESTE PARTICIPANTE PORQUE ES EL LÍDER DE SU EQUIPO';
    END IF;

    -- Devolver la nueva fila si no hay problemas
    RETURN NEW;
END;
$body$;

CREATE OR REPLACE TRIGGER tg_comprobar_lider
    BEFORE UPDATE
    ON public.participantes
    FOR EACH ROW
    EXECUTE FUNCTION comprobar_lider();


