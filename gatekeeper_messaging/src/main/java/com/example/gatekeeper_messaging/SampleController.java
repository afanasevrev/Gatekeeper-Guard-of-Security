package com.example.gatekeeper_messaging;


import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {
    Logger logger = Logger.getLogger(SampleController.class);

    @Autowired
    private AmqpTemplate template;

    @RequestMapping("/")
    @ResponseBody
    private String home() {
        return "Empty mapping";
    }

    @RequestMapping("/emit/{message}")
    @ResponseBody
    private String queue(@PathVariable("message") String message) {
        logger.info(String.format("Emit '%s'", message));
        String response = (String) template.convertSendAndReceive("Gatekeeper", message);
        logger.info(String.format("Received on producer '%s'", message));
        return String.valueOf("Returned from worker: " + response);
    }
}
