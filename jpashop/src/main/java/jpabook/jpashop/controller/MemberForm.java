package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "회원이름은 필수 입니다.")  //맴버컨트롤에서 바인딩 리절트를 사용하므로 오류발생시 해당 메세지보여줌
    private String name;
    private String city;
    private String street;
    private String zipcode;

}
