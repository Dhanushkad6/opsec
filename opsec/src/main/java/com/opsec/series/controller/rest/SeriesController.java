package com.opsec.series.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opsec.series.model.response.SeriesResponse;
import com.opsec.series.service.rest.SeriesService;
import com.opsec.series.util.ACTIVITY_STATUS;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/series")
public class SeriesController {

    @Autowired
    private SeriesService seriesService;

    private static final Logger LOGGER = LogManager.getLogger(SeriesController.class);

    @GetMapping(path = "/{title}")
    public ResponseEntity<?> getPage(@PathVariable("title") String title )
            throws ExecutionException, InterruptedException {

        long startTime = System.currentTimeMillis();
        String methodName = "getPage";

        LOGGER.info(methodName + " | TRACE_ID | " + startTime + ACTIVITY_STATUS.START);

        //SeriesResponse seriesResponse = new SeriesResponse();
        CompletableFuture<SeriesResponse>  seriesResponse = new CompletableFuture<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            seriesResponse = seriesService.getPage(title);
            String seriesResponseStr =  mapper.writeValueAsString(seriesResponse.get());
            LOGGER.info(methodName + " | seriesResponse:" + seriesResponseStr);

            return ResponseEntity.ok(seriesResponse.get());
        }catch (Exception e){
            LOGGER.error(methodName + ACTIVITY_STATUS.ERROR + " | Exception:" + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(seriesResponse.get());
        }

    }
}
