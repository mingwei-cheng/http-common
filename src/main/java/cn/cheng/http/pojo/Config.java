package cn.cheng.http.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author Cheng Mingwei
 * @create 2020-12-17 11:29
 **/
@Component
@ConfigurationProperties(prefix = "cn.cheng.http")
public class Config {
    private Integer maxMemorySize;

    public Integer getMaxMemorySize() {
        return maxMemorySize;
    }

    public void setMaxMemorySize(Integer maxMemorySize) {
        this.maxMemorySize = maxMemorySize;
    }
}
