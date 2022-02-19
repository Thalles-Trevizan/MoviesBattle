package com.letscode.dto;

import java.util.Arrays;

public class GetPageMoviesDTO {

	private GetMoviesDTO[] Search;
	private String totalResults;
	private String Response;

	public GetPageMoviesDTO(GetMoviesDTO[] Search, String totalResults, String Response) {
		super();
		this.Search = Search;
		this.totalResults = totalResults;
		this.Response = Response;
	}

	public GetMoviesDTO[] getSearch() {
		return Search;
	}

	public void setSearch(GetMoviesDTO[] Search) {
		this.Search = Search;
	}

	public String getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(String totalResults) {
		this.totalResults = totalResults;
	}

	public String getResponse() {
		return Response;
	}

	public void setResponse(String Response) {
		this.Response = Response;
	}

	@Override
	public String toString() {
		return "GetPageMoviesDTO [Search=" + Arrays.toString(Search) + ", totalResults=" + totalResults + ", Response="
				+ Response + "]";
	}
}
