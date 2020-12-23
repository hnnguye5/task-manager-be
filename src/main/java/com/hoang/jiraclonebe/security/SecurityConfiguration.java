package com.hoang.jiraclonebe.security;

import com.hoang.jiraclonebe.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.hoang.jiraclonebe.security.SecurityConstants.H2_URL;
import static com.hoang.jiraclonebe.security.SecurityConstants.SIGN_UP_URL;

/**
 * The class handles the security configuration of the application.
 *
 * @author Hoang Nguyen
 * @version 1.0, 7 Nov 2020
 */
@Configurable
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthHandler;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    /**
     * Builds the authentication and make sure User is valid.
     *
     * @param  authenticationManagerBuilder       builds up the authentication.
     * @return                                    all of the authentication.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(customUserDetailService).passwordEncoder(bCryptPasswordEncoder);
    }

    /**
     * Manages the authentication.
     *
     * @return      authentication manager.
     */
    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /**
     * Handles the default security settings.
     *
     * @param  http       the application routes.
     * @return            allows authorization within configurations.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable() //disable since we using JWT
                .exceptionHandling().authenticationEntryPoint(unauthHandler) // send error response if user is unauthorized
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //server does not hold sessions(no state, uses JTW)
                .and()
                .headers().frameOptions().sameOrigin() // allows uses H2 Database
                .and()
                .authorizeRequests() // allow routes
                .antMatchers(
                        "/",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()
                .antMatchers(SIGN_UP_URL).permitAll() // testing creating user without enter password
                .antMatchers(H2_URL).permitAll()
                .anyRequest().authenticated(); // any other request, we need authentication

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
