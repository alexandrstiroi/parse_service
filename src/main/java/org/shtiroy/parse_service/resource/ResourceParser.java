package org.shtiroy.parse_service.resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.shtiroy.parse_service.utils.UserAgent;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.Arrays;

public abstract class ResourceParser {
    private static final Logger LOGGER = LogManager.getLogger(ResourceParser.class.getName());

    public abstract boolean getJSONFromUrl(String url);

    protected Object methodGet(String urlStr){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("user-agent", UserAgent.randomUserAgent().getDescription());
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        try{
            ResponseEntity<String> response = restTemplate.getForEntity(urlStr, String.class, entity);
            return response.getBody();
        } catch (HttpServerErrorException ex){
            LOGGER.error(ex.getMessage());
        }
        return null;
    }
}
