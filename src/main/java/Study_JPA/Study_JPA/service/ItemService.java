package Study_JPA.Study_JPA.service;

import Study_JPA.Study_JPA.domain.item.Book;
import Study_JPA.Study_JPA.domain.item.Item;
import Study_JPA.Study_JPA.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    /**
     * 변경감지기능 사용 예시
     * @param itemId
     */
    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity){
        Item findItem = itemRepository.findOne(itemId);// 영속상태 item을 찾아옴
        findItem.setPrice(price);
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity);
        // item객체의 값들이 변경이 일어난 후 Transactional어노테이션에 의해 commit 되고 이때 flush 하면서 변경감지된 값들을 update
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
