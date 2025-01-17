

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
    No debo dejar que se haga  el cambio


    AUP DD
    Cambiamos a un participante de equipo.
    Si ese participante era el lider de un equipo, tenemos que asignarle al equipo afectado, otro lider
    teniendo en cuenta que tendremos los check de que haya un menor de 18 y al menos 3 registros en participantes
    con un equipo.




*/



CREATE OR REPLACE FUNCTION herencia_disjunta_entre_mountain_y_etapas()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
COST 100
VOLATILE NOT LEAKPROOF
AS $body$
DECLARE


BEGIN


END;
$body$;



CREATE OR REPLACE TRIGGER tg_herencia_disjunta_entre_mountain_y_etapas
    BEFORE INSERT
    ON public.mountain_etapas
    FOR EACH ROW
    EXECUTE FUNCTION public.herencia_disjunta_entre_mountain_y_etapas();


