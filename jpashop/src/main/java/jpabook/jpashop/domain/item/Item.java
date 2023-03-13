package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)  //상속관계 전략을 잡아야함 ..부모에서 지정
@DiscriminatorColumn(name="dtype")  //여기서 정한 타입을 각 자식 구현체에서 밸류값지정 해야함. 디폴트는 클래스명
@Getter @Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //--비지니스 로직--//
    //재고 증가
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    //재고감소
    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if(restStock <0 ){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity= restStock;
    }

}
