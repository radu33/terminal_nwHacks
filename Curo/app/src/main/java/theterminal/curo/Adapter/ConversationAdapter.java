package theterminal.curo.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import theterminal.curo.Model.Message;
import theterminal.curo.Model.Minion;
import theterminal.curo.R;

/**
 * Created by Raza on 2015-03-14.
 */
public class ConversationAdapter extends ArrayAdapter<Message> {
    /* Fields */

    private Context mContext;
    private ArrayList<Message> mMessages;
    private String firstAuthor;
    /* Constructors */

    public ConversationAdapter(Context context, ArrayList<Message> messages){
        super(context, R.layout.conversation_list_row, messages);
        mContext = context;
        mMessages = messages;
        firstAuthor="";
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View rowView = convertView;

        if (convertView == null){

            //initialise inflater to inflate row layout
            LayoutInflater inflater =
                    (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //inflate XML row layout of R.layout.conversation_list_row, activating UI sub Views
            rowView = inflater.inflate(R.layout.conversation_list_row, parent, false);

            TextView message = (TextView) rowView.findViewById(R.id.conversation_list_row_message);

            //set minion and task names
            message.setText(mMessages.get(position).getMessageBody());
            // color background based on the author
            message.setBackground(getBackground(mMessages.get(position)));


            RelativeLayout.LayoutParams params =(RelativeLayout.LayoutParams)  message.getLayoutParams();
            if(mMessages.get(position).getSender().equals(firstAuthor)) {
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            }
            else
            {
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            }

        }

        return rowView;
    }

    private Drawable getBackground(Message m)
    {
        if(firstAuthor == "")
        {
            firstAuthor = m.getSender();
            return mContext.getResources().getDrawable(R.drawable.rounded_rectangle_grey);
        }
        else
        {
            if(m.getSender()== firstAuthor) {
                return mContext.getResources().getDrawable(R.drawable.rounded_rectangle_grey);
            }
        }

        return mContext.getResources().getDrawable(R.drawable.rounded_rectangle_blue);
    }

}
