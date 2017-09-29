package alexanderivanets.superdealtest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import alexanderivanets.superdealtest.R;
import alexanderivanets.superdealtest.model.RepoCard;

/**
 * Created by alexander on 29.09.17.
 */

public class RepoAdapter extends RecyclerView.Adapter<RepoViewHolder> {
    private ArrayList<RepoCard> cards;
    private Context context;

    public RepoAdapter(Context context, ArrayList<RepoCard> cards){
        this.context = context;
        this.cards = cards;
    }

    public void addInfo(ArrayList<RepoCard> cards){
        this.cards.addAll(cards);
    }
    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repos, parent, false);
        return new RepoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {
        holder.description.setText(cards.get(position).getDescription());
        holder.name.setText(cards.get(position).getHeader());
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}
