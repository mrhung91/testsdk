package com.profilegame.manager;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.cuuchau.sdk9chau.CuuChauSdk;
import com.cuuchau.sdk9chau.callback.AuthCallback;
import com.cuuchau.sdk9chau.callback.PaymentCallback;

import org.json.JSONException;
import org.json.JSONObject;


public class HomeActivity extends Activity implements View.OnClickListener {

    private Button btnCharge;
    public AuthCallback authCallback = new AuthCallback() {

        @Override
        protected void onLoginSuccess(JSONObject jsonObject) {
            try {
                Toast.makeText(getApplicationContext(), "Xin chào: " + jsonObject.getString("username"), Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onRegisterSuccess(JSONObject jsonObject) {

        }

        @Override
        protected void onLogout() {
            CuuChauSdk.showAuthPanel(authCallback);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        btnCharge = (Button) findViewById(R.id.btnCharge);
        btnCharge.setOnClickListener(this);
        findViewById(R.id.btnProfile).setOnClickListener(this);


        CuuChauSdk.sdkInitialize(this);
        CuuChauSdk.showAuthPanel(authCallback);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
        switch (id){
            case R.id.action_settings:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.btnProfile){
            CuuChauSdk.showProfilePanel();
        }else if(v.getId()== R.id.btnCharge){
            CuuChauSdk.showRechargePanel("",new PaymentCallback() {
                @Override
                public void onSuccess() {
                }
            });
        }
    }
}
