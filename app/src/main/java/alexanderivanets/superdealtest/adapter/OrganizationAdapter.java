package alexanderivanets.superdealtest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import alexanderivanets.superdealtest.R;
import alexanderivanets.superdealtest.model.OrgCard;

/**
 * Created by alexander on 29.09.17.
 */

public class OrganizationAdapter extends RecyclerView.Adapter<OrganizationViewHolder> {
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
        OrgCard card = orgCards.get( position );
        holder.orgName.setText( card.getOrgName() );
        holder.orgBlog.setText( card.getOrgBlog() );
        holder.orgLocation.setText( card.getOrgLocation() );

        Picasso.with(context).load( card.getImagePath() )
                .into(holder.orgImage);

        holder.clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };

    }

    @Override
    public int getItemCount() {
        return orgCards.size();
    }
}
