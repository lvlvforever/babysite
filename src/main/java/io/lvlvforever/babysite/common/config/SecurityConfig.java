package io.lvlvforever.babysite.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

/**
 * Created by lvlvforever on 2019/4/6.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        System.err.println(encoder.encode("daipeng322"));

        manager.createUser(User.withUsername("daipeng").password(encoder.encode("daipeng322")).roles("ADMIN").build());
        manager.createUser(User.withUsername("dai").password(encoder.encode("daipeng123")).roles("USER").build());
        return manager;
    }
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .anyRequest().permitAll()
//                .and()
//                .formLogin()
//                .defaultSuccessUrl("/admin",true)
//                .and()
//                .httpBasic();
        http.authorizeRequests().anyRequest().permitAll();
        http.csrf().disable();
        System.err.println(http);
    }
}
