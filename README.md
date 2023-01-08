## Diazero - Desenvolvedor Java Pleno

### Stack:
- Java 8
- Spring boot 2.7.7
- Apache Maven 4.0.0
- H2 runtime

### Dependências
- spring-boot-starter: utilizado para startar o projeto com o spring boot.
- spring-boot-devtools: utilizado para evitar rebuilds durante o desenvolvimento.
- spring-boot-starter-web: possibilita a criação e inserção de anotações para desenvolvimento de rest apis.
- spring-boot-starter-validation: possibilita a inserção de validadores para variáveis via anotações.
- spring-boot-starter-data-jpa: possibilita a comunicação com o banco de dados de forma simplificada, utilizando anotações.
- h2: cria um banco de dados em memória.
- lombok: possibilita a inserção de getters e setters e outros atalhos via anotações.
- spring-boot-starter-security: possibilita a criação de camada de autenticação, tornando os dados mais seguros.
- commons-beanutils: utlizado para a comunicação entre objetos evitando conflito entre espaços de memória.
- springfox-swagger2 e springfox-swagger-ui: possibilita a criação de um swagger para a documentação da api.

### Considerações
- 2 usuários em mémoria foram criados:

| username | password     | role  |
|----------|--------------|---|
| admin    | testediazero | admin  |
| rita     | testediazero         | user  |

- Os endpoints para as requições são diferentes dependendo do tipo de Role do usuário.
- Endpoints de alteração como: create, update, patch e delete, estão disponíveis apenas ao admin;
- É possível fechar e reabrir um incidente;

### Utilização dos recursos
localhost:8080

| Info                                            | Method | URI                    | Content-Type |
|-------------------------------------------------|--------|------------------------|---|
| Listando incidents                              | GET    | /incidents             |   |
| Listando top 20 incidentes em ordem decrescente | GET    | /incidents/top20       |   |
| Registrando incident                            | POST   | /incidents             | application/json |
| Buscando incident por ID                        | GET    | /incidents/{Id}        |   |
| Atualizando incident                            | PUT    | /incidents/{Id}        | application/json |
| Atualizando informação do incidente             | PATCH  | /incidents/{Id}        | application/json |
| Fechando incident                               | POST   | /incidents/{Id}/close  |   |
| Reabrindo incident                              | POST    | /incidents/{Id}/reopen |   |
| Deletando incident                              | DELETE | /incident/{Id}         |   |

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
### Critérios para avaliação
#### Será avaliado o conhecimento do framework spring, como você estrutrou o projeto e a clareza da solução. Lembre-se de usar inglês no processo de codificação.
1) Stack
   - JPA/Hibernate
   - Spring Boot
   - Maven
2) Criar uma aplicação spring-boot para cadastro de incidentes. A aplicação deverá fornecer operações
   REST que possibilitem o cadastro/manutenção/remoção de incidentes.
   Um incidente é composto pelos campos (coloque mais campos se achar necessário).
    - idIncident
    - name
    - description
    - createdAt
    - updatedAt
    - closedAt
   
3) Sua aplicação deve ter as funcionalidades abaixo:
   - Cadastrar Incidentes
   - Atualizar Incidentes
   - Deletar Incidentes
   - Listar todos incidentes
   - Listar incidente por ID
   - Listar os últimos 20 incidentes ordenados por ordem decrescente

4) Utilize algum banco de dados 'embeded' (h2 por ex) para persistência dos dados. Pode usar qualquer outro banco desde que você consiga documentar os passos para inicialização da aplicação.

5) Adicione um README com:
   - Instruções sobre como compilar e executar o projeto;
   - Uma justificativa para o uso de frameworks ou bibliotecas (caso sejam usadas);
   - Notas adicionais que você considere importantes para a avaliação.

6) Opcional
   - docker e docker-compose
   - spring-security
   - swagger
   - testes unitários