package com.zerodhatech.backoffice;

import java.util.HashMap;
import java.util.Map;

public class Routes {
    public Map<String, String> routes;
    private final static String _rootUrl = "https://console.zerodha.com/api";

    // Initialize all routes,
    public Routes() {
        routes =
                new HashMap<>() {
                    {
                        put("reports.holdings.breakdown", "/reports/holdings/breakdown");
                        put("reports.holdings.portfolio", "/reports/holdings/portfolio");
                        put("tags", "/tags");
                        put("tags.holdings", "/tags/holdings");
                        put("tags.queue", "/tags/queue");
                    }
                };
    }

    public String get(String key) {
        return _rootUrl + routes.get(key);
    }
}
