# API de Usuários - Curso Java Nauta

## 📋 Descrição

Esta é uma API REST desenvolvida em Java com Spring Boot para gerenciamento de usuários, endereços e telefones. O projeto faz parte do Curso Java Nauta e implementa funcionalidades completas de CRUD com autenticação JWT e segurança.

## 🚀 Tecnologias Utilizadas

- **Java 21** - Linguagem de programação
- **Spring Boot 3.4.2** - Framework principal
- **Spring Security** - Segurança e autenticação
- **Spring Data JPA** - Persistência de dados
- **MySQL** - Banco de dados
- **JWT (JSON Web Token)** - Autenticação
- **Lombok** - Redução de código boilerplate
- **Gradle** - Gerenciamento de dependências

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas bem definida:


## 🔧 Configuração e Instalação

### Pré-requisitos

- Java 21 ou superior
- MySQL 8.0 ou superior
- Gradle (incluído no projeto via wrapper)

### Configuração do Banco de Dados

1. Certifique-se de que o MySQL está rodando
2. O banco `db_usuario` será criado automaticamente
3. Configure as credenciais no arquivo `application.properties` se necessário:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/db_usuario?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=
```

### Executando a Aplicação

```bash
# Clone o repositório
git clone https://github.com/JeanHeberth/usuario

# Navegue até o diretório
cd usuario

# Execute a aplicação
./gradlew bootRun
```

A aplicação estará disponível em: `http://localhost:8088`

## 📚 Endpoints da API

### Autenticação

| Método | Endpoint | Descrição |
|--------|----------|----------|
| POST | `/usuario/login` | Realizar login e obter token JWT |

### Usuários

| Método | Endpoint | Descrição | Autenticação |
|--------|----------|-----------|-------------|
| POST | `/usuario` | Cadastrar novo usuário | Não |
| GET | `/usuario?email={email}` | Buscar usuário por email | Sim |
| PUT | `/usuario` | Atualizar dados do usuário | Sim |
| DELETE | `/usuario/{email}` | Deletar usuário | Sim |

### Endereços

| Método | Endpoint | Descrição | Autenticação |
|--------|----------|-----------|-------------|
| POST | `/usuario/endereco` | Cadastrar endereço | Sim |
| PUT | `/usuario/endereco?id={id}` | Atualizar endereço | Sim |

### Telefones

| Método | Endpoint | Descrição | Autenticação |
|--------|----------|-----------|-------------|
| POST | `/usuario/telefone` | Cadastrar telefone | Sim |
| PUT | `/usuario/telefone?id={id}` | Atualizar telefone | Sim |

## 🔐 Autenticação

A API utiliza JWT (JSON Web Token) para autenticação. Para acessar endpoints protegidos:

1. Faça login através do endpoint `/usuario/login`
2. Use o token retornado no header `Authorization` das requisições:
   ```
   Authorization: Bearer <seu-token-jwt>
   ```

## 📝 Exemplo de Uso

### Cadastrar Usuário
```json
POST /usuario
{
  "nome": "João Silva",
  "email": "joao@email.com",
  "senha": "123456"
}
```

### Login
```json
POST /usuario/login
{
  "email": "joao@email.com",
  "senha": "123456"
}
```

### Cadastrar Endereço
```json
POST /usuario/endereco
Authorization: Bearer <token>
{
  "rua": "Rua das Flores, 123",
  "cidade": "São Paulo",
  "estado": "SP",
  "cep": "01234-567"
}
```

## 🧪 Testes

```bash
# Executar todos os testes
./gradlew test

# Executar testes com relatório
./gradlew test --info
```

## 📦 Build

```bash
# Gerar JAR da aplicação
./gradlew build

# O arquivo JAR será gerado em: build/libs/
```

## 🤝 Contribuição

Este projeto faz parte do Curso Java Nauta. Para contribuir:

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`)
3. Commit suas mudanças (`git commit -am 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/nova-feature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto é parte do material educacional do Curso Java Nauta.

## 📞 Suporte

Para dúvidas e suporte, consulte o material do curso ou entre em contato através dos canais oficiais do Curso Java Nauta.

---

**Desenvolvido por **Jean Heberth** como parte do Curso Java Nauta** 🚀

## Autor
- Github: [JeanHeberth](https://github.com/JeanHeberth)
- Linkedin: [JeanHeberth](https://www.linkedin.com/in/jean-heberth-souza-vieira-725166150/)
