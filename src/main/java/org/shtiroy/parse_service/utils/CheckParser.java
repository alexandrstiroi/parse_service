package org.shtiroy.parse_service.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.shtiroy.parse_service.entity.Resource;
import org.shtiroy.parse_service.repository.CompanyJSONRepository;
import org.shtiroy.parse_service.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class CheckParser {
    private static final Logger LOGGER = LogManager.getLogger(CheckParser.class.getName());
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private CompanyJSONRepository companyJSONRepository;

    /**
     * Метод проверяет все ресурсы на работоспособность.
     * @return - json.
     */
    public Object checkParser(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode checkService = objectMapper.createObjectNode();
        ArrayNode resourceList = objectMapper.createArrayNode();
        List<Resource> resources = resourceRepository.findByResourceIsActive(true);
        for (Resource resource : resources){
            checkService.put("resource",resource.getResourceName());
            LocalDateTime maxCreateTs = companyJSONRepository.getMaxCreateTs(resource.getResourceName()).toLocalDateTime();
            LocalDateTime currentDateTime = LocalDateTime.now();
            int count = companyJSONRepository.getCount(resource.getResourceName());
            long seconds = Duration.between(maxCreateTs,currentDateTime).getSeconds();
            if (seconds >= 300){
                checkService.put("status","stop");
                checkService.put("last_update",maxCreateTs.format(formatter));
            } else {
                checkService.put("status","run");
                checkService.put("last_update",maxCreateTs.format(formatter));
            }
            checkService.put("count", count);
            resourceList.add(checkService);
        }
        return resourceList;
    }

    /**
     * Метод проверяет конкретный ресурс.
     * @param resourceName - название ресурса.
     * @return - json.
     */
    public Object checkServiceResource(String resourceName){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode checkService = objectMapper.createObjectNode();
        Resource resource = resourceRepository.findByResourceName(resourceName);
        checkService.put("resource",resource.getResourceName());
        LocalDateTime maxCreateTs = companyJSONRepository.getMaxCreateTs(resource.getResourceName()).toLocalDateTime();
        LocalDateTime currentDateTime = LocalDateTime.now();
        int count = companyJSONRepository.getCount(resource.getResourceName());
        long seconds = Duration.between(maxCreateTs,currentDateTime).getSeconds();
        if (seconds >= 300){
            checkService.put("status","stop");
            checkService.put("last_update",maxCreateTs.format(formatter));
        } else {
            checkService.put("status","run");
            checkService.put("last_update",maxCreateTs.format(formatter));
        }
        checkService.put("count", count);
        return checkService;
    }

    /**
     * Метод проверяет конкретный ресурс.
     * @param resourceName - название ресурса.
     * @param check -
     * @return - boolean.
     */
    public boolean checkServiceResource(String resourceName, boolean check){
        long seconds = 0;
        try {
            LocalDateTime maxCreateTs = companyJSONRepository.getMaxCreateTs(resourceName).toLocalDateTime();
            LocalDateTime currentDateTime = LocalDateTime.now();
            seconds = Duration.between(maxCreateTs,currentDateTime).getSeconds();
        } catch (Exception ex){
           return false;
        }
        return seconds < 300;
    }
}
