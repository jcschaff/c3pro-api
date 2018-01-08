package edu.uconn.c3pro.server.api.config;

import org.bch.c3pro.server.external.KeyValueStorage;
import org.bch.c3pro.server.external.Queue;
import org.bch.c3pro.server.external.S3Access;
import org.bch.c3pro.server.external.SQSAccess;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfig {
	
	@Bean
	public Queue sqsQueue() {
		return new SQSAccess();
		
	}
 
	@Bean
	public KeyValueStorage s3KeyValueStorage() {
		return new S3Access();
		
	}
 
//	@Autowired
//    JwtAccessTokenConverter jwtAccessTokenConverter;
//	 
//	@Bean
//	public TokenStore tokenStore() {
//	    return new JwtTokenStore(jwtAccessTokenConverter);
//	}
//	
//    @Bean
//    protected JwtAccessTokenConverter jwtTokenEnhancer() {
//        JwtAccessTokenConverter converter =  new JwtAccessTokenConverter();
//        Resource resource = new ClassPathResource("public.cert");
//        String publicKey = null;
//        try {
//            publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        converter.setVerifierKey(publicKey);
//        return converter;
//    }

}