package sk.janobono.oocp.pso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sk.janobono.oocp.common.CCLImageUtil;

@Configuration
public class PsoUtilConfig {

    @Bean
    public CCLImageUtil imageUtil() {
        return new CCLImageUtil();
    }
}
