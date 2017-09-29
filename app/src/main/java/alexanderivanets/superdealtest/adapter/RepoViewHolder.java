package alexanderivanets.superdealtest.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import alexanderivanets.superdealtest.R;

/**
 * Created by alexander on 29.09.17.
 */

public class RepoViewHolder extends RecyclerView.ViewHolder {
    protected TextView name;
    protected TextView description;

    public RepoViewHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.tv_item_repos_name);
        description = (TextView) itemView.findViewById(R.id.tv_item_repos_description);
    }

}
