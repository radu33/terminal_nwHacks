package theterminal.curo.Adapter;

import android.content.Context;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import theterminal.curo.Model.Minion;
import theterminal.curo.R;

public class MinionAdapter extends ArrayAdapter<Minion>{

    /* Fields */

    private Context mContext;
    private ArrayList<Minion> mMinions;

    /* Constructors */

    public MinionAdapter(Context context, ArrayList<Minion> minions){
        super(context, R.layout.minion_list_row, minions);
        mContext = context;
        mMinions = minions;
    }



}
