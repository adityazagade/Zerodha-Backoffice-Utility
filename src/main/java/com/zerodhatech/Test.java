package com.zerodhatech;

import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException, KiteException {
        test();
    }

    private static void test() throws IOException, KiteException {
        KiteConnect kiteSdk = new KiteConnect(KiteConnectConstants.API_KEY);
        kiteSdk.setUserId(KiteConnectConstants.USER_ID);
        String url = kiteSdk.getLoginURL();
        // Get accessToken as follows,
        var user = kiteSdk.generateSession(KiteConnectConstants.REQUEST_TOKEN, KiteConnectConstants.API_SECRET);

        // Set request token and public token which are obtained from login process.
        kiteSdk.setAccessToken(user.accessToken);
        kiteSdk.setPublicToken(user.publicToken);

        kiteSdk.setAccessToken(KiteConnectConstants.ACCESS_TOKEN);
        kiteSdk.setPublicToken(KiteConnectConstants.PUBLIC_TOKEN);

        // Set session expiry callback.
        kiteSdk.setSessionExpiryHook(() -> System.out.println("session expired"));

        var holdings = kiteSdk.getHoldings();
        var mfHoldings = kiteSdk.getMFHoldings();
    }
}
