package cn.cheng.http.config;

import cn.cheng.http.pojo.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.Resource;

/**
 * @author Cheng Mingwei
 * @create 2020-12-16 14:44
 **/
@Configuration
public class HttpAutoConfiguration {
    @Resource
    private Config config;
    private final Integer MAX_MEMORY_SIZE_DEFAULT = 262144;

    @Bean
    public WebClient webClient() {
        return WebClient.builder().exchangeStrategies(ExchangeStrategies.builder()
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(config.getMaxMemorySize() == null || config.getMaxMemorySize() < MAX_MEMORY_SIZE_DEFAULT ? MAX_MEMORY_SIZE_DEFAULT : config.getMaxMemorySize())
                )
                .build())
                .build();
    }

}
