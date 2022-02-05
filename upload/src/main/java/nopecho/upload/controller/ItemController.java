package nopecho.upload.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nopecho.upload.controller.dto.ItemDto;
import nopecho.upload.domain.Item;
import nopecho.upload.domain.ItemRepository;
import nopecho.upload.domain.UploadFile;
import nopecho.upload.file.FileStore;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final FileStore fileStore;

    @GetMapping("/items/new")
    public String newItem(@ModelAttribute ItemDto itemDto){
        return "item-form";
    }

    @PostMapping("/items/new")
    public String saveItem(@ModelAttribute ItemDto itemDto, RedirectAttributes redirectAttributes) throws IOException {
        UploadFile uploadFile = fileStore.storeFile(itemDto.getFile());
        List<UploadFile> uploadFiles = fileStore.storeFiles(itemDto.getFiles());

        Item item = new Item();
        item.setItemName(itemDto.getItemName());
        item.setFile(uploadFile);
        item.setFiles(uploadFiles);
        itemRepository.save(item);

        redirectAttributes.addAttribute("itemId",item.getId());

        return "redirect:/items/{itemId}";
    }

    @GetMapping("/items/{id}")
    public String items(@PathVariable Long id, Model model){
        Item findItem = itemRepository.findById(id);
        model.addAttribute("item",findItem);
        return "item-view";
    }

    @ResponseBody
    @GetMapping("/images/{fileName}")
    public Resource downwloadImage(@PathVariable String fileName) throws MalformedURLException {
        return new UrlResource("file:"+fileStore.getFullPath(fileName));
    }

    @GetMapping("/attach/{itemId}")
    public ResponseEntity<Resource> download(@PathVariable Long itemId) throws MalformedURLException {
        Item item = itemRepository.findById(itemId);
        String storeFileName = item.getFile().getStoreFileName();
        String uploadFileName = item.getFile().getUploadFileName();
        UrlResource urlResource = new UrlResource("file:" + fileStore.getFullPath(storeFileName));

        String contentDisposition = "attachment; filename=\""+uploadFileName+"\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,contentDisposition)
                .body(urlResource);
    }
}
