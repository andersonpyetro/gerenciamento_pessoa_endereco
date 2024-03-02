Descrição:

Este projeto demonstra a implementação de um sistema CRUD utilizando JPA com as classes Pessoa e Endereco. O projeto permite gerenciar pessoas e seus endereços principais, incluindo a criação, leitura, atualização e exclusão de dados.

Tecnologias:
Java 17
Spring Boot 2.7.0
JPA 2.2
H2 Database Engine

Classes:

Pessoa:

Possui atributos como id (auto-gerado), nome, sobrenome, dataNascimento e enderecoPrincipal.
A coluna endereco_principal_id referencia o ID do endereço principal na tabela Endereco.

Endereco:

Possui atributos como id (auto-gerado), logradouro, cep, numero, cidade, estado e principal.
Um Endereco pode ser o endereço principal de uma Pessoa.

Mapeamento JPA:

A relação entre Pessoa e Endereco é mapeada com @OneToOne com optional = true.
A coluna endereco_principal_id na tabela Pessoa é mapeada com @JoinColumn.

Funcionalidades CRUD:

Create: Cadastrar novas pessoas com seus endereços principais.
Read: Consultar dados de pessoas, podendo filtrar por ID ou recuperar todas as pessoas.
Update: Modificar informações de pessoas e seus endereços principais.
Delete: Remover pessoas e seus endereços principais do banco de dados.
