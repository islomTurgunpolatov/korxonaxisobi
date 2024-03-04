package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/api/add/employee").permitAll()
                        .requestMatchers("/api/department").permitAll()
                        .requestMatchers("/api/list","/api/one/employee/**","/api/delete/employee","/api/update/**").permitAll()
                        .requestMatchers("/api/account","/api/client","/api/client/**").permitAll()
                        .requestMatchers("/api/adds","/api/adds/list","/api/adds/param").permitAll()
                        .requestMatchers("/api/").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}