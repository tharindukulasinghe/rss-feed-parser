package com.gifted.rss.repository;

import com.gifted.rss.beans.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findOneByTitle(String title);
    List findTop10ByOrderByPubDateAsc();
    Page findAll(Pageable pageable);
}
