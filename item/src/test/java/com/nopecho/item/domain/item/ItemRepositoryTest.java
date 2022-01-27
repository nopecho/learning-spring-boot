package com.nopecho.item.domain.item;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach //@Test 실행 후 실행 될 메소드
    void afterEach(){
        itemRepository.clearStore(); // itemRepository 초기화
    }

    @Test
    void save(){
        //given
        Item item = new Item("itemA",10000,10);

        //when
        Item saveItem = itemRepository.save(item);

        //then
        assertThat(saveItem).isEqualTo(item);
    }

    @Test
    void find(){
        //given
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);
        itemRepository.save(item1);
        itemRepository.save(item2);

        //when
        List<Item> all = itemRepository.findAll();

        //then
        assertThat(all.size()).isEqualTo(2);
        assertThat(all).contains(item1,item2); //assertThat(then).contains(then이 해당 매개변수를 포함하는지)
    }

    @Test
    void update(){
        //given
        Item item1 = new Item("item1", 10000, 20);
        Item saveItem = itemRepository.save(item1);

        Long id = saveItem.getId();
        //when
        Item itemParam = new Item("item2",15000,10);
        itemRepository.update(id,itemParam);
        Item findItem = itemRepository.findById(id);

        //then
        assertThat(findItem.getItemName()).isEqualTo(itemParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(itemParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(itemParam.getQuantity());
    }
}
