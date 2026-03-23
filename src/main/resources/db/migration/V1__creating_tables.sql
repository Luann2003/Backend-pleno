-- Extensão para gerar UUID automaticamente
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- =========================
-- Tabela: board
-- =========================
CREATE TABLE board (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255) NOT NULL
);

-- =========================
-- Tabela: columns
-- =========================
CREATE TABLE columns (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255) NOT NULL,
    position INTEGER NOT NULL,
    board_id UUID NOT NULL,

    CONSTRAINT fk_board
        FOREIGN KEY (board_id)
        REFERENCES board(id)
        ON DELETE CASCADE
);

-- =========================
-- Tabela: task
-- =========================
CREATE TABLE task (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255) NOT NULL,
    position INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL,
    due_date TIMESTAMP,
    completed BOOLEAN DEFAULT FALSE,
    tags TEXT[],
    column_id UUID NOT NULL,

    CONSTRAINT fk_column
        FOREIGN KEY (column_id)
        REFERENCES columns(id)
        ON DELETE CASCADE
);