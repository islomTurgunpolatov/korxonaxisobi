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

                        //Employee entity permissions
                        .requestMatchers("/api/add/employee"
                                ,"/api/employee/list"
                                ,"/api/one/employee/**").permitAll()
                        .requestMatchers("/api/delete/employee").hasRole("DIRECTOR")

                        //Department and Account entities permissions
                        .requestMatchers("/api/save/department"
                                ,"/api/save/account").hasAnyRole("DIRECTOR","HEAD_OF_DEPARTMENT")

                        //Client entity permissions
                        .requestMatchers("/api/add/client"
                                ,"/api/client/**").permitAll()
                        .requestMatchers("/api/delete/client/").hasAnyRole("DIRECTOR","HEAD_OF_DEPARTMENT")

                        //Advertising(Expenses) entity permissions
                        .requestMatchers("/api/save/adds"
                                ,"/api/adds/list"
                                ,"/api/find/adds/param"
                        ,"/api/update/adds/**").permitAll()
                        .requestMatchers("/api/delete/add/**").hasAnyRole("DIRECTOR","HEAD_OF_DEPARTMENT")

                        //Statistics permissions
                        .requestMatchers("/api/statistics/employee/**").hasAnyRole("DIRECTOR","HEAD_OF_DEPARTMENT")
                        .requestMatchers("/api/statistics/client/date"
                                ,"/api/statistics/active/employee"
                                ,"/api/statistics/top3-employees"
                                ,"/api/statistics/clients/last-month"
                                ,"/api/statistics/date/most_clients-regitered").hasAnyRole("DIRECTOR","HEAD_OF_DEPARTMENT")
                        .requestMatchers("/api/statistics/most-edd-expense"
                                ,"/api/statistics/most-expensive-add-type"
                        ,"/api/statistics/count-adds-within-month"
                        ,"/api/statistics/count-stopped-adds-last-month").hasAnyRole("DIRECTOR","HEAD_OF_DEPARTMENT")

                        //permissions for UserEntity
                        .requestMatchers("/api/save/user").permitAll()
                        .requestMatchers("/api/get/user"
                        ,"/api/delete/user/**").hasAnyRole("DIRECTOR","HEAD_OF_DEPARTMENT")
                        .anyRequest().authenticated())
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
