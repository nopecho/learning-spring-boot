package nopecho.upload.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadFile {

    //파일명 두개인 이유 => 사용자 A,B 가있을때 두 사용자 모두 a.png 파일을 서버에 업로드 할 수 있다. 그러므로 서버에선 각 이미지들의 고유이름을 저장 시켜야함
    private String uploadFileName;
    private String storeFileName;
}
