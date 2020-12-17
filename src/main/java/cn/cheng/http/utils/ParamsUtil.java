package cn.cheng.http.utils;

import org.springframework.util.MultiValueMap;

import java.util.Arrays;

/**
 * @author Cheng Mingwei
 * @create 2020-12-16 11:02
 **/
public class ParamsUtil {
    public static void addParams(String[] attrs, MultiValueMap<String, String> params, String spiltStr) {
        if (!Arrays.equals(new String[]{""}, attrs)) {
            for (String attr : attrs) {
                String key = attr.substring(0, attr.indexOf(spiltStr));
                String value = attr.substring(attr.indexOf(spiltStr) + 1);
                params.add(key, value);
            }
        }
    }
}
