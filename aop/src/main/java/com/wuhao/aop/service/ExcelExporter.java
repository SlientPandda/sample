package com.wuhao.aop.service;

import com.wuhao.aop.annotation.Excel;
import com.wuhao.aop.pojo.User;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExcelExporter {


    public static void export(List<User> users) {

        try {
            // 获取所有包含Excel注解的字段
            Field[] declaredFields = User.class.getDeclaredFields();

            List<Field> filteredFields = Stream.of(declaredFields)
                    .filter(f -> f.isAnnotationPresent(Excel.class))
                    .collect(Collectors.toList());

            // 写入标题行
            writeTitleRow(filteredFields);

            // 写入数据行
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                writeDataRow(user, filteredFields);
            }

            // 写入文件

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    private static void writeTitleRow(List<Field> filteredFields) {
        for (int i = 0; i < filteredFields.size(); i++) {
            Excel excel = filteredFields.get(i).getAnnotation(Excel.class);
            System.out.println(excel.name() + excel.dictValue());
        }
    }

    private static void writeDataRow(User user, List<Field> filteredFields) throws IllegalAccessException {

        // 构造字段的值字典
        Map<Field, Map<String, String>> fieldDict = buildFieldDict(filteredFields);

        for (int i = 0; i < filteredFields.size(); i++) {

            Field field = filteredFields.get(i);
//            String key = String.valueOf(field.get(user));
//            System.out.println(key);
            //由于是私有成员变量，所以获取值时需要先获取
            field.setAccessible(true);
            Excel annotation = field.getAnnotation(Excel.class);
            if (StringUtils.hasLength(annotation.dictValue())) {
                String key1= String.valueOf(field.get(user));
                System.out.println(key1);
            }
            field.setAccessible(false);
        }

    }

    private static Map<Field, Map<String, String>> buildFieldDict(List<Field> filteredFields) {
        Map<Field, Map<String, String>> fieldDict = new HashMap<>();
        for (Field field : filteredFields) {
            Excel annotation = field.getAnnotation(Excel.class);
            if (StringUtils.hasLength(annotation.dictValue())) {
                String d = annotation.dictValue();
                String[] kvs = d.split(",");
                Map<String, String> map = new HashMap<>();
                for (String kv : kvs) {
                    String[] split = kv.split("=");
                    map.put(split[0], split[1]);
                }
                fieldDict.put(field, map);
            }
        }
        return fieldDict;
    }

    public static void main(String[] args) throws IOException {
        List<User> userList = new ArrayList<>(Arrays.asList(
                new User("zhangsan", 10, 1),
                new User("lisa", 30, 0),
                new User("abby", 24, 0),
                new User("bob", 60, 1),
                new User("cooper", 40, 0),
                new User("truman", 80, 1)
        ));
        ExcelExporter.export(userList);
    }
}