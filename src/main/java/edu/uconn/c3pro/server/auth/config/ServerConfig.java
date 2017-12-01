package edu.uconn.c3pro.server.auth.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
public class ServerConfig {
 
	@Autowired
	private Environment env;
	 
	@Bean
	public DataSource dataSource() {
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
	    dataSource.setUrl(env.getProperty("jdbc.url"));
	    dataSource.setUsername(env.getProperty("jdbc.user"));
	    dataSource.setPassword(env.getProperty("jdbc.pass"));
	    return dataSource;
	}
	 
	@Bean
	public TokenStore tokenStore() {
	    return new JdbcTokenStore(dataSource());
	}
	
//	@Primary
//	@Bean
//	public RemoteTokenServices tokenService() {
//	    RemoteTokenServices tokenService = new RemoteTokenServices();
//	    tokenService.setCheckTokenEndpointUrl(
//	      "http://localhost:8080/spring-security-oauth-server/oauth/check_token");
//	    tokenService.setClientId("fooClientIdPassword");
//	    tokenService.setClientSecret("secret");
//	    return tokenService;
//	}
}