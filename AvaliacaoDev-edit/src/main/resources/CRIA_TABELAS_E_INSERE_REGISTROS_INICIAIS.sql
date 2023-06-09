CREATE TABLE exame (rowid bigint auto_increment PRIMARY KEY, nm_exame VARCHAR(255));
INSERT INTO exame (nm_exame) VALUES ('Acuidade Visual'), ('Urina'), ('Clinico'), ('Sangue');

CREATE TABLE funcionario (rowid bigint auto_increment PRIMARY KEY, nm_funcionario VARCHAR(255));
INSERT INTO funcionario (nm_funcionario) VALUES ('Carlos'), ('Roberto'), ('Miguel');

CREATE TABLE exame_realizado (
  rowid bigint auto_increment PRIMARY KEY,
  rowid_exame bigint REFERENCES exame(rowid) ON DELETE RESTRICT,
  rowid_funcionario bigint REFERENCES funcionario(rowid) ON DELETE CASCADE,
  data_exame DATE
);
ALTER TABLE exame_realizado
ADD CONSTRAINT uk_exame_realizado UNIQUE (rowid_exame, rowid_funcionario, data_exame);

INSERT INTO exame_realizado
(rowid_exame, rowid_funcionario, data_exame) 
VALUES (2, 1, '2020-10-12');
INSERT INTO exame_realizado
(rowid_exame, rowid_funcionario, data_exame) 
VALUES (2, 2, '2020-10-12');
INSERT INTO exame_realizado
(rowid_exame, rowid_funcionario, data_exame) 
VALUES (3, 2, '2023-02-18');


/*INSERT INTO funcionario (nm_funcionario) VALUES
('Heitor'), ('Gustavo'), ('Felipe'), ('João'),
('Jeferson'), ('Victor'), ('Beatriz'),
('Rafael'),('Pedro'), ('Leonardo'), ('Herman'), ('Eric'), ('Hugo'), ('Nathália');


INSERT INTO exame (nm_exame) VALUES
('Ecocardiograma'), ('Colesterol'), ('Ureia e Creatina'), ('TSH e T4 livre'),
('Ácido Úrico'), ('Raio X'), ('Mamografia'), ('Tomografia'),
 ('Ultrassonografia'), ('Densitometria Óssea'), ('Eletroneuromiografia'),
('Eletrocardiograma'), ('Papanicolau'), ('Transaminases'), ('Ressonância Magnética'), ('Audiometria');


INSERT INTO exame_realizado
(rowid_exame, rowid_funcionario, data_exame) 
VALUES
(10, 5, '2020-12-18'),
(12, 8, '2022-09-10'),
(20, 1, '2023-02-15'),
(14, 11, '2022-05-19'),
(10, 5, '2021-01-28'),
(9, 4, '2022-03-20'),
(1, 13, '2021-04-11');*/



