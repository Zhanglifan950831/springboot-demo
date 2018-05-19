package com.my.demo;


import com.google.common.collect.Lists;
import com.my.demo.doo.Student;
import com.my.demo.doo.Teacher;
import com.my.demo.dto.request.PageShelfReq;
import com.my.demo.util.BaseConverter;
import com.my.demo.util.JsonUtil;
import com.sun.deploy.util.StringUtils;

import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Test :
 *
 * @author Administrator
 * @version 1.0.0
 * @since 2018/04/25 17:23
 */
public class Test {

    public static void main(String[] args) {
        String json = JsonUtil.toJson(new PageShelfReq());

        List<String> strArr = Lists.newArrayList("a", "b", "c", "d");

        Student student = new Student();
        Teacher teacher = BaseConverter.copyProperties(student, Teacher.class);

        System.out.println(JsonUtil.toJson(teacher));
//        System.out.println(StringUtils.join(strArr, ","));
//        System.out.println("------------------------------");
//        System.out.println(String.join(",", strArr));
//        System.out.println(json);
    }


}
