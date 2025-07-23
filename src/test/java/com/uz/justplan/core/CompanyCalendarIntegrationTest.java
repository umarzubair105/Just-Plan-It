package com.uz.justplan.core;

import com.uz.justplan.utils.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CompanyCalendarIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    public void setup() {
        // Clean up repository before each test
        companyRepository.deleteAll();

        // Preload test data
        Company com = new Company();
        com.setName("Name1");
        com.setSample(true);
        com.setCountryId(1l);
        companyRepository.saveAll(List.of(com));
    }

    //@Test
    public void testGetWeekends_success() throws Exception {
        System.out.println(Utils.getNameFromEmail("umar-zubair.@ttsme.com"));
        System.out.println(Utils.getNameFromEmail("umar.m_zubair@ttsme.com"));
        mockMvc.perform(get("/companies?page=0&size=20")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$._embedded.companies", hasSize(1)))
                .andExpect(jsonPath("$._embedded.companies[0].name", is("Name1")))
                .andExpect(jsonPath("$._embedded.companies[0].sample", is(true)))
                .andExpect(jsonPath("$._embedded.companies[0].new", is(false)))
                .andExpect(jsonPath("$._embedded.companies[0]._links.self.href", is("http://localhost/companies/1")))
                .andExpect(jsonPath("$._links.self.href", is("http://localhost/companies?page=0&size=20")))
                .andExpect(jsonPath("$.page.size", is(20)))
                .andExpect(jsonPath("$.page.totalElements", is(1)))
                .andExpect(jsonPath("$.page.totalPages", is(1)))
                .andExpect(jsonPath("$.page.number", is(0)));

/*        mockMvc.perform(MockMvcRequestBuilders.get("/companies"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Name1"));*/
    }

/*    @Test
    public void testGetWeekends_noData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/companies/2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }*/
}
