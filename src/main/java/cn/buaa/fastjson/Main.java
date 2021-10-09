package cn.buaa.fastjson;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * @author hct
 * @Slogan 我的代码永远 0 错误
 * @date 2021/8/19
 **/
public class Main {
    public static void main(String[] args) {
        List<Person> listOfPersons = new ArrayList<Person>();

        listOfPersons.add(new Person(15, "John Doe", new Date()));
        listOfPersons.add(new Person(20, "Janette Doe", new Date()));

        String jsonOutput = JSON.toJSONString(listOfPersons);
        System.out.println(jsonOutput);
    }
}
