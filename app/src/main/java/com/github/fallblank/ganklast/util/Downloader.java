package com.github.fallblank.ganklast.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.fallblank.ganklast.R;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URLConnection;

/**
 * Created by fallb on 2016/6/22.
 */
public class Downloader {

    private static RequestQueue sQueue;


    public static void download(final Context context, String url, final File file) {
        if (sQueue == null) {
            sQueue = Volley.newRequestQueue(context);
        }
        LoaderRequest stringRequest = new LoaderRequest(url,
            new Response.Listener<byte[]>() {
                @Override public void onResponse(byte[] response) {
                    byte[] imageByte = response;
                    OutputStream out = null;
                    try {
                        out = new FileOutputStream(file);
                        out.write(imageByte);
                    } catch (FileNotFoundException e) {
                        Toast.makeText(context, R.string.download_failed, Toast.LENGTH_SHORT)
                            .show();
                    } catch (IOException e) {
                        Toast.makeText(context, R.string.download_failed, Toast.LENGTH_SHORT)
                            .show();
                    } finally {
                        if (out != null) {
                            try {
                                out.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    Toast.makeText(context, "已保存！", Toast.LENGTH_SHORT).show();
                }
            },
            new Response.ErrorListener() {
                @Override public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, R.string.download_failed, Toast.LENGTH_SHORT);
                }
            });
        sQueue.add(stringRequest);
    }
}
