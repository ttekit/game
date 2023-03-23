package com.example.clientsservice.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


import static com.example.clientsservice.models.User.Role.ADMIN;
import static com.example.clientsservice.models.User.Role.USER;

@Configuration
public class Config {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    InMemoryUserDetailsManager inMemory(BCryptPasswordEncoder encoder) {
        return new InMemoryUserDetailsManager(
                User.builder().passwordEncoder(encoder::encode).username("root").password("root").roles(ADMIN.name()).build(),
                User.builder().passwordEncoder(encoder::encode).username("u").password("u").roles(USER.name()).build()
        );
    }

    @Bean
    public AuthenticationManager authManager(
            HttpSecurity security,
            BCryptPasswordEncoder encoder,
            UserDetailsService userDetailsService
    ) throws Exception {
        System.err.println("authManager");
        return security
                .getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder)
                .and().build();
    }

    @Bean
    public WebSecurityCustomizer securityCustomizer() {
        return customizer ->
                customizer.debug(false)
                        .ignoring()
                        .antMatchers("/css/**")
                        .antMatchers("/register")
                        .antMatchers("/js/registerUser.js")
                        .mvcMatchers(HttpMethod.POST, "/register")
                        .mvcMatchers(HttpMethod.POST, "/user/submitRegister");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity secure) throws Exception {

        secure.authorizeRequests()
                .antMatchers("/error", "/register")
                .permitAll()
                .mvcMatchers(
                        "/games",
                        "/games/game"
                )
                .authenticated()
                .mvcMatchers(
                        "/admin",
                        "/admin/editUsers",
                        "/admin/editGames"

                )
                .hasAuthority(
                        ADMIN.name()
                )
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/loginAction")
                .defaultSuccessUrl("/")
                .and()
                .logout()
                //               .logoutUrl("j_spring_security_logout")
                .logoutSuccessUrl("/login")
                .and()
                .csrf()
                .disable()
        ;
        return secure.build();
    }

}
