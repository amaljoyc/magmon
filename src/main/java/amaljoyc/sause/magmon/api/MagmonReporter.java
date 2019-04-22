package amaljoyc.sause.magmon.api;

import amaljoyc.sause.magmon.service.FailureReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MagmonReporter {

    @Autowired
    private FailureReportingService reportingService;

    @GetMapping("/report/{rangeInMins}")
    public MagmonReport getMonitoringReport(@PathVariable("rangeInMins") Integer rangeInMins) {
        return reportingService.getFailureReport(rangeInMins);
    }
}
