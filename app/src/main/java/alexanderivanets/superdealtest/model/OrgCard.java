package alexanderivanets.superdealtest.model;

/**
 * Created by alexander on 29.09.17.
 */

public class OrgCard {
    private String imagePath;
    private String orgName;
    private String orgLocation;
    private String orgBlog;
    private String reposPath;


    public OrgCard(String imagePath,
                   String orgName,
                   String orgLocation,
                   String orgBlog,
                   String reposPath){
        this.imagePath = imagePath;
        this.orgBlog = orgBlog;
        this.orgLocation = orgLocation;
        this.orgName = orgName;
        this.reposPath = reposPath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getOrgBlog() {
        return orgBlog;
    }

    public String getOrgLocation() {
        return orgLocation;
    }

    public String getOrgName() {
        return orgName;
    }

    public String getReposPath() {
        return reposPath;
    }
}
