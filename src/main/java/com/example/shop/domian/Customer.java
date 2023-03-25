package com.example.shop.domian;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;


@Getter
@Setter
@EqualsAndHashCode
public class Customer {

    @Nullable
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private String surname;
    @NonNull
    private String email;
    @NonNull
    private String address;
    @Nullable
    private int age;
    @Nullable
    private String phoneNumber;


    public Customer(){
    }

    public Customer(@NonNull String name, @NonNull String surname, @NonNull String email, @NonNull String address, @Nullable int age, @Nullable String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.address = address;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }


}
