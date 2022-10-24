package com.ravensoftware.reporting.transaction.resources;

import com.ravensoftware.reporting.controller.TransactionResources;
import com.ravensoftware.reporting.security.JWTAuthorizationFilter;
import com.ravensoftware.reporting.transaction.TransactionControl;
import com.ravensoftware.reporting.user.control.UserDetailsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by bilga
 */
@RunWith(SpringRunner.class)
@WebMvcTest(TransactionResources.class)
public class TransactionResourcesTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TransactionControl transactionControl;
    @MockBean
    UserDetailsServiceImpl userDetailsService;


    @Test
    public void shouldNotRequestWhenTokenIsNotExist() throws Exception {

        mockMvc.perform(get("/transactions/client?transactionId={searchText}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andReturn();

    }

    @Test
    public void shouldRequestWhenTokenIsValid() throws Exception {

        // TODO: look for a better solution. It is done because made to pass the test
        String token = JWTAuthorizationFilter.createToken("bilge");

        mockMvc.perform(get("/transactions/client?transactionId={searchText}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn();
    }
}
