# Movies Battle
Projeto Movies Battle, Será que você consegue adivinhar qual dos filmes tem o maior pontuação?

[![NPM](https://img.shields.io/npm/l/react)](https://github.com/Thalles-Trevizan/MoviesBattle/blob/main/LICENSE)

# Sobre o projeto

Movies Battle é uma aplicação Back-end construido para o processo seletivo da [LetsCode](https://devsuperior.com "Site da LetsCode").

A aplicação consiste em um jogo estilo card games, onde serão informados dois filmes e o jogador deve acertar aquele que possui melhor pontuação, considerando o rating do IMDb e a quantidade de votos.

# Tecnologias utilizadas
## Back end
- Java 11
- Maven
- Spring Web, Boot, Data, Security e Cloud


# Como executar e jogar o projeto via Postman

## Back end
Pré-requisitos: Java 11, Postman

Obs: Apenas o Backend construido, logo, você irá receber o ImdbId de dois filmes, por enquanto, se quiser saber há quais filmes os ids se referenciam, você pode chamar a rota no seu navegador http://www.omdbapi.com/?apikey=8153fddd&i=tt0096895, alterando o campo i para o id recebido.

Primeiramente, clone o projeto e o execute-o na sua maquina

```bash
# clonar repositório
git clone https://github.com/Thalles-Trevizan/MoviesBattle.git

# entrar na pasta do projeto 
# executar o projeto
./mvnw spring-boot:run
```

Para iniciar o jogo, você pode criar um usuário dando um post na rota /users passando um body como na imagem {{host}} = localhost:8080

![image](https://user-images.githubusercontent.com/55063360/154869611-50897eda-668a-44b8-bb69-3f8af19007cb.png)

Ou utilizando um dos dois players pré-cadastrados para demo do jogo 

![image](https://user-images.githubusercontent.com/55063360/154869681-95322f1e-b461-40b4-909b-e51426512939.png)

Assim que escolher se irá criar o seu player ou jogar com um pré existente, basta fazer o login em {{host}}/oauth/token para receber o seu token e começar a utilizar os endpoints do jogo(Obs: tirando o post de criação de novo usuário, todos os outros endpoints é necessário estar logado para utilizar)

Exemplo de chamada e retorno da autenticação:

![image](https://user-images.githubusercontent.com/55063360/154869685-1f094ae9-f3b9-46ec-b65b-50b75b184e96.png)

Assim que estiver logado, para iniciar o jogo você deve fazer um post na rota game/start, você receberá um retorno de jogo iniciado e o primiero quizz a ser respondido
{{host}}/game/start

![image](https://user-images.githubusercontent.com/55063360/154869703-15076d7d-b6e1-4993-99f5-7fbf0f4bbf81.png)

Para responde-lo, basta fazer um post em 
{{host}}/quizz/answer/{quizzId}/{answerId}
Enviando no campo {quizzId} o id do quizz  que você deseja responder (recebido ao iniciar o game ou responder uma pergunta), e a sua resposta (1 ou 2) de qual filme tem maior pontuação no campo {answerId}.Caso você acerte, irá ganhar um ponto e como resposta um parabéns e um novo quizz para responder, caso erre, não pontua e recebe um novo quizz(Caso ainda não tenha errado 3 vezes)

![image](https://user-images.githubusercontent.com/55063360/154869733-8e018fb8-c368-4d31-b534-74c648b68f29.png)

![image](https://user-images.githubusercontent.com/55063360/154869737-51cef0f5-f8b2-4003-9350-56d85c1e9e6d.png)

Assim que você errar 3 vezes, seu jogo é finalizado, e é necessário iniciar um novo jogo na rota game/start 

![image](https://user-images.githubusercontent.com/55063360/154870460-ff12376f-e1a2-4058-80fd-77a8ff3a24dc.png)


Caso queira finalizar o jogo antes de errar 3 vezes, basta chamar a rota game/finish
{{host}}/game/finish

![image](https://user-images.githubusercontent.com/55063360/154869750-65f48368-ee23-4d0c-b97d-d864362d1684.png)

Se você tentar iniciar um jogo sem estar logado, fechar o jogo, sem ter iniciado  um, ou tentar responder um quizz sem ter um jogo em andamento, você receberá um Unathorized Exception como resposta
Exemplo no caso de fechar um jogo sem ter um aberto:

![image](https://user-images.githubusercontent.com/55063360/154869759-46278579-2330-49fc-8470-1d33a2979a31.png)

você também não consegue gerar um novo quizz sem antes responder o anterior

O jogo manterá uma tabela de ranking com os jogos realizados, o nome do jogador e o seu score 

![image](https://user-images.githubusercontent.com/55063360/154869774-dfb959e3-899f-42a6-ad8d-9cfda3250085.png)

(imagem exemplo: H2 local)

Você também pode acompanhar as informações nas tabelas do jogo enquanto joga pelo navegador no link: localhost:8080/h2-console/login.jsp

# Autor

Thalles de Antonio Trevizan

https://www.linkedin.com/in/thalles-trevizan/

