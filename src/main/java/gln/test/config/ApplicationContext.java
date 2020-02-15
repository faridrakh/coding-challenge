package gln.test.config;

import org.springframework.beans.factory.annotation.Value;

public abstract class ApplicationContext {
    public static final String SUCCESSFUL = "SUCCESSFUL";
    public static final String UNSUCCESSFUL = "UNSUCCESSFUL";
    @Value("${app.result_per_page}")
    public Integer RESULT_PER_PAGE;
}
