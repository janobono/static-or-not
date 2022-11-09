package sk.janobono.oocp.pso.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import sk.janobono.oocp.common.LocalStorage;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Slf4j
public class PsoLocalStorage extends LocalStorage {

    @PostConstruct
    public void init() {
        super.init();
        log.debug("Local storage initialized. {}", storageLocation);
    }

    @PreDestroy
    public void clean() {
        super.clean();
        log.debug("Local storage cleaned. {}", storageLocation);
    }
}
