package com.letscode.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.letscode.dto.GetMoviePropDTO;

@FeignClient(name = "getMovie", url = "https://www.omdbapi.com")
public interface GetClientMovieProp {

	@GetMapping
	GetMoviePropDTO getAllPosts(@RequestParam(value = "apikey", defaultValue = "8153fddd") String apikey,
			@RequestParam(value = "i", defaultValue = "tt0327451") String imdbId);
}
