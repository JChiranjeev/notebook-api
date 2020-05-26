package dev.jainchiranjeev.Utils;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CustomEncoder {

    static CustomEncoder customEncoder = null;

    public static CustomEncoder getInstance() {
        if(customEncoder == null) {
            customEncoder = new CustomEncoder();
        }
        return customEncoder;
    }
    
    @Bean
    public BCryptPasswordEncoder getEncoder() {
        return customEncoder.getEncoder();
    }
}