package com.vanchutin;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.vanchutin.event.Event;
import com.vanchutin.generator.EventGenerator;
import com.vanchutin.httpClient.HttpClient;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Application {

    public static void main(String[] args) throws Exception {

        EventGenerator eventGenerator = new EventGenerator();
        ObjectMapper objectMapper = new ObjectMapper();
        HttpClient httpClient = new HttpClient();

        while(true){
            Event newEvent = eventGenerator.generate();
            String message = objectMapper.writeValueAsString(newEvent);
            log.info(String.format("Sending message...%s", message));
            httpClient.post(message);
            Thread.sleep(1000);
        }
    }
}
