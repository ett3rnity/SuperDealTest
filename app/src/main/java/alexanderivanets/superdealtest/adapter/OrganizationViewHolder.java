package alexanderivanets.superdealtest.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import alexanderivanets.superdealtest.R;

/**
 * Created by alexander on 29.09.17.
 */

public class OrganizationViewHolder extends RecyclerView.ViewHolder {

    protected ImageView orgImage;
    protected TextView orgName;
    protected TextView orgLocation;
    protected TextView orgBlog;


    public OrganizationViewHolder(View itemView) {
        super(itemView);
        orgName = (TextView) itemView.findViewById( R.id.tv_item_org_name );
        orgLocation = (TextView) itemView.findViewById( R.id.tv_item_org_location );
        orgBlog = (TextView) itemView.findViewById( R.id.tv_item_org_blog );
        orgImage = (ImageView) itemView.findViewById(R.id.iv_item_org);

    }

}
