INSERT INTO artista (nome, assessor_responsavel, foto_url)
VALUES
    ('Quarteto Sol Nascente', 'Mariana Araujo', 'https://images.unsplash.com/photo-1498038432885-c6f3f1d2b7f8?auto=format&fit=crop&w=1200&q=80'),
    ('Banda Noite Clara', 'Rafael Pinto', 'https://images.unsplash.com/photo-1501386761578-eac5c94b800a?auto=format&fit=crop&w=1200&q=80'),
    ('Duo Estacao Viva', 'Camila Souza', 'https://images.unsplash.com/photo-1511379938547-c1f69419868d?auto=format&fit=crop&w=1200&q=80');

INSERT INTO artista_telefones (artista_id, telefones)
SELECT a.id, v.telefone
FROM artista a
JOIN (VALUES
    ('Quarteto Sol Nascente', '(11) 98888-6060'),
    ('Quarteto Sol Nascente', '(11) 97777-7070'),
    ('Banda Noite Clara', '(21) 96666-8080'),
    ('Duo Estacao Viva', '(31) 95555-9090')
) AS v(nome_artista, telefone)
ON a.nome = v.nome_artista;

INSERT INTO casa_de_show (nome, rua, numero, bairro, cidade, uf, capacidade_maxima, telefone, foto_url)
VALUES
    ('Arena Litoral', 'Avenida Beira Mar', '120', 'Mucuripe', 'Fortaleza', 'CE', 4100, '(85) 3222-4400', 'https://images.unsplash.com/photo-1518977956815-dee9c84d0b27?auto=format&fit=crop&w=1200&q=80'),
    ('Palco das Artes', 'Rua do Teatro', '78', 'Centro', 'Salvador', 'BA', 2600, '(71) 3333-5500', 'https://images.unsplash.com/photo-1501386761578-eac5c94b800a?auto=format&fit=crop&w=1200&q=80');

INSERT INTO patrocinador (nome, cnpj, telefone)
VALUES
    ('Energia Prime', '90.123.456/0001-77', '(11) 4200-1000'),
    ('Viva Beats', '91.234.567/0001-88', '(11) 4200-2000');

INSERT INTO veiculo_imprensa (cnpj, razao_social, telefone, nome_responsavel, tipo, numero, frequencia, canal)
VALUES
    ('92.345.678/0001-99', 'Radio Horizonte', '(11) 3600-1000', 'Patricia Lima', 'RADIO', '98.7', 'FM', NULL),
    ('93.456.789/0001-00', 'Portal Show News', '(11) 3600-2000', 'Eduardo Reis', 'TV', NULL, NULL, 'Canal 12');

INSERT INTO show (data, artista_id, casa_de_show_id)
SELECT CURRENT_DATE + INTERVAL '75 day', a.id, c.id
FROM artista a
CROSS JOIN casa_de_show c
WHERE a.nome = 'Quarteto Sol Nascente'
  AND c.nome = 'Arena Litoral';

INSERT INTO show (data, artista_id, casa_de_show_id)
SELECT CURRENT_DATE + INTERVAL '90 day', a.id, c.id
FROM artista a
CROSS JOIN casa_de_show c
WHERE a.nome = 'Banda Noite Clara'
  AND c.nome = 'Palco das Artes';

INSERT INTO show (data, artista_id, casa_de_show_id)
SELECT CURRENT_DATE + INTERVAL '105 day', a.id, c.id
FROM artista a
CROSS JOIN casa_de_show c
WHERE a.nome = 'Duo Estacao Viva'
  AND c.nome = 'Arena Litoral';

INSERT INTO show_veiculo_imprensa (show_id, veiculo_imprensa_id)
SELECT s.id, v.id
FROM show s
CROSS JOIN veiculo_imprensa v
WHERE (s.data = CURRENT_DATE + INTERVAL '75 day' AND v.razao_social = 'Radio Horizonte')
   OR (s.data = CURRENT_DATE + INTERVAL '75 day' AND v.razao_social = 'Portal Show News')
   OR (s.data = CURRENT_DATE + INTERVAL '90 day' AND v.razao_social = 'Radio Horizonte')
   OR (s.data = CURRENT_DATE + INTERVAL '105 day' AND v.razao_social = 'Portal Show News');

INSERT INTO patrocinio_show (show_id, patrocinador_id, valor_patrocinado)
SELECT s.id, p.id, x.valor
FROM show s
CROSS JOIN patrocinador p
CROSS JOIN (VALUES
    (CURRENT_DATE + INTERVAL '75 day', 'Energia Prime', 210000.00),
    (CURRENT_DATE + INTERVAL '90 day', 'Viva Beats', 160000.00),
    (CURRENT_DATE + INTERVAL '105 day', 'Energia Prime', 175000.00)
) AS x(data_show, nome_patrocinador, valor)
WHERE s.data = x.data_show
  AND p.nome = x.nome_patrocinador;

INSERT INTO convidado (cpf, nome, telefone, patrocinador_id)
SELECT c.cpf, c.nome, c.telefone, p.id
FROM patrocinador p
JOIN (VALUES
    ('567.890.123-43', 'Juliana Alves', '(11) 98888-4444', 'Energia Prime'),
    ('678.901.234-54', 'Thiago Rocha', '(11) 97777-5555', 'Viva Beats'),
    ('789.012.345-65', 'Patricia Gomes', '(11) 96666-6666', 'Energia Prime')
) AS c(cpf, nome, telefone, nome_patrocinador)
ON p.nome = c.nome_patrocinador;
