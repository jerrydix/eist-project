package server.model.entertainment.movies;

public class Movie {
    private final String name;
    private final MovieTag tag;
    private final String description;
    private final String coverPath;
    private final int ageRating;
    Movie(MovieTag tag, String name, String description, int ageRating, String coverPath) {
        this.tag = tag;
        this.name = name;
        this.description = description;
        this.ageRating = ageRating;
        this.coverPath = coverPath;
    }

    public String getName() {
        return name;
    }

    public MovieTag getTag() {
        return tag;
    }

    public String getDescription() {
        return description;
    }

    public int getAgeRating() {
        return ageRating;
    }

    public String getCoverPath() {
        return coverPath;
    }
}
