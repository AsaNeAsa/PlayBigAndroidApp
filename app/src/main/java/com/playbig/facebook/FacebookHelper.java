package com.playbig.facebook;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.ShareApi;
import com.facebook.share.Sharer;
import com.facebook.share.model.AppInviteContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.AppInviteDialog;
import com.facebook.share.widget.ShareDialog;

import org.json.JSONArray;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by bhavesh on 9/4/15.
 */
public class FacebookHelper {
    private static final String TAG = "FacebookHelper";
    private Activity mActivity;
    private LoginManager mLoginManager;

    public static final String EMAIL = "email";
        public static final String PUBLIC_PROFILE = "public_profile";
    public static final String BIRTH_DATE = "user_birthday";
    public static final String USER_FRIENDS = "user_friends";
    // public static final String AGE_RANGE = "age_range";
    public static final String PUBLISH_ACTIONS = "publish_actions";


    private Collection<String> READ_PERMISSIONS = Arrays.asList(EMAIL, PUBLIC_PROFILE, USER_FRIENDS
    );
    private Collection<String> WRITE_PERMISSIONS = Arrays
            .asList(PUBLISH_ACTIONS);

    private enum PendingAction {
        NONE, POST_STATUS_UPDATE
    }

    private ShareDialog shareDialog;
    private AppInviteDialog appInviteDialog;
    private PendingAction pendingAction = PendingAction.NONE;
    private boolean canPresentShareDialog;
    private ShareContent mShareContent;

    public interface FacebookCallbacks {
        public void onFBLoginSuccessCalled(LoginResult loginResult);

        public void onFBLoginCancelledCalled();

        public void onFBLoginonErrorCalled(FacebookException e);

    }

    public interface FacebookLinkShareCallbacks {
        public void onFBShareImageLinkCancelled();

        public void onFBShareImageLinkSuccess(Sharer.Result result);

        public void onFBShareImageLinkError(FacebookException error);

    }

    public interface FacebookAppInviteCallbacks {
        public void onFBAppInviteCancelled();

        public void onFBAppInviteSuccess(AppInviteDialog.Result result);

        public void onFBAppInviteError(FacebookException error);

    }

    public interface FacebookGetMyFriendListCallbacks {
        public void onFBGetMyFriendListSuccessCalled(JSONArray jsonArray);
    }

    private FacebookCallbacks mFacebookCallbacks;
    private FacebookLinkShareCallbacks mFacebookLinkShareCallbacks;
    private FacebookAppInviteCallbacks mFacebookAppInviteCallbacks;
    private FacebookGetMyFriendListCallbacks mFacebookGetMyFriendListCallbacks;

    public FacebookHelper(Activity mActivity, CallbackManager mCallbackManager) {
        this.mActivity = mActivity;
        // mFacebookCallbacks = (FacebookCallbacks) mActivity;
        FacebookSdk.sdkInitialize(mActivity.getApplicationContext());
        mLoginManager = LoginManager.getInstance();
        mLoginManager
                .registerCallback(mCallbackManager, mLoginFacebookCallback);

        // For Sharing Dialog
        shareDialog = new ShareDialog(mActivity);
        shareDialog.registerCallback(mCallbackManager, shareCallback);

        //For Invitation Dialog
        appInviteDialog = new AppInviteDialog(mActivity);
        appInviteDialog.registerCallback(mCallbackManager, appInviteCallback);

        // Can we present the share dialog for regular links?
        canPresentShareDialog = ShareDialog.canShow(ShareLinkContent.class);
    }

    public void setFacebookCallbacks(FacebookCallbacks mCallbacks) {
        mFacebookCallbacks = mCallbacks;
    }

    public void setFacebookLinkShareCallbacks(
            FacebookLinkShareCallbacks mCallbacks) {
        mFacebookLinkShareCallbacks = mCallbacks;
    }

    public void setmFacebookAppInviteCallbacks(FacebookAppInviteCallbacks mCallbacks) {
        mFacebookAppInviteCallbacks = mCallbacks;
    }


    public void login() {
        mLoginManager.logInWithReadPermissions(mActivity, READ_PERMISSIONS);
    }

    public void getFriendList(FacebookGetMyFriendListCallbacks mFacebookGetMyFriendListCallbacks) {
        this.mFacebookGetMyFriendListCallbacks = mFacebookGetMyFriendListCallbacks;
        GraphRequest request = GraphRequest.newMyFriendsRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONArrayCallback() {
            @Override
            public void onCompleted(JSONArray jsonArray, GraphResponse graphResponse) {
                FacebookHelper.this.mFacebookGetMyFriendListCallbacks.onFBGetMyFriendListSuccessCalled(jsonArray);
            }
        });
        request.executeAsync();
    }

    private FacebookCallback<LoginResult> mLoginFacebookCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            mFacebookCallbacks.onFBLoginSuccessCalled(loginResult);
        }

        @Override
        public void onCancel() {
            mFacebookCallbacks.onFBLoginCancelledCalled();
        }

        @Override
        public void onError(FacebookException e) {
            mFacebookCallbacks.onFBLoginonErrorCalled(e);
        }
    };

    private FacebookCallback<Sharer.Result> shareCallback = new FacebookCallback<Sharer.Result>() {
        @Override
        public void onCancel() {
            mFacebookLinkShareCallbacks.onFBShareImageLinkCancelled();
        }

        @Override
        public void onError(FacebookException error) {
            mFacebookLinkShareCallbacks.onFBShareImageLinkError(error);
        }

        @Override
        public void onSuccess(Sharer.Result result) {
            Log.d("HelloFacebook", "Success!");
            mFacebookLinkShareCallbacks.onFBShareImageLinkSuccess(result);
        }

    };

    private FacebookCallback<AppInviteDialog.Result> appInviteCallback = new FacebookCallback<AppInviteDialog.Result>() {
        @Override
        public void onSuccess(AppInviteDialog.Result result) {
            Log.d("HelloFacebook", "appInviteCallback onSuccess!");
            mFacebookAppInviteCallbacks.onFBAppInviteSuccess(result);
        }

        @Override
        public void onCancel() {
            Log.d("HelloFacebook", "appInviteCallback onCancel!");
            mFacebookAppInviteCallbacks.onFBAppInviteCancelled();
        }

        @Override
        public void onError(FacebookException e) {
            Log.d("HelloFacebook", "appInviteCallback onError!");
            mFacebookAppInviteCallbacks.onFBAppInviteError(e);
        }
    };

    public void logout() {
        mLoginManager.logOut();
    }

    public boolean isLogin() {
        if (AccessToken.getCurrentAccessToken() != null)
            return true;
        return false;
    }

    public static String getUserProfilePhoto(String userID) {
        if (userID != null)
            return String.format(
                    "https://graph.facebook.com/%s/picture?type=large", userID);
        return "";
    }

    public void shareImageUrl(String name, String desc, String url,
                              String pic_url) {
        mShareContent = new ShareContent(name, desc, url, pic_url);
        performPublish(PendingAction.POST_STATUS_UPDATE, canPresentShareDialog);
    }

    private boolean hasPublishPermission() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null
                && accessToken.getPermissions().contains(PUBLISH_ACTIONS);
    }

    private void performPublish(PendingAction action, boolean allowNoToken) {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null) {
            pendingAction = action;

            handlePendingAction();
            /**
             * We are commenting below and calling handlePendingAction() because we are using ShareDialog which will NOT require to use any permission and no need to submit app
             */

            /*if (hasPublishPermission()) {
                // We can do the action right away.
                handlePendingAction();
                return;
            } else {
                // We need to get new permissions, then complete the action when
                // we get called back.
                mLoginManager.logInWithPublishPermissions(mActivity,
                        WRITE_PERMISSIONS);
                return;
            }*/
        }

        if (allowNoToken) {
            pendingAction = action;
            handlePendingAction();
        }
    }

    private void handlePendingAction() {
        PendingAction previouslyPendingAction = pendingAction;
        // These actions may re-set pendingAction if they are still pending, but
        // we assume they
        // will succeed.
        pendingAction = PendingAction.NONE;

        switch (previouslyPendingAction) {
            case NONE:
                break;
            case POST_STATUS_UPDATE:
                postStatusUpdate();
                break;
        }
    }
    public void invite() {
        String appLinkUrl, previewImageUrl;

//        appLinkUrl = "https://fb.me/936462733077341";
        appLinkUrl = "https://fb.me/890481204357185";

        previewImageUrl = "https://www.mydomain.com/my_invite_image.jpg";

        if (AppInviteDialog.canShow()) {
            AppInviteContent content = new AppInviteContent.Builder()
                    .setApplinkUrl(appLinkUrl)
                    .setPreviewImageUrl(previewImageUrl)
                    .build();


            appInviteDialog.show(content);
//            AppInviteDialog.show(mActivity, content);
        }
    }

    private void postStatusUpdate() {
        Profile profile = Profile.getCurrentProfile();
        ShareLinkContent linkContent = new ShareLinkContent.Builder()
                .setContentDescription(mShareContent.desc)
                .setContentTitle(mShareContent.name)
                .setContentUrl(Uri.parse(mShareContent.url))
                .setImageUrl(Uri.parse(mShareContent.pic_url)).build();
        if (canPresentShareDialog) {
            shareDialog.show(linkContent);
        } else if (profile != null && hasPublishPermission()) {
            ShareApi.share(linkContent, shareCallback);
        } else {
            pendingAction = PendingAction.POST_STATUS_UPDATE;
        }
    }

    static final class ShareContent {
        String name, desc, url, pic_url;

        ShareContent(String name, String desc, String url, String pic_url) {
            this.name = name;
            this.desc = desc;
            this.url = url;
            this.pic_url = pic_url;
        }
    }
}