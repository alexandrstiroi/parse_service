package org.shtiroy.parse_service.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.shtiroy.parse_service.utils.CheckParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ParseController {
    private static final Logger LOGGER = LogManager.getLogger(ParseController.class.getName());
    @Autowired
    private CheckParser checkParser;

    /**
     * Метод возвращает json объек, список ресурсов.
     * @return - json.
     */
    @PostMapping("/check_service")
    public ResponseEntity<Object> getCheckService(){
        LOGGER.info("call checkService");
        Object response = checkParser.checkParser();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
