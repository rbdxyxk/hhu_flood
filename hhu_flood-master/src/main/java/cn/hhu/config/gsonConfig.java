package cn.hhu.config;


import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @author tlj
 */
@Configuration
public class gsonConfig {
    @Bean
    public Gson getGson(){
        return new Gson();
    }
}
