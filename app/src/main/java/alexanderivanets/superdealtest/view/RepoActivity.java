package alexanderivanets.superdealtest.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
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
    private int orientation;
    private String orgName;
    private RepoAdapter adapter;
    private int orgRepos;

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
        presenter.onGetInfo(orgName);
    }

    @Override
    public void onShowInfo(ArrayList<RepoCard> cards) {
        if (adapter == null){
            adapter = new RepoAdapter(this, cards);
        }else {
            adapter.addInfo(cards);
        }
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }


    private void initViews(){
        backBtn = (ImageView) findViewById( R.id.btn_repos_main );
        header = (TextView) findViewById( R.id.tv_header_repos );
        recyclerView = (RecyclerView) findViewById(R.id.rv_repos);

        GridLayoutManager glm;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE){
            glm = new GridLayoutManager(this, 2);
        } else{
            glm = new GridLayoutManager(this, 1);
        }

        recyclerView.setLayoutManager(glm);
        recyclerView.setAdapter(new BaseAdapter());

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        presenter = new RepoPresenter(this);
    }

    private void getMainIntent(){
        Intent intent = getIntent();
        orgName = intent.getExtras().getString("orgName");
        orgRepos = intent.getExtras().getInt("orgRepos");
    }


}
