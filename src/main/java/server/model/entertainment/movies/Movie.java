package server.model.entertainment.movies;

public class Movie {
    private final String title;
    private final String plot;
    private final String director;
    private final String poster;
    private final String ageRating;
    private final int year ;
    private final String[] genres;
    public Movie(String title, int year, String ageRating, String[] genres, String director,
          String plot, String poster) {
        this.title = title;
        this.plot = plot;
        this.year = year;
        this.ageRating = ageRating;
        this.poster = poster;
        this.genres = genres;
        this.director = director;
    }
    public String getTitle() {
        return title;
    }

    public String getPlot() {
        return plot;
    }

    public String getDirector() {
        return director;
    }

    public String getPoster() {
        return poster;
    }

    public String getAgeRating() {
        return ageRating;
    }

    public int getYear() {
        return year;
    }

    public String[] getGenres() {
        return genres;
    }
}
