package nopecho.upload.file;

import nopecho.upload.domain.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStore {

    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String fileName){
        return fileDir+fileName;
    }

    public List<UploadFile> storeFiles(List<MultipartFile> files) throws IOException {
        List<UploadFile> uploadFiles = new ArrayList<>();
        for (MultipartFile file : files) {
            if(!file.isEmpty()){
                uploadFiles.add(storeFile(file));
            }
        }
        return uploadFiles;
    }

    public UploadFile storeFile(MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return null;
        }
        String originalFileName = file.getOriginalFilename();
        String storeFileName = createFileName(originalFileName);
        file.transferTo(new File(getFullPath(storeFileName)));
        return new UploadFile(originalFileName,storeFileName);
    }

    private String createFileName(String originalFilename) {
        String uuid = UUID.randomUUID().toString();
        String ext = extracted(originalFilename);
        return uuid+"."+ext;
    }

    private String extracted(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
