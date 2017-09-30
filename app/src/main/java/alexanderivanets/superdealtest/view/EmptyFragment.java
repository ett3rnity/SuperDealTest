package alexanderivanets.superdealtest.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import alexanderivanets.superdealtest.R;

/**
 * Created by alexander on 30.09.17.
 */

public class EmptyFragment  extends Fragment{

    private ImageView imageView;

    public EmptyFragment(){

    }

    public static EmptyFragment newInstance(){
        EmptyFragment emptyFragment = new EmptyFragment();
        return emptyFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_no_recent, container, false);
        imageView = (ImageView) view.findViewById(R.id.iv_no_items);
        imageView.setImageResource(R.drawable.no_items);
        return view;
    }
}
