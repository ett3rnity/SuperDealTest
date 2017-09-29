package alexanderivanets.superdealtest.view;

import alexanderivanets.superdealtest.model.OrgCard;

/**
 * Created by alexander on 29.09.17.
 */

public interface IActivity {
    void showInfo(OrgCard card);
    void showError(String e);
}
