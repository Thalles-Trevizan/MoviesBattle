package com.letscode.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.letscode.dto.GetPageMoviesDTO;

@FeignClient(name = "GetPageMovies", url = "https://www.omdbapi.com")
public interface GetClientMovies {

	@GetMapping
	GetPageMoviesDTO getAllMovies(@RequestParam(value = "apikey", defaultValue = "8153fddd") String apikey,
			@RequestParam(value = "s", defaultValue = "movie") String movie,
			@RequestParam(value = "page", defaultValue = "0") Integer page);
}
