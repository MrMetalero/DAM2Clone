

--Tabla, ejemplo
CREATE TABLE chales(
    id_chale VARCHAR(255) PRIMARY KEY,
    precio_estimado FLOAT(9),
    CONSTRAINT fk_id_chale_apunta_id_vivienda FOREIGN KEY (id_chale) REFERENCES viviendas(id_vivienda)
);

-- Insert Syntax
INSERT INTO films (code, title, did, date_prod, kind) VALUES
    ('B6717', 'Tampopo', 110, '1985-02-10', 'Comedy'),
    ('HG120', 'The Dinner Game', 140, DEFAULT, 'Comedy');

-- Update Syntax
UPDATE employees SET sales_count = sales_count + 1 WHERE id =
  (SELECT sales_person FROM accounts WHERE name = 'Acme Corporation');

-- Delete Syntax
DELETE FROM tasks WHERE status = 'DONE' RETURNING *;

--Alter Syntax
ALTER TABLE table_name
MODIFY COLUMN column_name datatype; 

ALTER TABLE table_name
DROP COLUMN column_name; 

ALTER TABLE Customers
ADD Email varchar(255);

ALTER TABLE table_name
RENAME COLUMN old_name to new_name; 

-- TOTAL TRIGGER EJEMPLO
CREATE OR REPLACE FUNCTION herencia_total_entre_mountain_y_etapas()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
COST 100
VOLATILE NOT LEAKPROOF
AS $body$
DECLARE


BEGIN
    IF EXISTS (SELECT * FROM etapas WHERE numero_etapa = OLD.id_etapa)
    THEN 
    RAISE NOTICE 'NO PUEDES ELIMINAR ESTA ETAPA DE MONTAÑA PORQUE TIENE RESTRICCIÓN TOTAL EN ETAPAS (EXISTE UNA ETAPA CON ESTA ID)';
        RETURN NULL;
    ELSE
        RETURN OLD;
    END IF;

END;
$body$;



CREATE OR REPLACE TRIGGER tg_herencia_total_entre_mountain_y_etapas
    BEFORE DELETE
    ON public.mountain_etapas
    FOR EACH ROW
    EXECUTE FUNCTION public.herencia_total_entre_mountain_y_etapas();







-- DISJUNTA TRIGGER EJEMPLO (contrarreloj y etapas de montaña)


CREATE OR REPLACE FUNCTION herencia_disjunta_entre_contrarreloj_y_etapas()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
COST 100
VOLATILE NOT LEAKPROOF
AS $body$
DECLARE


BEGIN
    IF EXISTS (SELECT * FROM mountain_etapas WHERE mountain_etapas.id_etapa = NEW.id_etapa)
    THEN 
    RAISE NOTICE 'NO PUEDES INSERTAR ESTA ETAPA DE CONTRARRELOJ PORQUE TIENE RESTRICCIÓN DISJUNTA EN ETAPAS (NO PUEDE ESTAR EN AMBAS TABLAS MOUNTAIN Y CONTRARRELOJ)';
        RETURN NULL;
    ELSE
        RETURN OLD;
    END IF;

END;
$body$;



CREATE OR REPLACE TRIGGER tg_herencia_disjunta_entre_contrarreloj_y_etapas
    BEFORE INSERT OR UPDATE
    ON public.contrarreloj
    FOR EACH ROW
    EXECUTE FUNCTION public.herencia_disjunta_entre_contrarreloj_y_etapas();




-- TRIGGER DEFERRERABLE INITIALLY DEFERRED (se aplaza para que puedas meter en una transacción una venta con el id_empleado)

CREATE OR REPLACE FUNCTION no_insertar_en_empleado_si_no_hay_venta_con_dniempleado()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
COST 100
VOLATILE NOT LEAKPROOF
AS $body$
DECLARE

BEGIN
    IF EXISTS (SELECT * FROM ventas WHERE dni_empleado = NEW.dni_empleado)
    THEN 
        RETURN NEW;
    ELSE
        RAISE EXCEPTION 'NO SE PUEDE INTRODUCIR ESTE EMPLEADO SIN UNA VENTA';
        RETURN NULL;
    END IF;

END;
$body$;


--RECORDAR QUE ES UN CONSTRAINT TRIGGER
CREATE CONSTRAINT TRIGGER no_insertar_en_empleado_si_no_hay_venta_con_dniempleado
    AFTER INSERT OR UPDATE
    ON public.empleados
    DEFERRABLE INITIALLY DEFERRED
    FOR EACH ROW
    EXECUTE FUNCTION public.no_insertar_en_empleado_si_no_hay_venta_con_dniempleado();




--CURSORES EJEMPLO:

IF (SELECT COUNT(*) FROM ciclista WHERE nomeq=new.nomeq >0)


Primer, en la QueryTool, la funció del disparador:

CREATE OR REPLACE FUNCTION public.ai_equi_min_1()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
  DECLARE
  cuenta integer;
    cur cursor for select count(*) from CICLISTA where nomeq = NEW.nomeq; 
  BEGIN
   
   OPEN cur;
    LOOP
         FETCH cur INTO cuenta;
         EXIT WHEN NOT FOUND;
     if (cuenta>=1) then
          CLOSE cur;
          return NULL;
     else
          CLOSE cur;
        ROLLBACK;
          RETURN NULL;
     END IF;
    END LOOP;

   RETURN NULL;
  END;
 
$BODY$;

Després, a la taula equip, el disparador:

CREATE CONSTRAINT TRIGGER "DAI_equi_min_1"
    AFTER INSERT
    ON public.equipo
    DEFERRABLE INITIALLY DEFERRED
    FOR EACH ROW
    EXECUTE FUNCTION public.ai_equi_min_1();

Una vegada fet, provar a insertar un equip així (no funcionarà):

INSERT INTO public.equipo(nomeq, director)
        VALUES ('EQUIBORRAR', 'Direborrar');

INSERT INTO public.ciclista(dorsal, nombre, edad, nomeq)
        VALUES (999, 'borrar', 50, 'EQUIBORRAR');

Després provar amb una transacció:

BEGIN;
    INSERT INTO public.equipo(nomeq, director)
        VALUES ('EQUIBORRAR', 'Direborrar');
    INSERT INTO public.ciclista(dorsal, nombre, edad, nomeq)
        VALUES (999, 'borrar', 50, 'EQUIBORRAR');
COMMIT;

Per què funciona amb una transacció i no sense?



-- TRIGGER EJEMPLO LIMITE DE REGISTROS

-- Trigger to enforce the maximum of 5 books borrowed by a member
CREATE OR REPLACE FUNCTION check_borrow_limit() 
RETURNS TRIGGER AS $$
BEGIN
    IF (SELECT COUNT(*) FROM Borrowing WHERE MemberID = NEW.MemberID AND ReturnDate IS NULL) >= 5 THEN
        RAISE EXCEPTION 'A member cannot borrow more than 5 books at a time';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER borrow_limit_trigger
BEFORE INSERT ON Borrowing
FOR EACH ROW EXECUTE FUNCTION check_borrow_limit();


--USO DE VARIABLES

CREATE FUNCTION holaMundo() RETURNS VARCHAR(30)
BEGIN
DECLARE salida VARCHAR(30) DEFAULT 'Hola mundo';
SET salida = ‘Hola mundo con variables’;
RETURN salida;
END

