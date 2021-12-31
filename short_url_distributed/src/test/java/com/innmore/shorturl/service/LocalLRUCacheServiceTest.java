package com.innmore.shorturl.service;

import com.innmore.shorturl.DistributedApp;
import com.innmore.shorturl.distributed.LocalLRUCacheService;
import com.innmore.shorturl.model.Record;
import com.innmore.shorturl.model.Res;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DistributedApp.class)
public class LocalLRUCacheServiceTest {

    @Autowired
    private LocalLRUCacheService localLRUCacheService;

    @Test
    public void createShortUrlByLongUrlTest(){
        Res<List<Record>> res = localLRUCacheService.createShortUrlByLongUrl(new HashSet<>(Arrays.asList("asdasdsda"))).block();
        Assertions.assertTrue(res.isSuccess());
    }

}
