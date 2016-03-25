package com.playbig.Activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.playbig.R;
import com.playbig.facebook.FacebookHelper;
import com.playbig.modal.BaseActivity;
import com.playbig.network.NetworkCallbacks;
import com.playbig.network.WebServiceConfigs;
import com.playbig.network.WebUtils;
import com.playbig.util.ConstantCodes;
import com.playbig.util.PreferenceUtility;
import com.playbig.util.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;


/**
 * Created by User on 2/24/2016.
 */
public class Activity_Login extends BaseActivity implements  FacebookHelper.FacebookCallbacks , GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    PreferenceUtility preferenceUtility;

    private LinearLayout ly_loginfb_second_part,ly_login_google;
    private CallbackManager mCallbackManager;
    private FacebookHelper mFacebookHelper;
    private String email_id;
    private String first_name, last_name, defalut_password, customer_id, facebook_uid, user_input_emailid;
    private boolean flag;
    private PreferenceUtility preference_obj;
    private String sessionid;
    private TelephonyManager telephonyManager;
    String last_activity,personphoto;
    JSONObject Jobject_response;

    private static final int RC_SIGN_IN = 10;
    // Logcat tag
    private static final String TAG = "MainActivity";

    // Profile pic image size in pixels

    private static final int PROFILE_PIC_SIZE = 400;

    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;

    /**
     * A flag indicating that a PendingIntent is in progress and prevents us
     * from starting further intents.
     */

    private static  final int REQUEST_READ_CONTACTS=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);



        preferenceUtility = PreferenceUtility.getSession(getApplicationContext());

        PackageInfo info = null;
        try {
            info = getPackageManager().getPackageInfo(
                    "com.playbig",
                    PackageManager.GET_SIGNATURES);

            for (Signature signature : info.signatures) {
                MessageDigest md = null;

                md = MessageDigest.getInstance("SHA");

                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }

        }catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            }



        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();



    }


    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }





    private void accessContacts(){
        if (!mayRequestContacts()) {
            return;
        }
        // This Build is < 6 , you can Access contacts here.
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(Manifest.permission.GET_ACCOUNTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }

        if (shouldShowRequestPermissionRationale(Manifest.permission.GET_ACCOUNTS)) {
            Snackbar.make(ly_login_google, "Grant Permission", Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{Manifest.permission.GET_ACCOUNTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{Manifest.permission.GET_ACCOUNTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted , Access contacts here or do whatever you need.
            }
        }
    }


  /*  @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        Log.i("TAG", "request" + requestCode + " ResultCode" + resultCode + " FilterItem_Data " + data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);

    }
*/




    @Override
    public void onConnectionFailed(ConnectionResult result) {
        if (!result.hasResolution()) {
            GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), this,
                    0).show();
            return;
        }



    }


    @Override
    public void onFBLoginSuccessCalled(LoginResult loginResult) {

        fetchUserInfo();

    }

    @Override
    public void onFBLoginCancelledCalled() {

    }

    @Override
    public void onFBLoginonErrorCalled(FacebookException e) {


        Log.i("::::", "onFBLoginonErrorCalled called");
        if (e instanceof FacebookAuthorizationException && e.getMessage().toString().equalsIgnoreCase("User logged in as different Facebook user.")) {
            mFacebookHelper.logout();
            mFacebookHelper.login();
        }


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "Something Wrong. Try Again.", Toast.LENGTH_SHORT).show();
            }
        });





    }



    private void fetchUserInfo() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null) {
            GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject me, GraphResponse response) {

                    Log.i("User FilterItem_Data::", "Facebook-User Details : " + me.toString());
                    try {
                        preferenceUtility.putBoolean(ConstantCodes.PREFERENCE_KEY.IS_USER_LOGGED_IN, true);
                        preferenceUtility.putString(ConstantCodes.PREFERENCE_KEY.USER_ID, me.getString("id"));
                        preferenceUtility.putString(ConstantCodes.PREFERENCE_KEY.USER_EMAIL_ID, me.getString("email"));
                        preferenceUtility.putString(ConstantCodes.PREFERENCE_KEY.USER_NAME, me.getString("name"));
                        preferenceUtility.putString(ConstantCodes.PREFERENCE_KEY.USER_PROFILE_PIC, new JSONObject(new JSONObject(me.getString("picture")).getString("data")).getString("url").toString());
                      //  preferenceUtility.putString(ConstantCodes.PREFERENCE_KEY.USER_PROFILE_PIC, );


                       Toast.makeText(getApplicationContext(), "Welcome, " + me.getString("name"), Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(getApplicationContext(), Activity_myprofile.class));



//                   finish();

                        me.put("mode","Facebook_Sign_IN");
                       lgoinUser(me.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }



            });
            Bundle bundle = new Bundle();
            bundle.putString("fields", "email,name,id,picture");
            request.setParameters(bundle);
            GraphRequest.executeBatchAsync(request);
        } else {
            //user = null;
        }
    }







    @Override
    public void initVariable() {

    }

    @Override
    public void initView() {



        ly_loginfb_second_part = (LinearLayout) findViewById(R.id.ly_loginfb_second_part);

        ly_login_google = (LinearLayout) findViewById(R.id.ly_login_google);

        mCallbackManager = CallbackManager.Factory.create();
        mFacebookHelper = new FacebookHelper(this, mCallbackManager);
        mFacebookHelper.setFacebookCallbacks(this);

        ly_loginfb_second_part.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFacebookHelper.login();


            }
        });


        ly_login_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();


            }
        });




    }



























    public void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        this.startActivityForResult(signInIntent, RC_SIGN_IN);
    }

   @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)  {
        //this.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
        else {
            Log.i("TAG", "request" + requestCode + " ResultCode" + resultCode + " FilterItem_Data " + data);
            mCallbackManager.onActivityResult(requestCode, resultCode, data);

        }



    }

    private void handleSignInResult(GoogleSignInResult result)   {
        //Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();

            Uri  personPhotouri = acct.getPhotoUrl();
               if(personPhotouri!=null) {
                   personphoto =personPhotouri.toString();
               }else{
                   personphoto =null;
               }


            preferenceUtility.putBoolean(ConstantCodes.PREFERENCE_KEY.IS_USER_LOGGED_IN, true);
            preferenceUtility.putString(ConstantCodes.PREFERENCE_KEY.USER_ID, personId);
            preferenceUtility.putString(ConstantCodes.PREFERENCE_KEY.USER_EMAIL_ID, personEmail);
            preferenceUtility.putString(ConstantCodes.PREFERENCE_KEY.USER_NAME, personName);
            if(personphoto!=null) {
                preferenceUtility.putString(ConstantCodes.PREFERENCE_KEY.USER_PROFILE_PIC, personphoto);
            }
            else{
                preferenceUtility.putString(ConstantCodes.PREFERENCE_KEY.USER_PROFILE_PIC, null);
            }



            JSONObject Jobject_SignIN_response = new JSONObject();

            try {
                Jobject_SignIN_response.put("id", personId);
                Jobject_SignIN_response.put("name", personName);
                Jobject_SignIN_response.put("email", personEmail);
                Jobject_SignIN_response.put("profile_pic", personphoto);
                Jobject_SignIN_response.put("mode", "Google_Sign_IN");
            } catch (JSONException e) {
                e.printStackTrace();
            }


            try {
                lgoinUser(Jobject_SignIN_response.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Toast.makeText(getApplicationContext(), "Welcome, " + personName, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), Activity_myprofile.class));






        }


    }












    private void lgoinUser(String s) throws JSONException {

        //final HashMap<String, String> params = new HashMap<String, String>();
        JSONObject jsonParams = new JSONObject();
        Jobject_response = new JSONObject(s);
        jsonParams.put("User_id", Jobject_response.getString("id"));
        jsonParams.put("Name", Jobject_response.getString("name"));
        jsonParams.put("email", Jobject_response.getString("email"));
        String mode = Jobject_response.getString("mode");
        if( mode.equalsIgnoreCase("Facebook_Sign_IN")){
            jsonParams.put("profile_pic", new JSONObject(new JSONObject(Jobject_response.getString("picture")).getString("data")).getString("url").toString());

        }
        else{
            jsonParams.put("profile_pic", Jobject_response.getString("profile_pic"));

        }
        Utility.showProgressDialog(this, null);
        WebUtils.newWebServiceRequest(getApplicationContext(), jsonParams, WebServiceConfigs.WebService.LOG_IN, networkcallbacks);
    }

    NetworkCallbacks networkcallbacks;

    {
        networkcallbacks = new NetworkCallbacks() {
            @Override
            public void successWithString(Object values, WebServiceConfigs.WebService webService) throws JSONException {
                super.successWithString(values, webService);
                Utility.dismissProgressDialog();
                JSONObject jobj = new JSONObject(values.toString());
                if (jobj.getString(ConstantCodes.IN_STATUS).equalsIgnoreCase(ConstantCodes.IN_RESULT_OK)) {
                    JSONObject data_jobj = jobj.getJSONObject("DATA");

                    startActivity(new Intent(Activity_Login.this, MainActivity.class));
                    finish();
                }
            }




            @Override
            public void failedWithMessage(Object values, WebServiceConfigs.WebService webService) {
                super.failedWithMessage(values, webService);
                Utility.dismissProgressDialog();
                try {
                    Toast.makeText(getApplicationContext(), new JSONObject(values.toString()).getString("MESSAGE"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failedForNetwork(Object values, WebServiceConfigs.WebService webService) {
                super.failedForNetwork(values, webService);
                Utility.dismissProgressDialog();
            }
        };
    }






















    @Override
    public void addAdapter() {

    }

    @Override
    public void loadData() {

    }

    @Override
    public void onConnected(Bundle bundle) {


        mGoogleApiClient.connect();

      //  mSignInClicked = false;
       Toast.makeText(this, "User is connected!", Toast.LENGTH_LONG).show();

        // Get user's information
     //  getProfileInformation();


    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();

    }
}
