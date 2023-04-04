package ma.learn.quiz;

import ma.learn.quiz.migration.DataBaseMigration;
import ma.learn.quiz.migration.DriveApiService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class MyConfiguration {
    @Bean
    public DataBaseMigration DataBaseMigration() {
        return new DataBaseMigration();
    }

    @Bean
    public DriveApiService DriveApiService() {
        return new DriveApiService();
    }
}
