package com.playbig.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ParseException;
import android.os.Looper;
import android.util.Log;

import com.playbig.network.WebServiceConfigs;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ParseException;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Set;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.entity.mime.content.FileBody;


public class CallWebservice {

    private static final String TAG = "WS";
    public static final int TIMEOUT_DURATION = 60000;
    private static AsyncHttpClient client;
    private static AsyncHttpClient client_sync;

    public static String result = null;
    public static RequestParams requestParams;

    public static String get(String mUrl, HashMap<String, String> params, HashMap<String, String> headerMap) throws Exception {

        client = new AsyncHttpClient();
        client_sync = new SyncHttpClient();

        InputStream is;
        String line;


        StringBuilder url = new StringBuilder(mUrl);

        if (params != null) {
            Set<String> keys = params.keySet();
            if (!mUrl.endsWith("&"))
                url.append("&");
            for (String key : keys) {
                url.append(key + "=" + URLEncoder.encode(params.get(key), "UTF-8") + "&");
            }
        }
        url.deleteCharAt(url.length() - 1);
        Log.i(TAG, "callGetMethod url: " + url.toString());

        // adding headers
        if (headerMap != null) {
            Set<String> headerKeys = headerMap.keySet();

            for (String key : headerKeys) {
                Log.d(TAG, "key=> " + key + " : value=> " + headerMap.get(key));
                client.addHeader(key, headerMap.get(key));
            }
        }

//        HttpResponse response = httpClient.execute(httpGet);
//        HttpEntity entity = response.getEntity();
//        is = entity.getContent();

//        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
//        StringBuilder sb = new StringBuilder();
//
//        while ((line = reader.readLine()) != null) {
//
//            sb.append(line + "\n");
//        }
        getClient().get(mUrl, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                result = responseString.toString();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                result = responseString.toString();
            }
        });

        Log.i(TAG, "===================================================");
        Log.i(TAG, "Response : " + result);
        Log.i(TAG, "===================================================");
        //is.close();
        //result = sb.toString();
        return result;

    }

    public static String post(String mUrl, HashMap<String, String> params, HashMap<String, String> headerMap) throws Exception {

        client = new AsyncHttpClient();
        client_sync = new SyncHttpClient();

        result = null;
        Log.i(TAG, "===================================================");

        //MultipartEntityBuilder reqEntity = MultipartEntityBuilder.create();
        //reqEntity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

        JSONObject jsonObject = new JSONObject();
        requestParams = new RequestParams();


        Log.i(TAG, "URL : " + mUrl);
        Log.i(TAG, "PARAMS");
        if (params != null) {
            Set<String> keys = params.keySet();
            for (String key : keys) {
                String value = "";
                if (params.get(key) != null) {
                    // Log.d(TAG, "key=> " + key + " : value=> " + params.get(key));
                    // Log.d(TAG, key + " : " + params.get(key));
                    value = params.get(key);
                    jsonObject.put(key, value);
                } else {
                    Log.d("log_tag", "KEY_NULL : " + key);
                }

                if (key.startsWith(WebServiceConfigs.FILE_IMAGE)) {
                    File fileToUpload = new File(value);
                    // Log.d(TAG, "key=> " + key + " : value=> " + value);
                    Log.d(TAG, key + " : " + params.get(key));
                    if (fileToUpload.exists()) {
                        Log.d(TAG, "UploadFile Size: " + fileToUpload.length());

                        // WAY 1 - Directly send file
                        //reqEntity.addPart(key.replace(WebServiceConfigs.FILE_IMAGE, ""), new FileBody(fileToUpload));


                        // WAY 2 - Send byte array
                        //byte[] data = getByteArrayOfImageFile(fileToUpload);

                        //ByteArrayBody bab = new ByteArrayBody(data, "image/jpeg", fileToUpload.getName());
                        //reqEntity.addPart(key.replace(WebServiceConfigs.FILE_IMAGE, ""), bab);
                        requestParams.put(key.replace(WebServiceConfigs.FILE_IMAGE, ""), fileToUpload);
                    }
                } else if (key.startsWith(WebServiceConfigs.FILE_VIDEO)) {
                    File fileToUpload = new File(value);
                    // Log.d(TAG, "key=> " + key + " : value=> " + value);
                    Log.d(TAG, key + " : " + params.get(key));
                    if (fileToUpload.exists()) {
                        Log.d(TAG, "File Size: " + fileToUpload.length());

                        // WAY 1 - Directly send file
                        //reqEntity.addPart(key.replace(WebServiceConfigs.FILE_VIDEO, ""), new FileBody(fileToUpload));
                        requestParams.put(key.replace(WebServiceConfigs.FILE_VIDEO, ""), fileToUpload);
                        // WAY 2 - Send byte array
                        // byte[] data = getByteArrayOfSimpleFile(fileToUpload);
                        // ByteArrayBody bab = new ByteArrayBody(data, "video/mp4", fileToUpload.getName());
                        // reqEntity.addPart(key.replace(FILE_VIDEO, ""), bab);
                    }
                } else if (key.startsWith(WebServiceConfigs.FILE_FILE)) {
                    File fileToUpload = new File(value);
                    // Log.d(TAG, "key=> " + key + " : value=> " + params.get(key));
                    Log.d(TAG, key + " : " + params.get(key));
                    if (fileToUpload.exists()) {
                        Log.d(TAG, "UploadFile Size: " + fileToUpload.length());
                        //reqEntity.addPart(key.replace(WebServiceConfigs.FILE_FILE, ""), new FileBody(fileToUpload));
                        requestParams.put(key.replace(WebServiceConfigs.FILE_FILE, ""), fileToUpload);
                    }
                } else {
                    Log.d(TAG, key + " : " + params.get(key));
                    //reqEntity.addPart(key, new StringBody(value));
                    requestParams.put(key.replace(WebServiceConfigs.FILE_FILE, ""), value);
                }
            }
        }

//        HttpClient httpClient = new DefaultHttpClient();
//        HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), TIMEOUT_DURATION);
//        HttpConnectionParams.setSoTimeout(httpClient.getParams(), TIMEOUT_DURATION);
//        Log.i(TAG, "HEADER PARAMS");
//        // adding headers
//        HttpPost httpPost = new HttpPost(mUrl);
//        if (headerMap != null) {
//            Set<String> headerKeys = headerMap.keySet();
//
//            for (String key : headerKeys) {
//                // Log.d(TAG, "key=> " + key + " : value=> " + headerMap.get(key));
//                Log.d(TAG, key + " : " + headerMap.get(key));
//                httpPost.addHeader(key, headerMap.get(key));
//            }
//        }
//
//        httpPost.setEntity(reqEntity.build());
//        HttpResponse response;
//        response = httpClient.execute(httpPost);
//        HttpEntity entity = response.getEntity();

        // Way1
        //result = _getResponseBody(entity);
        getClient().post(mUrl, requestParams, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                result = responseString;
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                result = responseString;
            }
        });
        // Way2
        // result = EntityUtils.toString(entity);

        Log.i(TAG, "Response : " + result);
        Log.i(TAG, "===================================================");
        return result;
    }


    /**
     * POST in JSON formate
     *
     * @return
     */

    public static String postGSON(Context context, String mUrl, JSONObject jsonObject) throws Exception {

        client = new AsyncHttpClient();
        client_sync = new SyncHttpClient();

        result = null;
        Log.i(TAG, "===================================================");

        //MultipartEntityBuilder reqEntity = MultipartEntityBuilder.create();
        //reqEntity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        StringEntity entity = new StringEntity(jsonObject.toString());


        Log.i(TAG, "URL : " + mUrl);
        Log.i(TAG, "PARAMS");
        Log.i(TAG, "===================================================");
        Log.i(TAG, jsonObject.toString());
        Log.i(TAG, "===================================================");


        getClient().post(context, mUrl, entity, "application/json",
                new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        result = responseString;
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        result = responseString;
                    }
                });
        // Way2
        // result = EntityUtils.toString(entity);

        Log.i(TAG, "Response : " + result);
        Log.i(TAG, "===================================================");
        return result;
    }

    private static AsyncHttpClient getClient() {
        // Return the synchronous HTTP client when the thread is not prepared
        if (Looper.myLooper() == null)
            return client_sync;
        return client;
    }

//    public static String _getResponseBody(final HttpEntity entity) throws IOException, ParseException {
//        if (entity == null) {
//            throw new IllegalArgumentException("HTTP entity may not be null");
//        }
//        InputStream instream = entity.getContent();
//        if (instream == null) {
//            return "";
//        }
//        if (entity.getContentLength() > Integer.MAX_VALUE) {
//            throw new IllegalArgumentException("HTTP entity too large to be buffered in memory");
//        }
//        String charset = EntityUtils.getContentCharSet(entity);
//        if (charset == null) {
//            charset = HTTP.DEFAULT_CONTENT_CHARSET;
//        }
//        Reader reader = new InputStreamReader(instream, charset);
//        StringBuilder buffer = new StringBuilder();
//        try {
//            char[] tmp = new char[1024];
//            int l;
//            while ((l = reader.read(tmp)) != -1) {
//                buffer.append(tmp, 0, l);
//            }
//        } finally {
//            reader.close();
//        }
//        return buffer.toString();
//    }
//
//    public static byte[] getByteArrayOfImageFile(File file) {
//        try {
//            FileInputStream fis = new FileInputStream(file);
//
//            Bitmap bi = null;
//            BitmapFactory.Options options = null;
//
//            // Way 1 : simple
//            // bi = BitmapFactory.decodeStream(fis);
//
//            // Way 2 : Reducing Color scheme
//            // options = new BitmapFactory.Options();
//            // options.inPreferredConfig = Config.RGB_565;
//            // bi = BitmapFactory.decodeStream(fis, null, options);
//
//            // Way 3 : scaling image
//            options = new BitmapFactory.Options();
//            options.inSampleSize = 4;
//            bi = BitmapFactory.decodeFile(file.getPath(), options);
//            // bi = BitmapFactory.decodeStream(fis, null ,options);
//
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            bi.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//            byte[] data = baos.toByteArray();
//            fis.close();
//            baos.close();
//            bi.recycle();
//            return data;
//        } catch (OutOfMemoryError e) {
//            return null;
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    public static byte[] getByteArrayOfSimpleFile(File file) {
//        try {
//            FileInputStream fis = new FileInputStream(file);
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            int nRead;
//            byte[] d = new byte[16384];
//            while ((nRead = fis.read(d, 0, d.length)) != -1) {
//                baos.write(d, 0, nRead);
//            }
//            byte[] data = baos.toByteArray();
//            baos.flush();
//            fis.close();
//            return data;
//        } catch (Exception e) {
//            return null;
//        }
//    }
}