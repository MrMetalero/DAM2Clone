-- Active: 1726053967925@@127.0.0.1@5432@ligamena

TG_OP = 'DELETE'
TG_OP = 'UPDATE'
TG_OP = 'INSERT'



INSERT INTO "paises"("nombre","m2") VALUES
    ('Senegal',2312),
    ('Brasil',2312),
    ('France',2312),
    ('Spain',2312),
    ('Germany',2312),
    ('Italy',2312);


INSERT INTO "ligas" ("nivel","fk_nombre_pais") VALUES
    ('1','Spain'),
    ('2','Spain'),
    ('3','Spain'),
    ('4','Spain');


INSERT INTO  "equipos" ("nombre", "localidad", "anyofundacion", "fk_nivel_liga","fk_nombre_pais_liga") VALUES
    ('FC1','localidad1','12/5/1997',1,'Spain'),
    ('FC2','localidad2','12/5/1997',2,'Spain'),
    ('FC3','localidad3','12/5/1997',3,'Spain'),
    ('FC4','localidad1','12/5/1997',1,'Spain'),
    ('FC5','localidad2','12/5/1997',2,'Spain'),
    ('FC6','localidad3','12/5/1997',3,'Spain');

INSERT INTO  "tecnicos" ("dni","nombre") VALUES
    ('1','Jose'),
    ('2','Marta'),
    ('3','Carlos'),
    ('4','Raul'),
    ('5','Javier');

INSERT INTO  "tecnico_pertenece" ("fk_dni_tecnico","fk_nombre_equipo","funcion") VALUES
    ('1','FC2','Fun1'),
    ('2','FC5','Fun2'),
    ('3','FC3','Fun1'),
    ('3','FC2','Fun2'),
    ('2','FC2','Fun3');
    

    

