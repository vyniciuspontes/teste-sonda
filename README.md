## Bem vindo candidato(a)!

Vamos explicar como funciona o nosso desafio:

Um desenvolvedor recebeu um tarefa de uma pessoa da equipe de produto. A pessoa de produto queria poder controlar sondas em outros planetas por meio de comandos. Para explicar o funcionamento do produto, o seguinte exemplo foi escrito em um pedaço de papel:

### Explicação da necessidade:
```
Tamanho da área do planeta : 5x5

Posição de pouso da sonda 1: x=1, y=2 apontando para Norte
Sequencia de comandos: LMLMLMLMM
Posição final da sonda: x=1 y=3 apontando para Norte

Posição de pouso da sonda 2: x=3, y=3 apontando para Leste
Sequencia de comandos: MMRMMRMRRML
Posição final da sonda: x=5 y=1 apontando para Norte
```

### Detalhes sobre o funcionamento acima:

A sequência de comandos é um conjunto de instruções enviadas da terra para a sonda, onde :
- `M` -> Andar para a frente na direção que está 1 posição.
- `L` -> Virar a sonda para a esquerda (90 graus)
- `R` -> Virar a sonda para a direita (90 graus)

A área do planeta é um plano cartesiano com o tamanho informado pelo operador.

A orientação da sonda dentro do plano cartesiano usa uma rosa dos ventos como referência

![rosa dos ventos](http://i.imgur.com/li8Ae5L.png "Rosa dos Ventos")


## O desafio

### Regra de negócios:

- [ ] Primeiramente, **antes de olhar o código** pense quais comportamentos fazem sentido para dar suporte *a várias sondas pousando em um mesmo planeta com uma superfície limitada (podendo haver vários planetas)*. Considere que as sondas possuem combustível infinito e sempre estão disponíveis para receber ordens de movimento.

Essa aplicação foi implementada por um desenvolvedor não muito experiente. O código está funcional e com certa cobertura de testes automatizados mas não necessariamente está seguindo boas práticas. Verifique a implementação sugerida nesse repositório e verifique se:

- [ ] o código da aplicação dá suporte aos comportamentos pensados no primeiro item
- [ ] a API da aplicação dá suporte aos comportamentos pensados no primeiro item

### Crie a sua melhoria sobre a solução proposta aqui:

- [ ] Crie um repositório e faça o push dessa solução para seu novo repo
- [ ] De preferência usando pequenos commits procure corrigir os problemas levantados acima. Para fins de código e divisão de responsabilidades em códigos Orientados a Objeto seguem dois links que exprimem alguns guidelines do Elo7: 

- https://www.alura.com.br/artigos/nao-aprender-oo-getters-e-setters
- https://www.alura.com.br/artigos/o-que-e-modelo-anemico-e-por-que-fugir-dele

Obs: fique à vontade para alterar todas as classes, pacotes etc. **Aproveite apenas o que achar que faz sentido!**

### Para pretensões senior APENAS:

No caso da pretenção estar no patamar de senior nós requisitamos alguns desafios extras:

- [ ] O teste possui um mecanismo de persistência em memória, altere para uma persistência utilizando um ou mais banco de dados de forma a armazenar as informações de planetas e sondas e buscá-las ou alterá-las de maneira eficiente;
- [ ] Se preocupe com uma maneira de documentar a api do sistema web;
- [ ] Tenha em mente escalabilidade, disponibilidade e performance em sua solução. Apesar do problema proposto ser bem didático procure tratar a solução como um sistema de produção real.

Obs: Se você está em dúvida se a sua pretenção é senior ou não procure nossa tech recruiter sobre o assunto, ela saberá responder. Caso sua pretenção seja junior ou pleno você pode encarar os pontos acima como opcionais para demonstrar seu conhecimento e potencializar o valor inicial de nossa oferta, mas se a sua pretenção é junior ou pleno os pontos acima NÃO SÃO OBRIGATÓRIOS para a entrega da solução.

## Informações sobre o projeto

### Como subir o projeto

- Certifique-se que a porta 8080 esteja desocupada;
- Certifique-se de que você possui o maven instalado localmente;
- Certifique-se de que você está na raiz do projeto;
- Rode o `./mvnw spring-boot:run`

Com isso as dependências serão baixadas e a API subirá na porta `8080`;

### Fazendo uma requisição

- Aqui você pode usar o Postman, por exemplo, ou o curl como abaixo:

```bash
curl -X POST http://localhost:8080/planet-with-probes -H 'Content-Type: application/json' -d '{"width":10,"height":10,"probes":[{"x":1,"y":2,"direction":"N","commands": "LMLMLMLMM"},{"x":3,"y":3,"direction":"E","commands": "MMRMMRMRRM"}]}'
```

# Área do Candidato

## Regras de negócio
- As sondas não podem se mover para fora do plano cartesiano
- As sondas não podem colidir
- Lançar a sonda e mover a sonda são 2 comandos distintos

## Padrão de Arquiterura
O projeto foi reestruturado para o padrão Hexagonal aplicando DDD com camadas bem definidas:

- Domain, concentrando o Aggregate, com as entidades, value objects, repository, regras de negócio e exceções do domínio.
- Application, contendo os casos de uso, suas implementações e também as portas para os adapters e exceções de aplicação.
- Adapter, a camada concentra a implementação das portas definidas na camada de aplicação e o uso de infraestruturas e bibliotecas externas.

## Banco de Dados
Escolhi o MongoDb pela praticidade de conseguir salvar o aggregate como um documento só.

### Sobre Repositories e o uso do Spring Data
Há 2 repositórios na aplicação DefaultPlanetRepository e MementoPlanetRepository. 
Um salva a classe do Aggregate Root diretamente e o outro utiliza um entidade de persistencia "PlanetDocument". 

Para salvar e resgatar o Aggregate Root "Planet" diretamente no banco sem precisar mapear para uma 
entidade de persistência, tive que inserir um construtor vazio na classe. Isso fere o princío de isolamento do domínio. 

No entanto, para agradar os puristas, também criei uma solução para persistir o Aggregate Root aplicando o padrão Memento, 
que cria um snapshot do Aggregate Root, o qual utilizo para mapear as propriedades do domínio para uma entidade 
de persistência e vice e versa. O Aggregate Root gera um snapshot de si mesmo na hora e também 
sabe se rehidratar a partir de um snapshop vindo partir do banco de dados. 

## Rodando a aplicação
Para rodar a aplicação é necessário instalar o Docker e o Docker Compose. Na pasta raiz do projeto execute o seguinte comando:

```
docker-compose up
``` 

O Docker Compose executará a aplicação na porta 8080 e um container de MongoDB.

## Testando
Para testar, há 2 opções na pasta docs:

- Utilizar o arquivo de **swagger.yml** e realizar os requests através do swagger
- Importar a collection do Postman 

## O que faltou ?
- Testes de integração, para validar a integração com o MongoDb e as respostas da API
- Testes E2E utilizando alguma solução dockerizada 
- Versionamento da API
- Segurança da API
- Endpoint para buscar uma sonda específica
- Endpoint para mudar o nome do planeta ou da sonda
- Alguma solução de rastreabilidade dos requests
- Logs