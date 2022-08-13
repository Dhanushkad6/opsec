package com.opsec.series.service.rest;

import com.opsec.series.bean.SeriesBean;
import com.opsec.series.model.response.SeriesResponse;
import com.opsec.series.util.ACTIVITY_STATUS;
import com.opsec.series.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;


@Service
public class SeriesService {

    //@Value("${series.domain.url}")
    private String seriesDomainUrl = "https://www.watchseries1.video/";
    private static final Logger LOGGER = LogManager.getLogger(SeriesService.class);


    public SeriesResponse getPage(String title) {

        long startTime = System.currentTimeMillis();
        String methodName = "getPage | TRACE_ID | " + startTime;

        LOGGER.info(methodName + " | TRACE_ID | " + startTime + ACTIVITY_STATUS.START + ACTIVITY_STATUS.SERVICE_START);

        SeriesResponse seriesResponse = new SeriesResponse();
        SeriesBean seriesBean = new SeriesBean();

        int responseCode = Constants.SUCCESS_CODE;
        String responseMessage = Constants.SUCCESS;

        seriesBean.setTitle(title);

        URL domain = null;
        URL url = null;
        try {
            domain = new URL(seriesDomainUrl);
            url = new URL(domain + title);
        } catch (MalformedURLException e) {
            LOGGER.error(methodName , ACTIVITY_STATUS.ERROR,
                    ACTIVITY_STATUS.SERVICE_END, "| MalformedURLException occurred: " + e.getMessage());
        }

        LOGGER.info(methodName + "| url: "+ url.toString());

        if (url ==null ) {
            responseCode = Constants.FAILURE_CODE;
            responseMessage = Constants.FAILURE;
        }

        seriesBean.setHref(url);
        seriesResponse.setResponseCode(responseCode);
        seriesResponse.setResponseMessage(responseMessage);

        seriesResponse.setSeriesBean(seriesBean);

        LOGGER.info(methodName + ACTIVITY_STATUS.END + ACTIVITY_STATUS.SERVICE_END,
                "| responseMessage: "+ seriesResponse.getResponseMessage());

        return seriesResponse;

    }
}
