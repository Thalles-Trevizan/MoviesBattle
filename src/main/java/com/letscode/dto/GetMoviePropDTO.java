package com.letscode.dto;

import java.util.Arrays;

public class GetMoviePropDTO {

	private String Title;
	private String Year;
	private String Rated;
	private String Released;
	private String Runtime;
	private String Genre;
	private String Director;
	private String Writer;
	private String Actors;
	private String Plot;
	private String Language;
	private String Country;
	private String Awards;
	private String Poster;
	private Object[] Rating;
	private String Metascore;
	private String ImdbRating;
	private String ImdbVotes;
	private String ImdbID;
	private String Type;
	private String DVD;
	private String BoxOffice;
	private String Production;
	private String Website;
	private String Response;

	public GetMoviePropDTO(String Title, String Year, String Rated, String Released, String Runtime, String Genre,
			String Director, String Writer, String Actors, String Plot, String Language, String Country, String Awards,
			String Poster, Object[] Rating, String Metascore, String imdbRating, String imdbVotes, String imdbId,
			String Type, String DVD, String BoxOffice, String Production, String Website, String Response) {
		super();
		this.Title = Title;
		this.Year = Year;
		this.Rated = Rated;
		this.Released = Released;
		this.Runtime = Runtime;
		this.Genre = Genre;
		this.Director = Director;
		this.Writer = Writer;
		this.Actors = Actors;
		this.Plot = Plot;
		this.Language = Language;
		this.Country = Country;
		this.Awards = Awards;
		this.Poster = Poster;
		this.Rating = Rating;
		this.Metascore = Metascore;
		ImdbRating = imdbRating;
		ImdbVotes = imdbVotes;
		ImdbID = imdbId;
		this.Type = Type;
		this.DVD = DVD;
		this.BoxOffice = BoxOffice;
		this.Production = Production;
		this.Website = Website;
		this.Response = Response;
	}



	public String gettitle() {
		return Title;
	}

	public void setTitle(String Title) {
		this.Title = Title;
	}

	public String getYear() {
		return Year;
	}

	public void setYear(String Year) {
		this.Year = Year;
	}

	public String getRated() {
		return Rated;
	}

	public void setRated(String Rated) {
		this.Rated = Rated;
	}

	public String getReleased() {
		return Released;
	}

	public void setReleased(String Released) {
		this.Released = Released;
	}

	public String getRuntime() {
		return Runtime;
	}

	public void setRuntime(String Runtime) {
		this.Runtime = Runtime;
	}

	public String getGenre() {
		return Genre;
	}

	public void setGenre(String Genre) {
		this.Genre = Genre;
	}

	public String getDirector() {
		return Director;
	}

	public void setDirector(String Director) {
		this.Director = Director;
	}

	public String getWriter() {
		return Writer;
	}

	public void setWriter(String Writer) {
		this.Writer = Writer;
	}

	public String getActors() {
		return Actors;
	}

	public void setActors(String Actors) {
		this.Actors = Actors;
	}

	public String getPlot() {
		return Plot;
	}

	public void setPlot(String Plot) {
		this.Plot = Plot;
	}

	public String getLanguage() {
		return Language;
	}

	public void setLanguage(String Language) {
		this.Language = Language;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String Country) {
		this.Country = Country;
	}

	public String getAwards() {
		return Awards;
	}

	public void setAwards(String Awards) {
		this.Awards = Awards;
	}

	public String getPoster() {
		return Poster;
	}

	public void setPoster(String Poster) {
		this.Poster = Poster;
	}

	public Object[] getRating() {
		return Rating;
	}

	public void setRating(Object[] Rating) {
		this.Rating = Rating;
	}

	public String getMetascore() {
		return Metascore;
	}

	public void setMetascore(String Metascore) {
		this.Metascore = Metascore;
	}

	public String getImdbRating() {
		return ImdbRating;
	}

	public void setImdbRating(String imdbRating) {
		ImdbRating = imdbRating;
	}

	public String getImdbVotes() {
		return ImdbVotes;
	}

	public void setImdbVotes(String imdbVotes) {
		ImdbVotes = imdbVotes;
	}

	public String getImdbID() {
		return ImdbID;
	}

	public void setImdbID(String imdbId) {
		ImdbID = imdbId;
	}

	public String getType() {
		return Type;
	}

	public void setType(String Type) {
		this.Type = Type;
	}

	public String getDVD() {
		return DVD;
	}

	public void setDVD(String DVD) {
		this.DVD = DVD;
	}

	public String getBoxOffice() {
		return BoxOffice;
	}

	public void setBoxOffice(String BoxOffice) {
		this.BoxOffice = BoxOffice;
	}

	public String getProduction() {
		return Production;
	}

	public void setProduction(String Production) {
		this.Production = Production;
	}

	public String getWebsite() {
		return Website;
	}

	public void setWebsite(String Website) {
		this.Website = Website;
	}

	public String getResponse() {
		return Response;
	}

	public void setResponse(String Response) {
		this.Response = Response;
	}

	@Override
	public String toString() {
		return "PostMoviesDTO [Title=" + Title + ", Year=" + Year + ", Rated=" + Rated + ", Released=" + Released
				+ ", Runtime=" + Runtime + ", Genre=" + Genre + ", Director=" + Director + ", Writer=" + Writer
				+ ", Actors=" + Actors + ", Plot=" + Plot + ", Language=" + Language + ", Country=" + Country
				+ ", Awards=" + Awards + ", Poster=" + Poster + ", Rating=" + Arrays.toString(Rating) + ", Metascore="
				+ Metascore + ", ImdbRating=" + ImdbRating + ", ImdbVotes=" + ImdbVotes + ", ImdbID=" + ImdbID
				+ ", Type=" + Type + ", DVD=" + DVD + ", BoxOffice=" + BoxOffice + ", Production=" + Production
				+ ", Website=" + Website + ", Response=" + Response + "]";
	}
	
	public String toString2() {
		return "Title=" + Title + "ImdbRating=" + ImdbRating + ", ImdbVotes=" + ImdbVotes + ", ImdbID=" + ImdbID +
				"]";
	}
}
