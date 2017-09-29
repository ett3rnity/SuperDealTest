package alexanderivanets.superdealtest.utils;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import alexanderivanets.superdealtest.model.Config;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alexander on 29.09.17.
 */

public class RetrofitSingleton {
    private static Retrofit retrofit;
    private static GitHubApi api;

    public static GitHubApi getApi(){
        if (api == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Config.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            api = retrofit.create(GitHubApi.class);
        }
        return api;
    }
}
