package com.example.demo.Controller;

import com.example.demo.entity.*;
import com.example.demo.mapper.*;
import com.example.demo.utils.BaiduFace;
import com.example.demo.utils.BaiduVoice;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FilmMapper filmMapper;
    @Autowired
    private CollecterWithAreaMapper collectMapper;
    @Autowired
    private CollecterMapper collectionMapper;
    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private final BaiduFace faceUtil=new BaiduFace();
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private ActivityinfoMapper activityinfoMapper;
    @Autowired
    private ActivityV0Mapper activityV0Mapper;
    @GetMapping("/get")
    public User hello2() throws  Exception{
//       Thread.sleep(3000);
        User user=userMapper.getUserById(1);
        return user;
    }
    @GetMapping("/getfilm")
    public List<Film> file(){
        List<Film> film=filmMapper.getAllFilm();
        return film;
    }
    @PostMapping("/addfilm")
    public void file(@RequestBody Film film){
       int num=filmMapper.addFilm(film);
        System.out.println("成功插入了电影数据"+num+"条");
    }
    @PutMapping("/deleteById")
    public void delete(@RequestParam String id){
        int num=filmMapper.deleteFilmById(id);
        System.out.println("成功删除了电影数据"+num+"条");
    }

    @GetMapping("/getCollect")
    public void getAllCollect(){
//        return collectMapper.getAll();
    }

    //获取本人的采集数据
    @GetMapping("/collects")
    public List<CollecterWithArea> getCollects(){
    //先默认采集用户2的信息
        return collectMapper.getAll(2);
    }

    //获取某责任人的区域
    @GetMapping("/getArea")
    public List<Area> getArea(int usernum){
        return areaMapper.getAll(usernum);
    }

    //查询{本人}x天的数据collection,
    @GetMapping("/getday")
    public List<CollecterWithArea> getDay(String day) {
        return collectMapper.getDay(day);
    }
//    @GetMapping("/getgroup")
//    public Map<String,List<CollecterWithArea>> getGroup() {
//        List<CollecterWithArea> list = collectMapper.getAll();
//        return list.stream()
//                .filter(item -> item.getTime()!= null)
//                .collect(Collectors.groupingBy(item -> item.getTime().split(" ")[0]));
//    }

   @GetMapping("/news")
    public Map<String, Object> getNews() {
        HashMap<String, Object> map = new HashMap();
        List<Notice> notices = noticeMapper.getAllNotice();
        map.put("code", 0); // 成功状态码
        map.put("message", "success");
        map.put("data", notices);
//       System.out.println(map);
       return map;
   }
   @PutMapping("/del")
    public String delNews(@RequestParam int id) {
        int num = noticeMapper.deleteById(id);
        return "成功删除"+num+"条数据";
   }
   //获取某人的活动
    @GetMapping("/activitys")
    public Map<String, Object> getAllActivity(@RequestParam int uid) {
        HashMap<String, Object> map = new HashMap();

        List<Activity> activities = activityMapper.getAllActivity();
         Activityinfo activityinfo=activityinfoMapper.selectById(uid);
//        System.out.println(activityinfo);
         if(activityinfo!=null){
             map.put("code", 0); // 成功状态码
             map.put("message", "success");
             map.put("data", activities);
             map.put("activityinfo", activityinfo);
             System.out.println(map);
         }else{
             map.put("code", 404);
             map.put("message", "此用户未参加活动");
             map.put("data", activities);
             map.put("activityinfo", " ");
         }
        return map;
    }
    //获取所有活动
    @GetMapping("/activityes")
    public Map<String, Object> getAllActivity() {
        HashMap<String, Object> map = new HashMap();
        List<Activity> activities = activityMapper.getAllActivity();
            map.put("code", 0); // 成功状态码
            map.put("message", "success");
            map.put("data", activities);
            System.out.println(map);
        return map;
    }
    @GetMapping("/myactivitys")
    public Map<String, Object> getAllMyActivity(@RequestParam int uid) {
        HashMap<String, Object> map = new HashMap();
        List<ActivityV0> myactivitys = activityV0Mapper.getActivityV0Mapper(uid);
        map.put("code", 0); // 成功状态码
        map.put("message", "success");
        map.put("data", myactivitys);
        System.out.println(map);
        return map;
    }
    @PostMapping("/addinfo")
    public Map<String, Object> addinfo(@RequestParam int aid, @RequestParam int uid) {
        HashMap<String, Object> map = new HashMap();
        Activity activity = activityMapper.selectById(aid);
        //活动总人数限制
        if(activity.getDoman()<activity.getDomans()){
//            System.out.println(activityinfoMapper.selectInfo(aid,uid));null
            //如果原本没有信息就添加信息
            if(activityinfoMapper.selectInfo(aid,uid)==null){
                int status = activityinfoMapper.insert(aid, uid);
                if(status!=0){
                    //人数足够
                    int doman =activity.getDoman();
                    activity.setDoman(doman+1);
                    map.put("code", 202); // 成功状态码
                    map.put("message", "success");
                    map.put("data", "领取活动信息成功");
                }else{
                    map.put("code", 410); // 成功状态码
                    map.put("message", "success");
                    map.put("data", "添加活动信息失败");
                }
            }
        }else{
            map.put("code", 410);
            map.put("message", "活动人数已满");
            map.put("data", "活动人数已满");
        }
        return map;
    }
    @Transactional
    @PutMapping("/addTarget")
    public Map<String, Object> runMyActivity(@RequestParam int aid,@RequestParam int uid) {
        HashMap<String, Object> map = new HashMap();
        int status = activityinfoMapper.update(aid, uid);//更新活动状态
        if(status!=0){
           Activityinfo act= activityinfoMapper.selectInfo(aid, uid);
           //只有当新增时人数加了
            if(act.getStatus()==1){
                //如果修改成功，人数减一
                Activity activity = activityMapper.selectById(aid);
                int doman =activity.getDoman();
//            activity.setDoman(doman+1);
                int up= activityMapper.update(aid, doman+1);//更新活动人数

                if(up!=0){
                    map.put("code", 202); // 成功状态码
                    map.put("message", "success");
                    map.put("data", "更新活动人数、状态成功");
                }else{
                    map.put("code", 410); // 成功状态码
                    map.put("message", "success");
                    map.put("data", "更新活动人数、状态失败");
                }
            }else{
                //否则只有状态的改变
                map.put("code", 203); // 成功状态码
                map.put("message", "success");
                map.put("data", "更新活动人数、状态成功");
            }


        }else{
            map.put("code", 410); // 成功状态码
            map.put("message", "success");
            map.put("data", "更新活动状态失败");
        }
        return map;
    }
    //任务完成，增加积分
    @Transactional
    @PutMapping("/addScore")
    public Map<String, Object> addScore(@RequestParam int num,@RequestParam int uid) {
        HashMap<String, Object> map = new HashMap();
       int n= userMapper.updatePay(uid,num);//更新积分
       if(userMapper.getUserById(uid)!=null){
           int score=userMapper.getUserById(uid).getScore();//获取积分
           if(n!=0){
               map.put("code", 202); // 成功状态码
               map.put("message", "success");
               map.put("data", "更新积分成功");
               map.put("score", score);
           }else{
               map.put("code", 410); // 成功状态码
               map.put("message", "success");
               map.put("data", "更新积分失败");
           }
       }
       return map;
    }
    @PutMapping("/delActivity")
    public String delAcivity(@RequestParam int id) {
        int num = activityMapper.deleteById(id);
        return "成功删除"+num+"条数据";
    }
}
