package com.example.demo.utils;

import com.baidu.aip.face.AipFace;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.util.Base64;
import java.util.HashMap;
@Component
public class BaiduFace {
    // 设置你的百度云人脸识别的 AK/SK
    public static final String APP_ID = "6964005";
    public static final String API_KEY = "1MX5P9oEhjCU56qgfRc09dWT";
    public static final String SECRET_KEY = "PrBgfMOQo2ptM7koL592DfSY13sXZDwI";
    private AipFace client;

    public BaiduFace() {
        // 初始化一个 AipFace 客户端
        client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
    }

    /**
     * 人脸注册
     *
     * @param image      图像数据（Base64 编码）
     * @param imageType  图像类型（BASE64 / URL / FACE_TOKEN）
     * @param groupId    用户组ID
     * @param userId     用户ID
     * @return JSONObject 响应结果
     */
    public JSONObject registerFace(String image, String imageType, String groupId, String userId) {
        HashMap<String, Object> options = new HashMap<>();
//        options.put("user_info", userId);         // 用户信息（可选）
//        options.put("liveness_control", "none"); // 活体检测控制（可选）
//        options.put("action_type", "replace");   // 如果用户已存在，则替换
        // 调用人脸注册接口
        return client.addUser(image, imageType, groupId, userId, null);
    }

    /**
     * 人脸识别（搜索）
     *
     * @param image      图像数据（Base64 编码）
     * @param imageType  图像类型（BASE64 / URL / FACE_TOKEN）
     * @param groupIdList 用户组列表，用逗号分隔多个组名
     * @return JSONObject 响应结果
     */
    public JSONObject identifyFace(String image, String imageType, String groupIdList) {
        HashMap<String, Object> options = new HashMap<>();
//        options.put("liveness_control", "low"); // 活体检测控制（可选）
        options.put("max_user_num", 3);          // 最多返回匹配用户数量
        // 调用人脸识别接口
        return client.search(image, imageType, groupIdList, options);
    }

    /**
     * 删除人脸
     *
     * @param userId   用户ID
     * @param groupId  用户组ID
     * @param faceToken 人脸Token（唯一标识一张人脸）
     * @return JSONObject 响应结果
     */
    public JSONObject deleteFace(String userId, String groupId, String faceToken) {
        return client.faceDelete(userId, groupId, faceToken, null);
    }

// 将图片转换为 Base64 编码
    public static String encodeImageToBase64(String imagePath) throws Exception {
        File file = new File(imagePath);
        byte[] fileContent = Files.readAllBytes(file.toPath());
        return Base64.getEncoder().encodeToString(fileContent);
    }
    // 新增方法：从上传的 MultipartFile 转 Base64
    public static String encodeImageToBase64(MultipartFile file) throws Exception {
        byte[] fileContent = file.getBytes();
        return Base64.getEncoder().encodeToString(fileContent);
    }

    // 示例 main 方法测试
    public static void main(String[] args) throws  Exception{
        String base64=encodeImageToBase64("C:\\Users\\86182\\Desktop\\uml\\tttt.jpg");

        BaiduFace faceUtil = new BaiduFace();

        // 示例图像 Base64 数据（请替换成真实的图片 Base64 字符串）
        String imageBase64 = base64;
        String groupId = "100";       // 用户组名称
        String userId = "lxy";         // 用户ID
   String faceToken = "e9a740fd9b7b159bbbabaa6c735e66c5";  // 从注册或识别结果中获取mm11
//mm2        3638ba3550c07aa60940ce02a1a1dba1
//tttt.jpg lxy a691597a850286297b77b1ece240d5dd
        //lyf e9a740fd9b7b159bbbabaa6c735e66c5
        // 1. 注册人脸
//        JSONObject registerRes = faceUtil.registerFace(imageBase64, "BASE64", groupId, userId);
//        System.out.println("注册结果：" + registerRes.toString(2));
        // 2. 人脸识别
//        JSONObject identifyRes = faceUtil.identifyFace(imageBase64, "BASE64", groupId);
//        System.out.println("识别结果：" + identifyRes.toString(2));

        // 3. 删除人脸
//        JSONObject deleteRes = faceUtil.deleteFace(userId, groupId, faceToken);
//        System.out.println("删除结果：" + deleteRes.toString(2));
    }
}

/**
 * 创建用户组（可选）
 */
//    public JSONObject createGroup(String groupId) {
//        return client.groupAdd(groupId);
//    }

/**
 * 删除用户组（可选）
 */
//    public JSONObject deleteGroup(String groupId) {
//        return client.groupDelete(groupId);
//    }