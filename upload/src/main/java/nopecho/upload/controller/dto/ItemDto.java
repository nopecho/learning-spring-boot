package nopecho.upload.controller.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Form에서 넘어오는 데이터 전송객체 (해당 객체가 id,name,file정보를 가지고 뷰에서 컨트롤러로 넘어온다)
 */
@Data
public class ItemDto {

    private Long itemId;
    private String itemName;
    private List<MultipartFile> files;
    private MultipartFile file;
}
