package cn.demo.service1.client;

import cn.demo.service1.client.fallback.factory.Service0FallbackFactory;
import cn.demo.service1.controller.Service1Controller;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by kun on 17-6-27.
 */
@FeignClient(name = "service0", fallbackFactory = Service0FallbackFactory.class)
public interface Service0Client {

    @RequestMapping(method = RequestMethod.GET, path = "user/{userId}/{sleepSec}")
    String test(
            @PathVariable("userId") String userId,
            @PathVariable("sleepSec") int sleepSec
    );


}
