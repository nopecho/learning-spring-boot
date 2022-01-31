package com.nopecho.item.web.validation;

import com.nopecho.item.domain.item.DeliveryCode;
import com.nopecho.item.domain.item.Item;
import com.nopecho.item.domain.item.ItemRepository;
import com.nopecho.item.domain.item.ItemType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.*;

@Slf4j
@RequiredArgsConstructor //RequiredArgsConstructor -> final붙은 필드변수 생성자
@Controller
@RequestMapping("/validation/v3/items")
public class ValidationItemContollerV3 {

    private final ItemRepository itemRepository;

    @ModelAttribute("regions")
    public Map<String, String> regions() {
        Map<String, String> regions = new LinkedHashMap<>();
        regions.put("SEOUL", "서울");
        regions.put("DAEGU", "대구");
        regions.put("JEJU", "제주");
        return regions;
    }

    @ModelAttribute("itemType")
    public ItemType[] itemType() {
        ItemType[] values = ItemType.values();
        return values;
    }

    @ModelAttribute("deliveryCodes")
    public List<DeliveryCode> deliveryCodes() {
        List<DeliveryCode> deliveryCodes = new ArrayList<>();
        deliveryCodes.add(new DeliveryCode("FAST", "빠른 배송"));
        deliveryCodes.add(new DeliveryCode("NOMAL", "보통 배송"));
        deliveryCodes.add(new DeliveryCode("SLOW", "느린 배송"));
        return deliveryCodes;
    }

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "validation/v3/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v3/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "validation/v3/addForm";
    }

    /**
     * @param item @Validated 애노테이션으로 해당 객체를 @ModelAttribute로 바인딩할때 검증 로직 수행
     * @param bindingResult @Validated의 오류 결과를 BindingResult객체에 담음 (Model에 담김)
     */
    @PostMapping("/add")
    public String addItem(@Validated @ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(item.getPrice() != null && item.getQuantity() != null){
            int totalPeice = item.getPrice() * item.getQuantity();
            if(totalPeice < 10000){
                bindingResult.reject("totalPriceMin",new Object[]{10000,totalPeice},null);
            }
        }

        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "validation/v3/addForm";
        }
        Item saveItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", saveItem.getId()); //Redirect속성 지정가능
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v3/items/{itemId}"; //PRG 패턴
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v3/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @Validated @ModelAttribute Item item,BindingResult bindingResult) {
        itemRepository.update(itemId, item);
        if(item.getPrice() != null && item.getQuantity() != null){
            int totalPeice = item.getPrice() * item.getQuantity();
            if(totalPeice < 10000){
                bindingResult.reject("totalPriceMin",new Object[]{10000,totalPeice},null);
            }
        }

        if(bindingResult.hasErrors()){
            log.info("error = {}",bindingResult);
            return "validation/v3/editForm";
        }

        return "redirect:/validation/v3/items/{itemId}";
    }

    @PostConstruct
    public void init() {
        itemRepository.save(new Item("ItemE", 24000, 54));
        itemRepository.save(new Item("ItemF", 56700, 11));
    }
}
