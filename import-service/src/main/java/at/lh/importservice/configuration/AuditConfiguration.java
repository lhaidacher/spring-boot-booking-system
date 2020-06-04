package at.lh.importservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditConfiguration {
    @Bean
    public DateTimeProvider dateAuditing() {
        return CurrentDateTimeProvider.INSTANCE;
    }
}
