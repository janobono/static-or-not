package sk.janobono.oocp.pst.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sk.janobono.ccl.RandomString;

@Configuration
public class PstUtilConfig {

    @Bean
    public RandomString randomString() {
        return RandomString.INSTANCE();
    }
}
