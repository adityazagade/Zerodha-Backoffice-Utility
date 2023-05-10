package com.zerodhatech.backoffice.models.tags;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TagHoldings {
  private String state;
  private Result result;
  private Object pagination;

  @AllArgsConstructor
  @NoArgsConstructor
  @Data
  static class Result {
    private ArrayList<Eq> eq;
    private ArrayList<Mf> mf;
  }

  @AllArgsConstructor
  @NoArgsConstructor
  @Data
  static class Eq {
    @SerializedName("instrument_id")
    private String instrumentId;

    @SerializedName("tradingsymbol")
    private String tradingSymbol;

    private ArrayList<Integer> tags;
  }

  @AllArgsConstructor
  @NoArgsConstructor
  @Data
  static class Mf {
    @SerializedName("instrument_id")
    private String instrumentId;

    @SerializedName("tradingsymbol")
    private String tradingSymbol;

    private ArrayList<Integer> tags;
  }
}
