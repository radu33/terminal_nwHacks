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
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.HashMap;
import java.util.TimeZone;

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
    private static String receiver;

    //List of messages to Display
    private ArrayList<Message> mMessages;

    //ListView where messages are displayed
    private ListView mListView;
    private static String mUsername;
    private Firebase mFirebaseRef;
    private ValueEventListener mConnectedListener;
    private ConversationAdapter mChatListAdapter;
    private Button send_btn;

    public Conversation() {

    }

    /**
     * Singleton pattern instantiation
     *
     * @return current instance of Inbox if instance is not null, else create new instance
     */
    public static Conversation getInstance(Minion minion){

        if(instance == null) {
            instance = new Conversation();
        }
        setupUsers(minion);
        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

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
                }
            }
        });
        mFirebaseRef = new Firebase(getActivity().getResources().getString(R.string.firebase_url));

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
        mFirebaseRef.child(".info/connected").removeEventListener(mConnectedListener);
 //       mChatListAdapter.cleanup();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater){
        MenuItem closeFragment = menu.add("close Fragment");
        closeFragment.setIcon(getActivity().getResources().getDrawable(R.drawable.ic_action_remove));
        super.onCreateOptionsMenu(menu, menuInflater);


    }

    private ArrayList<Message> getMessages()
    {
// this should call the firebase database and get the data from there

        ArrayList messages = new ArrayList<Message>();

        Firebase ref1 = mFirebaseRef.child("conversations").child(mUsername).child(receiver);
        Firebase ref2 = mFirebaseRef.child("conversations").child(receiver).child(mUsername);


        ref1.addChildEventListener(new ChildEventListener() {
            // Retrieve new posts as they are added to Firebase
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildKey) {
                Map<String, Object> newPost = (Map<String, Object>) snapshot.getValue();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
            //... ChildEventListener also defines onChildChanged, onChildRemoved,
            //    onChildMoved and onCanceled, covered in later sections.
        });

        ref2.addChildEventListener(new ChildEventListener() {
            // Retrieve new posts as they are added to Firebase
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildKey) {
                Map<String, Object> newPost = (Map<String, Object>) snapshot.getValue();
                System.out.println("Author: " + newPost.get("author"));
                System.out.println("Title: " + newPost.get("title"));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
            //... ChildEventListener also defines onChildChanged, onChildRemoved,
            //    onChildMoved and onCanceled, covered in later sections.
        });

        return messages;
    }

    private static void setupUsers(Minion m) {
        // get this from Firebase by querying the email

        //  TODO: switch here !!!
        mUsername = "Radu Nesiu";
        receiver= m.getName();
    }


    private void sendMessage() {
        EditText inputText = (EditText) getActivity().findViewById(R.id.conversation_msg);
        String input = inputText.getText().toString();
        if (!input.equals("")) {

            Firebase ref = mFirebaseRef.child("conversations").child(mUsername).child(receiver).child("message_"+ GregorianCalendar.YEAR+GregorianCalendar.MONTH+GregorianCalendar.DATE+GregorianCalendar.HOUR+GregorianCalendar.MINUTE+GregorianCalendar.SECOND);
            Map<String, Message> message = new HashMap<String, Message>();
            Message m= new Message(input);
            message.put("message", m);

            ref.setValue(m);



            Message msg = new Message(input, mUsername,receiver);
            // Create a new, auto-generated child of that chat location, and save our chat data there

            inputText.setText("");
        }
    }

}

