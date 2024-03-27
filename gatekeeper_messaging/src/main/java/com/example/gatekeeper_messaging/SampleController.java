package com.example.gatekeeper_messaging;


import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {
    Logger logger = Logger.getLogger(SampleController.class);

    @Autowired
    AmqpTemplate template;

    @RequestMapping("/emit")
    @ResponseBody
    String queue() {
        logger.info("Emit to Gatekeeper");
        template.convertAndSend("Gatekeeper", "Papista-falli");
        return "emit to Gatekeeper";
    }
}
