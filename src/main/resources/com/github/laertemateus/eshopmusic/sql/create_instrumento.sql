CREATE TABLE instrumento (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    condicao TEXT NOT NULL,
    tipo TEXT NOT NULL,
    marca TEXT,
    modelo TEXT,
    anoFabricao INTEGER,
    cor TEXT,
    material TEXT,
    descricao TEXT,
    preco TEXT,
    criado_em DATETIME,
    ativo INTEGER
);