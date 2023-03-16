CREATE TABLE exame (rowid bigint auto_increment PRIMARY KEY, nm_exame VARCHAR(255));
INSERT INTO exame (nm_exame) VALUES ('Acuidade Visual'), ('Urina'), ('Clinico'), ('Sangue');

CREATE TABLE funcionario (rowid bigint auto_increment PRIMARY KEY, nm_funcionario VARCHAR(255));
INSERT INTO funcionario (nm_funcionario) VALUES ('Carlos');
INSERT INTO funcionario (nm_funcionario) VALUES ('Roberto');

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