package com.letscode.dto;

public class GetMoviesDTO {

	private String Title;
	private String Year;
	private String ImdbID;
	private String Type;
	private String Poster;

	public GetMoviesDTO(String Title, String Year, String imdbID, String Type, String Poster) {
		super();
		this.Title = Title;
		this.Year = Year;
		ImdbID = imdbID;
		this.Type = Type;
		this.Poster = Poster;
	}

	public String getTitle() {
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

	public String getImdbID() {
		return ImdbID;
	}

	public void setImdbID(String imdbID) {
		ImdbID = imdbID;
	}

	public String getType() {
		return Type;
	}

	public void setType(String Type) {
		this.Type = Type;
	}

	public String getPoster() {
		return Poster;
	}

	public void setPoster(String Poster) {
		this.Poster = Poster;
	}

	@Override
	public String toString() {
		return "GetMoviesDTO [Title=" + Title + ", Year=" + Year + ", ImdbID=" + ImdbID + ", Type=" + Type + ", Poster="
				+ Poster + "]";
	}
}
