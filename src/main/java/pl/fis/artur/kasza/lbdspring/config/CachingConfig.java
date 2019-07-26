package pl.fis.artur.kasza.lbdspring.config;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;


@Configuration
@EnableCaching
public class CachingConfig {
 
    @Bean
    public Cache cache(){
        return (Cache) CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.MINUTES).build();
    }
}
