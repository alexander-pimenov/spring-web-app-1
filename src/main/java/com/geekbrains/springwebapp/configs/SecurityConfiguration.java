package com.geekbrains.springwebapp.configs;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Этот класс указывает Спрингу, что Пользователей нужно дергать из БД,
 * и какие Порльзователи куда могут обращаться.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    //За подключение к БД отвечает бин DataSource
    //настраивается он в application.properties
    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /* Переопределим метод configure.
     * И далее в методе говорим, что мы хотим использовать
     * jdbcAuthentication() т.е. аутентификация пользователя будет
     * проходить через БД, и сюда прокидываем dataSource, чтобы
     * получить ссылку на источник данных.
     * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }

    /* Переопределим другой метод configure.
     * Мы позволяем Пользователю гулять по всему сайту (anyRequest().permitAll())
     * но в Детали может заходить только АДМИН (antMatchers("/details/**)
     * .hasAnyRole("ADMIN")
     * Для Логина мы используем Форму Логина, чтоб в нее попасть нужно
     * постучаться в "/login" (formLogin().loginPage("/login").permitAll())
     * и в качестве проверки Пользователя будет выступать этот адрес -
     * "/authenticateTheUser" (loginProcessingUrl("/authenticateTheUser")).
     * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/details/**").hasAnyRole("ADMIN")
                .anyRequest().permitAll()
                .and().formLogin().loginPage("/login").permitAll()
                .loginProcessingUrl("/authenticateTheUser");
    }
}
