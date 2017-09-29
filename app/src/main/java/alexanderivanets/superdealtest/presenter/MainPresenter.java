package alexanderivanets.superdealtest.presenter;

import android.content.Context;

import alexanderivanets.superdealtest.utils.NetworkState;
import alexanderivanets.superdealtest.model.OrgCard;
import alexanderivanets.superdealtest.model.OrgResponse;
import alexanderivanets.superdealtest.utils.RetrofitSingleton;
import alexanderivanets.superdealtest.view.MainActivity;
import alexanderivanets.superdealtest.view.IActivity;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by alexander on 29.09.17.
 */

public class MainPresenter implements IPresenter {

    private IActivity view;
    private Context context;

    public MainPresenter(MainActivity view, Context context){
        this.view = view;
        this.context = context;
    }

    @Override
    public void onGetInfo(String input) {

        Observable<OrgResponse> observable = RetrofitSingleton.getApi().getOrganization(input);
        observable.
                subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<OrgResponse, OrgCard>() {
                    @Override
                    public OrgCard apply(@NonNull OrgResponse orgResponse) throws Exception {
                        return new OrgCard(orgResponse.getAvatarUrl(), orgResponse.getName(),
                                orgResponse.getLocation(),  orgResponse.getBlog(),
                                orgResponse.getReposUrl(), orgResponse.getPublicRepos());
                    }
                })
                .subscribe(new Consumer<OrgCard>() {
                               @Override
                               public void accept(@NonNull OrgCard card) throws Exception {
                                view.showInfo(card);
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                if (!NetworkState.isConnected(context)){
                                    view.showError("No Internet Connection!");
                                } else {
                                    view.showError(throwable.getLocalizedMessage());
                                }
                            }
                        });



    }



}
