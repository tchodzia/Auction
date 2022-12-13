package sda.project.auction.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterCHain(HttpSecurity http) throws Exception {

        return http.authorizeHttpRequests()
                .requestMatchers("/update/user/**").authenticated()
                .requestMatchers("/").permitAll()
                .requestMatchers("/signup").permitAll()
                .requestMatchers("").permitAll()
                .requestMatchers("/auctions/**").permitAll()
                .requestMatchers("/images/**").permitAll()
                .requestMatchers("/css/styles.css").permitAll()
                .requestMatchers("/webjars/**").permitAll()
                .requestMatchers("/auctions/create").permitAll()
                .requestMatchers("/auctions/create/**").permitAll()
                .requestMatchers("/auctions/add/**").permitAll()
                .requestMatchers("/auctions/add").permitAll()
                .requestMatchers("/auctions/update").permitAll()
                .requestMatchers("/upload").permitAll()
                .requestMatchers("/files").permitAll()
                .requestMatchers("/files/**").permitAll()
                .and().formLogin()
                .and().httpBasic()
                .and().logout()
                .and().csrf().disable()
                .build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
