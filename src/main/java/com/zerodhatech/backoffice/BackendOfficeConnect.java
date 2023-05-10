package com.zerodhatech.backoffice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.zerodhatech.backoffice.models.breakdown.BreakdownResponse;
import com.zerodhatech.backoffice.models.holdings.PortfolioHoldingsResponse;
import com.zerodhatech.backoffice.models.tags.Tag;
import com.zerodhatech.backoffice.models.tags.TagHoldings;
import com.zerodhatech.kiteconnect.kitehttp.SessionExpiryHook;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.Proxy;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BackendOfficeConnect {
    private final Routes routes = new Routes();
    private final Gson gson;
    public static SessionExpiryHook sessionExpiryHook = null;
    public static boolean ENABLE_LOGGING = false;
    private Proxy proxy;
    private final BackendOfficeRequestHandler requestHandler;

    public BackendOfficeConnect(Proxy proxy, boolean enableDebugLog) {
        this.proxy = proxy;
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, (JsonDeserializer<Date>) (jsonElement, type, jsonDeserializationContext) -> {
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return format.parse(jsonElement.getAsString());
            } catch (ParseException e) {
                return null;
            }
        });
        gson = gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        ENABLE_LOGGING = enableDebugLog;
        requestHandler = new BackendOfficeRequestHandler(proxy);
    }

    public void login(String cookies, String csrfToken) {
        this.requestHandler.login(cookies, csrfToken);
    }

    public List<Tag> getTags() throws KiteException, JSONException, IOException {
        JSONObject response = requestHandler.getRequest(routes.get("tags"));
        var data = response.getJSONObject("data");
        var result = data.getJSONArray("result");
        return Arrays.asList(gson.fromJson(String.valueOf(result), Tag[].class));
    }

    public TagHoldings getTagHoldings() throws IOException, KiteException {
        JSONObject response = requestHandler.getRequest(routes.get("tags.holdings"));
        var data = response.getJSONObject("data");
        return gson.fromJson(String.valueOf(data), TagHoldings.class);
    }

    public PortfolioHoldingsResponse getHoldings(LocalDate date) throws IOException, KiteException {
        JSONObject response = requestHandler.getRequest(routes.get("reports.holdings.portfolio"), Map.of("date", date));
        var data = response.getJSONObject("data");
        return gson.fromJson(String.valueOf(data), PortfolioHoldingsResponse.class);
    }

    public BreakdownResponse getBreakDown(String instrumentId, String segment) throws IOException, KiteException {
        JSONObject response = requestHandler.getRequest(routes.get("reports.holdings.breakdown"), Map.of("instrument_id", instrumentId, "segment", segment));
        var data = response.getJSONObject("data");
        return gson.fromJson(String.valueOf(data), BreakdownResponse.class);
    }
}
