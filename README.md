CREATE DATABASE biblioteca;

USE biblioteca;

CREATE TABLE livro (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(200),
    ano INT,
    isbn VARCHAR(50)
);

CREATE TABLE membro (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    matricula VARCHAR(50) NOT NULL,
    contato VARCHAR(100)
);

CREATE TABLE emprestimo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    livro_id INT,
    membro_id INT,
    data_inicio DATE NOT NULL,
    data_fim DATE,
    FOREIGN KEY (livro_id) REFERENCES livro(id),
    FOREIGN KEY (membro_id) REFERENCES membro(id)
);

CREATE TABLE funcionario (
	id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cargo VARCHAR(50) NOT NULL,
    data_contratacao DATE NOT NULL, 
    salario DECIMAL(10,2) NOT NULL
    );
