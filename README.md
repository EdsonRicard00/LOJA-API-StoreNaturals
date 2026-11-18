# 👕 LOJA-API-StoreNaturals

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Maven](https://img.shields.io/badge/Apache_Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![Status](https://img.shields.io/badge/Status-Em_Desenvolvimento-yellow?style=for-the-badge)

## 📋 Sobre o Projeto

A **LOJA-API-StoreNaturals** é uma API RESTful desenvolvida em **Java 21** com **Spring Boot**, projetada para gerenciar todo o back-end de um e-commerce especializado na venda de *t-shirts*.

O sistema foi construído seguindo o padrão de **arquitetura em camadas**, garantindo separação de responsabilidades, segurança, escalabilidade e facilidade de manutenção para a operação da loja virtual.

---

## 🚀 Tecnologias Utilizadas

* **Linguagem:** Java 21
* **Framework:** Spring Boot
* **Persistência de Dados:** Spring Data JPA / Jakarta Persistence
* **Banco de Dados:** Relacional (MySQL / SQL Server)
* **Gerenciamento de Dependências:** Maven
* **Controle de Versão:** Git & GitHub

---

## 🏗️ Arquitetura do Projeto

O código-fonte está estruturado sob o pacote base `SENAI.ipiranga.com.StoreNatural`, dividido nas seguintes camadas:

```text
src/main/java/SENAI/ipiranga/com/StoreNatural/
├── Model/           # Entidades JPA mapeadas para o banco de dados (Produto, Pedido, Usuario, UsuarioLogin)
├── Repository/      # Interfaces de acesso e persistência de dados (Spring Data JPA)
├── Service/         # Regras de negócio da aplicação (ex: UsuarioService)
├── Controller/      # Endpoints REST para integração com o front-end (em desenvolvimento)
└── StoreNaturalApplication.java  # Classe principal de inicialização do Spring Boot
