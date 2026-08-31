# API Carteira Digital (Mini Pix)

Uma API RESTful desenvolvida em Java e Spring Boot que simula o funcionamento de uma carteira digital, permitindo a criação de contas, consulta de saldo e transferências seguras entre usuários (estilo Pix).

# Tecnologias Utilizadas

* Java
* Spring Boot (Web, Data JPA, Validation)
* Banco de Dados H2 (Em memória)
* Swagger / OpenAPI (Documentação interativa)

# Funcionalidades e Regras de Negócio

* Criação de Conta: Permite cadastrar novos usuários com CPF, Nome e Saldo inicial.
* Consulta de Saldo: Busca detalhada pelo CPF, com tratamento de erro (404 Not Found) caso não exista.
* Transferência (Mini Pix): Motor de transferência seguro que:
    * Valida se as duas contas (origem e destino) existem no banco.
    * Impede transferências de quem não possui saldo suficiente.
    * Bloqueia requisições com valores negativos ou CPFs em branco.
    * Atualiza o saldo das duas contas simultaneamente.

# Arquitetura do Projeto

O projeto foi construído utilizando o padrão de mercado de Arquitetura em Camadas:

* Controller: Gerencia as requisições HTTP da internet e devolve os Status Codes corretos (200, 201, 400, 404).
* Service: Isola as lógicas pesadas e regras de negócio.
* Repository: Ponte de comunicação com o Banco de Dados.
* Model: Representação das entidades (Tabelas).

# Como Testar a API

Com a aplicação rodando localmente, acesse a interface gráfica do Swagger para testar todas as rotas diretamente pelo navegador:
`http://localhost:8080/swagger-ui/index.html`