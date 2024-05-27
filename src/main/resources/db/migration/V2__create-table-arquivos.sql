CREATE TABLE if NOT EXISTS arquivos (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    data BYTEA,
    titulo TEXT,
    filename TEXT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
)