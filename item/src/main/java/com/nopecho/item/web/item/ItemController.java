package com.nopecho.item.web.item;

import com.nopecho.item.domain.item.*;
import com.nopecho.item.web.validation.form.ItemSaveForm;
import com.nopecho.item.web.validation.form.ItemUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/items")
public class ItemController {

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
        return "items/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "items/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "items/addForm";
    }

    @PostMapping("/add")
    public String addItem(@Validated @ModelAttribute("item") ItemSaveForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(form.getPrice() != null && form.getQuantity() != null){
            int totalPeice = form.getPrice() * form.getQuantity();
            if(totalPeice < 10000){
                bindingResult.reject("totalPriceMin",new Object[]{10000,totalPeice},null);
            }
        }

        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "items/addForm";
        }
        Item item = getItem(form);

        Item saveItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", saveItem.getId()); //Redirect속성 지정가능
        redirectAttributes.addAttribute("status", true);
        return "redirect:/items/{itemId}"; //PRG 패턴
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "items/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String editV2(@PathVariable Long itemId, @Validated @ModelAttribute("item") ItemUpdateForm form, BindingResult bindingResult) {
        if(form.getPrice() != null && form.getQuantity() != null){
            int totalPeice = form.getPrice() * form.getQuantity();
            if(totalPeice < 10000){
                bindingResult.reject("totalPriceMin",new Object[]{10000,totalPeice},null);
            }
        }

        if(bindingResult.hasErrors()){
            log.info("error = {}",bindingResult);
            return "items/editForm";
        }
        Item item = getItem(form);
        itemRepository.update(itemId, item);
        return "redirect:/items/{itemId}";
    }

    private Item getItem(ItemSaveForm form) {
        Item item = new Item();
        item.setItemName(form.getItemName());
        item.setPrice(form.getPrice());
        item.setQuantity(form.getQuantity());
        item.setRegions(form.getRegions());
        item.setDeliveryCode(form.getDeliveryCode());
        item.setOpen(form.getOpen());
        item.setItemType(form.getItemType());
        return item;
    }

    private Item getItem(ItemUpdateForm form) {
        Item item = new Item();
        item.setItemName(form.getItemName());
        item.setPrice(form.getPrice());
        item.setQuantity(form.getQuantity());
        item.setPrice(form.getPrice());
        item.setQuantity(form.getQuantity());
        item.setRegions(form.getRegions());
        item.setDeliveryCode(form.getDeliveryCode());
        item.setOpen(form.getOpen());
        item.setItemType(form.getItemType());
        return item;
    }
}
