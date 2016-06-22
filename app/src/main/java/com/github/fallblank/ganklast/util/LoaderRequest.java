package com.github.fallblank.ganklast.util;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

/**
 * Created by fallb on 2016/6/22.
 */
public class LoaderRequest extends Request<byte[]> {

    private final Response.Listener<byte[]> mListener;


    public LoaderRequest(String url, Response.Listener<byte[]> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        mListener = listener;
    }


    @Override protected Response<byte[]> parseNetworkResponse(NetworkResponse response) {
        byte[] result = response.data;
        return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));
    }


    @Override protected void deliverResponse(byte[] response) {
        mListener.onResponse(response);
    }
}
