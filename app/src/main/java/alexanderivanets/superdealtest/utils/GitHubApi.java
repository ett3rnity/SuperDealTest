package alexanderivanets.superdealtest.utils;

import alexanderivanets.superdealtest.model.OrgResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by alexander on 29.09.17.
 */

public interface GitHubApi {

    @GET("/orgs/{mOrg}")
    Observable<OrgResponse> getOrganization(@Path("mOrg") String mOrg);
}
