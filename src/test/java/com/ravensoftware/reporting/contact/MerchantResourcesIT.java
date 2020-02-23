package com.ravensoftware.reporting.contact;

import com.ravensoftware.reporting.UrlHelper;
import com.ravensoftware.reporting.base.Currency;
import com.ravensoftware.reporting.base.TokenResponse;
import com.ravensoftware.reporting.contact.entity.Merchant;
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

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class MerchantResourcesIT {
    @Test
    public void testAddMerchantSuccess() throws URISyntaxException {
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

        /** add new merchant **/
        Merchant merchant = new Merchant();
        merchant.setCode("LB");
        merchant.setName("Llyods Bank");
        merchant.setOriginalAmount(new BigDecimal(1200));
        merchant.setOriginalCurrency(Currency.GBP);

        final String url = UrlHelper.REPORTING_BASE_PATH + "merchants";
        URI uri = new URI(url);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", resultToken.getBody().getToken());
        HttpEntity<Merchant> request = new HttpEntity<>(merchant, headers);

        ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

        //Verify request succeed
        assertEquals(200, result.getStatusCodeValue());
        assertNotNull(result.getBody());
    }

    @Test
    public void testAddMerchantMissingHeader() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String url = UrlHelper.REPORTING_BASE_PATH + "merchants";
        URI uri = new URI(url);

        Merchant merchant = new Merchant();
        merchant.setCode("LB");
        merchant.setName("Llyods Bank");
        merchant.setOriginalAmount(new BigDecimal(1200));
        merchant.setOriginalCurrency(Currency.GBP);

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<Merchant> request = new HttpEntity<>(merchant, headers);

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
