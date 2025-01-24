-- Active: 1726053967925@@127.0.0.1@5432@compoformula



-- RI1: El precio de un producto nunca podrá ser inferior a 3€

-- RI2: No puede existir un producto en la tabla Componentes y
--      estar también en la tabla Fórmulas y viceversa

-- RI3: No pueden haber mas de 4 registros en piezas por cada nombre_componente

-- RI4: Al insertar un registro en la tabla Productos, se debe de insertar un componente
--      o una formula con su id

-- RI5: Si se inserta un registro en componentes, debe haber al menos 1 registro con su id 
--      en componentes_contienen

-- RI6: Si se inserta un registro en piezas, debe haber al menos 1 registro con su id 
--      en componentes_contienen 
/*


BIF -- Que no se encuentre en componentes su id
AIP -- Porque necesito transacción para insertar en componentes o funciones
AIC -- Que se haya metido por transacción una pieza al menos en componentes_contienen, que no esté en fórmulas su id


*/

--Trigger Disjunta BEFORE Insert or Update en Formula

CREATE OR REPLACE FUNCTION fn_disjunta_entre_formulas()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
COST 100
VOLATILE NOT LEAKPROOF
AS $body$
DECLARE


BEGIN
    IF EXISTS (SELECT * FROM componentes WHERE fk_nombre_producto = NEW.fk_nombre_producto)
    THEN 
    RAISE NOTICE 'No se puede insertar este registro porque existe en la tabla componentes';
        RETURN NULL;
    ELSE
        RETURN NEW;
    END IF;

END;
$body$;



CREATE OR REPLACE TRIGGER tg_disjunta_entre_formulas
    BEFORE INSERT OR UPDATE
    ON public.formulas
    FOR EACH ROW
    EXECUTE FUNCTION public.fn_disjunta_entre_formulas();





--Trigger Disjunta AFTER Insert or Update en Componentes

CREATE OR REPLACE FUNCTION fn_disjunta_entre_componentes()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
COST 100
VOLATILE NOT LEAKPROOF
AS $body$
DECLARE


BEGIN
    IF EXISTS (SELECT * FROM formulas WHERE fk_nombre_producto = NEW.fk_nombre_producto)
    THEN 
    RAISE NOTICE 'No se puede insertar este registro porque existe en la tabla formulas';
        RETURN NULL;
    ELSE
        RETURN NEW;
    END IF;

END;
$body$;



CREATE OR REPLACE TRIGGER tg_disjunta_entre_componentes
    BEFORE INSERT OR UPDATE
    ON public.componentes
    FOR EACH ROW
    EXECUTE FUNCTION public.fn_disjunta_entre_componentes();






--Trigger Total AFTER Insert on Productos

CREATE OR REPLACE FUNCTION fn_total_entre_componentes_funciones()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
COST 100
VOLATILE NOT LEAKPROOF
AS $body$
DECLARE


BEGIN
    IF EXISTS (SELECT * FROM formulas WHERE fk_nombre_producto = NEW.nombre_producto) OR EXISTS (SELECT * FROM componentes WHERE fk_nombre_producto = NEW.nombre_producto)
    THEN 
        RETURN NEW;
    ELSE
        RAISE EXCEPTION 'No se puede insertar este registro porque se necesita que exista en componentes o en formulas ';
    END IF;

END;
$body$;



CREATE CONSTRAINT TRIGGER tg_total_entre_componentes_funciones
    AFTER INSERT
    ON productos
    DEFERRABLE INITIALLY DEFERRED
    FOR EACH ROW
    EXECUTE FUNCTION public.fn_total_entre_componentes_funciones();










