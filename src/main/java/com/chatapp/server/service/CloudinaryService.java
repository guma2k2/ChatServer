package com.chatapp.server.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CloudinaryService {

    private final Cloudinary cloudinary;
    public String uploadFile(MultipartFile multipartFile)  {
        try {
            HashMap<String, String> map = new HashMap<>();
            map.put("public_id", "album-images/40/"+multipartFile.getOriginalFilename());
            map.put("resource_type", "auto");
            return cloudinary.uploader()
                    .upload(multipartFile.getBytes(), map)
                    .get("url")
                    .toString();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    public void destroyFile(String fileDir)  {
        try {
            cloudinary.uploader().destroy(fileDir, ObjectUtils.emptyMap());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
