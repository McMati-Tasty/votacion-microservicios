package com.microservicio.bff_gateway.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


 
@Component
public class CustomResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        
        return response.getStatusCode().is4xxClientError() || 
               response.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
      
        String body = new String(response.getBody().readAllBytes(), StandardCharsets.UTF_8);

       
        throw HttpClientErrorException.create(
            response.getStatusCode(),
            response.getStatusText(),
            response.getHeaders(),
            body.getBytes(StandardCharsets.UTF_8), 
            StandardCharsets.UTF_8
        );
    }
}
