CREATE TABLE artista (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255),
    assessor_responsavel VARCHAR(255)
);

CREATE TABLE artista_telefones (
    artista_id BIGINT NOT NULL,
    telefones VARCHAR(255),
    CONSTRAINT fk_artista_telefones_artista FOREIGN KEY (artista_id) REFERENCES artista (id)
);

CREATE TABLE casa_de_show (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255),
    rua VARCHAR(255),
    numero VARCHAR(255),
    bairro VARCHAR(255),
    cidade VARCHAR(255),
    uf VARCHAR(255),
    capacidade_maxima INTEGER,
    telefone VARCHAR(255)
);

CREATE TABLE patrocinador (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255),
    cnpj VARCHAR(255),
    telefone VARCHAR(255)
);

CREATE TABLE veiculo_imprensa (
    id BIGSERIAL PRIMARY KEY,
    cnpj VARCHAR(255),
    razao_social VARCHAR(255),
    telefone VARCHAR(255),
    nome_responsavel VARCHAR(255),
    tipo VARCHAR(255),
    numero VARCHAR(255),
    frequencia VARCHAR(255),
    canal VARCHAR(255)
);

CREATE TABLE show (
    id BIGSERIAL PRIMARY KEY,
    data DATE,
    artista_id BIGINT NOT NULL,
    casa_de_show_id BIGINT NOT NULL,
    CONSTRAINT fk_show_artista FOREIGN KEY (artista_id) REFERENCES artista (id),
    CONSTRAINT fk_show_casa FOREIGN KEY (casa_de_show_id) REFERENCES casa_de_show (id)
);

CREATE TABLE show_veiculo_imprensa (
    show_id BIGINT NOT NULL,
    veiculo_imprensa_id BIGINT NOT NULL,
    PRIMARY KEY (show_id, veiculo_imprensa_id),
    CONSTRAINT fk_show_veiculo_show FOREIGN KEY (show_id) REFERENCES show (id),
    CONSTRAINT fk_show_veiculo_imprensa FOREIGN KEY (veiculo_imprensa_id) REFERENCES veiculo_imprensa (id)
);

CREATE TABLE patrocinio_show (
    id BIGSERIAL PRIMARY KEY,
    show_id BIGINT NOT NULL,
    patrocinador_id BIGINT NOT NULL,
    valor_patrocinado NUMERIC(12,2) NOT NULL,
    CONSTRAINT fk_patrocinio_show FOREIGN KEY (show_id) REFERENCES show (id),
    CONSTRAINT fk_patrocinio_patrocinador FOREIGN KEY (patrocinador_id) REFERENCES patrocinador (id)
);

CREATE TABLE convidado (
    id BIGSERIAL PRIMARY KEY,
    cpf VARCHAR(255) NOT NULL UNIQUE,
    nome VARCHAR(255) NOT NULL,
    telefone VARCHAR(255) NOT NULL,
    patrocinador_id BIGINT NOT NULL,
    CONSTRAINT fk_convidado_patrocinador FOREIGN KEY (patrocinador_id) REFERENCES patrocinador (id)
);
