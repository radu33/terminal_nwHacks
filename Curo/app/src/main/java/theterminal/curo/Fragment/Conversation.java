package theterminal.curo.Fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import theterminal.curo.Adapter.ConversationAdapter;
import theterminal.curo.Model.Message;
import theterminal.curo.Model.Minion;
import theterminal.curo.R;
import android.app.ListActivity;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;


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
    // TODO: change this to our own url
    private static final String FIREBASE_URL = "https://android-chat.firebaseio-demo.com";
    private String receiver;

    //List of messages to Display
    private ArrayList<Message> mMessages;

    //ListView where messages are displayed
    private ListView mListView;
    private String mUsername;
    private Firebase mFirebaseRef;
    private ValueEventListener mConnectedListener;
    private ConversationAdapter mChatListAdapter;
    private Button send_btn;

    /**
     * Singleton pattern instantiation
     *
     * @return current instance of Inbox if instance is not null, else create new instance
     */
    public static Conversation getInstance(Minion minion){

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

        // Make sure we have a mUsername
        setupUsername();


        getActivity().setTitle("Chatting as " + mUsername);

        // Setup our Firebase mFirebaseRef
        mFirebaseRef = new Firebase(FIREBASE_URL).child("conversations");

        // Setup our input methods. Enter key on the keyboard or pushing the send button
        EditText inputText = (EditText) getActivity().findViewById(R.id.conversation_msg);
        inputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_NULL && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    sendMessage();
                }
                return true;
            }
        });

        getActivity().findViewById(R.id.conversation_send_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });


        return inflater.inflate(R.layout.fragment_conversation, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        send_btn = (Button) getActivity().findViewById(R.id.conversation_send_button);

        //sets up click listener
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == send_btn.getId()) {
                    sendMessage();
                    mChatListAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceStates){
        super.onActivityCreated(savedInstanceStates);

        getActivity().setTitle("Messaging with " + receiver);

        mMessages = getMessages();

        //assign ListView
        mListView = (ListView) getActivity().findViewById(R.id.conversation_listview);


        //instantiate adapter
        ConversationAdapter adapter = new ConversationAdapter(getActivity(),mMessages);
        mListView.setAdapter(adapter);

    }
    @Override
    public void onStop() {
        super.onStop();
        mFirebaseRef.getRoot().child(".info/connected").removeEventListener(mConnectedListener);
 //       mChatListAdapter.cleanup();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater){
        MenuItem closeFragment = menu.add("close Fragment");
        closeFragment.setIcon(getActivity().getResources().getDrawable(R.drawable.ic_action_remove));
        super.onCreateOptionsMenu(menu, menuInflater);


    }

    static ArrayList<Message> getMessages()
    {
// this should call the firebase database and get the data from there

        ArrayList messagesRaduAvo = new ArrayList<Message>();
        ArrayList messagesAvoRadu = new ArrayList<Message>();

        // query the list of messages from Radu to Avo


        // query the list of messages from Avo to Radu

//  TODO: switch here !!!
        messagesAvoRadu.addAll(messagesRaduAvo);
        return messagesAvoRadu;
        //messagesRaduAvo.addAll(messagesAvoRadu);
        //return messagesRaduAvo;
    }

    private void setupUsername() {
        // get this from Firebase by querying the email

        //  TODO: switch here !!!
        mUsername = "Radu Nesiu";
        receiver= "Avetis Muradyan";
        //mUsername = "Avetis Muradyan"; // for the second instance
        //receiver= "Radu Nesiu";
        if (mUsername == null || mUsername.isEmpty()) {
            mUsername = "DefaultUser";
        }
    }


    private void sendMessage() {
        EditText inputText = (EditText) getActivity().findViewById(R.id.conversation_msg);
        String input = inputText.getText().toString();
        if (!input.equals("")) {

            Firebase ref = mFirebaseRef.child("conversations").child(mUsername).child(receiver);
            Map<String, Message> message = new HashMap<String, Message>();
            Message m= new Message();
            message.put("message", m);

            ref.push().setValue(message);



            Message msg = new Message(input, mUsername,receiver);
            // Create a new, auto-generated child of that chat location, and save our chat data there

            inputText.setText("");
        }
    }

}

