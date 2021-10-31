package com.gifted.rss.service;

import com.gifted.rss.beans.Item;
import com.gifted.rss.repository.ItemRepository;
import com.gifted.rss.scheduled.RssReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private static final Logger log = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    ItemRepository itemRepository;

    public List<Item> findItems(Integer page, Integer size, String sort, String direction) {
        List<Item> items = null;
        try {
            if (page == null && size == null) {
                items = itemRepository.findTop10ByOrderByPubDateAsc();
            }
            else {
                Pageable pageable;
                if (direction.equals("desc")) {
                    pageable = PageRequest.of(page - 1, size, Sort.by(sort).descending());
                }
                else {
                    pageable = PageRequest.of(page - 1, size, Sort.by(sort).ascending());
                }
                items = itemRepository.findAll(pageable).toList();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return items;
    }
}
