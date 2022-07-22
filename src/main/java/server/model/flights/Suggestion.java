package server.model.flights;

public class Suggestion {
    /**
     * Class used for frontend city suggestions
     */
    String label;

    public Suggestion(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
