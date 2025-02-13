-- Active: 1727983525436@@127.0.0.1@5432@facturaluz

CREATE DATABASE facturaluz;

CREATE TABLE clientes (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL
);

CREATE TABLE contratos (
    cliente_id INTEGER REFERENCES clientes(id) ON DELETE CASCADE,
    fecha_renovacion DATE NOT NULL,
    PRIMARY KEY (cliente_id, fecha_renovacion) 
);

CREATE TABLE consumos (
    id SERIAL ,
    cliente_id INTEGER REFERENCES clientes(id) ON DELETE CASCADE,
    fecha_renovacion DATE NOT NULL,
    dia INTEGER NOT NULL CHECK (dia BETWEEN 1 AND 31),
    horas INTEGER[] NOT NULL, 
    FOREIGN KEY (cliente_id, fecha_renovacion) REFERENCES contratos(cliente_id, fecha_renovacion) ON DELETE CASCADE,
    PRIMARY KEY (cliente_id, fecha_renovacion, dia)
);