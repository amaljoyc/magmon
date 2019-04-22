package amaljoyc.sause.magmon.service;

import amaljoyc.sause.magmon.api.MagmonReport;
import amaljoyc.sause.magmon.persistence.StatusFailure;
import amaljoyc.sause.magmon.persistence.StatusFailureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class FailureReportingService {

    @Value("${monitor.frequency.inSeconds}")
    private int monitorFrequencyInSecs;

    @Autowired
    private StatusFailureRepository statusFailureRepository;

    public MagmonReport getFailureReport(Integer rangeInMins) {
        Instant since = LocalDateTime.now().minusMinutes(rangeInMins).
                atZone(ZoneId.systemDefault()).toInstant();
        List<StatusFailure> failures = statusFailureRepository.findAllFailuresSince(Date.from(since));

        float failureCount = failures.size();
        float totalHealthCheckPerMin = (60 / monitorFrequencyInSecs);
        float totalHealthCheckPerReportingRange = totalHealthCheckPerMin * rangeInMins;
        String failureRate = (failureCount / totalHealthCheckPerReportingRange * 100f) + "%";

        return new MagmonReport((int) failureCount, failureRate);
    }

    public void addNewFailure() {
        StatusFailure failure = new StatusFailure();
        statusFailureRepository.save(failure);
    }
}