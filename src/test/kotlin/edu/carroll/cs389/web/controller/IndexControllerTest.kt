package edu.carroll.cs389.web.controller

import org.hamcrest.Matchers.containsString
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IndexController::class)
class IndexControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc;

    @Test
    fun indexTest() {
        mockMvc.perform(get("/")).andDo(print())
            .andExpect(status().isOk)
            .andExpect(content().string(containsString("Hello Student!")))
    }

    @Test
    fun indexWithInputTest() {
        val name = "TestName";
        mockMvc.perform(get("/").param("name", name)).andDo(print())
            .andExpect(status().isOk)
            .andExpect(content().string(containsString("Hello $name")));
    }
}