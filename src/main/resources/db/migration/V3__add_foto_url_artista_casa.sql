ALTER TABLE artista
ADD COLUMN foto_url VARCHAR(255);

ALTER TABLE casa_de_show
ADD COLUMN foto_url VARCHAR(255);

UPDATE artista
SET foto_url = 'https://images.unsplash.com/photo-1511379938547-c1f69419868d?auto=format&fit=crop&w=1200&q=80'
WHERE id = 1;

UPDATE casa_de_show
SET foto_url = 'https://images.unsplash.com/photo-1514525253161-7a46d19cd819?w=600'
WHERE id = 1;
