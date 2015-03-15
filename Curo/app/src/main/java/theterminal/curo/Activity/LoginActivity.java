package theterminal.curo.Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import theterminal.curo.R;
import android.content.Intent;

/**
 * Created by Radu on 2015-03-14.
 */
public class LoginActivity extends ActionBarActivity {


    private EditText username = null;
    private EditText password = null;
    private Button login;

    public void login() {
        if (authenticateUser(username.getText().toString(), password.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Redirecting...",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, PanelActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Wrong Credentials",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean authenticateUser(String username, String password) {
    // this should query the Firebase database but for now it will accept only some users
        if (username.equals("avaastav@gmail.com") || username.equals("rnesiu@gmail.com")
                || username.equals("amuradyan@gmail.com") || username.equals("gbrown@gmail.com")
                || username.equals("nesiur@gmail.com")) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }

    @Override
    public void onResume() {
        super.onResume();

        login = (Button) findViewById(R.id.login_button);
        username = (EditText) findViewById(R.id.login_email);
        password = (EditText) findViewById(R.id.login_password);



        //sets up click listener
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == login.getId()) {
                    login();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_panel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
