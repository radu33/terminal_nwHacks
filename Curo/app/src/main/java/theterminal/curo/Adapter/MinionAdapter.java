package theterminal.curo.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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


    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View rowView = convertView;

        if (convertView == null){

            //initialise inflater to inflate row layout
            LayoutInflater inflater =
                    (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //inflate XML row layout of R.layout.live_game_list_row, activating UI sub Views
            rowView = inflater.inflate(R.layout.minion_list_row, parent, false);

            TextView minionName = (TextView) rowView.findViewById(R.id.minion_list_row_minon_name);
            TextView taskName = (TextView) rowView.findViewById(R.id.minion_list_row_task_name);
            ImageView completionIcon = (ImageView) rowView.
                    findViewById(R.id.minion_list_row_completion_icon);

            //set minion and task names
            minionName.setText(mMinions.get(position).getName());
            taskName.setText(mMinions.get(position).getCurrentTask().getName());

            //TODO: complete icons
            //Drawable icon = getIcon();

        }

        return rowView;
    }



}
