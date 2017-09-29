package alexanderivanets.superdealtest.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import alexanderivanets.superdealtest.R;
import alexanderivanets.superdealtest.model.OrgCard;
import alexanderivanets.superdealtest.view.RepoActivity;

/**
 * Created by alexander on 29.09.17.
 */

public class OrganizationAdapter extends RecyclerView.Adapter<OrganizationViewHolder>{
    private List<OrgCard> orgCards;
    private Context context;

    public OrganizationAdapter(Context context, ArrayList<OrgCard> orgCards){
        this.context = context;
        this.orgCards = orgCards;
    }

    @Override
    public OrganizationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_organizations, parent, false);
        return new OrganizationViewHolder(v);
    }

    @Override
    public void onBindViewHolder(OrganizationViewHolder holder, int position) {
        final OrgCard card = orgCards.get( holder.getAdapterPosition() );
        holder.orgName.setText( card.getOrgName() );
        holder.orgBlog.setText( card.getOrgBlog() );
        holder.orgLocation.setText( card.getOrgLocation() );

        Picasso.with(context).load( card.getImagePath() )
                .into(holder.orgImage);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RepoActivity.class);
                intent.putExtra("orgName", card.getOrgName());
                intent.putExtra("orgRepos", card.getReposNumb());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return orgCards.size();
    }
}
