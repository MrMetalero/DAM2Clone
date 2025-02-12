
--m2 tiene un check de >=1000.
paises(nombre:varchar,m2:integer){
CP: nombre
VNN: m2
}

--Dependencia de Identificación de Pais
ligas(nivel:varchar,numequipos:integer,fk_nombre_pais){
CP: nivel
VNN:numequipos
VNN: fk_nombre_pais
Caj fk_nombre_pais -> paises(nombre)
}

tecnicos(dni:varchar,nombre:varchar){
CP: dni
VNN: nombre
}

tecnico_pertenece(fk_dni_tecnico:varchar,fk_nombre_equipo:varchar,funcion:varchar){
CP: (fk_dni_tecnico,fk_nombre_equipo)
VNN: funcion
}

equipos(nombre:varchar,localidad:varchar,anyofundacion:date,fk_nivel_liga:varchar){
CP: nombre
VNN: localidad
VNN: anyofundacion
Caj: fk_nivel_liga -> ligas(nivel)
}

equiposborrados(id:varchar,fechaborrado:date,nombre:varchar,localidad:varchar,anyofundacion:date){
CP: id
VNN: fechaborrado
VNN: nombre
VNN: localidad
VNN: anyofundacion
}


RI0: si existe un registro en la tabla liga con la id de un pais, ese pais en la tabla pais no se puede eliminar
RI1: Cuando se borre un registro de la tabla Equipo, se deben almacear sus datos en la tabla equiposborrados


