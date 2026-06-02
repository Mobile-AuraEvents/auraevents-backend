INSERT INTO artista (nome, assessor_responsavel)
VALUES ('Banda Horizonte Azul', 'Marina Alves');

INSERT INTO artista_telefones (artista_id, telefones)
SELECT a.id, v.telefone
FROM artista a
JOIN (VALUES
    ('Banda Horizonte Azul', '(11) 98888-1111'),
    ('Banda Horizonte Azul', '(11) 97777-2222')
) AS v(nome_artista, telefone)
ON a.nome = v.nome_artista;

INSERT INTO casa_de_show (nome, rua, numero, bairro, cidade, uf, capacidade_maxima, telefone)
VALUES ('Arena Aurora Sul', 'Rua das Luzes', '145', 'Centro', 'Sao Paulo', 'SP', 5000, '(11) 3333-4444');

INSERT INTO patrocinador (nome, cnpj, telefone)
VALUES ('Energia Max Sul', '12.345.678/0001-90', '(11) 4002-8922');

INSERT INTO veiculo_imprensa (cnpj, razao_social, telefone, nome_responsavel, tipo, numero, frequencia, canal)
VALUES ('23.456.789/0001-10', 'Radio Cidade Viva Sul', '(11) 3555-7000', 'Ricardo Lima', 'RADIO', '101.3', 'FM', NULL);

INSERT INTO show (data, artista_id, casa_de_show_id)
SELECT CURRENT_DATE + INTERVAL '20 day', a.id, c.id
FROM artista a
CROSS JOIN casa_de_show c
WHERE a.nome = 'Banda Horizonte Azul'
  AND c.nome = 'Arena Aurora Sul';

INSERT INTO show_veiculo_imprensa (show_id, veiculo_imprensa_id)
SELECT s.id, v.id
FROM show s
CROSS JOIN artista a
CROSS JOIN casa_de_show c
CROSS JOIN veiculo_imprensa v
WHERE s.artista_id = a.id
  AND s.casa_de_show_id = c.id
  AND a.nome = 'Banda Horizonte Azul'
  AND c.nome = 'Arena Aurora Sul'
  AND v.razao_social = 'Radio Cidade Viva Sul';

INSERT INTO patrocinio_show (show_id, patrocinador_id, valor_patrocinado)
SELECT s.id, p.id, 150000.00
FROM show s
CROSS JOIN artista a
CROSS JOIN casa_de_show c
CROSS JOIN patrocinador p
WHERE s.artista_id = a.id
  AND s.casa_de_show_id = c.id
  AND a.nome = 'Banda Horizonte Azul'
  AND c.nome = 'Arena Aurora Sul'
  AND p.nome = 'Energia Max Sul';

INSERT INTO convidado (cpf, nome, telefone, patrocinador_id)
SELECT '234.567.890-10', 'Camila Rocha', '(11) 96666-5555', p.id
FROM patrocinador p
WHERE p.nome = 'Energia Max Sul';
