package com.prueba.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestService {

    private static final Logger logger = Logger.getLogger(RestService.class.getName());

    private final RestTemplate restTemplate;

    public <T> T restGET(String url, Class resultClazz, Map<String, String> customHeaders) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            if (customHeaders != null) {
                customHeaders.forEach(headers::set);
            }
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
            ResponseEntity<T> response = restTemplate.exchange(url,
                    HttpMethod.GET, requestEntity, resultClazz);
            return response.getBody();
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            logger.log(Level.SEVERE, String.format("Error HTTP %d al consumir %s: %s",
                    ex.getStatusCode().value(), url, ex.getResponseBodyAsString()), ex);
        } catch (ResourceAccessException ex) {
            logger.log(Level.SEVERE, "Error de conexión al acceder a: " + url, ex);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error inesperado en restGET: " + url, ex);
        }
        return null;
    }


    public <T> List<T> restGETList(String url, Class<T> resultClazz, Map<String, String> customHeaders) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            if (customHeaders != null) {
                customHeaders.forEach(headers::set);
            }
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
            ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                    url, HttpMethod.GET, requestEntity,
                    new ParameterizedTypeReference<List<Map<String, Object>>>() {});
            ObjectMapper mapper = new ObjectMapper();
            List<Map<String, Object>> rawList = response.getBody();
            return rawList.stream()
                    .map(map -> mapper.convertValue(map, resultClazz))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error en restGETList: " + url, e);
            return Collections.emptyList();
        }
    }

}
