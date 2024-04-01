package de.seven.product.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AppConfig {

    @Value("${methods.communication}")
    private String communicationMethod;

    @Value("${methods.database}")
    private String databaseMethod;
}