CREATE TABLE conta (
    id BIGINT PRIMARY KEY NOT NULL,
    descricao VARCHAR(255),
    valor NUMERIC(10, 2),
    data_vencimento DATE,
    data_pagamento DATE,
    situacao VARCHAR(50)
);