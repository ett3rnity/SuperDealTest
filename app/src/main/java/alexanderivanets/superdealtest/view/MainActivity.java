package alexanderivanets.superdealtest.view;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.TextKeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import alexanderivanets.superdealtest.R;
import alexanderivanets.superdealtest.adapter.OrganizationAdapter;
import alexanderivanets.superdealtest.model.Config;
import alexanderivanets.superdealtest.model.OrgCard;
import alexanderivanets.superdealtest.presenter.IPresenter;
import alexanderivanets.superdealtest.presenter.MainPresenter;
import alexanderivanets.superdealtest.utils.NetworkState;

public class MainActivity extends AppCompatActivity implements IActivity {

    private RelativeLayout mainLayout;
    private EditText input;
    private RecyclerView recyclerView;

    private IPresenter presenter;
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



    @Override
    public void showInfo(OrgCard card) {
        organizations = new ArrayList<>();
        organizations.add(card);

        recyclerView.setAdapter(new OrganizationAdapter(this, organizations));
        recyclerView.getAdapter().notifyDataSetChanged();

    }

    @Override
    public void showError(String e) {
        Snackbar snackbar =
                Snackbar.make(mainLayout, e, BaseTransientBottomBar.LENGTH_LONG);
        snackbar.show();
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
            final String string = s.toString();
            if (s.length() >= Config.ET_MIN) {
                if (NetworkState.isConnected(MainActivity.this)){
                    handler.removeCallbacks(runnable);
                    runnable = new Runnable() {
                        @Override
                        public void run() {
                            presenter.onGetInfo(string);
                        }
                    };
                    handler.postDelayed(runnable, 700);
                } else {
                    showError("No internet connection!");
                }
            }
        }
    };

    private void initViews(){
        presenter = new MainPresenter(this, this);

        input = (EditText) findViewById( R.id.et_input_main );
        recyclerView = (RecyclerView) findViewById( R.id.rv_main );
        mainLayout = (RelativeLayout) findViewById(R.id.rl_main);

        input.addTextChangedListener(inputWatcher);
        input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
                    input.clearFocus();
                    return true;
                }
                return false;
            }
        });
        GridLayoutManager glm;

        if (orientationMode == Configuration.ORIENTATION_PORTRAIT){
            glm = new GridLayoutManager(this, 1);
        }
        else {
            glm = new GridLayoutManager(this, 2);
        }
        recyclerView.setLayoutManager(glm);

    }


}
