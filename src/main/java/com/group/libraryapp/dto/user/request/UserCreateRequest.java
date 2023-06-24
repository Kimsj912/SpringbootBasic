package com.group.libraryapp.dto.user.request;

public class UserCreateRequest {

    private final String name;
    private final Integer age; //선택적으로 들어옴.


    public UserCreateRequest(String name, Integer age){
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
