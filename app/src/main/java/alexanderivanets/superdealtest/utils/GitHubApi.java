package alexanderivanets.superdealtest.utils;

import java.util.List;

import alexanderivanets.superdealtest.model.OrgResponse;
import alexanderivanets.superdealtest.model.repomodel.RepoResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by alexander on 29.09.17.
 */

public interface GitHubApi {

    @GET("/orgs/{mOrg}")
    Observable<OrgResponse> getOrganization(@Path("mOrg") String mOrg);

    @GET("orgs/{mOrg}/repos")
    Observable<List<RepoResponse>> getRepositories(@Path("mOrg") String mOrg,
                                                   @Query("page") String mPage);

}
