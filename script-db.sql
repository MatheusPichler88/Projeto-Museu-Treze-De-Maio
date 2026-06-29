CREATE DATABASE db_museu;
USE db_museu;

CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    nome VARCHAR(100),
    perfil ENUM('ADMINISTRADOR', 'FUNCIONARIO', 'VISITANTE') DEFAULT 'VISITANTE',
    ativo BOOLEAN DEFAULT TRUE
);

CREATE TABLE categoria (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    descricao TEXT,
    ativo BOOLEAN DEFAULT TRUE
);

CREATE TABLE item_acervo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo_item ENUM('LIVRO', 'REVISTA', 'OUTRO') NOT NULL,
    codigo_patrimonio VARCHAR(50) UNIQUE NOT NULL,
    titulo VARCHAR(200) NOT NULL,
    autor_criador VARCHAR(255),
    editora_origem VARCHAR(100),
    ano_registro INT,
    quantidade INT DEFAULT 1,
    estado_conservacao ENUM('EXCELENTE', 'BOM', 'REGULAR', 'RUIM', 'EM_RESTAURO') DEFAULT 'BOM',
    localizacao_fisica VARCHAR(150),
    descricao TEXT,
    ativo BOOLEAN DEFAULT TRUE,

    categoria_id BIGINT NOT NULL,
    CONSTRAINT fk_acervo_categoria FOREIGN KEY (categoria_id) REFERENCES categoria(id)
);
