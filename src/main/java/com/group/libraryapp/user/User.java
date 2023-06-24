package com.group.libraryapp.user;

public class User {

    private final String name;
    private final Integer age;

    public User(String name, Integer age){
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("�߸��� name(%s)�� ���Խ��ϴ�.", name));
        }
        this.name = name;
        this.age = age;
    }

    public String getName(){
        return name;
    }

    public Integer getAge(){
        return age;
    }
}
