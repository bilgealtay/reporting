package com.ravensoftware.reporting.contact;

import com.ravensoftware.reporting.entity.TokenRequest;
import com.ravensoftware.reporting.UrlHelper;
import com.ravensoftware.reporting.base.PaymentMethod;
import com.ravensoftware.reporting.base.TokenResponse;
import com.ravensoftware.reporting.contact.entity.Acquirer;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class AcquirerResourcesIT {
    @Test
    public void testAddAcquirerSuccess() throws URISyntaxException {

        RestTemplate restTemplate = new RestTemplate();

        TokenRequest tokenRequest = new TokenRequest("bilgealtay@gmail.com", "1234");
        final String urlToken = UrlHelper.REPORTING_BASE_PATH + "login";
        URI uriToken = new URI(urlToken);
        HttpHeaders headersToken = new HttpHeaders();
        headersToken.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TokenRequest> requestToken = new HttpEntity<>(tokenRequest, headersToken);
        ResponseEntity<TokenResponse> resultToken = restTemplate.postForEntity(uriToken, requestToken, TokenResponse.class);
        assertNotNull(resultToken);
        assertNotNull(resultToken.getBody());
        assertNotNull(resultToken.getBody().getToken());

        /** add new acquirer **/
        Acquirer acquirer = new Acquirer();
        acquirer.setCode("LB");
        acquirer.setName("Llyods Bank");
        acquirer.setType(PaymentMethod.CEPBANK);

        final String url = UrlHelper.REPORTING_BASE_PATH + "acquirers";
        URI uri = new URI(url);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", resultToken.getBody().getToken());
        HttpEntity<Acquirer> request = new HttpEntity<>(acquirer, headers);

        ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

        //Verify request succeed
        assertEquals(200, result.getStatusCodeValue());
        assertNotNull(result.getBody());
    }

    @Test
    public void testAddAcquirerMissingHeader() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String url = UrlHelper.REPORTING_BASE_PATH + "acquirers";
        URI uri = new URI(url);

        Acquirer acquirer = new Acquirer();
        acquirer.setCode("LB");
        acquirer.setName("Llyods Bank");
        acquirer.setType(PaymentMethod.CEPBANK);

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<Acquirer> request = new HttpEntity<>(acquirer, headers);

        try {
            restTemplate.postForEntity(uri, request, String.class);
            Assert.fail();
        }
        catch(HttpClientErrorException ex) {
            //Verify bad request and missing header
            Assert.assertEquals(400, ex.getRawStatusCode());
            Assert.assertEquals(true, ex.getResponseBodyAsString().contains("Missing request header"));
        }
    }
}
