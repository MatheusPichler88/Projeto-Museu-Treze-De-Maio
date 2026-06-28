# 🏛️ Projeto Museu Treze de Maio

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white"/>
  <img src="https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white"/>
  <img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white"/>
  <img src="https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white"/>
  <img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white"/>
</p>

---

## 📋 Descrição do Problema e da Solução Proposta

O **Museu Treze de Maio** não possuía presença digital própria, o que dificultava o acesso público ao seu acervo histórico e cultural. A ausência de um sistema informatizado tornava a consulta ao acervo restrita ao ambiente físico do museu, limitando o alcance e a preservação das informações sobre as peças e documentos históricos sob sua guarda.

A solução proposta é um **sistema web completo** que permite ao museu gerenciar e divulgar digitalmente o seu acervo. A plataforma conta com uma interface pública para consulta das peças do acervo e uma área administrativa restrita, na qual funcionários e administradores podem cadastrar, editar e remover itens, com controle de acesso por perfil de usuário.

---

## 🛠️ Tecnologias Utilizadas

| Categoria              | Tecnologia                        |
|------------------------|-----------------------------------|
| Linguagens             | Java, HTML, CSS                   |
| Framework Back-end     | Spring Boot, Spring Data JPA/Hibernate, Spring Security |
| Template Engine        | Thymeleaf                         |
| Framework Front-end    | Bootstrap                         |
| Banco de Dados         | MySQL                             |
| Containerização        | Docker                            |
| Prototipação           | Figma                             |
| Diagramas              | Astah UML                         |
| Gerenciamento          | Trello                            |

---

## 🏗️ Arquitetura Adotada

O projeto adota a arquitetura **MVC (Model-View-Controller)**, um padrão amplamente utilizado no desenvolvimento de aplicações web:

- **Model** — Camada de dados e regras de negócio, implementada com entidades Java mapeadas via JPA/Hibernate e persistidas no MySQL.
- **View** — Camada de apresentação construída com templates Thymeleaf, HTML, CSS e Bootstrap, responsável pela interface exibida ao usuário.
- **Controller** — Camada intermediária implementada com Spring Boot, que recebe as requisições HTTP, aciona a lógica de negócio e retorna as respostas adequadas às views.

O Spring Security atua transversalmente, garantindo autenticação e autorização por perfil de acesso em todas as rotas da aplicação.

---

## 🚀 Instruções de Instalação e Execução

### Pré-requisitos

- [Docker](https://www.docker.com/) instalado e em execução na máquina
- [Git](https://git-scm.com/) instalado

---

### Passo 1 — Clonar o repositório

Abra um terminal, navegue até a pasta onde deseja criar o projeto e execute:

```bash
git clone https://github.com/MatheusPichler88/Projeto-Museu-Treze-De-Maio
```

Entre na pasta gerada:

```bash
cd Projeto-Museu-Treze-De-Maio
```

---

### Passo 2 — Configurar as variáveis de ambiente

Dentro da pasta do projeto, crie um arquivo chamado `.env` com o seguinte conteúdo:

```env
DB_HOST=localhost
DB_PORT=3306
DB_NAME=db_museu
DB_USER=root
DB_PASSWORD=senha_do_banco
```

> ⚠️ Substitua `senha_do_banco` pela senha desejada para o banco de dados.

---

### Passo 3 — Subir o projeto com Docker

Ainda no terminal, dentro da pasta do projeto, execute:

```bash
docker compose up
```

Aguarde o Docker baixar as imagens e iniciar os containers. Ao finalizar, acesse o sistema pelo navegador:

```
http://localhost:8080
```

---

### Passo 4 — Acessar o sistema

Utilize as credenciais abaixo conforme o perfil desejado:

| Perfil        | Usuário | Senha  |
|---------------|---------|--------|
| Administrador | admin   | 123456 |
| Funcionário   | user    | 123456 |

---

### Passo 5 — Gerenciar os containers

Para **parar** os containers:

```bash
docker compose down
```

Para **subir** novamente:

```bash
docker compose up
```

---

## 🎬 Vídeo Demonstrativo

> 🔗 *https://youtu.be/oOf_JJc3D1A*

---

## 👥 Equipe de Desenvolvimento

| Nome             | GitHub                                                      | Responsabilidades                                      |
|------------------|-------------------------------------------------------------|--------------------------------------------------------|
| Andrisa Santos   | [@Andrisa-S](https://github.com/Andrisa-S)                 | Scrum Master, organização e documentação               |
| Gabriel Gonzalez | [@Gabrielgonzalez7](https://github.com/Gabrielgonzalez7)   | Desenvolvimento front-end, design e testes             |
| Gustavo Sefrin   | [@TavoSefrin](https://github.com/TavoSefrin)               | Diagramas, arquitetura e apoio geral                   |
| Matheus Picler   | [@MatheusPicler88](https://github.com/MatheusPicler88)     | Desenvolvimento back-end, banco de dados, segurança e design |

---

<p align="center">Desenvolvido com ❤️ para o Museu Treze de Maio</p>