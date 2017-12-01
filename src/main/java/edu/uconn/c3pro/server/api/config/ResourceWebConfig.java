package edu.uconn.c3pro.server.api.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan({ "org.baeldung.web.controller" })
public class ResourceWebConfig extends WebMvcConfigurerAdapter {}
