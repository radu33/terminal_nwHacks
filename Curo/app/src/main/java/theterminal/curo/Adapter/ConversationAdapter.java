package theterminal.curo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
            message.setBackgroundColor(getBackground(mMessages.get(position)));

        }

        return rowView;
    }

    private int getBackground(Message m)
    {
        if(firstAuthor.isEmpty())
        {
            firstAuthor = m.getSender();
            return 1;
        }
        else
        {
            if(m.getSender()== firstAuthor) {
                return 1;
            }
        }

        return 0;
    }

}
