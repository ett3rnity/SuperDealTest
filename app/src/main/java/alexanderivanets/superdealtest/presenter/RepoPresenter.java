package alexanderivanets.superdealtest.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import alexanderivanets.superdealtest.BuildConfig;
import alexanderivanets.superdealtest.model.OrgCard;
import alexanderivanets.superdealtest.model.OrgResponse;
import alexanderivanets.superdealtest.model.RepoCard;
import alexanderivanets.superdealtest.model.repomodel.RepoResponse;
import alexanderivanets.superdealtest.utils.NetworkState;
import alexanderivanets.superdealtest.utils.RetrofitSingleton;
import alexanderivanets.superdealtest.view.IRepoActivity;
import alexanderivanets.superdealtest.view.RepoActivity;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by alexander on 29.09.17.
 */

public class RepoPresenter implements IRepoPresenter {
    private IRepoActivity view;

    public RepoPresenter(RepoActivity view){
        this.view = view;
    }

    @Override
    public void onGetInfo(String orgName) {
        Observable<List<RepoResponse>> observable = RetrofitSingleton.getApi().getRepositories(orgName);
        observable.
                subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<List<RepoResponse>, ArrayList<RepoCard>>() {
                    @Override
                    public ArrayList<RepoCard> apply(@NonNull List<RepoResponse> repoResponses)
                            throws Exception {
                       ArrayList<RepoCard> cards = new ArrayList<>();
                        for (RepoResponse response:
                             repoResponses) {
                            RepoCard card =
                                    new RepoCard(response.getName(), response.getDescription());
                            cards.add(card);
                        }
                        return cards;
                    }
                })
                .subscribe(new Consumer<ArrayList<RepoCard>>() {
                               @Override
                               public void accept(@NonNull ArrayList<RepoCard> repoCards) throws Exception {
                                    view.onShowInfo(repoCards);
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                Log.v("ERROR", throwable.getLocalizedMessage().substring(0,9));
                            }
                        });

    }
}
