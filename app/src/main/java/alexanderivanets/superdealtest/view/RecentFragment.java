package alexanderivanets.superdealtest.view;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import alexanderivanets.superdealtest.R;
import alexanderivanets.superdealtest.adapter.BaseAdapter;
import alexanderivanets.superdealtest.adapter.OrganizationAdapter;
import alexanderivanets.superdealtest.database.DBHelper;
import alexanderivanets.superdealtest.database.DBQueries;
import alexanderivanets.superdealtest.model.OrgCard;

public class RecentFragment extends Fragment {

    private ArrayList<OrgCard> orgCards;
    private RecyclerView recyclerView;
    private TextView header;
    private int orientationMode;
    private RecentFragmentInterface listener;
    private OrganizationAdapter adapter;


    public RecentFragment() {
    }

    public static RecentFragment newInstance() {
        RecentFragment fragment = new RecentFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_recent, container, false);
        orientationMode = getResources().getConfiguration().orientation;
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_recent);
        header = (TextView) view.findViewById(R.id.tv_header_recent);
        setRecyclerView();
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RecentFragmentInterface){
            listener = (RecentFragmentInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " implement RecentFragmentInterface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


    private void setRecyclerView(){
        GridLayoutManager glm;

        if (orientationMode == Configuration.ORIENTATION_PORTRAIT){
            glm = new GridLayoutManager(listener.getContext(), 1);
        }
        else {
            glm = new GridLayoutManager(listener.getContext(), 2);
        }
        recyclerView.setLayoutManager(glm);
        recyclerView.setAdapter(new BaseAdapter());

        orgCards = DBQueries.getEntityList(listener.getContext(), DBHelper.TABLE_NAME_RECENT);
        if (adapter == null){
            adapter = new OrganizationAdapter(listener.getContext(),orgCards);
        }
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        String headerText = getResources().getString(R.string.recent_search)
                + "(" + adapter.getItemCount() + ")";
        header.setText(headerText);
    }




    public interface RecentFragmentInterface{
        Context getContext();
    }




}
