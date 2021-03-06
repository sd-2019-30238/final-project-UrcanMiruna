package com.books.addict.security;

import com.books.addict.model.Admin;
import com.books.addict.service.readService.AdminServiceR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public SecurityConfig(){
        super();
    }

    @Autowired
    private AdminServiceR staffService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/logorreg", "/readerPage/loginOthers").permitAll()
                .antMatchers("/adminPage/register").permitAll()
                .antMatchers("/writerPage/loginAuth").permitAll()
                .antMatchers("/writerPage/feedback/*", "/readerPage/reviewPage").permitAll()
                .antMatchers("/writerPage/all", "/writerPage/delete", "/writerPage/*").permitAll()
                .antMatchers("/readerPage/*").permitAll()
                .antMatchers("/readerPage/feedbackPage", "/readerPage/delete/*", "/readerPage/getFeedback/*").permitAll()
                .antMatchers("/readerPage/addBook/*", "/readerPage/all").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/adminPage/all")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/logorreg" )
                .permitAll()
                .and()
                .csrf().disable();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        List<Admin> admins = staffService.getAllAdmins();
        for(Admin admin:admins){
            manager.createUser(User.withUsername(admin.getUsername()).password(encoder().encode(admin.getPassword())).roles("ADMIN").build());
        }
        return manager;
    }
    @Bean
    public static PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
