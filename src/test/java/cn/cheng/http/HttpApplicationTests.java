package cn.cheng.http;

import cn.cheng.http.annotation.Get;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@SpringBootTest
class HttpApplicationTests {

    @Resource
    private Demo demo;

    @Test
    void demoTest() {
        System.out.println(demo.getBaidu().block());
    }

}
@Service
class Demo {
    @Get(url = "http://www.baidu.com")
    public Mono<String> getBaidu() {
        //执行出错回退方法
        return Mono.just("failed");
    }
}
