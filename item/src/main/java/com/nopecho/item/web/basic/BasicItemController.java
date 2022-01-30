package com.nopecho.item.web.basic;

import com.nopecho.item.domain.item.DeliveryCode;
import com.nopecho.item.domain.item.Item;
import com.nopecho.item.domain.item.ItemRepository;
import com.nopecho.item.domain.item.ItemType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor //RequiredArgsConstructor -> final붙은 필드변수 생성자
@Controller
@RequestMapping("/basic/items")
public class BasicItemController {

    private final ItemRepository itemRepository;

    /**
     * 메서드 레벨에서 @ModelAttribute("[model에서 조회할 이름]") 애노테이션으로 메소드를 등록 가능
     * -> @ModelAttribute를 메서드로 등록 해놓으면 해당 컨트롤러(클래스)의 모든 핸들러메서드들의 model에 해당 값이 담긴다.
     */
    @ModelAttribute("regions")
    public Map<String,String> regions(){
        Map<String,String> regions = new LinkedHashMap<>();
        regions.put("SEOUL", "서울");
        regions.put("DAEGU", "대구");
        regions.put("JEJU", "제주");
        return regions;
    }

    @ModelAttribute("itemType")
    public ItemType[] itemType(){
        ItemType[] values = ItemType.values();
        return values;
    }

    @ModelAttribute("deliveryCodes")
    public List<DeliveryCode> deliveryCodes(){
        List<DeliveryCode> deliveryCodes = new ArrayList<>();
        deliveryCodes.add(new DeliveryCode("FAST","빠른 배송"));
        deliveryCodes.add(new DeliveryCode("NOMAL","보통 배송"));
        deliveryCodes.add(new DeliveryCode("SLOW","느린 배송"));
        return deliveryCodes;
    }

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
    public String addForm(Model model){
        model.addAttribute("item",new Item());
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

//    @PostMapping("/add")
    public String saveV5(Item item){ //아예 전부 생략 가능 BUT 어떤 동작을 하는지 명시해주는게 좋다.
        itemRepository.save(item);
        return "basic/item";
    }

    /**
     * Post/Redirect/Get PRG패턴 사용
     * Post요청이오면 Redirect로 Get방식으로
     */
//    @PostMapping("/add")
    public String savePRG(Item item){
        itemRepository.save(item);
        return "redirect:/basic/items/"+item.getId(); //PRG 패턴
    }

    @PostMapping("/add")
    public String savePRGparam(@ModelAttribute Item item, RedirectAttributes redirectAttributes){ //RedirectAttributes로 리다이렉트시 필요값 지정 가능
        log.info("item open = {}",item.getOpen());
        log.info("item regions = {}",item.getRegions());
        log.info("item itemType = {}",item.getItemType());
        log.info("item deliveryCode = {}",item.getDeliveryCode());
        Item saveItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId",saveItem.getId()); //Redirect속성 지정가능
        redirectAttributes.addAttribute("status",true);
        return "redirect:/basic/items/{itemId}"; //PRG 패턴
    }

    @GetMapping("/{itemId}/edit") //Item 수정 Form View 호출 컨트롤러
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit") //Item 수정 Post요청, PathVariable로 Id값 받고 ModelAttribute로 객체 받아서 모델에 값 넣음
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item){
        itemRepository.update(itemId,item);
        return "redirect:/basic/items/{itemId}";
    }

    @PostConstruct
    public void init(){
        itemRepository.save(new Item("ItemA",10000,10));
        itemRepository.save(new Item("ItemB",20000,20));
    }
}
