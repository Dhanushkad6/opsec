package com.opsec.series.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opsec.series.bean.SeriesBean;

public class SeriesResponse {

    private int responseCode;
    private String responseMessage;
    @JsonProperty("series")
    private SeriesBean seriesBean;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public SeriesBean getSeriesBean() {
        return seriesBean;
    }

    public void setSeriesBean(SeriesBean seriesBean) {
        this.seriesBean = seriesBean;
    }
}
