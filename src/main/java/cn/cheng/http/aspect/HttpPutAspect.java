package cn.cheng.http.aspect;

import cn.cheng.http.annotation.Head;
import cn.cheng.http.annotation.Put;
import cn.cheng.http.utils.ParamsUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.net.URI;

/**
 * @author Cheng Mingwei
 * @create 2020-12-15 14:55
 **/
@Aspect
@Component
@SuppressWarnings({"unused"})
public class HttpPutAspect {
    public static final Logger logger = LoggerFactory.getLogger(HttpPutAspect.class);
    @Resource
    private WebClient webClient;

    @Pointcut("@annotation(cn.cheng.http.annotation.Put)")
    public void annotationPointcut() {
    }

    @Around("annotationPointcut()")
    public Object beforePointcut(ProceedingJoinPoint joinPoint) {
        MethodSignature sign = (MethodSignature) joinPoint.getSignature();
        Method method = sign.getMethod();
        Put put = method.getAnnotation(Put.class);
        String url = put.url();
        logger.info("请求:{},方法：Put", url);
        String[] heads = put.heads();
        String[] attrs = put.attrs();
        String[] cookies = put.cookies();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        ParamsUtil.addParams(attrs, params, "=");
        try {
            String uri = UriComponentsBuilder.fromUri(new URI(url)).queryParams(params).toUriString();
            return webClient.put()
                    .uri(uri)
                    .cookies(c -> {
                        ParamsUtil.addParams(cookies, c, "=");
                    })
                    .headers(headers -> {
                        ParamsUtil.addParams(heads, headers, ":");
                    })
                    .retrieve()
                    .onStatus(HttpStatus::isError, resp -> {
                        logger.error("请求出错,请求地址：{},错误代码：{},原因:{}", url, resp.statusCode().value(), resp.statusCode().getReasonPhrase());
                        return Mono.error(new RuntimeException(resp.statusCode().getReasonPhrase()));
                    })
                    .bodyToMono(String.class)
                    .onErrorResume(a -> {
                        try {
                            logger.error("请求出错,尝试执行原方法,请求地址：{},原因:", url, a);
                            return (Mono) joinPoint.proceed();
                        } catch (Throwable throwable) {
                            logger.error("请求出错,原方法调用失败,请求地址：{},原因:", url, throwable);
                            return Mono.empty();
                        }
                    });
        } catch (Exception e) {
            logger.error("请求出错,请求地址：{},原因", url, e);
            return Mono.empty();
        }
    }

}
