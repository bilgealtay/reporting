package com.ravensoftware.reporting.contact;

import com.ravensoftware.reporting.UrlHelper;
import com.ravensoftware.reporting.base.TokenResponse;
import com.ravensoftware.reporting.contact.entity.CustomerInfo;
import com.ravensoftware.reporting.entity.TokenRequest;
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
public class CustomerInfoResourcesIT {
    @Test
    public void testAddCustomerInfoSuccess() throws URISyntaxException {

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

        /** add new customerInfo **/
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setBillingFirstName("Ali");
        customerInfo.setBillingLastName("Veli");
        customerInfo.setEmail("ali@veli.com");
        customerInfo.setNumber("RT342342");

        final String url = UrlHelper.REPORTING_BASE_PATH + "customerInfos";
        URI uri = new URI(url);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", resultToken.getBody().getToken());
        HttpEntity<CustomerInfo> request = new HttpEntity<>(customerInfo, headers);
        ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

        //Verify request succeed
        assertEquals(200, result.getStatusCodeValue());
        assertNotNull(result.getBody());
    }

    @Test
    public void testAddCustomerInfoMissingHeader() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String url = UrlHelper.REPORTING_BASE_PATH + "customerInfos";
        URI uri = new URI(url);

        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setBillingFirstName("Ali");
        customerInfo.setBillingLastName("Veli");
        customerInfo.setEmail("ali@veli.com");
        customerInfo.setNumber("RT342342");

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<CustomerInfo> request = new HttpEntity<>(customerInfo, headers);

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
