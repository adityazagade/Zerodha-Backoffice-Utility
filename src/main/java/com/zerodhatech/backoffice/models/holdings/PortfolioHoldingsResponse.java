package com.zerodhatech.backoffice.models.holdings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PortfolioHoldingsResponse {
    public String state;
    public Result result;
    public Object pagination;
}