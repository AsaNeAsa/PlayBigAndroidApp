package com.playbig.network;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import com.playbig.network.WebServiceConfigs.WebService;


public class WebUtils {


    public static AsyncTask<Void, Void, Object> newWebServiceRequest(final Context mActivity, final String[] urlParams, final HashMap<String, String> params, final HashMap<String, String> headerParams, final WebServiceConfigs.WebService webService, final NetworkCallbacks callback, boolean isRunOnExecutor) {

        final CustomRequestLifeCycle requestLifeCycle = CustomRequestLifeCycle.getInstance(mActivity);

        if (requestLifeCycle.isInternetConnected()) {

            return new AsyncTask<Void, Void, Object>() {

                @Override
                protected Object doInBackground(Void... args) {
                    // TODO Auto-generated method stub
                    try {

                        WebServiceModel webServiceModel = WebServiceConfigs.getMethods().get(webService);
                        if (webServiceModel != null) {
                            HashMap<String, String> headers = headerParams;

                            if (headers == null) {
                                if (webServiceModel.isAddHeader)
                                    headers = requestLifeCycle.getHeaders(webServiceModel);
                            }

                            String url = webServiceModel.url;
                            if (urlParams != null) {
                                url = String.format(url, urlParams);
                            }

                            if (webServiceModel.method == WebServiceConfigs.Method.POST)
                                return CallWebservice.post(url, params, headers);
                            else
                                return CallWebservice.get(url, params, headers);
                        } else {
                            throw new Exception("This Service not defined in WebS");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d("DownloadManager", "Error: " + e);

                        return e;
                    }
                }

                @Override
                protected void onPostExecute(Object result) {
                    // TODO Auto-generated method stub
                    super.onPostExecute(result);
                    if (callback != null) {
                        try {
                            if (result instanceof Exception) {
                                callback.failedForNetwork(result, webService);
                            } else {
                                JSONObject json = new JSONObject(result.toString());
                                StringBuilder failMessage = new StringBuilder();
                                if (requestLifeCycle.validateResponse(json, failMessage)) {
                                    callback.successWithString(result, webService);
                                } else {
                                    callback.failedWithMessage(result, webService);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            callback.failedForNetwork(e, webService);
                        }
                    }
                }
            }.executeOnExecutor(isRunOnExecutor ? AsyncTask.SERIAL_EXECUTOR : AsyncTask.THREAD_POOL_EXECUTOR);

        } else {
            callback.failedForNetwork(new Exception(requestLifeCycle.getNoInternetMessage()), webService);
            return null;
        }
    }


    public static AsyncTask<Void, Void, Object> newJSONWebServiceRequest(final Context mActivity, final JSONObject jsonObject, final WebServiceConfigs.WebService webService, final NetworkCallbacks callback) {

        final CustomRequestLifeCycle requestLifeCycle = CustomRequestLifeCycle.getInstance(mActivity);

        if (requestLifeCycle.isInternetConnected()) {

            return new AsyncTask<Void, Void, Object>() {

                @Override
                protected Object doInBackground(Void... args) {
                    // TODO Auto-generated method stub
                    try {

                        WebServiceModel webServiceModel = WebServiceConfigs.getMethods().get(webService);
                        if (webServiceModel != null) {

                            String url = webServiceModel.url;

                            if (webServiceModel.method == WebServiceConfigs.Method.POST)
                                return CallWebservice.postGSON(mActivity, url, jsonObject);
                            else
                                return CallWebservice.get(url, null, null);
                        } else {
                            throw new Exception("This Service not defined in WebS");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d("DownloadManager", "Error: " + e);

                        return e;
                    }
                }

                @Override
                protected void onPostExecute(Object result) {
                    // TODO Auto-generated method stub
                    super.onPostExecute(result);
                    if (callback != null) {
                        try {
                            if (result instanceof Exception) {
                                callback.failedForNetwork(result, webService);
                            } else {
                                JSONObject json = new JSONObject(result.toString());
                                StringBuilder failMessage = new StringBuilder();
                                if (requestLifeCycle.validateResponse(json, failMessage)) {
                                    callback.successWithString(result, webService);
                                } else {
                                    callback.failedWithMessage(result, webService);
                                }
                                callback.successWithString(result, webService);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            callback.failedForNetwork(e, webService);
                        }
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else {
            callback.failedForNetwork(new Exception(requestLifeCycle.getNoInternetMessage()), webService);
            return null;
        }
    }

    /**
     * Calling json ojbect webservic
     *
     * @param webService
     * @param callback   * @return
     */


    public static AsyncTask<Void, Void, Object> newWebServiceRequest(final Context mActivity, final HashMap<String, String> params, final HashMap<String, String> headerParams, final WebService webService, final NetworkCallbacks callback, boolean isRunOnExecutor) {
        return newWebServiceRequest(mActivity, null, params, headerParams, webService, callback, false);
    }

    public static AsyncTask<Void, Void, Object> newWebServiceRequest(final Context mActivity, String[] urlParams, final HashMap<String, String> params, final WebService webService, final NetworkCallbacks callback) {
        return newWebServiceRequest(mActivity, urlParams, params, null, webService, callback, false);
    }

    public static AsyncTask<Void, Void, Object> newWebServiceRequest(final Context mActivity, String[] urlParams, final HashMap<String, String> params, final WebService webService, final NetworkCallbacks callback, boolean isRunOnExecutor) {
        return newWebServiceRequest(mActivity, urlParams, params, null, webService, callback, isRunOnExecutor);
    }

    /**
     * @param mActivity
     * @param params
     * @param headers
     * @param webService
     * @param callback
     * @return
     */
    public static AsyncTask<Void, Void, Object> newWebServiceRequest(Context mActivity, final HashMap<String, String> params, final HashMap<String, String> headers, final WebService webService, final NetworkCallbacks callback) {

        return newWebServiceRequest(mActivity, params, headers, webService, callback, false);

    }

    /**
     * @param mActivity
     * @param params
     * @param webService
     * @param callback
     * @return
     */
    public static AsyncTask<Void, Void, Object> newWebServiceRequest(Context mActivity, final HashMap<String, String> params, final WebService webService, final NetworkCallbacks callback) {

        return newWebServiceRequest(mActivity, params, null, webService, callback, false);
    }

    /**
     * @param mActivity
     * @param params
     * @param webService
     * @param callback
     * @param isRunOnExecutor
     * @return
     */
    public static AsyncTask<Void, Void, Object> newWebServiceRequest(Context mActivity, final HashMap<String, String> params, final WebService webService, final NetworkCallbacks callback, boolean isRunOnExecutor) {

        return newWebServiceRequest(mActivity, params, null, webService, callback, false);

    }

    /**
     * JSON calling WS
     *
     * @param mActivity
     * @param jsonObject
     * @param webService
     * @param callback
     * @return
     */

    public static AsyncTask<Void, Void, Object> newWebServiceRequest(Context mActivity, final JSONObject jsonObject, final WebService webService, final NetworkCallbacks callback) {

        return newJSONWebServiceRequest(mActivity, jsonObject, webService, callback);

    }

    /*public static AsyncTask<Void, Void, Object> newWebServiceRequest(final Context mActivity, final int method, final String url, final HashMap<String, String> params, final HashMap<String, String> headerParams, final NetworkCallbacks callback, boolean isRunOnExecutor) {


        final ApplicationSession applicationSession = ApplicationSession.getSession(mActivity);

        if (Utility.isInternetConnected(mActivity)) {

            return new AsyncTask<Void, Void, Object>() {

                @Override
                protected Object doInBackground(Void... args) {
                    // TODO Auto-generated method stub
                    try {

                        HashMap<String, String> headers = headerParams;

                        if (headers == null) {
                            headers = getHeaders(mActivity);
                        }

                        if (method == Method.POST)
                            return CallWebservice.post(url, params, headers);
                        else
                            return CallWebservice.get(url, params, headers);

                    } catch (Exception e) {
                        Log.d("DownloadManager", "Error: " + e);

                        return e;
                    }
                }

                @Override
                protected void onPostExecute(Object result) {
                    // TODO Auto-generated method stub
                    super.onPostExecute(result);
                    if (callback != null) {
                        try {
                            if (result instanceof Exception) {
                                callback.failedForNetwork(result, null);
                            } else {
                                JSONObject json = new JSONObject(result.toString());
                                if (json.has(WebServiceParams.IN_SUCCESS) && json.optString(WebServiceParams.IN_SUCCESS).equalsIgnoreCase("1")) {
                                    callback.successWithString(result, null);
                                } else if (json.has(WebServiceParams.IN_MESSAGE)) {
                                    callback.failedWithMessage(json.optString(WebServiceParams.IN_MESSAGE), null);
                                } else {
                                    callback.successWithString(result, null);
                                }
                            }
                        } catch (Exception e) {
                            callback.failedForNetwork(result, null);
                        }
                    }
                }
            }.executeOnExecutor(isRunOnExecutor ? AsyncTask.SERIAL_EXECUTOR : AsyncTask.THREAD_POOL_EXECUTOR);

        } else {
            callback.failedForNetwork(new Exception("Internet unavailable"), null);
            return null;
        }

    }*/

    public static void downloadFromUrl(Context context, final String downloadUrl, final ImageDownloadCallback callback) {

        final CustomRequestLifeCycle requestLifeCycle = CustomRequestLifeCycle.getInstance(context);

        new AsyncTask<Void, Void, Exception>() {

            File file;

            @Override
            protected Exception doInBackground(Void... params) {
                // TODO Auto-generated method stub
                try {

                    file = null;
                    URL url = new URL(downloadUrl); // you can write here any link
                    if (!TextUtils.isEmpty(downloadUrl) && downloadUrl.endsWith(".mp4")) {
                        file = requestLifeCycle.createNewVideoFile();
                    } else if (!TextUtils.isEmpty(downloadUrl)) {
                        file = requestLifeCycle.createNewImageFile();
                    }
                    if (!file.exists())
                        file.createNewFile();

                    long startTime = System.currentTimeMillis();
                    Log.d("DownloadManager", "download begining");
                    Log.d("DownloadManager", "download url:" + url);
                    Log.d("DownloadManager", "downloaded file name:" + file.getName());

					/* Open a connection to that URL. */
                    URLConnection ucon = url.openConnection();

					/*
                     * Define InputStreams to read from the URLConnection.
					 */
                    InputStream is = ucon.getInputStream();
                    BufferedInputStream bis = new BufferedInputStream(is);


					/*
                     * Read bytes to the Buffer until there is nothing more to read(-1).
					 */
//                    ByteArrayBuffer baf = new ByteArrayBuffer(5000);
                    StringBuffer stringBuffer = new StringBuffer();
                    int current = 0;
                    while ((current = bis.read()) != -1) {
//                        baf.append((byte) current);
                        stringBuffer.append(current);
                    }

					/* Convert the Bytes read to a String. */
                    FileOutputStream fos = new FileOutputStream(file);
//                    fos.write(baf.toByteArray());
                    fos.write(stringBuffer.toString().getBytes());
                    fos.flush();
                    fos.close();
                    Log.d("DownloadManager", "download ready in" + ((System.currentTimeMillis() - startTime) / 1000) + " sec");

                    return null;

                } catch (IOException e) {
                    Log.d("DownloadManager", "Error: " + e);

                    return e;
                }

            }

            @Override
            protected void onPostExecute(Exception result) {
                // TODO Auto-generated method stub
                super.onPostExecute(result);
                if (callback != null) {
                    if (result == null)
                        callback.onSuccess(file.getPath(), result);
                    else
                        callback.onSuccess(null, result);
                }
            }
        }.execute();
    }

    public static void downloadFromUrlNew(Context context, final String downloadUrl, final ImageDownloadCallback callback) {

        final CustomRequestLifeCycle requestLifeCycle = CustomRequestLifeCycle.getInstance(context);

        new AsyncTask<Void, String, Exception>() {

            File file;

            @Override
            protected Exception doInBackground(Void... params) {
                // TODO Auto-generated method stub
                try {

                    file = null;
                    URL url = new URL(downloadUrl); // you can write here any link
                    if (!TextUtils.isEmpty(downloadUrl) && downloadUrl.endsWith(".mp4")) {
                        file = requestLifeCycle.createNewVideoFile();
                    } else if (!TextUtils.isEmpty(downloadUrl)) {
                        file = requestLifeCycle.createNewImageFile();
                    }
                    if (!file.exists())
                        file.createNewFile();

                    long startTime = System.currentTimeMillis();
                    Log.d("DownloadManager", "download begining");
                    Log.d("DownloadManager", "download url:" + url);
                    Log.d("DownloadManager", "downloaded file name:" + file.getName());

					/* Open a connection to that URL. */
                    URLConnection ucon = url.openConnection();

					/*
                     * Define InputStreams to read from the URLConnection.
					 */
                    InputStream is = ucon.getInputStream();

                    BufferedInputStream bis = new BufferedInputStream(is);
                    int lenghtOfFile = ucon.getContentLength();

					/*
                     * Read bytes to the Buffer until there is nothing more to read(-1).
					 */

                    FileOutputStream fos = new FileOutputStream(file);

                    long total = 0;
                    byte data[] = new byte[1024];
                    int count = 0;
                    while ((count = bis.read(data)) != -1) {
                        total += count;
                        // publishing the progress....
                        // After this onProgressUpdate will be called
//						publishProgress("" + (int) ((total * 100) / lenghtOfFile));

                        // writing data to file
                        fos.write(data, 0, count);
                    }

                    fos.flush();
                    fos.close();
                    Log.d("DownloadManager", "download ready in" + ((System.currentTimeMillis() - startTime) / 1000) + " sec");

                    return null;

                } catch (IOException e) {
                    Log.d("DownloadManager", "Error: " + e);

                    return e;
                }

            }

            @Override
            protected void onProgressUpdate(String... values) {
                // TODO Auto-generated method stub
                super.onProgressUpdate(values);
                if (callback != null) {
                    callback.onPublishProgress(values[0]);
                }
            }

            @Override
            protected void onPostExecute(Exception result) {
                // TODO Auto-generated method stub
                super.onPostExecute(result);
                if (callback != null) {
                    if (result == null)
                        callback.onSuccess(file.getPath(), result);
                    else
                        callback.onSuccess(null, result);
                }
            }
        }.execute();
    }

    public static void downloadFromUrl(final String downloadUrl, boolean isTempFile, final ImageDownloadCallback callback) {

        new AsyncTask<Void, Void, Exception>() {

            File file;

            @Override
            protected Exception doInBackground(Void... params) {
                // TODO Auto-generated method stub
                try {

                    URL url = new URL(downloadUrl); // you can write here any link
                    if (file == null) {
                        file = File.createTempFile("temp" + System.currentTimeMillis(), ".jpeg");
                        file.deleteOnExit();
                    }

                    long startTime = System.currentTimeMillis();
                    Log.d("DownloadManager", "download begining");
                    Log.d("DownloadManager", "download url:" + url);
                    Log.d("DownloadManager", "downloaded file name:" + file.getName());

					/* Open a connection to that URL. */
                    URLConnection ucon = url.openConnection();

					/*
                     * Define InputStreams to read from the URLConnection.
					 */
                    InputStream is = ucon.getInputStream();
                    BufferedInputStream bis = new BufferedInputStream(is);

					/*
                     * Read bytes to the Buffer until there is nothing more to read(-1).
					 */
//                    ByteArrayBuffer baf = new ByteArrayBuffer(5000);
                    StringBuffer stringBuffer = new StringBuffer();
                    int current = 0;
                    while ((current = bis.read()) != -1) {
//                        baf.append((byte) current);
                        stringBuffer.append(current);
                    }

					/* Convert the Bytes read to a String. */
                    FileOutputStream fos = new FileOutputStream(file);
//                    fos.write(baf.toByteArray());
                    fos.write(stringBuffer.toString().getBytes());
                    fos.flush();
                    fos.close();
                    Log.d("DownloadManager", "download ready in" + ((System.currentTimeMillis() - startTime) / 1000) + " sec");

                    return null;

                } catch (IOException e) {
                    Log.d("DownloadManager", "Error: " + e);

                    return e;
                }

            }

            @Override
            protected void onPostExecute(Exception result) {
                // TODO Auto-generated method stub
                super.onPostExecute(result);
                if (callback != null) {
                    if (result == null)
                        callback.onSuccess(file.getPath(), result);
                    else
                        callback.onSuccess(null, result);
                }
            }
        }.execute();
    }

    public interface ImageDownloadCallback {
        public void onSuccess(String filePath, Exception e);

        public void onPublishProgress(String percentage);
    }

    public interface RequestLifeCycle {

        public boolean validateResponse(JSONObject jsonObject, StringBuilder failMessage);

        public HashMap<String, String> getHeaders(WebServiceModel webService);

        public boolean isInternetConnected();

        public File createNewVideoFile();

        public File createNewImageFile();

        public String getNoInternetMessage();
    }
}
