package com.example.demo.Controller;

import com.example.demo.entity.Collecter;
import com.example.demo.entity.CollecterWithArea;
import com.example.demo.entity.User;
import com.example.demo.mapper.CollecterMapper;
import com.example.demo.mapper.CollecterWithAreaMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.utils.BaiduFace;
import com.example.demo.utils.BaiduVoice;
import com.example.demo.utils.JWTUtil;
import com.sun.javafx.scene.traversal.Algorithm;
import io.jsonwebtoken.Jwt;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


//我做了自己的任务然后提交
//cgy02开始工作
@RestController
@RequestMapping("/baidu")
public class BaiduController {
    @Autowired
    private BaiduFace faceUtil;
    @Autowired
    private CollecterMapper collectionMapper;
    @Autowired
    private CollecterWithAreaMapper collectMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private UserMapper userMapper;
    //提交采集信息接口
    @PostMapping(value = "/commit",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String commited(
            @RequestPart("name") String name,
            @RequestPart("area") String area,
            @RequestPart("avatar") MultipartFile avatar) throws Exception{
//        // 构造实体对象并插入数据库
        Collecter collecter = new Collecter();
        collecter.setName(name);
        collecter.setArea(Integer.parseInt(area));
        collecter.setAvatar(avatar.toString());
        collecter.setTime(LocalDateTime.now().toString());
        collecter.setUid(2);//默认用户id为2
        String base64 = BaiduFace.encodeImageToBase64(avatar);
        JSONObject result= faceUtil.registerFace(base64, "BASE64", "100", name);
        if (result.has("result") && !result.isNull("result")) {
            String faceToken = result.getJSONObject("result").getString("face_token");
            System.out.println(faceToken);
            collecter.setFacetoken(faceToken);

            // 2. 处理头像文件
            if (!avatar.isEmpty()) {
                // 定义文件保存路径（例如：D:/uploads/avatar/）
//            String uploadDir = "D:/uploads/avatar/";

                String BASE_PATH = System.getProperty("user.dir");
                String uploadDir = BASE_PATH + "/src/main/resources/static/peoples/";
                File dir = new File(uploadDir);
                if (!dir.exists()) {
                    dir.mkdirs(); // 创建目录
                }
                // 获取原始文件名
                String originalFilename = avatar.getOriginalFilename();
                // 拼接完整路径
                String filePath = uploadDir + originalFilename;

                // 保存文件到服务器
                avatar.transferTo(new File(filePath));
                // 将文件路径赋值给实体类字段
                String filePath2 = "http://localhost:8082/peoples/" + originalFilename;
                collecter.setAvatar(filePath2); // 或者存相对路径 / URL
            }
            // 3. 插入数据库
            collectionMapper.insert(collecter);
            return "上传成功了1条数据";
        }else{
            return "error"+"注册失败：" + result.toString();
        }
    }
    //删除采集信息接口
    @PutMapping("/delete")
    public String delCollect(int id){

        Collecter collecter=collectionMapper.getById(id);
        String faceToken= collecter.getFacetoken();
        if(faceToken!=null){
            //删除百度人脸库
            JSONObject result= faceUtil.deleteFace(collecter.getName(), "100", faceToken);
            int errorCode = result.getInt("error_code");
            if( errorCode != 0){
                System.out.println("错误码"+errorCode);
                return "error"+"删除失败：" + result.toString();
            }else{
                collectionMapper.deleteById(id);
                return "删除成功1条数据"+result.toString();
            }
        }else{
            collectionMapper.deleteById(id);
            return "删除成功1条未注册人脸的数据";
        }
    }
    //查询{本人}今天的数据collection,
    @GetMapping("/gettoday")
    public List<CollecterWithArea> getCollect() {
        return collectMapper.getToday();
    }
    //查询本人分组采集信息
    @GetMapping("/getgroup")
    public List<Map<String, Object>> getGroup() {
        List<CollecterWithArea> list = collectMapper.getAll(2);
        return list.stream()
                .filter(item -> item.getTime() != null)
                .collect(Collectors.groupingBy(
                        item -> item.getTime().split(" ")[0]
                ))
                .entrySet()      //将 Map 转换为 List 并排序
                .stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))//降序
                .map(entry -> {
                    Map<String, Object> result = new HashMap<>();
                    result.put("date", entry.getKey());
                    result.put("count", entry.getValue().size());
                    return result;
                })
                .collect(Collectors.toList());
    }
    // 百度人脸识别
    @PostMapping(value = "/identify",produces = MediaType.APPLICATION_JSON_VALUE)
    public String identify(@RequestParam("avatar") MultipartFile image){
        System.out.println("开始识别");
        String groupId = "100";
        if (image.isEmpty()) {
            return "error"+"请上传图片";
        }
        // 将上传的图片转换为 Base64 编码
        try {
            String base64 = BaiduFace.encodeImageToBase64(image);
            JSONObject result = faceUtil.identifyFace(base64, "BASE64", groupId);
            int errorCode = result.getInt("error_code");
            if( errorCode != 0){
                System.out.println("错误码"+errorCode);
                return "error"+"识别失败";
            }else{
//    System.out.println(result.getJSONObject("result").getString("face_token"));
//System.out.println(result.getJSONObject("result").getJSONArray("user_list").getJSONObject(0).getString("user_id"));
                String name=result.getJSONObject("result").getJSONArray("user_list").getJSONObject(0).getString("user_id");
                int score=result.getJSONObject("result").getJSONArray("user_list").getJSONObject(0).getInt("score");
                System.out.println(name+"  "+score);
                if(score>80){
                    return name;
                }else{
                    return "error"+"识别失败";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error"+"识别失败：" + e.getMessage();
        }
    }
    // 百度语音识别
    @PostMapping(value = "/voice",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String voice(@RequestParam(value="voice",required = false) MultipartFile voice) throws Exception {
        BaiduVoice voiceUtil=new BaiduVoice();

        System.out.println("开始识别");
        if (voice.isEmpty()) {
            return "error"+"请上传图片";
        }
        // 将上传的图片转换为 Base64 编码
        try {
            JSONObject res = voiceUtil.getVoice(voice);
            String content=res.getJSONArray("result").getString(0);
//            int errorCode = res.getInt("err_no");
//            System.out.println(content+"---"+errorCode);
            return content;
        } catch (Exception e) {
            e.printStackTrace();
            return "error"+"识别失败请重试" + e.getMessage();
        }
    }
    // 百度人脸注册测试接口
    @PostMapping(value = "/registry",produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCollectWithArea(@RequestParam String userId,
                                     @RequestParam String groupId,
                                     @RequestParam("image") MultipartFile image){
        if (image.isEmpty()) {
            return "error"+"请上传图片";
        }
        try {
            // 将上传的图片转换为 Base64 编码
            String base64Image = BaiduFace.encodeImageToBase64(image);

            // 调用百度人脸注册接口
            JSONObject result = faceUtil.registerFace(base64Image, "BASE64", groupId, userId);
            int errorCode = result.getInt("error_code");
            if( errorCode != 0){
                System.out.println("错误码"+errorCode);
                return "error"+"注册失败：" + result.toString();
            }else{
                return "注册成功"+result.toString();
            }
//       return "注册成功"+result.toString()+"code"+errorCode;
        } catch (Exception e) {
            e.printStackTrace();
            return "error"+"注册失败：" + e.getMessage();
        }
    }
    // 百度人脸删除测试接口
    @DeleteMapping(value = "/delPeople",produces = MediaType.APPLICATION_JSON_VALUE)
    public String delPeople(@RequestParam String userId,
                            @RequestParam String groupId,
                            @RequestParam String faceToken){
        JSONObject result = faceUtil.deleteFace(userId, groupId, faceToken);
        int errorCode = result.getInt("error_code");
        if( errorCode != 0){
            System.out.println("错误码"+errorCode);
            return "error"+"删除失败：" + result.toString();
        }else{
            return "删除成功"+result.toString();
        }
    }
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestParam String phone, @RequestParam String code) {
        System.out.println("开始登录");
        Map<String, Object> response = new HashMap<>();
      if(phone!=null&& code!= null){
    String storedCode = (String)redisTemplate.opsForValue().get("code:" + phone);
//          System.out.println(storedCode);
        if (storedCode != null && storedCode.equals(code)) {
            User user = userMapper.getUserByPhone(phone);
            //数据库查询用户信息
            if (user == null) {
                User user1 = new User();
                String[] surnames = {"赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈"};
                String[] givenNames = {"伟", "芳", "娜", "敏", "静", "丽", "强", "磊", "洋", "勇"};
                Random random = new Random();
                String surname = surnames[random.nextInt(surnames.length)];
                String givenName = givenNames[random.nextInt(givenNames.length)];
                //随机生成用户名
                user1.setUsername(surname+givenName);
                user1.setPhone(phone);
                //随机生成头像
                user1.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
                userMapper.insert(user1);//插入数据库新用户
                String token = jwtUtil.createToken(user1);
                response.put("code", 104);
                response.put("msg", "用户注册登录成功");
                response.put("token", token);
                response.put("user", user1);
            }else{
                String token = jwtUtil.createToken(user);
                response.put("code", 100);
                response.put("msg", "老用户登录成功");
                response.put("token", token);
                response.put("user", user);
            }
        } else {
            response.put("code", 404);
            response.put("msg", "验证码错误或已过期");
        }
      }else {
          response.put("code", 403);
          response.put("msg", "验证码为空或手机号为空");
      }
        return ResponseEntity.ok(response);
    }
    @PostMapping("/login2")
    public ResponseEntity<Map<String, Object>> login2(@RequestParam String code) {
             //前端传用户code
        //post -->http://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token=code
        //小程序端：http://developers.weixin.qq.com/miniprogram/dev/framework/open-ablilty/getPhoneNumber.html
        //通过微信开发平台接口获得用户手机号
        //拿到手机号再数据库里查询判断是否为新用户
        //新用户则插入数据库，再签发token，老用户直接签发token，返回用户信息
        //这里使用第一个用户的信息快速登录默认
        User user = userMapper.getUserById(1);
        String token = jwtUtil.createToken(user);
        Map<String, Object> response = new HashMap<>();
        response.put("code", 100);
        response.put("msg", "用户登录成功");
        response.put("token", token);
        response.put("user", user);
        return ResponseEntity.ok(response);
    }
    @PutMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody User user) {
        System.out.println(user);
       int num= userMapper.updateById(user);
        System.out.println("更新了"+num+"条数据");
    }

}
