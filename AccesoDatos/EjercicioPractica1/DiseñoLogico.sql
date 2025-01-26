-- Active: 1727983525436@@127.0.0.1@5432@ejerciciopractica1
CREATE DATABASE ejerciciopractica1;

miembros(id_miembro:serial, nombre:varchar, telefono:varchar, fecha_alta:date){
CP: id_miembro
VNN: nombre
VNN: telefono
VNN: fecha_alta
}


libros(isbn:varchar,titulo:varchar,autor:varchar,fecha_publicacion:date,){
CP: isbn
VNN: titulo
VNN: autor
VNN: fecha_publicacion
}


prestados(id_prestamo:serial,fk_miembro_prestamo:varchar,fk_isbn:varchar,fecha_prestamo:date,fecha_retorno:date){
CP: id_prestamo
VNN: fk_miembro_prestamo
VNN: fk_isbn
VNN: fecha_prestamo
Caj: fk_isbn -> libros(isbn)
Caj: fk_miembro_prestamo -> miembros(id_miembro)
}

