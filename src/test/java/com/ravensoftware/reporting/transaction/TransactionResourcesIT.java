package com.ravensoftware.reporting.transaction;

import com.ravensoftware.reporting.entity.TokenRequest;
import com.ravensoftware.reporting.UrlHelper;
import com.ravensoftware.reporting.base.Currency;
import com.ravensoftware.reporting.base.Operation;
import com.ravensoftware.reporting.base.PaymentMethod;
import com.ravensoftware.reporting.base.TokenResponse;
import com.ravensoftware.reporting.contact.entity.Acquirer;
import com.ravensoftware.reporting.contact.entity.CustomerInfo;
import com.ravensoftware.reporting.contact.entity.Merchant;
import com.ravensoftware.reporting.transaction.entity.ErrorCode;
import com.ravensoftware.reporting.transaction.entity.Transaction;
import com.ravensoftware.reporting.transaction.entity.TransactionStatus;
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
public class TransactionResourcesIT {
    @Test
    public void testAddTransactionSuccess() throws URISyntaxException {

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

        /** add new transaction **/
        Transaction transaction = new Transaction();
        transaction.setCustomerInfo(createCustomerInfo(resultToken.getBody().getToken()));
        transaction.setMerchant(createMerchant(resultToken.getBody().getToken()));
        transaction.setAcquirer(createAcquirer(resultToken.getBody().getToken()));
        transaction.setAmount(1200L);
        transaction.setCurrency(Currency.EUR);
        transaction.setErrorCode(ErrorCode.CURRENCY_NOT_ALLOWED);
        transaction.setOperation(Operation.DIRECT);
        transaction.setPaymentMethod(PaymentMethod.CEPBANK);
        transaction.setReferenceNo("Ref-04");
        transaction.setTransactionNo("TR-345345");
        transaction.setTransactionStatus(TransactionStatus.APPROVED);
        transaction.setTxDateStr("2020-01-14");

        final String url = UrlHelper.REPORTING_BASE_PATH + "transactions";
        URI uri = new URI(url);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", resultToken.getBody().getToken());
        HttpEntity<Transaction> request = new HttpEntity<>(transaction, headers);

        ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

        //Verify request succeed
        assertEquals(200, result.getStatusCodeValue());
        assertNotNull(result.getBody());
    }

    @Test
    public void testAddTransactionMissingHeader() throws URISyntaxException {
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

        final String url = UrlHelper.REPORTING_BASE_PATH + "transactions";
        URI uri = new URI(url);

        Transaction transaction = new Transaction();
        transaction.setCustomerInfo(createCustomerInfo(resultToken.getBody().getToken()));
        transaction.setMerchant(createMerchant(resultToken.getBody().getToken()));
        transaction.setAcquirer(createAcquirer(resultToken.getBody().getToken()));
        transaction.setAmount(1200L);
        transaction.setCurrency(Currency.EUR);
        transaction.setErrorCode(ErrorCode.CURRENCY_NOT_ALLOWED);
        transaction.setOperation(Operation.DIRECT);
        transaction.setPaymentMethod(PaymentMethod.CEPBANK);
        transaction.setReferenceNo("Ref-04");
        transaction.setTransactionNo("TR-345345");
        transaction.setTransactionStatus(TransactionStatus.APPROVED);
        transaction.setTxDateStr("2020-01-14");

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<Transaction> request = new HttpEntity<>(transaction, headers);

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

    private CustomerInfo createCustomerInfo(String token) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        /** add new customerInfo **/
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setBillingFirstName("Ali");
        customerInfo.setBillingLastName("Veli");
        customerInfo.setEmail("ali@veli.com");
        customerInfo.setNumber("RT342342");

        final String url = UrlHelper.REPORTING_BASE_PATH + "customerInfos";
        URI uri = new URI(url);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<CustomerInfo> request = new HttpEntity<>(customerInfo, headers);
        ResponseEntity<CustomerInfo> result = restTemplate.postForEntity(uri, request, CustomerInfo.class);
        return result.getBody();
    }

    private Merchant createMerchant(String token) throws URISyntaxException{
        RestTemplate restTemplate = new RestTemplate();
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
        headers.set("Authorization", token);
        HttpEntity<Merchant> request = new HttpEntity<>(merchant, headers);
        ResponseEntity<Merchant> result = restTemplate.postForEntity(uri, request, Merchant.class);
        return result.getBody();
    }

    private Acquirer createAcquirer(String token) throws URISyntaxException{
        RestTemplate restTemplate = new RestTemplate();
        /** add new acquirer **/
        Acquirer acquirer = new Acquirer();
        acquirer.setCode("LB");
        acquirer.setName("Llyods Bank");
        acquirer.setType(PaymentMethod.CEPBANK);

        final String url = UrlHelper.REPORTING_BASE_PATH + "acquirers";
        URI uri = new URI(url);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", token);
        HttpEntity<Acquirer> request = new HttpEntity<>(acquirer, headers);

        ResponseEntity<Acquirer> result = restTemplate.postForEntity(uri, request, Acquirer.class);
        return result.getBody();
    }
}
