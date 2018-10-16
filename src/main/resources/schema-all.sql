DROP TABLE personas IF EXISTS;

CREATE TABLE personas  (
    persona_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    nombre VARCHAR(20),
    apellido VARCHAR(20)
);