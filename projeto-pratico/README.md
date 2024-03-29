#### Instruções

### Central de Erros

#### Objetivo

Em projetos modernos é cada vez mais comum o uso de arquiteturas baseadas em serviços ou microsserviços. Nestes ambientes complexos, erros podem surgir em diferentes camadas da aplicação (backend, frontend, mobile, desktop) e mesmo em serviços distintos. Desta forma, é muito importante que os desenvolvedores possam centralizar todos os registros de erros em um local, de onde podem monitorar e tomar decisões mais acertadas. Neste projeto vamos implementar uma API Rest para centralizar registros de erros de aplicações.

Abaixo estão os requisitos desta API, o time terá total liberdade para tomar as decisões técnicas e de arquitetura da API, desde que atendam os requisitos abaixo.

#### API

##### Tecnologia
- [x] Utilizar a tecnologia base da aceleração para o desenvolvimento (Exemplo: Java, Node.js)

##### Premissas

- [x] A API deve ser pensada para atender diretamente um front-end
- [x] Deve ser capaz de gravar os logs de erro em um banco de dados relacional
- [x] O acesso a ela deve ser permitido apenas por requisições que utilizem um token de acesso válido

##### Funcionalidades

- [x] Deve permitir a autenticação do sistema que deseja utilizar a API gerando o Token de Acesso
- [x] Pode ser acessado por multiplos sistemas
- [x] Deve permitir gravar registros de eventos de log salvando informações de Level(error, warning, info), Descrição do Evento, LOG do Evento, ORIGEM(Sistema ou Serviço que originou o evento), DATA(Data do evento), QUANTIDADE(Quantidade de Eventos de mesmo tipo)
- [x] Deve permitir a listagem dos eventos juntamente com a filtragem de eventos por qualquer parâmetro especificado acima
- [x] Deve suportar Paginação
- [x] Deve suportar Ordenação por diferentes tipos de atributos
- [x] A consulta de listagem não deve retornar os LOGs dos Eventos
- [x] Deve permitir a busca de um evento por um ID, dessa maneira exibindo o LOG desse evento em específico

[Link de orientações para o Projeto](./%5BAceleraDev%20Java%5D%20Orienta%C3%A7%C3%B5es%20para%20entrega%20do%20projeto%20final.pdf).