package org.example.clinicservice.config;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.filter.JwtAuthenticationFilter;
import org.example.clinicservice.security.JwtService;
import org.example.clinicservice.security.UserDetailsServiceImpl;
import org.example.clinicservice.service.interfeces.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Set;

import static org.example.clinicservice.entity.enums.RoleName.ADMIN;
import static org.example.clinicservice.entity.enums.RoleName.SPECIALIST;
import static org.example.clinicservice.entity.enums.UserType.PATIENT;
import static org.example.clinicservice.security.security_util.AuthorityRoleList.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
                .logout(logout -> logout
                        .deleteCookies("JSESSIONID")
                        .logoutUrl("/logout"))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                        .requestMatchers(SPECIALIST_LIST).hasRole(String.valueOf(SPECIALIST))
                        .requestMatchers(PATIENT_LIST).hasRole(String.valueOf(PATIENT))
                        .requestMatchers(ADMIN_LIST).hasRole(String.valueOf(ADMIN))
                        .anyRequest().authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(new JwtAuthenticationFilter(jwtService, userDetailsService), UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .oauth2Login(config -> config
                        .loginPage("/login")
                        .defaultSuccessUrl("/users")
                        .userInfoEndpoint(userInfo -> userInfo.oidcUserService(oidcUserService())));
        return http.build();
    }

    private OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService(){
        return userRequest -> {
            String email = userRequest.getIdToken().getClaim("email");
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            return new DefaultOidcUser(userDetails.getAuthorities(), userRequest.getIdToken());
        };

//        return userRequest -> {
//           String email = userRequest.getIdToken().getClaim("email");
//          UserDetails userDetails = userDetailsService.loadUserByUsername(email);
//         DefaultOidcUser oidcUser = new DefaultOidcUser(userDetails.getAuthorities(), userRequest.getIdToken());
//            Set<Method> userDetailsMethod = Set.of(userDetails.getClass().getMethods());
//            return Proxy.newProxyInstance(SecurityConfig.class.getClassLoader(),
//                    new Class[]{UserDetails.class, OidcUser.class}, (proxy, method, args) -> userDetailsMethod.contains(method)
//            ? method.invoke(userDetails, args)
//                    : method.invoke(oidcUser, args));
//        };
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }


}
