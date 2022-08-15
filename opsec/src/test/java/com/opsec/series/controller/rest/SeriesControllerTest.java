package com.opsec.series.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opsec.series.SeriesApplication;
import com.opsec.series.bean.SeriesBean;
import com.opsec.series.model.response.SeriesResponse;
import com.opsec.series.service.rest.SeriesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SeriesControllerTest {

    @InjectMocks
    private SeriesService seriesService = new SeriesService();

    @Test
    void getSeriesPageForTitle() throws JsonProcessingException, ExecutionException, InterruptedException {

        String expected = getSuccessResponse();
        String actualResponse= convertJsonToString(seriesService.getPage("ncis").get());

        assertEquals(expected, actualResponse);

    }

    @Test
    void getSeriesPageForEmptyTitle() throws JsonProcessingException, ExecutionException, InterruptedException {

        String expected = getEmptyTitleResponse();
        String actualResponse= convertJsonToString(seriesService.getPage("").get());

        assertEquals(expected, actualResponse);

    }

    @Test
    void getSeriesPageForNullTitle() throws JsonProcessingException, ExecutionException, InterruptedException {

        String expected = getNullTitleResponse();
        String actualResponse= convertJsonToString(seriesService.getPage(null).get());

        assertEquals(expected, actualResponse);

    }

    private String getSuccessResponse(){

        String response = "{\"responseCode\":0,\"responseMessage\":\"success\",\"series\":{\"title\"" +
                ":\"ncis\",\"href\":\"https://www.watchseries1.video/ncis\"}}";

        return response;
    }

    private String getEmptyTitleResponse(){

        String response = "{\"responseCode\":0,\"responseMessage\":\"success\",\"series\"" +
                ":{\"title\":\"\",\"href\":\"https://www.watchseries1.video/all-series/\"}}";

        return response;
    }

    private String getNullTitleResponse(){

        String response = "{\"responseCode\":0,\"responseMessage\":\"success\",\"series\":{\"title\"" +
                ":null,\"href\":\"https://www.watchseries1.video/all-series/\"}}";

        return response;
    }

    public String convertJsonToString(Object object) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        String stringValue = mapper.writeValueAsString(object);

        return stringValue;
    }
}