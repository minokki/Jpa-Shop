package jpabook.jpashop.domain;


import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable // 어딘가 내장되 어 있다.
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() {    //기본생성자
    }
    public Address(String city, String street, String zipcode) {  //값타임은 변경 불가능 하게 설계.  기본생성자는 public 또는 protected 로 만들자
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
