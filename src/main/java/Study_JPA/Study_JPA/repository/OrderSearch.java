package Study_JPA.Study_JPA.repository;

import Study_JPA.Study_JPA.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class OrderSearch {

    private String MemberName;
    private OrderStatus orderStatus;

}
