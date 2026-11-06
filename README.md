# 🔐 CRUD COM JAVA

## 📝 Resumo

Sistema de login feito em Java com interface gráfica (Swing) e banco de dados MySQL, desenvolvido na disciplina de Desenvolvimento para Servidores I, na Fatec São Roque. Permite cadastrar, entrar, alterar e excluir usuários, com as senhas salvas criptografadas em MD5.

## 📁 Diretório

```
📦 repositório
 ┣ 📂 database
 ┃ ┣ 📄 bd_login.sql
 ┃ ┗ 📄 mysql-connector-j-9.7.0.jar
 ┗ 📂 Login            (projeto NetBeans)
   ┗ 📂 src/login
     ┣ ☕ Conexao.java
     ┣ ☕ TelaAlteracao.java
     ┣ ☕ TelaCadastro.java
     ┣ ☕ TelaInicio.java
     ┣ ☕ TelaLogin.java
     ┣ ☕ TestaConexao.java
     ┣ ☕ TestaTelaAlteracao.java
     ┣ ☕ TestaTelaCadastro.java
     ┣ ☕ TestaTelaInicio.java
     ┣ ☕ TestaTelaLogin.java
     ┗ ☕ Usuario.java
```

## 🛠️ Tecnologias usadas

- ☕ Java (Swing)
- 🐬 MySQL
- 🔌 MySQL Connector/J 9.7.0
- 💻 NetBeans

## 🚀 Como rodar

1. Execute o `database/bd_login.sql` no MySQL para criar o banco e a tabela.
2. Abra a pasta `Login` no NetBeans.
3. Adicione o `mysql-connector-j-9.7.0.jar` nas bibliotecas (*Libraries*) do projeto.
4. Na classe `Conexao`, ajuste o servidor, o usuário e a senha do seu MySQL.
5. Execute a `TelaLogin`.