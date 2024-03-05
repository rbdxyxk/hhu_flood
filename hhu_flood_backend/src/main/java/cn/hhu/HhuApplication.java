package cn.hhu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author tlj
 */
@EnableTransactionManagement
@SpringBootApplication
public class HhuApplication {

    public static void main(String[] args) {

        SpringApplication.run(HhuApplication.class, args);
    }

}
