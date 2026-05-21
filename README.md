# Ecommerce Backend Project

##  Sobre o projeto
Este repositório faz parte do desenvolvimento de um **sistema completo de e-commerce**, que inclui:
- **Backend**: API REST construída com Spring Boot, responsável pela lógica de negócio, autenticação, persistência e integração com o banco de dados.
- **Frontend**: aplicação separada, que consumirá a API do backend para oferecer uma experiência moderna e responsiva ao usuário.
- **Banco de Dados**: persistência configurada com MySQL, garantindo integridade e consistência dos dados.

O objetivo é criar uma solução **modular e escalável**, onde o backend e o frontend são independentes, mas se comunicam de forma eficiente.

---

##  Tecnologias utilizadas

- **Java 25** → Linguagem de programação na versão 21 (LTS).
- **Spring Boot** → framework principal para desenvolvimento do backend.
- **Spring Data JPA** → abstração para persistência e ORM.
- **Hibernate** → implementação JPA para mapeamento objeto-relacional.
- **Spring Security** → autenticação e autorização da aplicação.
- **Spring Validation** → validação de dados de entrada (DTOs e entidades).
- **Spring Web MVC** → construção de APIs RESTful.
- **MySQL** → banco de dados relacional para persistência.
- **MySQL driver** → driver JDBC para integração com MySQL.
- **Lombok** → redução de boilerplate (getters, setters, builders).
- **JWT** (Java JSON Web Token) → geração e validação de tokens JWT para autenticação.
- **Spring Boot DevTools** → ferramentas de desenvolvimento (hot reload, melhorias de produtividade).
- **Maven** → gerenciamento de dependências e build do projeto.

---

##  Estrutura
- `backend/` → código do servidor (Spring Boot)
```
  EcommerceBackendProject/
  │
  ├── src/
  │   ├── main/
  │   │   ├── java/com/project/ecommerce/
  │   │   │   ├── config/          → Configurações globais (CORS, beans, segurança)
  │   │   │   ├── controllers/     → Endpoints REST da API
  │   │   │   ├── entities/        → Entidades JPA (mapeamento das tabelas)
  │   │   │   ├── enums/           → Enumerações (status, roles, etc.)
  │   │   │   ├── repositories/    → Interfaces JPA para persistência
  │   │   │   ├── requests/        → DTOs de entrada (payloads)
  │   │   │   ├── responses/       → DTOs de saída (retorno da API)
  │   │   │   ├── services/        → Lógica de negócio
  │   │   │   ├── exceptions/      → Exceções customizadas e handlers globais
  │   │   │   ├── security/        → Autenticação/autorização (JWT, roles)
  │   │   │   └── EcommerceBackendProjectApplication.java → Classe principal
  │   │   │
  │   │   └── resources/
  │   │       ├── application.properties        → Configuração padrão
  │   │       ├── application-dev.properties    → Configuração ambiente dev
  │   │       ├── application-prod.properties   → Configuração ambiente prod
  │   │       └── static/ & templates/          → Recursos estáticos (se precisar)
  │   │
  │   └── test/java/com/project/ecommerce/      → Testes unitários e de integração
  │
  ├── .gitattributes
  ├── .gitignore
  ├── HELP.md
  ├── mvnw
  ├── mvnw.cmd
  ├── pom.xml
  └── README.md 
  ```
---
##  Funcionalidades planejadas
- Cadastro e autenticação de usuários
- Gerenciamento de produtos e categorias
- Carrinho de compras e pedidos
- Persistência completa no banco de dados
- API documentada para integração com o frontend

##  Hospedagem
Ainda em avaliação: o projeto poderá ser hospedado em serviços como **AWS**, ou **Azure**, dependendo da necessidade de escalabilidade e custo-benefício.
