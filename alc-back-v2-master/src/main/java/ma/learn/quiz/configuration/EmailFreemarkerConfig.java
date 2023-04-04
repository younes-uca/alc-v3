package ma.learn.quiz.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

@Configuration
public class EmailFreemarkerConfig {
    @Primary
    @Bean
    public FreeMarkerConfigurationFactoryBean getFreeMarkerConfig() {
        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        bean.setTemplateLoaderPath("classpath:/Templates/");
        return bean;
    }
}
