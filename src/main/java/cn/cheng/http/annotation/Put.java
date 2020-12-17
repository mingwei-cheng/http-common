package cn.cheng.http.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Put {
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
     * 格式：key=value
     *
     * @return 可以应用注解类型的元素的数组
     */
    String[] attrs() default "";


    /**
     * 格式：key=value
     *
     * @return 可以应用注解类型的元素的数组
     */
    String[] cookies()  default "";
}
