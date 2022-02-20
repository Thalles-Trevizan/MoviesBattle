package com.letscode.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.letscode.client.GetClientMovieProp;
import com.letscode.client.GetClientMovies;
import com.letscode.dto.GetMoviePropDTO;
import com.letscode.dto.GetMoviesDTO;
import com.letscode.dto.GetPageMoviesDTO;
import com.letscode.dto.newGameDTO;
import com.letscode.entities.Game;
import com.letscode.entities.Quizz;
import com.letscode.repositories.GameRepository;
import com.letscode.repositories.QuizzRepository;
import com.letscode.services.util.RandomizerUtil;

@Service
public class QuizzService {
	
	@Autowired
	private GetClientMovieProp client;
	
	@Autowired
	private GetClientMovies clientMovies;
	
	@Autowired
	private RandomizerUtil random;

	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private GameService gameService;

	@Autowired
	private RankingService rankingService;

	@Autowired
	private QuizzRepository repository;

	
	
	@Transactional
	public Quizz insertNewQuizz(Long gameId) {
	
		GetMoviePropDTO movie1 = chooseMovie();
		GetMoviePropDTO movie2 = chooseMovie();

		while (movie1.getImdbID() == movie2.getImdbID()) {
			movie2 = chooseMovie();
		}

		Quizz quizz = validatePointsAndSave(movie1, movie2, gameId);
		
		Game game = gameRepository.getOne(gameId);
		game.getQuizzes().add(quizz);
		gameRepository.save(game);
		
		return quizz;
	}

	public GetMoviePropDTO findMovieProperties(String movieId) {
			GetMoviePropDTO post = client.getAllPosts("8153fddd", movieId);
			System.out.println(post.toString());
		return post;
	}
	
	public GetPageMoviesDTO findMovies(Integer pageId, Pageable pageable) {
			GetPageMoviesDTO page = clientMovies.getAllMovies("8153fddd", "movie", pageId);
		return page;
	}
	
	
	public GetMoviePropDTO chooseMovie() {
		
		String imdbRating = "N/A",imdbVotes = "N/A";
		GetMoviePropDTO movieProp = null ;
		
		while (imdbRating.equals("N/A") || imdbVotes.equals("N/A")) {
			
			int numPageMovies = random.randomizador(516);
			int movie = random.randomizador(10);
			
			GetPageMoviesDTO PageMoviesRandom = findMovies(numPageMovies, null);
			GetMoviesDTO[] pageMovies = PageMoviesRandom.getSearch();
			String movieImdbId = pageMovies[movie].getImdbID();
			GetMoviePropDTO moviePropValidator = findMovieProperties(movieImdbId);
			movieProp = moviePropValidator;
			imdbRating = moviePropValidator.getImdbRating();
			imdbVotes = moviePropValidator.getImdbVotes();
		}
		
		String[] imdbVotesValid = movieProp.getImdbVotes().split(",");
		movieProp.setImdbVotes(imdbVotesValid[0]);
		return movieProp;
	}

	@Transactional
	public Quizz validateAnswerAndNewQuizz(Long quizzId, Long answerId, newGameDTO gameDto) {
		
		Boolean result = false;
		Quizz quizz = repository.getOne(quizzId);
		
		if (quizz.getAnswer() == answerId) {
			gameDto.setResponse("Parabéns! você acertou a ultima resposta, ganhou 1 ponto, agora, será que acerta essa ?");
			result = true;
			quizz.setResult(result);
			repository.save(quizz);
		}else {
			gameDto.setResponse("ERRRROOU! você errou o ultimo quizz, não marcou ponto, agora, será que consegue acertar essa ?");
		}
		
		Game game = gameService.UpdateGame(gameDto.getId(), result);
		
		if (game.getOpenGame() == true) {
			GetMoviePropDTO movie1 = chooseMovie();
			GetMoviePropDTO movie2 = chooseMovie();
			
			//Valida se o novo par de filmes são iguais aos anteriores ou iguais entre si
			while (movie1.getImdbID() == movie2.getImdbID() || (quizz.getMovie1() == movie1.getImdbID() && quizz.getMovie2() == movie2.getImdbID())
					|| (quizz.getMovie1() == movie2.getImdbID() && quizz.getMovie2() == movie1.getImdbID()) ) {
				movie2 = chooseMovie();
			}
			
			Quizz newQuizz = validatePointsAndSave(movie1, movie2, gameDto.getId());
			return newQuizz;
		}else {
			gameDto.setResponse("ACABOU! você errou o 3º quizz e perdeu o jogo!, o seu score foi de : " + game.getScore() + ", inicie um novo jogo para tentar novamente!");
			gameDto.setOpenGame(game.getOpenGame());
			rankingService.saveGame(game);
			gameService.finishGame(game);
			return new Quizz();
		}
		
	}
	
	@Transactional
	public Quizz validatePointsAndSave(GetMoviePropDTO movie1, GetMoviePropDTO movie2, Long gameId) {
		
		Integer movie1Points = (int) (Double.valueOf(movie1.getImdbRating())*Float.valueOf(movie1.getImdbVotes()));
		Integer movie2Points = (int) (Double.valueOf(movie2.getImdbRating())*Float.valueOf(movie2.getImdbVotes()));
		Long answer = movie1Points > movie2Points ? 1L : 2L;
		
		Game gameCurrently = gameRepository.getOne(gameId);
		Quizz quizz = new Quizz(gameCurrently, movie1.getImdbID(), movie2.getImdbID(), answer, false);
		
		repository.save(quizz);
		return quizz;
	}
	
	
}
