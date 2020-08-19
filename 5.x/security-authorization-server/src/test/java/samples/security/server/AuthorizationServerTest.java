package samples.security.server;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthorizationServerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    @Qualifier("jdbcClientDetailsService")
    ClientDetailsService clientDetailsService;

    @BeforeEach
    void setUp(@Autowired PasswordEncoder passwordEncoder) {
        BaseClientDetails clientDetails = new BaseClientDetails("oauth-client", null, "test", "password", null, null);
        clientDetails.setClientSecret(passwordEncoder.encode("oauth-client"));
        when(clientDetailsService.loadClientByClientId("oauth-client")).thenReturn(clientDetails);
    }

    @Test
    void requestTokenWhenUsingPasswordGrantTypeThenOk() throws Exception {
        mvc.perform(post("/oauth/token")
                .param("grant_type", "password")
                .param("username", "user")
                .param("password", "user")
                .header(HttpHeaders.AUTHORIZATION, withBasicAuthorize("oauth-client", "oauth-client")))
                .andExpect(status().isOk());
    }

    @Test
    public void requestJwkSetWhenUsingDefaultsThenOk() throws Exception {
        mvc.perform(get("/oauth/keys"))
                .andExpect(status().isOk());
    }

    private String withBasicAuthorize(String clientId, String clientSecret) {
        return "Basic " + HttpHeaders.encodeBasicAuth(clientId, clientSecret, null);
    }
}