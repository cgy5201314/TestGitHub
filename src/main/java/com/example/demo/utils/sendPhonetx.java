package com.example.demo.utils;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/code")
public class sendPhonetx {

    @Value("${tencent.sms.appid}")
    private String appId;

    @Value("${tencent.sms.appkey}")
    private String appKey;

    @Value("${tencent.sms.template-id}")
    private String templateId;

    @Value("${tencent.sms.sign-name}")
    private String signName;
  @Autowired
  private RedisTemplate redisTemplate;

    public sendPhonetx(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> sendVerificationCode(@RequestParam String phone) {
        Map<String, Object> response = new HashMap<>();
        String code = generateCode(6); // 生成6位验证码
        System.out.println(code);
        try {
//发送短信  boolean success = sendSms(phone, code);
            if (true) {
                // 缓存验证码到 Redis（有效期5分钟）
                redisTemplate.opsForValue().set("code:" + phone, code, 5, TimeUnit.MINUTES);
                response.put("success", true);
                response.put("msg", "验证码已发送");
            } else {
                response.put("success", false);
                response.put("msg", "验证码发送失败");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("msg", "系统异常");
            e.printStackTrace();
        }

        return ResponseEntity.ok(response);
    }
// 生成指定长度的随机数
    private String generateCode(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
// 发送短信
    private boolean sendSms(String phone, String code) throws Exception {
        Credential cred = new Credential(appId, appKey);

        SmsClient client = new SmsClient(cred, "");

        SendSmsRequest req = new SendSmsRequest();
        req.setPhoneNumberSet(new String[]{"+86" + phone}); // 注意加国家代码
        req.setTemplateId(templateId);
        req.setSignName(signName);
        req.setTemplateParamSet(new String[]{code});

        SendSmsResponse res = client.SendSms(req);
        return res.getSendStatusSet()[0].getCode().equals("Ok");
    }
}

