package com.gifted.rss.controller;

import com.gifted.rss.beans.Item;
import com.gifted.rss.repository.ItemRepository;
import com.gifted.rss.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ItemController {

    private static final Logger log = LoggerFactory.getLogger(ItemController.class);
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItems(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size, @RequestParam(required = false) String sort, @RequestParam(required = false) String direction) {
        try {
            List<Item> items = itemService.findItems(page, size, sort, direction);
            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
