package hello.api.statistic.web;

import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StatisticControllerTest {

    @Autowired
    private MockMvc mvc;

    private  final Gson gson=new Gson();

    static final String URL_API_STATISTIC = "http://localhost:8082/api-statistic/";


    static final String URL_API_STATISTIC_CREATE_STAT = URL_API_STATISTIC.concat("create");
    static final String URL_API_STATISTIC_GET_STAT = URL_API_STATISTIC.concat("get");
    static final String URL_API_STATISTIC_FIND_ALL_STATS = URL_API_STATISTIC.concat("getAll");
    static final String URL_API_STATISTIC_FIND_WEEK_STATS = URL_API_STATISTIC.concat("getWeek");
    static final String URL_API_STATISTIC_FIND_MONTH_STATS = URL_API_STATISTIC.concat("getMonth");
    static final String URL_API_STATISTIC_DELETE = URL_API_STATISTIC.concat("delete");


    final String uuid="1c7156bc-5cab-4458-9571-b102ca03fc53";
    final String vk="basta";
    @Before
    public void setUp() throws Exception {

        Map<String, String> info = new HashMap<String, String>();
        info.put("uid", uuid);
        info.put("vk", vk);

        mvc.perform(post(URL_API_STATISTIC_CREATE_STAT).contentType(MediaType.APPLICATION_JSON).
                content(gson.toJson(info))).andDo(print())
                .andExpect(status().isCreated());


    }

    @After
    public void tearDown() throws Exception {
        mvc.perform(delete(URL_API_STATISTIC_DELETE).
                param("uuid", uuid)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getStat() throws Exception {


        mvc.perform(get(URL_API_STATISTIC_GET_STAT).param("vk", vk).
                param("uuid", uuid)).andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void findAll() throws Exception {

        mvc.perform(get(URL_API_STATISTIC_FIND_ALL_STATS)
                .param("uuid", uuid)).andDo(print())
                .andExpect(status().isOk());


    }


    @Test
    public void findWeek() throws Exception {
        mvc.perform(get(URL_API_STATISTIC_FIND_WEEK_STATS)
                .param("uuid", uuid)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void findMonth() throws Exception {
        mvc.perform(get(URL_API_STATISTIC_FIND_MONTH_STATS)
                .param("uuid", uuid)).andDo(print())
                .andExpect(status().isOk());
    }


}