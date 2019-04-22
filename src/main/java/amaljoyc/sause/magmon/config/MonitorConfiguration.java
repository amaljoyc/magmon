package amaljoyc.sause.magmon.config;

import amaljoyc.sause.magmon.daemon.MagnificentMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class MonitorConfiguration {

    @Autowired
    private MagnificentMonitor magnificentMonitor;

    @Bean
    public ApplicationRunner startMonitorService() {
        return args -> magnificentMonitor.run();
    }
}
