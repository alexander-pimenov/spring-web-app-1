package com.geekbrains.springwebapp.configs;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.stereotype.Component;

/**
 * Этот класс указывает Спрингу, что мы хотим включить безопасность.
 */

@Component
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

}
