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
	private QuizzRepository repository;

//	@Autowired
//	private GameService gameService;
	
	@Transactional
	public Quizz insertNewQuizz(Long gameId) {
	
		GetMoviePropDTO movie1 = chooseMovie();
		GetMoviePropDTO movie2 = chooseMovie();

		while (movie1.getImdbID() == movie2.getImdbID()) {
			movie2 = chooseMovie();
		}
		
		Integer movie1Points = (int) (Double.valueOf(movie1.getImdbRating())*Float.valueOf(movie1.getImdbVotes()));
		Integer movie2Points = (int) (Double.valueOf(movie2.getImdbRating())*Float.valueOf(movie2.getImdbVotes()));
		
		Integer answer = movie1Points > movie2Points ? 1 : 2;
		
		Game game = gameRepository.getOne(gameId);
		
		Quizz quizz = new Quizz(game, movie1.getImdbID(), movie2.getImdbID(), answer, false);
		repository.save(quizz);
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
		
		String imdbRating = "N/A";
		String imdbVotes = "N/A";
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

//	@Transactional
//	public Quizz validateAnswer(Long quizzId, Game userGame) {
//		
//		Quizz quizz = repository.getOne(quizzId);
//		
//		
//		
//		
//
//		
//		System.out.println(quizz.getId() + " " + quizz.getAnswer());
////		Quizz lastQuizz = quizzes.get(); 
//		
//		
//		
//		
//		return null;
//	}
}
