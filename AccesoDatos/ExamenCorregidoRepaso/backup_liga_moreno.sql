PGDMP                         }            liga-moreno    14.17    14.17 '    "           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            #           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            $           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            %           1262    16394    liga-moreno    DATABASE     \   CREATE DATABASE "liga-moreno" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'es-ES';
    DROP DATABASE "liga-moreno";
                postgres    false            �            1255    16563    calcular_numequipos()    FUNCTION     �  CREATE FUNCTION public.calcular_numequipos() RETURNS TABLE(nivel character varying, nombre character varying, numequipos integer)
    LANGUAGE plpgsql
    AS $$
BEGIN
    RETURN QUERY
    SELECT l.nivel, l.nombre, COUNT(e.nombre)::INTEGER AS total_equipos
    FROM liga l
    LEFT JOIN equipo e ON l.nivel = e.nivel AND l.nombre = e.nombre_pais
    GROUP BY l.nivel, l.nombre;
END;$$;
 ,   DROP FUNCTION public.calcular_numequipos();
       public          postgres    false            �            1255    16560    registrar_equipo_borrado()    FUNCTION     m  CREATE FUNCTION public.registrar_equipo_borrado() RETURNS trigger
    LANGUAGE plpgsql
    AS $$-- Función que inserta el equipo eliminado en equiposborrados antes de borrarlo
BEGIN
    INSERT INTO equiposborrados ("fechaBorrado", nombre, localidad, anyofundacion)
    VALUES (CURRENT_DATE, OLD.nombre, OLD.localidad, OLD.anyofundacion);

    RETURN OLD;
END;
$$;
 1   DROP FUNCTION public.registrar_equipo_borrado();
       public          postgres    false            �            1255    16578    tecnicos_maximos()    FUNCTION       CREATE FUNCTION public.tecnicos_maximos() RETURNS trigger
    LANGUAGE plpgsql
    AS $$BEGIN
IF (SELECT COUNT(*) FROM tecnico_equipo WHERE nombre = NEW.nombre) >= 9
THEN
RAISE EXCEPTION 'No puede haber más de 9 técnicos en un equipos';
END IF;
RETURN NEW;
END;$$;
 )   DROP FUNCTION public.tecnicos_maximos();
       public          postgres    false            �            1255    16580    tecnicos_minimo_un_equipo()    FUNCTION     �  CREATE FUNCTION public.tecnicos_minimo_un_equipo() RETURNS trigger
    LANGUAGE plpgsql
    AS $$BEGIN
    -- Verificar si el técnico que se está insertando ya tiene al menos un equipo
    IF NOT EXISTS (SELECT 1 FROM tecnico_equipo WHERE dni = NEW.dni) THEN
        RAISE EXCEPTION 'Un técnico debe estar asignado a un equipo antes de ser creado.';
    END IF;
    
    RETURN NEW;
END;$$;
 2   DROP FUNCTION public.tecnicos_minimo_un_equipo();
       public          postgres    false            �            1259    16499    equipo    TABLE     �   CREATE TABLE public.equipo (
    nombre character varying NOT NULL,
    localidad character varying NOT NULL,
    anyofundacion integer NOT NULL,
    nivel character varying NOT NULL,
    nombre_pais character varying NOT NULL
);
    DROP TABLE public.equipo;
       public         heap    postgres    false            �            1259    16496    equiposborrados    TABLE     �   CREATE TABLE public.equiposborrados (
    "fechaBorrado" date,
    nombre character varying,
    localidad character varying,
    anyofundacion integer,
    id integer NOT NULL
);
 #   DROP TABLE public.equiposborrados;
       public         heap    postgres    false            �            1259    16569    equiposborrados_id_seq    SEQUENCE     �   CREATE SEQUENCE public.equiposborrados_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.equiposborrados_id_seq;
       public          postgres    false    211            &           0    0    equiposborrados_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.equiposborrados_id_seq OWNED BY public.equiposborrados.id;
          public          postgres    false    216            �            1259    16493    liga    TABLE     j   CREATE TABLE public.liga (
    nivel character varying NOT NULL,
    nombre character varying NOT NULL
);
    DROP TABLE public.liga;
       public         heap    postgres    false            �            1259    16490    pais    TABLE     ]   CREATE TABLE public.pais (
    nombre character varying NOT NULL,
    m2 integer NOT NULL
);
    DROP TABLE public.pais;
       public         heap    postgres    false            �            1259    16502    tecnico    TABLE     k   CREATE TABLE public.tecnico (
    dni character varying NOT NULL,
    nombre character varying NOT NULL
);
    DROP TABLE public.tecnico;
       public         heap    postgres    false            �            1259    16531    tecnico_equipo    TABLE     �   CREATE TABLE public.tecnico_equipo (
    dni character varying NOT NULL,
    nombre character varying NOT NULL,
    funcion character varying NOT NULL
);
 "   DROP TABLE public.tecnico_equipo;
       public         heap    postgres    false            �            1259    16564    vista_ligas_con_total    VIEW     2  CREATE VIEW public.vista_ligas_con_total AS
 SELECT l.nivel,
    l.nombre,
    COALESCE(te.numequipos, 0) AS numequipos
   FROM (public.liga l
     LEFT JOIN public.calcular_numequipos() te(nivel, nombre, numequipos) ON ((((l.nivel)::text = (te.nivel)::text) AND ((l.nombre)::text = (te.nombre)::text))));
 (   DROP VIEW public.vista_ligas_con_total;
       public          postgres    false    217    210    210            y           2604    16570    equiposborrados id    DEFAULT     x   ALTER TABLE ONLY public.equiposborrados ALTER COLUMN id SET DEFAULT nextval('public.equiposborrados_id_seq'::regclass);
 A   ALTER TABLE public.equiposborrados ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    211                      0    16499    equipo 
   TABLE DATA           V   COPY public.equipo (nombre, localidad, anyofundacion, nivel, nombre_pais) FROM stdin;
    public          postgres    false    212   [2                 0    16496    equiposborrados 
   TABLE DATA           _   COPY public.equiposborrados ("fechaBorrado", nombre, localidad, anyofundacion, id) FROM stdin;
    public          postgres    false    211   �2                 0    16493    liga 
   TABLE DATA           -   COPY public.liga (nivel, nombre) FROM stdin;
    public          postgres    false    210   �2                 0    16490    pais 
   TABLE DATA           *   COPY public.pais (nombre, m2) FROM stdin;
    public          postgres    false    209   3                 0    16502    tecnico 
   TABLE DATA           .   COPY public.tecnico (dni, nombre) FROM stdin;
    public          postgres    false    213   <3                 0    16531    tecnico_equipo 
   TABLE DATA           >   COPY public.tecnico_equipo (dni, nombre, funcion) FROM stdin;
    public          postgres    false    214   c3       '           0    0    equiposborrados_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.equiposborrados_id_seq', 2, true);
          public          postgres    false    216            �           2606    16521    equipo equipo_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.equipo
    ADD CONSTRAINT equipo_pkey PRIMARY KEY (nombre);
 <   ALTER TABLE ONLY public.equipo DROP CONSTRAINT equipo_pkey;
       public            postgres    false    212                       2606    16577 $   equiposborrados equiposborrados_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.equiposborrados
    ADD CONSTRAINT equiposborrados_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.equiposborrados DROP CONSTRAINT equiposborrados_pkey;
       public            postgres    false    211            }           2606    16512    liga liga_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.liga
    ADD CONSTRAINT liga_pkey PRIMARY KEY (nivel, nombre);
 8   ALTER TABLE ONLY public.liga DROP CONSTRAINT liga_pkey;
       public            postgres    false    210    210            x           2606    16562 $   pais m2 no puede ser inferior a 1000    CHECK CONSTRAINT     l   ALTER TABLE public.pais
    ADD CONSTRAINT "m2 no puede ser inferior a 1000" CHECK ((m2 > 1000)) NOT VALID;
 K   ALTER TABLE public.pais DROP CONSTRAINT "m2 no puede ser inferior a 1000";
       public          postgres    false    209    209            {           2606    16508    pais pais_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.pais
    ADD CONSTRAINT pais_pkey PRIMARY KEY (nombre);
 8   ALTER TABLE ONLY public.pais DROP CONSTRAINT pais_pkey;
       public            postgres    false    209            �           2606    16537 "   tecnico_equipo tecnico_equipo_pkey 
   CONSTRAINT     i   ALTER TABLE ONLY public.tecnico_equipo
    ADD CONSTRAINT tecnico_equipo_pkey PRIMARY KEY (dni, nombre);
 L   ALTER TABLE ONLY public.tecnico_equipo DROP CONSTRAINT tecnico_equipo_pkey;
       public            postgres    false    214    214            �           2606    16530    tecnico tecnico_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.tecnico
    ADD CONSTRAINT tecnico_pkey PRIMARY KEY (dni);
 >   ALTER TABLE ONLY public.tecnico DROP CONSTRAINT tecnico_pkey;
       public            postgres    false    213            �           2620    16568    equipo registrar_equipo_borrado    TRIGGER     �   CREATE TRIGGER registrar_equipo_borrado AFTER DELETE ON public.equipo FOR EACH ROW EXECUTE FUNCTION public.registrar_equipo_borrado();
 8   DROP TRIGGER registrar_equipo_borrado ON public.equipo;
       public          postgres    false    222    212            �           2620    16579    tecnico_equipo tecnicos_maximos    TRIGGER     �   CREATE TRIGGER tecnicos_maximos BEFORE INSERT OR UPDATE ON public.tecnico_equipo FOR EACH ROW EXECUTE FUNCTION public.tecnicos_maximos();
 8   DROP TRIGGER tecnicos_maximos ON public.tecnico_equipo;
       public          postgres    false    214    223            �           2620    16581 !   tecnico tecnicos_minimo_un_equipo    TRIGGER     �   CREATE TRIGGER tecnicos_minimo_un_equipo BEFORE INSERT OR UPDATE ON public.tecnico FOR EACH ROW EXECUTE FUNCTION public.tecnicos_minimo_un_equipo();
 :   DROP TRIGGER tecnicos_minimo_un_equipo ON public.tecnico;
       public          postgres    false    221    213            �           2606    16582 $   tecnico_equipo fk_dni_tecnico_equipo    FK CONSTRAINT     �   ALTER TABLE ONLY public.tecnico_equipo
    ADD CONSTRAINT fk_dni_tecnico_equipo FOREIGN KEY (dni) REFERENCES public.tecnico(dni) ON UPDATE CASCADE ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED NOT VALID;
 N   ALTER TABLE ONLY public.tecnico_equipo DROP CONSTRAINT fk_dni_tecnico_equipo;
       public          postgres    false    214    213    3203            �           2606    16513    liga fk_nombre_liga    FK CONSTRAINT     �   ALTER TABLE ONLY public.liga
    ADD CONSTRAINT fk_nombre_liga FOREIGN KEY (nombre) REFERENCES public.pais(nombre) ON UPDATE RESTRICT ON DELETE RESTRICT NOT VALID;
 =   ALTER TABLE ONLY public.liga DROP CONSTRAINT fk_nombre_liga;
       public          postgres    false    210    209    3195            �           2606    16522 "   equipo fk_nombre_pais_nivel_equipo    FK CONSTRAINT     �   ALTER TABLE ONLY public.equipo
    ADD CONSTRAINT fk_nombre_pais_nivel_equipo FOREIGN KEY (nivel, nombre_pais) REFERENCES public.liga(nivel, nombre) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;
 L   ALTER TABLE ONLY public.equipo DROP CONSTRAINT fk_nombre_pais_nivel_equipo;
       public          postgres    false    210    212    3197    210    212            �           2606    16543 '   tecnico_equipo fk_nombre_tecnico_equipo    FK CONSTRAINT     �   ALTER TABLE ONLY public.tecnico_equipo
    ADD CONSTRAINT fk_nombre_tecnico_equipo FOREIGN KEY (nombre) REFERENCES public.equipo(nombre) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;
 Q   ALTER TABLE ONLY public.tecnico_equipo DROP CONSTRAINT fk_nombre_tecnico_equipo;
       public          postgres    false    214    3201    212               8   x�s-,�,�7��I6�4�����)3���/*MMO�rK%�@��@I#�d� VR�         2   x�3202�50�50�JM�Q�ML)�L�R���f��\F�q��qqq 7n            x��)3���/*MMO��)3��c���� ���            x���/*MMO�47������� 4�e            x�K��4�H-H����� #��         (   x�K��4�t-,�,�7��M�+I����L�+������ ��
-     