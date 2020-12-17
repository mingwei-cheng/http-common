package cn.cheng.http.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Post {
    /**
     * 请求地址
     *
     * @return String
     */
    String url() default "";

    /**
     * 格式：key:value
     *
     * @return 可以应用注解类型的元素的数组
     */
    String[] heads() default "";

    /**
     * 格式：json
     *
     * @return String
     */
    String body() default "";

    /**
     * 格式：key=value
     *
     * @return 可以应用注解类型的元素的数组
     */
    String[] cookies() default "";
}
