INSERT INTO artista (nome, assessor_responsavel)
VALUES ('Banda Horizonte Sul', 'Marina Alves');

INSERT INTO artista_telefones (artista_id, telefones)
VALUES (1, '(11) 98888-1111'),
       (1, '(11) 97777-2222');

INSERT INTO casa_de_show (nome, rua, numero, bairro, cidade, uf, capacidade_maxima, telefone)
VALUES ('Arena Aurora', 'Rua das Luzes', '145', 'Centro', 'Sao Paulo', 'SP', 5000, '(11) 3333-4444');

INSERT INTO patrocinador (nome, cnpj, telefone)
VALUES ('Energia Max', '12.345.678/0001-90', '(11) 4002-8922');

INSERT INTO veiculo_imprensa (cnpj, razao_social, telefone, nome_responsavel, tipo, numero, frequencia, canal)
VALUES ('23.456.789/0001-10', 'Radio Cidade Viva', '(11) 3555-7000', 'Ricardo Lima', 'RADIO', '101.3', 'FM', NULL);

INSERT INTO show (data, artista_id, casa_de_show_id)
VALUES (CURRENT_DATE + INTERVAL '20 day', 1, 1);

INSERT INTO show_veiculo_imprensa (show_id, veiculo_imprensa_id)
VALUES (1, 1);

INSERT INTO patrocinio_show (show_id, patrocinador_id, valor_patrocinado)
VALUES (1, 1, 150000.00);

INSERT INTO convidado (cpf, nome, telefone, patrocinador_id)
VALUES ('123.456.789-09', 'Camila Rocha', '(11) 96666-5555', 1);
