-- Active: 1726053967925@@127.0.0.1@5432@compoformula

BEGIN TRANSACTION;

--DEFERRABLE--
CREATE TABLE productos(
    nombre_producto VARCHAR,
    precio FLOAT NOT NULL,
    CONSTRAINT pk_nombre_producto PRIMARY KEY (nombre_producto) 
); 


CREATE TABLE clientes(
    nombre_cliente VARCHAR PRIMARY KEY,
    telefono INTEGER NOT NULL
); 

CREATE TABLE interesado(
    fk_nombre_cliente VARCHAR,
    fk_nombre_producto VARCHAR,
    CONSTRAINT pk_nombre_cliente_nombre_producto PRIMARY KEY (fk_nombre_cliente,fk_nombre_producto),
    CONSTRAINT fk_interesado_producto FOREIGN KEY (fk_nombre_producto) REFERENCES productos(nombre_producto),
    CONSTRAINT fk_interesado_cliente FOREIGN KEY (fk_nombre_cliente) REFERENCES clientes(nombre_cliente)
); 

CREATE TABLE formulas(
    fk_nombre_producto VARCHAR PRIMARY KEY,
    fecha_comercializacion DATE NOT NULL,
    CONSTRAINT fk_formulas_producto FOREIGN KEY (fk_nombre_producto) REFERENCES productos(nombre_producto)
); 

--DEFERRABLE--
CREATE TABLE componentes(
    fk_nombre_producto VARCHAR,
    CONSTRAINT fk_componentes_producto FOREIGN KEY (fk_nombre_producto) REFERENCES productos(nombre_producto),
    CONSTRAINT pk_fk_nombre_producto PRIMARY KEY (fk_nombre_producto) 
); 

--DEFERRABLE--
CREATE TABLE piezas(
    nombre_pieza VARCHAR,
    peso FLOAT NOT NULL,
    CONSTRAINT pk_nombre_pieza PRIMARY KEY (nombre_pieza)
); 

CREATE TABLE componentes_contienen(
    fk_nombre_componente VARCHAR ,
    fk_nombre_pieza VARCHAR ,
    CONSTRAINT pk_nombre_componenteo_nombre_pieza PRIMARY KEY (fk_nombre_componente,fk_nombre_pieza),
    CONSTRAINT fk_componente_contienen FOREIGN KEY (fk_nombre_componente) REFERENCES componentes(fk_nombre_producto)
); 



COMMIT;

ROLLBACK;



