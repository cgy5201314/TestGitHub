package com.example.demo.utils;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/file")
public class UploadController {
    private static final String BASE_PATH = System.getProperty("user.dir");
    // 拼接上传路径（相对于项目根目录）
    private static final String UPLOAD_DIR = BASE_PATH + "/src/main/resources/static/uploads/";
//      private static final String UPLOAD_DIR =  "D:/xiangmu/image/";
private static final String UPLOAD_DIR2 = BASE_PATH + "/src/main/resources/static/temps/";
    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("文件为空");
        }

        try {
            // 确保目录存在
            File dir = new File(UPLOAD_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 使用原始文件名保存（可选：重命名）
            String originalFilename = file.getOriginalFilename();
            String filePath = UPLOAD_DIR + originalFilename;

            // 保存文件
            file.transferTo(new File(filePath));

            // 返回访问路径（假设你配置了静态资源映射）
            String fileUrl = UPLOAD_DIR + originalFilename;
            return ResponseEntity.ok(fileUrl);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("上传失败");
        }
    }
    // ==== 新增：获取随机图片接口 ====
    @GetMapping("/get")
    public ResponseEntity<InputStreamResource> getRandomImage() throws IOException {
        File folder = new File(UPLOAD_DIR);
        if (!folder.exists() || !folder.isDirectory()) {
            throw new FileNotFoundException("目录不存在: " + UPLOAD_DIR);
        }

        // 获取所有图片文件（简单扩展名过滤）
        List<File> imageFiles = Arrays.stream(Objects.requireNonNull(folder.listFiles()))
                .filter(file -> file.isFile() && isImageFile(file.getName()))
                .collect(Collectors.toList());

        if (imageFiles.isEmpty()) {
            throw new FileNotFoundException("目录中没有图片文件");
        }

        // 随机选一张图
        Random rand = new Random();
        File randomImage = imageFiles.get(rand.nextInt(imageFiles.size()));

        // 设置响应头
        MediaType mediaType = getMediaTypeForFileName(randomImage.getName());

        InputStreamResource resource = new InputStreamResource(new FileInputStream(randomImage));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + randomImage.getName())
                .contentType(mediaType)
                .contentLength(randomImage.length())
                .body(resource);
    }

    // 判断是否为图片文件
    private boolean isImageFile(String fileName) {
        String[] imageExtensions = {".jpg", ".jpeg", ".png", ".gif", ".bmp",".webp"};
        String lowerCaseName = fileName.toLowerCase();
        for (String ext : imageExtensions) {
            if (lowerCaseName.endsWith(ext)) {
                return true;
            }
        }
        return false;
    }

    // 根据文件名获取对应的 MediaType
    private MediaType getMediaTypeForFileName(String fileName) {
        String lowerCaseName = fileName.toLowerCase();
        if (lowerCaseName.endsWith(".png")) {
            return MediaType.IMAGE_PNG;
        } else if (lowerCaseName.endsWith(".jpg") || lowerCaseName.endsWith(".jpeg")) {
            return MediaType.IMAGE_JPEG;
        } else if (lowerCaseName.endsWith(".gif")) {
            return MediaType.IMAGE_GIF;
        } else if (lowerCaseName.endsWith(".bmp")) {
            return MediaType.valueOf("image/bmp");
        } else if (lowerCaseName.endsWith(".webp")) {
            return MediaType.valueOf("image/webp");
        }else {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }
    @PostMapping("/uploadAvatar")
    public ResponseEntity<String> upload(@RequestParam("avatar") MultipartFile avatar) {
        if (avatar.isEmpty()) {
            return ResponseEntity.badRequest().body("文件为空");
        }

        try {
            // 确保目录存在
            File dir = new File(UPLOAD_DIR2);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 使用原始文件名保存（可选：重命名）
            String originalFilename = avatar.getOriginalFilename();
            String filePath = UPLOAD_DIR2 + originalFilename;

            // 保存文件
            avatar.transferTo(new File(filePath));

            // 返回访问路径（假设你配置了静态资源映射）
            String filePath2 = "http://localhost:8082/temps/" + originalFilename;
//            String fileUrl = UPLOAD_DIR2 + originalFilename;
            System.out.println(filePath2);
            return ResponseEntity.ok(filePath2);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("上传失败");
        }
    }
}

