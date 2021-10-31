package com.gifted.rss.scheduled;

import com.gifted.rss.beans.Item;
import com.gifted.rss.repository.ItemRepository;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Component
public class RssReader {
    private static final Logger log = LoggerFactory.getLogger(RssReader.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    ItemRepository itemRepository;

    @Scheduled(fixedRate = 300000)
    public void reportCurrentTime() {
        readRssFeed();
    }

    public void readRssFeed() {
        try {
            URL feedSource = new URL("https://lifehacker.com/rss");
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedSource));
            ArrayList<SyndEntry> entries = (ArrayList<SyndEntry>) feed.getEntries();
            for (SyndEntry entry : entries) {
                persistItem(entry);
            }
            log.info("RSS feed parsed at : {}", dateFormat.format(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void persistItem(SyndEntry entry) {
        Item itemInDb = itemRepository.findOneByTitle(entry.getTitle());
        if (itemInDb == null) {
            Item item = new Item(entry.getTitle(), entry.getDescription().getValue(), entry.getPublishedDate());
            itemRepository.save(item);
        }
        else {
            itemInDb.setNewValues(entry.getDescription().getValue(), entry.getPublishedDate());
            itemRepository.save(itemInDb);
        }
    }

}
