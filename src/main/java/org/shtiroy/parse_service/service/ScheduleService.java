package org.shtiroy.parse_service.service;

import org.apache.catalina.LifecycleState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.shtiroy.parse_service.entity.Resource;
import org.shtiroy.parse_service.repository.ResourceRepository;
import org.shtiroy.parse_service.resource.data2b.Data2bResource;
import org.shtiroy.parse_service.utils.CheckParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
public class ScheduleService {
    private static final Logger LOGGER = LogManager.getLogger(ScheduleService.class.getName());
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private CheckParser checkParser;
    @Autowired
    private Data2bResource data2bResource;

    /**
     * Метод запускает процесс парсинга ресурсов в отдельные потоки.
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void runParseResources(){
        LOGGER.info("runParseResources");
        List<Resource> resourceList = resourceRepository.findByResourceIsActive(true);
        for (Resource resource : resourceList){
            switch (resource.getResourceName()){
                case "data2b.md": {
                    if (!checkParser.checkServiceResource(resource.getResourceName(),true)){
                       Thread data2b = new Thread(() -> {
                            while(true){
                                if (!data2bResource.getJSONFromUrl(resource.getResourceUrl()))
                                    break;
                                try{
                                    Thread.sleep(30000);
                                } catch (InterruptedException ex) {
                                    LOGGER.error(ex.getMessage());
                                }
                            }
                        });
                       data2b.setName(resource.getResourceName());
                       data2b.start();
                    }
                }
                break;
            }
        }
    }
}
