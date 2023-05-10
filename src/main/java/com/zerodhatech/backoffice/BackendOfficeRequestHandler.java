package com.zerodhatech.backoffice;

import com.zerodhatech.kiteconnect.kitehttp.KiteResponseHandler;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;

import java.io.IOException;
import java.net.Proxy;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import org.json.JSONException;
import org.json.JSONObject;

public class BackendOfficeRequestHandler {
    private final OkHttpClient client;
    private final String USER_AGENT = "javakiteconnect/3.1.14";
    private String cookies;
    private String csrfToken;

    public BackendOfficeRequestHandler(Proxy proxy) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10000, TimeUnit.MILLISECONDS);
        if (proxy != null) {
            builder.proxy(proxy);
        }

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (BackendOfficeConnect.ENABLE_LOGGING) {
            client = builder.addInterceptor(logging).build();
        } else {
            client = builder.build();
        }
    }

    public JSONObject getRequest(String url) throws IOException, KiteException, JSONException {
        Request request = createGetRequest(url);
        Response response = client.newCall(request).execute();
        String body = response.body().string();
        return new KiteResponseHandler().handle(response, body);
    }

    public JSONObject getRequest(String url, Map<String, Object> params) throws IOException, KiteException, JSONException {
        Request request = createGetRequest(url, params);
        Response response = client.newCall(request).execute();
        String body = response.body().string();
        return new KiteResponseHandler().handle(response, body);
    }

    public Request createGetRequest(String url, Map<String, Object> params) {
        var httpBuilder = HttpUrl.parse(url).newBuilder();
        for (var entry : params.entrySet()) {
            httpBuilder.addQueryParameter(entry.getKey(), entry.getValue().toString());
        }
        return new Request.Builder().url(httpBuilder.build())
                                    .addHeader("User-Agent", USER_AGENT)
                                    .addHeader("Cookie", cookies)
                                    .addHeader("X-CSRFToken", csrfToken)
                                    .build();
    }

    private Request createGetRequest(String url) {
        return new Request.Builder().url(url)
                                    .addHeader("User-Agent", USER_AGENT)
                                    .addHeader("Cookie", cookies)
                                    .addHeader("X-CSRFToken", csrfToken)
                                    .build();
    }

    public void login(String cookies, String csrfToken) {
        this.cookies = cookies;
        this.csrfToken = csrfToken;
    }
}
