package com.bankapi;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DemoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAll() throws Exception {
        String contentAsString = mockMvc.perform(get("/accounts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        Assert.assertEquals(contentAsString, "[{\"id\":1,\"firstName\":\"Lokesh\",\"lastName\":\"Gupta\",\"accountNumber\":1111222212334444,\"balance\":123.23,\"cards\":[{\"id\":1,\"cardNumber\":\"1111 2222 3333 4444\",\"expDate\":\"2022-09-16T21:00:00Z\",\"cvv\":123}]}," +
                "{\"id\":2,\"firstName\":\"John\",\"lastName\":\"Doe\",\"accountNumber\":2222336644445555,\"balance\":3847.13,\"cards\":[{\"id\":2,\"cardNumber\":\"1111 2322 3333 4444\",\"expDate\":\"2024-09-30T21:00:00Z\",\"cvv\":345}]}," +
                "{\"id\":3,\"firstName\":\"JHfhgfjh\",\"lastName\":\"Rruigregjkgh\",\"accountNumber\":1114022233334444,\"balance\":123.23,\"cards\":[{\"id\":3,\"cardNumber\":\"4561 2222 3355 4444\",\"expDate\":\"2022-09-16T21:00:00Z\",\"cvv\":123}]}," +
                "{\"id\":4,\"firstName\":\"Marta\",\"lastName\":\"Ddd\",\"accountNumber\":2222333399445555,\"balance\":3847.13,\"cards\":[{\"id\":4,\"cardNumber\":\"5869 2322 4563 4444\",\"expDate\":\"2024-09-30T21:00:00Z\",\"cvv\":345}]}," +
                "{\"id\":5,\"firstName\":\"RRRRRRRRRRRRRRR\",\"lastName\":\"Ajkgjkhg\",\"accountNumber\":1111220033334444,\"balance\":123.23,\"cards\":[{\"id\":5,\"cardNumber\":\"4510 2222 3355 4444\",\"expDate\":\"2022-09-16T21:00:00Z\",\"cvv\":123},{\"id\":6,\"cardNumber\":\"7593 2222 3355 4444\",\"expDate\":\"2024-09-16T21:00:00Z\",\"cvv\":123}]}," +
                "{\"id\":6,\"firstName\":\"Ignat\",\"lastName\":\"Panteleevich\",\"accountNumber\":2222324344445555,\"balance\":3847.13,\"cards\":[]}]");
    }

    @Test
    public void getById() throws Exception {
        String contentAsString = mockMvc.perform(get("/accounts/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        Assert.assertEquals(contentAsString, "{\"id\":2,\"firstName\":\"John\",\"lastName\":\"Doe\",\"accountNumber\":2222336644445555,\"balance\":3847.13,\"cards\":[{\"id\":2,\"cardNumber\":\"1111 2322 3333 4444\",\"expDate\":\"2024-09-30T21:00:00Z\",\"cvv\":345}]}");
    }

    @Test
    public void getByAccId() throws Exception {
        String contentAsString = mockMvc.perform(get("/accounts/search/2222336644445555")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        Assert.assertEquals(contentAsString, "[{\"id\":2,\"firstName\":\"John\",\"lastName\":\"Doe\",\"accountNumber\":2222336644445555,\"balance\":3847.13,\"cards\":[{\"id\":2,\"cardNumber\":\"1111 2322 3333 4444\",\"expDate\":\"2024-09-30T21:00:00Z\",\"cvv\":345}]}]");
    }


    @Test
    public void deleteById() throws Exception {
        mockMvc.perform(delete("/accounts/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String contentAsString = mockMvc.perform(get("/accounts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        Assert.assertEquals(contentAsString, "[{\"id\":1,\"firstName\":\"Lokesh\",\"lastName\":\"Gupta\",\"accountNumber\":1111222212334444,\"balance\":123.23,\"cards\":[{\"id\":1,\"cardNumber\":\"1111 2222 3333 4444\",\"expDate\":\"2022-09-16T21:00:00Z\",\"cvv\":123}]}," +
                "{\"id\":3,\"firstName\":\"JHfhgfjh\",\"lastName\":\"Rruigregjkgh\",\"accountNumber\":1114022233334444,\"balance\":123.23,\"cards\":[{\"id\":3,\"cardNumber\":\"4561 2222 3355 4444\",\"expDate\":\"2022-09-16T21:00:00Z\",\"cvv\":123}]}," +
                "{\"id\":4,\"firstName\":\"Marta\",\"lastName\":\"Ddd\",\"accountNumber\":2222333399445555,\"balance\":3847.13,\"cards\":[{\"id\":4,\"cardNumber\":\"5869 2322 4563 4444\",\"expDate\":\"2024-09-30T21:00:00Z\",\"cvv\":345}]}," +
                "{\"id\":5,\"firstName\":\"RRRRRRRRRRRRRRR\",\"lastName\":\"Ajkgjkhg\",\"accountNumber\":1111220033334444,\"balance\":123.23,\"cards\":[{\"id\":5,\"cardNumber\":\"4510 2222 3355 4444\",\"expDate\":\"2022-09-16T21:00:00Z\",\"cvv\":123},{\"id\":6,\"cardNumber\":\"7593 2222 3355 4444\",\"expDate\":\"2024-09-16T21:00:00Z\",\"cvv\":123}]}," +
                "{\"id\":6,\"firstName\":\"Ignat\",\"lastName\":\"Panteleevich\",\"accountNumber\":2222324344445555,\"balance\":3847.13,\"cards\":[]}]");
    }

}
