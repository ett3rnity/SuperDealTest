package alexanderivanets.superdealtest.model;

/**
 * Created by alexander on 29.09.17.
 */

public class RepoCard {
    private String header;
    private String description;

    public RepoCard(String header, String description){
        this.description = description;
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public String getHeader() {
        return header;
    }
}
