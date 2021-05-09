package cn.buaa.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @author hct
 * @Slogan 我的代码永远 0 错误
 * @date 2021/5/7
 **/
public class User {

    private Integer id;
    @JsonIgnore
    private String psw;
    private String name;
    private Double salary;
    @Past(message = "难道你穿越了吗")
    @DateTimeFormat(pattern = "yyyy=MM=dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", birthday=" + birthday +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
