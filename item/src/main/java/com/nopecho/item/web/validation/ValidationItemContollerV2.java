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
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.*;

@Slf4j
@RequiredArgsConstructor //RequiredArgsConstructor -> final붙은 필드변수 생성자
@Controller
@RequestMapping("/validation/v2/items")
public class ValidationItemContollerV2 {

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
        return "validation/v2/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "validation/v2/addForm";
    }

//    @PostMapping("/add") //실제 저장하는 로직
    public String addItemV1(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes) { //BindingResult => ModelAttribute에서 객체 바인딩된 결과를 담고있는 객체

        //검증 로직
        if (!StringUtils.hasText(item.getItemName())) {
            bindingResult.addError(new FieldError("item","itemName","상품 이름은 필수 입니다."));
        }
        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            bindingResult.addError(new FieldError("item","price","가격은 1000~1000000"));
        }
        if (item.getQuantity() == null || item.getQuantity() >= 9999) {
            bindingResult.addError(new FieldError("item","quantity","수량은 9999"));
        }
        //특정 필드가 아닌 복합 룰 검증
        if (item.getPrice() != null && item.getQuantity() != null) {
            int result = item.getPrice() * item.getQuantity();
            if (result < 10000) {
                bindingResult.addError(new ObjectError("item","가격*수량 의 합은 10,000원 이상이어야 합니다. / 현재값 = "+result));
            }
        }
        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "validation/v2/addForm";
        }

        //성공 로직
        Item saveItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", saveItem.getId()); //Redirect속성 지정가능
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}"; //PRG 패턴
    }

//    @PostMapping("/add") //실제 저장하는 로직
    public String addItemV2(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes) { //BindingResult => ModelAttribute에서 객체 바인딩된 결과를 담고있는 객체

        /**
         * FieldError , ObjectError의 생성자로 값을 담을 수 있다.
         * new FieldError(객체이름,필드명,실패값,바인딩 실패 여부,코드,인자,메시지) 등의 생성자로 다양한 정보 BindigResult객체에 삽입 가능
         */
        if (!StringUtils.hasText(item.getItemName())) {
            bindingResult.addError(new FieldError("item","itemName",item.getItemName(),false,null,null,"상품 이름은 필수 입니다."));
        }
        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            bindingResult.addError(new FieldError("item","price",item.getPrice(),false,null,null,"가격은 1000~1000000"));
        }
        if (item.getQuantity() == null || item.getQuantity() >= 9999) {
            bindingResult.addError(new FieldError("item","quantity",item.getQuantity(),false,null,null,"수량은 9999"));
        }
        //특정 필드가 아닌 복합 룰 검증
        if (item.getPrice() != null && item.getQuantity() != null) {
            int result = item.getPrice() * item.getQuantity();
            if (result < 10000) {
                bindingResult.addError(new ObjectError("item",null,null,"가격*수량 의 합은 10,000원 이상이어야 합니다. / 현재값 = "+result));
            }
        }
        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "validation/v2/addForm";
        }

        //성공 로직
        Item saveItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", saveItem.getId()); //Redirect속성 지정가능
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}"; //PRG 패턴
    }

//    @PostMapping("/add") //실제 저장하는 로직
    public String addItemV3(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("objectName = {}",bindingResult.getObjectName());
        log.info("target = {}",bindingResult.getTarget());

        if (!StringUtils.hasText(item.getItemName())) {
            bindingResult.addError(new FieldError("item","itemName",item.getItemName(),false,new String[]{"required.item.itemName"},null,null));
        }
        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            bindingResult.addError(new FieldError("item","price",item.getPrice(),false,new String[]{"range.item.price"},new Object[]{1000,1000000},null));
        }
        if (item.getQuantity() == null || item.getQuantity() >= 9999) {
            bindingResult.addError(new FieldError("item","quantity",item.getQuantity(),false,new String[]{"max.item.quantity"},new Object[]{9999},null));
        }
        //특정 필드가 아닌 복합 룰 검증
        if (item.getPrice() != null && item.getQuantity() != null) {
            int result = item.getPrice() * item.getQuantity();
            if (result < 10000) {
                bindingResult.addError(new ObjectError("item",new String[]{"totalPriceMin"},new Object[]{10000,result},null));
            }
        }
        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "validation/v2/addForm";
        }

        //성공 로직
        Item saveItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", saveItem.getId()); //Redirect속성 지정가능
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}"; //PRG 패턴
    }

    @PostMapping("/add") //실제 저장하는 로직
    public String addItemV4(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("objectName = {}",bindingResult.getObjectName());
        log.info("target = {}",bindingResult.getTarget());
        /**
         * BindingResult는 검증해야 할 객체를 이미 컨트롤러에 넘어오기 전에 알고 있음(DispatcherServlet에서 처리)
         * (BindingResult는 @ModelAttribute의 객체(바인딩될)를 검증 하기위해 사용되는 스프링 제공 객체) Model에 BindingResult값이 같이 단겨서 View로 넘어감
         * addError(new FieldError, new ObjectError)로 직접 오류 bindingResult객체에 담아도 되지만 더 편리하게 제공하는 기능이 reject
         * BindingResult.rejectVlue(필드값 검증)의 파라미터로 검증 필드, 에러 코드를 넘겨서 처리가능
         * BindingResult.reject(필드값 외 검증)의 파라미터로 에러 코드를 넘겨서 처리가능
         */

        // ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult,"itemName","required"); => 단순 공백 검증일경우 한줄로 가능

        if (!StringUtils.hasText(item.getItemName())) {
            bindingResult.rejectValue("itemName","required");
        }
        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            bindingResult.rejectValue("price","range",new Object[]{1000,1000000},null);
        }
        if (item.getQuantity() == null || item.getQuantity() >= 9999) {
            bindingResult.rejectValue("quantity","max",new Object[]{9999},null);
        }
        //특정 필드가 아닌 복합 룰 검증
        if (item.getPrice() != null && item.getQuantity() != null) {
            int result = item.getPrice() * item.getQuantity();
            if (result < 10000) {
                bindingResult.reject("totalPriceMin",new Object[]{10000,result},null);
            }
        }
        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "validation/v2/addForm";
        }

        //성공 로직
        Item saveItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", saveItem.getId()); //Redirect속성 지정가능
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}"; //PRG 패턴
    }

    private boolean hasErrors(Map<String, String> errors) {
        return !errors.isEmpty();
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/validation/v2/items/{itemId}";
    }

    @PostConstruct
    public void init() {
        itemRepository.save(new Item("ItemE", 24000, 54));
        itemRepository.save(new Item("ItemF", 56700, 11));
    }
}
