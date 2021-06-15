CREATE TABLE IF NOT EXISTS bugs (
    id IDENTITY PRIMARY KEY,
    name VARCHAR(255),
    description CLOB
);