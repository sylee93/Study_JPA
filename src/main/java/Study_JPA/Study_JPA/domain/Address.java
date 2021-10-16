package Study_JPA.Study_JPA.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter // 값타입, 변경 불가능한 설계를 위한 setter 제약
public class Address {

    private String city;
    private String street;
    private String zipcode;

    //기본생성자 protected 스코프로 생성하여 철저하게 접근금지
    protected Address(){}
    public Address(String city, String street, String zipcode){
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
