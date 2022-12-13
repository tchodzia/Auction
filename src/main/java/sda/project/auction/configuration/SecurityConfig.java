package sda.project.auction.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig{

    private final UserDetailsService userDetailsService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.authorizeHttpRequests()
                .requestMatchers("/update/user/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .requestMatchers("/", "", "/signup").permitAll()
                .requestMatchers("/css/styles.css", "/webjars/**", "/images/**").permitAll()
                .requestMatchers("/auctions/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .requestMatchers("/update/save").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .requestMatchers("/delete/user/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .anyRequest().permitAll()
                .and().formLogin()
                .and().httpBasic()
                .and().logout()
                .and().csrf().disable()
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
