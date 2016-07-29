package com.kaishengit.util;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

public class SearchParam {

    private String type;
    private String protertyName;
    private Object value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProtertyName() {
        return protertyName;
    }

    public void setProtertyName(String protertyName) {
        this.protertyName = protertyName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public static List<SearchParam> builderSearchParam(HttpServletRequest request){
        List<SearchParam> searchParamList = Lists.newArrayList();

        //获取所有的查询字符串
        Enumeration<String> enumeration = request.getParameterNames();

        while (enumeration.hasMoreElements()){
            String queryString = enumeration.nextElement();
            Object value = request.getParameter(queryString);

            if (queryString.startsWith("q_") && value !=null && StringUtils.isNotEmpty(value.toString())){
                //q_s_like_xxx
                String[] array = queryString.split("_",4);
                if (array.length != 4){
                    throw new RuntimeException("地址栏查询字符串格式错误:"+queryString);
                }
                String type = array[2];
                String propertyName = array[3];
                String valuetype = array[1];

                SearchParam searchParam = new SearchParam();
                searchParam.setType(type);
                searchParam.setProtertyName(propertyName);
                value = converterType(value,valuetype);
                searchParam.setValue(value);
                searchParamList.add(searchParam);

                request.setAttribute(queryString,value);
            }
        }
    return searchParamList;
    }

    private static Object converterType(Object value, String valuetype) {
        if ("s".equalsIgnoreCase(valuetype)){
            return Strings.toUTF8(value.toString());
        }else if ("f".equalsIgnoreCase(valuetype)){
            return Float.valueOf(value.toString());
        }else if ("d".equalsIgnoreCase(valuetype)){
            return Double.valueOf(value.toString());
        }else if ("b".equalsIgnoreCase(valuetype)){
            return Boolean.valueOf(value.toString());
        }else if ("i".equalsIgnoreCase(valuetype)){
            return Integer.valueOf(value.toString());
        }

        return null;
    }
}
