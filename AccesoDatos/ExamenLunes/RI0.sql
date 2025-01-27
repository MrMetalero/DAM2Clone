

--RI0: si existe un registro en la tabla liga con la id de un pais, ese pais en la tabla pais no se puede eliminar
--SE QUITA EL BORRADO EN CASCADA DE LA TABLA paises para que nos de error al borrar un registro del que dependen FK de otras tablas

/*
    EJ:
    SQL: DELETE FROM "paises" WHERE "nombre"='Spain'

    update or delete on table "paises" violates foreign key constraint "fk_nombre_pais_pais" on table "ligas"

*/