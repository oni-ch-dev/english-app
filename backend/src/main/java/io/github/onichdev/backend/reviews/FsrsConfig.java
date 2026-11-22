package io.github.onichdev.backend.reviews;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.openspacedrepetition.Scheduler;

@Configuration
public class FsrsConfig {
    @Bean
    public Scheduler scheduler() {
        return Scheduler.builder().build();
    }
}
