package com.gifted.rss.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.gifted.rss.beans.Item;
import com.gifted.rss.service.ItemService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @Test
    public void shouldReturnItemsFromService() throws Exception {
        List<Item> items = new ArrayList<>();
        items.add(new Item("title", "description", null));
        when(itemService.findItems(1, 1 , "title", "asc")).thenReturn(items);

        String result = "[{\"id\":null,\"title\":\"title\",\"description\":\"description\",\"pubDate\":null}]";

        this.mockMvc.perform(get("/items?page=1&size=1&sort=title&direction=asc")).andExpect(status().isOk())
                .andExpect(content().string(result));
    }

    @Test
    public void shouldCallServiceWithCorrectParams() throws Exception {
        List<Item> items = new ArrayList<>();
        items.add(new Item("title", "description", null));
        when(itemService.findItems(1, 1 , "title", "asc")).thenReturn(items);

        String result = "[{\"id\":null,\"title\":\"title\",\"description\":\"description\",\"pubDate\":null}]";

        this.mockMvc.perform(get("/items?page=1&size=1&sort=title&direction=asc"));
        verify(itemService, times(1)).findItems(1, 1, "title", "asc");
    }
}
