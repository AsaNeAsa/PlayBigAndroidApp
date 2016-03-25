package com.playbig.modal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.playbig.Activity.Activity_Login;
import com.playbig.Activity.Activity_aboutus;
import com.playbig.Activity.Activity_myprofile;
import com.playbig.Activity.Activity_quickplaceabet;
import com.playbig.Activity.Activity_terms;
import com.playbig.Activity.MainActivity;
import com.playbig.R;
import com.playbig.facebook.FacebookHelper;
import com.playbig.util.ConstantCodes;
import com.playbig.util.PreferenceUtility;

/**
 * Created by aalap on 12/6/15.
 */
public abstract class BaseActivity extends AppCompatActivity implements Act_ImpMethods {

    public Bundle savedInstanceState;
    protected BaseActivity mActivity;
    protected Context mApplication;
    private CallbackManager mCallbackManager;
    private FacebookHelper mFacebookHelper;
    public  PreferenceUtility preferenceUtility;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



// TODO Auto-generated method stub
        this.savedInstanceState = savedInstanceState;

        super.onCreate(savedInstanceState);
        mActivity = this;
        mApplication = getApplicationContext();
        preferenceUtility = PreferenceUtility.getSession(mActivity);
    }





    public void setContentView(int layoutResID, boolean isSuper) {
        if (isSuper) {
            super.setContentView(layoutResID);
        }
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.home) {

            Intent mainIntent = new Intent(BaseActivity.this,MainActivity.class);
            startActivity(mainIntent);
            finish();
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.myProfile) {

            if (preferenceUtility.getBoolean(ConstantCodes.PREFERENCE_KEY.IS_USER_LOGGED_IN) == true) {
                startActivity(new Intent(mActivity, Activity_myprofile.class));
                mActivity.finish();
            }else {
                Toast.makeText(mActivity, "Kindly Login First", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(mActivity, Activity_Login.class);
                startActivity(i);
            }
            return true;
        }

        //noinspection SimplifiableIfStatement
        else if (id == R.id.aboutus) {

            Intent mainIntent = new Intent(BaseActivity.this, Activity_aboutus.class);
            startActivity(mainIntent);
            finish();


            return true;
        }


        //noinspection SimplifiableIfStatement
        else if (id == R.id.terms) {

            Intent mainIntent = new Intent(BaseActivity.this,Activity_terms.class);
            startActivity(mainIntent);
            finish();


            return true;
        }


        else if (id == R.id.Logout) {


            Intent iobj = new Intent(mActivity, MainActivity.class);
            iobj.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
          //  ConstantCodes.CART_ID = null;
            PreferenceUtility preferenceUtility = PreferenceUtility.getSession(mActivity);
            preferenceUtility.clearAll();

          //  sendBroadcast();
            startActivity(iobj);


            finish();


            return true;
        }






        return super.onOptionsItemSelected(item);
    }










    @Override
    public void setContentView(int layoutResID) {
// TODO Auto-generated method stub
        try {

                super.setContentView(layoutResID);

            initVariable();
            initView();
            postInitView();
            addAdapter();
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
            showToast(e.toString());
            //Utility.showLogE(BaseActivity.class, e.toString());
        }
    }

    public void setSupportActionBar(int id) {
        super.setSupportActionBar((Toolbar) links(id));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    public View links(int res) {
        return findViewById(res);
    }

    @Override
    public void postInitView() {
// TODO Auto-generated method stub

    }

    @Override
    public Intent getIntent() {
// TODO Auto-generated method stub
        if (super.getIntent() == null) {
            return new Intent();
        } else
            return super.getIntent();
    }

    public final void showToast(String msg) {
//        if (ConstantCode.SHOW_TOAST && !TextUtils.isEmpty(msg))
            Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}


