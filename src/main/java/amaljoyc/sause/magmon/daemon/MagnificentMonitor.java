package amaljoyc.sause.magmon.daemon;

import amaljoyc.sause.magmon.service.FailureReportingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class MagnificentMonitor {

    @Value("${magnificent.server.url}")
    private String serverUrl;

    @Value("${monitor.frequency.inSeconds}")
    private int monitorFrequencyInSecs;

    private RestTemplate restTemplate;

    @Autowired
    public MagnificentMonitor(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Autowired
    private FailureReportingService reportingService;

    @Async
    public void run() {
        log.info("Starting the monitoring daemon...");
        while (true) {
            try {
                ResponseEntity<String> reponse = restTemplate.exchange(serverUrl,
                        HttpMethod.GET, null, String.class);
                log.info(reponse.getStatusCode().toString());
            } catch (HttpServerErrorException e) {
                log.warn("HEALTH CHECK FAILED: " + e.getMessage());
                reportingService.addNewFailure();
            }

            try {
                TimeUnit.SECONDS.sleep(monitorFrequencyInSecs);
            } catch (InterruptedException e) {
                log.error("Exception during the wait from daemon", e);
            }
        }
    }
}
