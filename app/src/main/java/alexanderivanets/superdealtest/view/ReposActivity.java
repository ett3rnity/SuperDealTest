package alexanderivanets.superdealtest.view;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import alexanderivanets.superdealtest.R;
import alexanderivanets.superdealtest.model.OrgCard;

/**
 * Created by alexander on 29.09.17.
 */

public class ReposActivity extends AppCompatActivity {

    private ImageView backBtn;
    private TextView header;
    private RecyclerView recyclerView;
    private int orientation;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);
        orientation = getResources().getConfiguration().orientation;
        initViews();

    }


    private void initViews(){
        backBtn = (ImageView) findViewById( R.id.btn_repos_main );
        header = (TextView) findViewById( R.id.tv_header_repos );
        GridLayoutManager glm;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE){
            glm = new GridLayoutManager(this, 2);
        } else{
            glm = new GridLayoutManager(this, 1);
        }
        recyclerView.setLayoutManager(glm);
    }


}
