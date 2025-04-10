CREATE OR REPLACE FUNCTION limitar_max_artistas_por_grupo() 
RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
DECLARE
    max_artistas INT;
BEGIN
    SELECT COUNT(*) INTO max_artistas 
    FROM pertence 
    GROUP BY cod 
    ORDER BY COUNT(*) DESC 
    LIMIT 1;

    IF (SELECT COUNT(*) FROM pertence WHERE cod = NEW.cod) >= max_artistas THEN
        RAISE EXCEPTION 'NO PUEDES TENER MÁS DE % ARTISTAS EN UN GRUPO', max_artistas;
    END IF;

    RETURN NEW;
END;
$$;

CREATE OR REPLACE TRIGGER tg_limitar_max_artistas_por_grupo
    BEFORE INSERT
    ON pertence
    FOR EACH ROW
    EXECUTE FUNCTION limitar_max_artistas_por_grupo();