CREATE SEQUENCE IF NOT EXISTS conta_id_seq;

CREATE TABLE IF NOT EXISTS conta (
    id BIGINT PRIMARY KEY DEFAULT nextval('conta_id_seq'),
    descricao VARCHAR(255),
    valor NUMERIC(10, 2),
    data_vencimento DATE NOT NULL,
    data_pagamento DATE,
    situacao VARCHAR(50)
);

ALTER TABLE conta 
ALTER COLUMN id SET DEFAULT nextval('conta_id_seq');