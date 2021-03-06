package cn.demo.service.client;

import cn.demo.service.client.fallback.factory.Service0FallbackFactory;
import cn.demo.service.controller.Service1Controller;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description:
 */
@FeignClient(name = "consumer", fallbackFactory = Service0FallbackFactory.class)
public interface Service0Client {

    @RequestMapping(method = RequestMethod.GET, path = "user/{userId}/{sleepSec}")
    String test(
            @PathVariable("userId") String userId,
            @PathVariable("sleepSec") int sleepSec
    );

    @RequestMapping(
            method = RequestMethod.GET,
            path = "test"
    )
    String user(
            Service1Controller.User user
    );

}
