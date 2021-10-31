package com.gifted.rss;

import com.gifted.rss.controller.ItemController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RssApplicationTests {

	@Autowired
	private ItemController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
