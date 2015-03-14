package theterminal.curo.Fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;

import theterminal.curo.Adapter.ConversationAdapter;
import theterminal.curo.Model.Message;
import theterminal.curo.R;


/**
 * Created by Radu on 2015-03-14.
 */
/**
        * A simple {@link Fragment} subclass.
        * Uses Singleton Pattern
        * make it like the message inbox on a phone
        */

public class Conversation extends Fragment {

    /* Fields */

    //current instance of Inbox, null when not instantiated
    private static volatile Conversation instance;

    //List of messages to Display
    private ArrayList<Message> mMessages;

    //ListView where messages are displayed
    private ListView mListView;

    /**
     * Singleton pattern instantiation
     *
     * @return current instance of Inbox if instance is not null, else create new instance
     */
    public static Conversation getInstance(){

        if(instance == null)
            instance = new Conversation();

        return instance;
    }

    public Conversation() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_conversation, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceStates){
        super.onActivityCreated(savedInstanceStates);

        mMessages = getMessages();

        //assign ListView
        mListView = (ListView) getActivity().findViewById(R.id.conversation_listview);


        //instantiate adapter
        ConversationAdapter adapter = new ConversationAdapter(getActivity(),mMessages);

    }

    static ArrayList<Message> getMessages()
    {
// this should call the firebase database and get the data from there
        ArrayList messages = new ArrayList<Message>();
        Message m= new Message();
        Message m1= new Message();
        m1.setSender("Vaastav Anand");
        m1.setReceiver("Radu Nesiu");
        messages.add(m);
        messages.add(m1);


        return messages;
    }




}

