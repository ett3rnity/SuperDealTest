package alexanderivanets.superdealtest.view;

import android.content.res.Configuration;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import alexanderivanets.superdealtest.R;
import alexanderivanets.superdealtest.adapter.OrganizationAdapter;
import alexanderivanets.superdealtest.model.Config;
import alexanderivanets.superdealtest.model.OrgCard;
import alexanderivanets.superdealtest.presenter.IPresenter;
import alexanderivanets.superdealtest.presenter.MainActivityPImpl;

public class MainActivity extends AppCompatActivity implements IActivity {

    private IPresenter presenter;
    private EditText input;
    private RecyclerView recyclerView;
    private ArrayList<OrgCard> organizations;
    private int orientationMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        orientationMode = getResources().getConfiguration().orientation;
        initViews();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void initViews(){
        presenter = new MainActivityPImpl(this, this);
        input = (EditText) findViewById( R.id.et_input_main );
        recyclerView = (RecyclerView) findViewById( R.id.rv_main );
        input.addTextChangedListener(inputWatcher);
        GridLayoutManager glm;

        if (orientationMode == Configuration.ORIENTATION_PORTRAIT){
            glm = new GridLayoutManager(this, 1);
        }
        else {
            glm = new GridLayoutManager(this, 2);
        }
        recyclerView.setLayoutManager(glm);



    }

    private TextWatcher inputWatcher = new TextWatcher() {
        Handler handler = new Handler(Looper.getMainLooper());
        Runnable runnable;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            final String string =  new String(s.toString());
            if (s.length() >= Config.ET_MIN) {
                handler.removeCallbacks(runnable);
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        presenter.onGetInfo(string);
                    }
                };
                handler.postDelayed(runnable, 700);
            }
        }
    };


    @Override
    public void showInfo(OrgCard card) {
        organizations = new ArrayList<>();
        organizations.add(card);

        recyclerView.setAdapter(new OrganizationAdapter(this, organizations));
        recyclerView.getAdapter().notifyDataSetChanged();

    }

    @Override
    public void showError(String e) {
        Toast.makeText(this, e, Toast.LENGTH_SHORT).show();
    }

}
