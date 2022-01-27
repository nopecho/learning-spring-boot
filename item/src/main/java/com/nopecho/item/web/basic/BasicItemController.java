package com.nopecho.item.web.basic;

import com.nopecho.item.domain.item.Item;
import com.nopecho.item.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@RequiredArgsConstructor //RequiredArgsConstructor -> final붙은 필드변수 생성자
@Controller
@RequestMapping("/basic/items")
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping //Item 전체 조회 핸들러 메서드
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items",items);
        return "basic/items";
    }

    @GetMapping("/{itemId}") //Item 상세 조회 핸들러 메서드
    public String item(@PathVariable long itemId,Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/item";
    }

    @GetMapping("/add") //Item 등록 view 이동 핸들러 메서드
    public String addForm(){
        return "basic/addForm";
    }

//    @PostMapping("/add")
    public String saveV1(@RequestParam String itemName,
                         @RequestParam(defaultValue = "0") int price,
                         @RequestParam(defaultValue = "0") Integer quantity,
                         Model model){
        Item item = new Item(itemName, price, quantity);
        Item saveItem = itemRepository.save(item);
        model.addAttribute("item",saveItem);
        return "basic/item";
    }

    /**
     * 1. @ModelAttribute 애노테이션 붙은 객체의 필드 조회 후 RequestParameter의 key:value로 객체 필드값 Binding
     * 파라미터명 이랑 객체 필드변수 명이랑 같아야함! (프로퍼티 접근법)
     */
//    @PostMapping("/add") //@ModelAttribute 애노테이션으로 상품 등록
    public String saveV2(@ModelAttribute Item item,Model model){
        Item saveItem = itemRepository.save(item);
        model.addAttribute("item",saveItem);
        return "basic/item";
    }

    /**
     * 2. @ModelAttribute("[모델에서 사용할 이름]")으로 Model객체에 해당 애노테이션이 붙은 객체를 addAttribute("[모델에서 사용할 이름]",해당 객체) 해준다.!!!!
     * 그래서 Model model 파라미터 생략 가능
     */
//    @PostMapping("/add")
    public String saveV3(@ModelAttribute("item") Item item){
        itemRepository.save(item);
        return "basic/item";
    }

    /**
     * 3. @ModelAttribute 애노테이션의 ("모델 이름")을 생략가능
     * ("모델이름")을 생략하면 해당 애노테이션이 붙은 객체 클래스네임을 "모델이름" 으로 모델에 자동 등록
     *  규칙 : Class의 첫글자를 소문자로 바꾸고 모델이름으로 등록한다.
     *      ex) @ModelAttribute Memeber hello -> model.addAttribute("member",hello) 와 같은뜻
     */
//    @PostMapping("/add")
    public String saveV4(@ModelAttribute Item item){
        itemRepository.save(item);
        return "basic/item";
    }

    @PostMapping("/add")
    public String saveV5(Item item){ //아예 전부 생략 가능 BUT 어떤 동작을 하는지 명시해주는게 좋다.
        itemRepository.save(item);
        return "basic/item";
    }

    @PostConstruct
    public void init(){
        itemRepository.save(new Item("ItemA",10000,10));
        itemRepository.save(new Item("ItemB",20000,20));
    }
}
