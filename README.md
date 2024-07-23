# Aplicação de Cadastro de Produtos com JSF e JPA

Este projeto é uma aplicação web simples para o cadastro de produtos, desenvolvida utilizando **JavaServer Faces (JSF)**, **Java Persistence API (JPA)** e **MySQL**. Ele permite ao usuário inserir informações sobre produtos e persistir essas informações em um banco de dados.

## Índice

- [Visão Geral](#visão-geral)
- [Requisitos](#requisitos)
- [Instalação e Configuração](#instalação-e-configuração)
  - [Configuração do Banco de Dados MySQL](#configuração-do-banco-de-dados-mysql)
  - [Configuração do Apache Tomcat](#configuração-do-apache-tomcat)
- [Execução](#execução)
- [Uso](#uso)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Contribuição](#contribuição)
- [Licença](#licença)

## Visão Geral

A aplicação possui uma interface simples que permite:

- **Cadastro de Produtos**: Insira informações como nome, descrição, data de validade, data de cadastro, e preço do produto.
- **Validações**:
  - **Descrição**: Limite de 50 caracteres.
  - **Data de Validade e Data de Cadastro**: Máscara de data (dd/MM/yyyy) e aceitação apenas de datas válidas.
  - **Preço**: Máscara de valor (R$ 000000,00), aceitação apenas de números, e limite de tamanho (13,2).
- **Operações de Persistência**: As informações do produto são persistidas em um banco de dados MySQL.

## Requisitos

Antes de começar, certifique-se de ter as seguintes ferramentas instaladas em sua máquina:

- [Java Development Kit (JDK) 8+](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Apache Maven](https://maven.apache.org/download.cgi)
- [MySQL](https://dev.mysql.com/downloads/mysql/)
- [Apache Tomcat 9+](https://tomcat.apache.org/download-90.cgi)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/download/)

## Instalação e Configuração

### Configuração do Banco de Dados MySQL

1. **Inicie o MySQL Server** em sua máquina.

2. **Crie um Banco de Dados** e um Usuário para a aplicação:

```sql
-- Criar o banco de dados
CREATE DATABASE product_db;

-- Criar um usuário e definir uma senha
CREATE USER 'product_user'@'localhost' IDENTIFIED BY 'your_password';

-- Conceder todas as permissões ao novo usuário para o banco de dados criado
GRANT ALL PRIVILEGES ON product_db.* TO 'product_user'@'localhost';

-- Aplicar as mudanças
FLUSH PRIVILEGES;
```
## Configurar a Conexão no `persistence.xml`

O arquivo `persistence.xml` deve estar localizado em `src/main/resources/META-INF`. Ele contém as configurações necessárias para o JPA se conectar ao banco de dados MySQL.

```xml
<persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence" version="2.2">
    <persistence-unit name="productPU">
        <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
        <class>com.example.Product</class>
        <properties>
            <!-- Configurações do banco de dados -->
            <property name="javax.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/>
            <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/product_db"/>
            <property name="javax.persistence.jdbc.user" value="product_user"/>
            <property name="javax.persistence.jdbc.password" value="your_password"/>
            <property name="hibernate.dialect" value="org.hibernate.dialect.MySQLDialect"/>
            
            <!-- Configurações do Hibernate -->
            <property name="hibernate.hbm2ddl.auto" value="update"/>
            <property name="hibernate.show_sql" value="true"/>
            <property name="hibernate.format_sql" value="true"/>
        </properties>
    </persistence-unit>
</persistence>
```
Certifique-se de substituir product_db, product_user, e your_password com o nome do seu banco de dados, usuário, e senha que você criou anteriormente.

## Configuração do Apache Tomcat

### Baixe e Instale o Tomcat

1. **Acesse [Apache Tomcat Downloads](https://tomcat.apache.org/download-90.cgi).**
2. **Baixe a versão mais recente** (Tomcat 9 ou superior).
3. **Descompacte o arquivo** em um diretório de sua escolha.

### Configurar o Tomcat no IntelliJ IDEA

#### Adicionar o Servidor

1. Vá para `File > Settings` (ou `IntelliJ IDEA > Preferences` no macOS).
2. Navegue até `Build, Execution, Deployment > Application Servers`.
3. Clique em `+` e selecione `Tomcat Server`.
4. Escolha o diretório onde você descompactou o Tomcat e clique em `OK`.

#### Configurar o Artefato

1. Abra `Run > Edit Configurations`.
2. Clique em `+` e selecione `Tomcat Server > Local`.
3. Na aba `Deployment`, clique em `+` e selecione `Artifact`.
4. Escolha o artefato `your-project:war` e clique em `OK`.

Certifique-se de que o artefato está configurado para ser construído corretamente:

1. Vá para `File > Project Structure > Artifacts`.
2. Verifique se o artefato WAR está sendo gerado corretamente.

#### Configurar Contexto

- Na aba `Server`, defina o contexto raiz do seu projeto (ex: `/product-app`).

## Execução

### Rodando a Aplicação no IntelliJ IDEA

#### Inicie o Tomcat

- Vá para `Run > Run` ou clique no botão `Run` (ícone de play) na parte superior da tela.
- O console do IntelliJ exibirá logs do servidor e confirmará a implantação da aplicação.

#### Acesse a Aplicação

- Abra um navegador e acesse `http://localhost:8080/product-app` (ou o contexto que você configurou).

## Uso

### Funcionalidades da Aplicação

#### Cadastrar Produto

1. Preencha os campos de **Descrição**, **Data de Validade**, **Data de Cadastro**, e **Preço**.
2. Clique em **Gravar** para salvar o produto no banco de dados.
3. Se algum campo obrigatório não estiver preenchido corretamente, uma mensagem de erro será exibida.

#### Limpar Formulário

- Clique em **Limpar** para limpar todos os campos do formulário.

### Validações

- **Descrição**: Máximo de 50 caracteres.
- **Data**: Aceita apenas datas válidas no formato `dd/MM/yyyy`.
- **Preço**: Máscara de valor `R$ 000000,00` com máximo de 13,2 dígitos.

### Mensagens de Feedback

- **Sucesso**: Mensagem indicando que o produto foi salvo com sucesso.
- **Erro**: Mensagens específicas para campos inválidos ou vazios.

###Estrutura do Projeto
your-project
|-- src

| |-- main

| | |-- java

| | | |-- com

| | | | |-- example

| | | | | |-- Product.java

| | | | | |-- ProductBean.java

| | |-- resources

| | | |-- META-INF

| | | | |-- persistence.xml

| | |-- webapp

| | |-- WEB-INF

| | | |-- faces-config.xml

| | |-- index.xhtml

|-- pom.xml

## Principais Arquivos

- **Product.java**: Entidade JPA que representa o produto.
- **ProductBean.java**: Managed Bean JSF que controla a lógica de interação do usuário.
- **index.xhtml**: Página JSF que renderiza o formulário de cadastro.
- **persistence.xml**: Configuração do JPA para conexão com o banco de dados.
- **faces-config.xml**: Configuração do JSF.
- **pom.xml**: Arquivo Maven para gerenciamento de dependências.

## Tecnologias Utilizadas

- **JavaServer Faces (JSF)**: Framework para construção de interfaces web.
- **Java Persistence API (JPA)**: API de persistência de dados.
- **MySQL**: Banco de dados relacional.
- **Apache Tomcat**: Servidor de aplicações web.
- **Maven**: Gerenciador de dependências.
