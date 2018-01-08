package edu.uconn.c3pro.server.auth;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import edu.uconn.c3pro.server.api.controller.FhirenvController;
import edu.uconn.c3pro.server.api.controller.QuestionareController;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class C3proServerResourceApplicationTests {

	@TestConfiguration
	static class C3ProServerAuthApplicationTestsContextConfiguration {
//		@Bean
//		public AuthDatabase authDatabase() {
//			AuthDatabase authDatabase = Mockito.mock(AuthDatabase.class);
//			return authDatabase;
//		}
//		@Bean
//		public AppleReceiptVerifier appleReceiptVerifier() {
//			AppleReceiptVerifier verifier = Mockito.mock(AppleReceiptVerifier.class);
//			return verifier;
//		}
//	    @Bean
//	    public AntispamFilter antispamFilter() {
//	    		AntispamFilter antispamFilter = Mockito.mock(AntispamFilter.class);
//	    		return antispamFilter;
//	    }		
//	    @Bean
//	    public CredentialGenerator credentialsGenerator() {
//	    		CredentialGenerator credentialGenerator = Mockito.mock(CredentialGenerator.class);
//	    		return credentialGenerator;
//	    }		
	}

	@Autowired
    private QuestionareController questionnaireController;


	@Autowired
    private FhirenvController fhirenvController;

	@Test
	public void contextLoads() {
		Assertions.assertThat(questionnaireController).isNotNull();
		Assertions.assertThat(fhirenvController).isNotNull();
	}
}
