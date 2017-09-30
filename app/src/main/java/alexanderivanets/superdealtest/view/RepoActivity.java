package alexanderivanets.superdealtest.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import alexanderivanets.superdealtest.R;
import alexanderivanets.superdealtest.adapter.BaseAdapter;
import alexanderivanets.superdealtest.adapter.RepoAdapter;
import alexanderivanets.superdealtest.model.RepoCard;
import alexanderivanets.superdealtest.presenter.IRepoPresenter;
import alexanderivanets.superdealtest.presenter.RepoPresenter;

/**
 * Created by alexander on 29.09.17.
 */

public class RepoActivity extends AppCompatActivity implements IRepoActivity {

    private ImageView backBtn;
    private TextView header;
    private RecyclerView recyclerView;
    private RelativeLayout repoLayout;
    private int orientation;
    private String orgName;
    private RepoAdapter adapter;
    private int orgRepos;
    private static int page = 1;

    private IRepoPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);
        orientation = getResources().getConfiguration().orientation;
        getMainIntent();
        initViews();

    }

    @Override
    protected void onStart() {
        super.onStart();
        String headerString = orgName +
                " Repositories(" +
                orgRepos +
                ")";
        header.setText(headerString);
        presenter.onGetInfo(orgName, String.valueOf(page));
    }

    @Override
    public void onShowInfo(ArrayList<RepoCard> cards) {
        if (adapter == null){
            adapter = new RepoAdapter(this, cards);
            recyclerView.setAdapter(adapter);
        }else {
            adapter.addInfo(cards);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onShowError(String e) {
        Snackbar snackbar = Snackbar.make(repoLayout, e, BaseTransientBottomBar.LENGTH_SHORT);
        snackbar.show();
    }


    private void initViews(){
        backBtn = (ImageView) findViewById( R.id.btn_repos_main );
        header = (TextView) findViewById( R.id.tv_header_repos );
        recyclerView = (RecyclerView) findViewById(R.id.rv_repos);
        repoLayout = (RelativeLayout) findViewById(R.id.rl_repo);

        GridLayoutManager glm;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE){
            glm = new GridLayoutManager(this, 2);
        } else{
            glm = new GridLayoutManager(this, 1);
        }

        recyclerView.setLayoutManager(glm);
        recyclerView.setAdapter(new BaseAdapter());
        recyclerView.addOnScrollListener(scrollListener);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        presenter = new RepoPresenter(this, this);
    }

    private void getMainIntent(){
        Intent intent = getIntent();
        orgName = intent.getExtras().getString("orgName");
        orgRepos = intent.getExtras().getInt("orgRepos");
    }


    private RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            GridLayoutManager glm = (GridLayoutManager) recyclerView.getLayoutManager();
            int lastVisPos = glm.findLastCompletelyVisibleItemPosition();
            if (lastVisPos != RecyclerView.NO_POSITION
                    && lastVisPos == (adapter.getItemCount()-1) ) {
                    page++;
                    presenter.onGetInfo(orgName, String.valueOf(page));
            }
        }
    };


}
