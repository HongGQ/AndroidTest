package com.hong.test.model;

/**
 * @author Hong
 * @Name: PersonModel
 * @Package com.hong.test.model
 * @Description: ${todo}
 * @date 15/11/25
 * @time 下午4:37
 * @copyright 广州市金税信息系统集成有限公司
 */
public class PersonModel {
    private int id;
    private String name;
    private int age;
    private int sex;

    public PersonModel(int id, String name, int age, int sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
