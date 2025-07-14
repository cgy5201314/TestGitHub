package com.example.demo.utils;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.util.Util;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;

public class BaiduVoice {
        //设置APPID/AK/SK
        public static final String APP_ID = "6966133";
        public static final String API_KEY = "dbw0WADZM9PIgPZDJtLRZPWP";
        public static final String SECRET_KEY = "VX1rBRlTXq3N7SG11MxtNVqGG4MT7Fqw";
        public static AipSpeech client;
        public BaiduVoice() {
           // 初始化一个 AipFace 客户端
           client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);
           // 可选：设置网络连接参数
           client.setConnectionTimeoutInMillis(2000);
           client.setSocketTimeoutInMillis(60000);
       }
        public static void main(String[] args) throws  IOException{

//            // 可选：设置网络连接参数
//            client.setConnectionTimeoutInMillis(20000);
//            client.setSocketTimeoutInMillis(60000);
//            // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//            client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//            client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理
            // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
            // 也可以直接通过jvm启动参数设置此环境变量
//            System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

            // 对本地语音文件进行识别
//            JSONObject asrRes = client.asr(file, "wav", 16000, options);
//            System.out.println(asrRes);

            // 对语音二进制数据进行识别

        }
       public  static  JSONObject getVoice(MultipartFile file) throws  Exception{
           HashMap options = new HashMap<String, Object>();
           options.put("dev_pid", 1537);
           byte[] data = file.getBytes(); // 正确获取音频二进制数据
          return client.asr(data, "pcm", 16000, options);
//           System.out.println(asrRes2);
        }
}
//package com.example.demo.utils;
//
//import com.baidu.aip.speech.AipSpeech;
//import com.baidu.aip.util.Util;
//import org.json.JSONObject;
//
//import java.io.File;
//import java.util.HashMap;
//
//public class BaiduVoice {
//
//    // 设置 APPID/AK/SK
//    public static final String APP_ID = "6966133";
//    public static final String API_KEY = "dbw0WADZM9PIgPZDJtLRZPWP";
//    public static final String SECRET_KEY = "VX1rBRlTXq3N7SG11MxtNVqGG4MT7Fqw";
//
//    /**
//     * 语音识别主方法
//     *
//     * @param filePath 音频文件路径（支持 pcm/wav/amr）
//     * @param format   音频格式（pcm/wav/amr）
//     * @param rate     采样率（8000 / 16000）
//     * @return 识别后的文本内容
//     */
//    public static String recognizeAudio(String filePath, String format, int rate){
//        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);
//
//        // 可选参数设置
//        HashMap<String, Object> options = new HashMap<>();
//        options.put("dev_pid", 1537); // 普通话(支持标点)阅读理解模型
//
//        try {
//            // 如果是文件路径识别
//            if (format.equals("pcm") || format.equals("wav")) {
//                JSONObject res = client.asr(filePath, format, rate, options);
//                return parseResult(res);
//            }
//            // 如果是二进制数据识别（可选）
//            else {
//                byte[] data = Util.readFileByBytes(new File(filePath).toString());
//                JSONObject res = client.asr(data, format, rate, options);
//                return parseResult(res);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "识别失败：" + e.getMessage();
//        }
//    }
//
//    /**
//     * 解析识别结果
//     */
//    private static String parseResult(JSONObject result) {
//        if (result.has("err_no") && result.getInt("err_no") != 0) {
//            return "识别失败: " + result.getString("err_msg");
//        }
//
//        if (result.has("result")) {
//            return result.getJSONArray("result").getString(0);
//        }
//
//        return "未识别到有效内容";
//    }
//
//    /**
//     * 示例 main 方法调用
//     */
//    public static void main(String[] args) {
//        String file = "C:\\Users\\86182\\Desktop\\uml\\67tool\\67tool\\hello.wav";
//
//        // 调用识别方法
//        String text = recognizeAudio(file, "wav", 16000);
//        System.out.println("识别结果：\n" + text);
//    }
//}
