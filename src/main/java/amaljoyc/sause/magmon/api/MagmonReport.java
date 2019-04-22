package amaljoyc.sause.magmon.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MagmonReport {

    private Integer totalFailureCount;
    private String failureRate;

    public MagmonReport(Integer count, String rate) {
        this.totalFailureCount = count;
        this.failureRate = rate;
    }
}
