package com.lcworld.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import com.lcworld.entity.*;
import com.lcworld.service.*;
import com.lcworld.utils.*;
import com.lcworld.validator.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.annotation.IgnoreSign;
import com.lcworld.annotation.IgnoreToken;
import com.lcworld.consts.APPConstant;
import com.lcworld.consts.RedisConst;
import com.lcworld.dto.PurchaseDTO;
import com.lcworld.exception.ZHHQException;
import com.lcworld.interceptor.TokenCheckInterceptor;
import com.lcworld.vo.TUserAuthVO;

@RestController
@RequestMapping("appuser/user")
public class TUserController {
    private Logger log = LoggerFactory.getLogger(TUserController.class);
    @Autowired
    private TUserService tUserService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private TUserAppealService tUserAppealService;
    @Autowired
    private TUserAuthService tUserAuthService;
    @Autowired
    private TCarCartypeService tCarCartypeService;
    @Autowired
    private TUserPositionService tUserPositionService;
    @Autowired
    private UserCaptchaService userCaptchaService;
    @Autowired
    private UserAdviceService userAdviceService;
    @Autowired
    private DepartService departService;
    @Autowired
    private InneruserService inneruserService;
    @Autowired
    private VisiuserLogService visiuserLogService;

    /**
     * 用户登录
     *
     * @param req
     * @return
     */
    @RequestMapping("/login")
    @IgnoreToken
    @ResponseBody
    public R login(HttpServletRequest req) {
        String biz = req.getParameter("biz");
        log.debug("biz:" + biz);
        R result = new R();
        TUserEntity user = FastJSONUtils.getObject(biz, TUserEntity.class);
        TUserEntity tuser = tUserService.queryByUserName(user.getUserName());
        if (tuser != null) {
            if (user.getPassword().equals(tuser.getPassword())) {
                result = R.ok().put("userinfo", getbasicInfo(tuser));
                String token = String.valueOf(tokenService.createToken(tuser.getId()).get("token"));
                result.put("token", token);
            } else {
                result = R.error(1, "密码错误");
            }
        } else {
            result = R.error(1000, "该用户不存在");
        }
        return result;
    }


    private Map<String, Object> getbasicInfo(TUserEntity tuser) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("nickname", tuser.getNickname());
        map.put("photo", tuser.getPhoto());
        map.put("sex", tuser.getSex());
        map.put("userId", tuser.getId());
        map.put("mobile", tuser.getMobile());
        map.put("realname", tuser.getRealname());
        map.put("nation", tuser.getNation());
        JSONObject params = new JSONObject();
        params.put("uid", tuser.getId());
//        map.put("rolelist", baseUserRoleService.queryList(params));
        return map;
    }

    /**
     * 获取验证码
     *
     * @param req
     * @return
     */
    @RequestMapping("/getCaptcha")
    @IgnoreToken
    @ResponseBody
    public R getCaptcha(HttpServletRequest req) {
        String biz = req.getParameter("biz");
        log.debug("biz:" + biz);
        R result = new R();
        JSONObject resultObj = new JSONObject();
        String captcha = WebUtils.getCapacha();
        // 获取入参
        JSONObject jsonParams = JSONObject.parseObject(biz);
        String uname = jsonParams.getString("userName");
        Integer from = jsonParams.getInteger("from");//from =0:来自注册 ，1：来自其他 
        if (!ValidateUtil.isValid(inneruserService.queryList(jsonParams))) {
            return result = R.error(1010, "非院内人员，不可使用");
        }
        //只有来自注册的才需要验证是否注册
        TUserEntity tuser = tUserService.queryByUserName(uname);
        if ((ValidateUtil.isValid(from) && from.intValue() == 0)) {
            if (ValidateUtil.isValid(tuser)) {
                result = R.error(1002, "该手机号已注册");
            }
        } else {
            if (!ValidateUtil.isValid(tuser)) {
                result = R.error(1003, "该手机号没有注册");
            }
        }

        if (ValidateUtil.isValid(uname)) {
            List<UserCaptchaEntity> tcaptcha = userCaptchaService.queryList(jsonParams);
            if (ValidateUtil.isValid(tcaptcha)) {
                userCaptchaService.deleteBatch(createIds(tcaptcha));
            }
            UserCaptchaEntity newcaptcha = new UserCaptchaEntity();
            newcaptcha.setCaptcha(captcha);
            newcaptcha.setUsername(uname);
            newcaptcha.setDeadline(DateUtil.add_minute(10));
            userCaptchaService.save(newcaptcha);
            
           /* redisUtil.set(RedisConst.USER_CAPTCHA_PRE + uname, captcha);
            redisUtil.expire(RedisConst.USER_CAPTCHA_PRE  + uname, 600);*/
            resultObj.put("captcha", captcha);
            result = R.ok().put("captcha", captcha);
        } else {
            result = R.error(-1, "缺少参数");
        }
        //短信工具类
        SmsUtils.getInstance().send(uname, captcha);
        return result;
    }

    private Integer[] createIds(List<UserCaptchaEntity> tcaptcha) {
        List<Integer> ids = new ArrayList<Integer>();
        if (ValidateUtil.isValid(tcaptcha)) {
            for (UserCaptchaEntity t : tcaptcha) {
                ids.add(t.getId());
            }
        }
        return ids.toArray(new Integer[]{});
    }


    /**
     * 注册
     *
     * @param req
     * @return
     */
    @RequestMapping("/register")
    @IgnoreToken
    @ResponseBody
    public R register(HttpServletRequest req) {
        String biz = req.getParameter("biz");
        log.debug("biz:" + biz);
        R result = new R();
        // 获取入参
        JSONObject jsonParams = JSONObject.parseObject(biz);
        boolean istest = jsonParams.getBooleanValue("istest");
        String uname = jsonParams.getString("userName");
        List<InneruserEntity> iu = null;
        if (!istest) {
            if (!ValidateUtil.isValid(iu = inneruserService.queryList(jsonParams))) {
                return result = R.error(1010, "非院内人员，不可注册");
            }
        }
        TUserEntity tuser = tUserService.queryByUserName(uname);
        String captcha = getCaptcha(jsonParams);
        if (ValidateUtil.isValid(captcha)) {
            if (captcha.equalsIgnoreCase(jsonParams.getString("captcha"))) {
                // 设置用户对象信息
                if (!ValidateUtil.isValid(tuser)) {
                    tuser = new TUserEntity();
                    tuser.setPassword(jsonParams.getString("password"));
                    tuser.setValid(1);
                    tuser.setCreatetime(new Date());
                    tuser.setUserName(uname);
                    tuser.setMobile(tuser.getUserName());
                    tuser.setDeadline(iu.get(0).getDeadline());
                    tUserService.saveUser(tuser);

                    result.put("userinfo", getbasicInfo(tuser));
                    String token = String.valueOf(tokenService.createToken(tuser.getId()).get("token"));
                    result.put("token", token);
                } else {
                    result = R.error(1005, "账号已注册");
                }
            } else {
                result = R.error(1006, "验证码错误");
            }
        } else {
            result = R.error(1004, "验证码已过期");
        }
        return result;
    }

    /**
     * 获取验证码
     *
     * @param jsonParams
     */
    private String getCaptcha(JSONObject jsonParams) {
        String uname = jsonParams.getString("userName");
        // 验证码验证
        List<UserCaptchaEntity> calist = userCaptchaService.queryList(jsonParams);
        if (ValidateUtil.isValid(calist) && calist.get(0).getDeadline().getTime() > new Date().getTime()) {
            return calist.get(0).getCaptcha();
        } else {
            return null;
        }
       /* String captcha = redisUtil.get(RedisConst.USER_CAPTCHA_PRE + uname);*/
//        return captcha;
    }


    @RequestMapping(value = "logout")
    @ResponseBody
    public R logout(HttpServletRequest request) {
        Integer uid = WebUtils.getUid(request);
        tokenService.delByUid(uid);
        return R.ok();
    }

    /**
     * 修改密码
     *
     * @param biz
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "updatePass")
    @ResponseBody
    @IgnoreToken
    public R updatePass(String biz) throws Exception {
        log.debug(biz);
        R result = new R();
        JSONObject params = FastJSONUtils.getJSONObject(biz);
        if (checkCaptcha(params)) {
            String uname = params.getString("userName");
            TUserEntity user = tUserService.queryByUserName(uname);
            if (ValidateUtil.isValid(user)) {
                user.setPassword(params.getString("password"));
                tUserService.update(user);
            } else {
                result = R.error(1011, "用户不存在");
            }
        } else {
            result = R.error(1008, "验证码错误");
        }

        return result;
    }

    /**
     * 检查
     *
     * @param params
     * @return
     */
    private Boolean checkCaptcha(JSONObject params) {
        String captcha = getCaptcha(params);
        return captcha.equalsIgnoreCase(params.getString("captcha")) ? true : false;
    }


    /**
     * 三方登录
     *
     * @param biz
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "thirdLogin")
    @ResponseBody
    @IgnoreToken
    public R thirdLogin(String biz) {
        R result = new R();
        log.debug("biz:" + biz);
        JSONObject params = FastJSONUtils.getJSONObject(biz);
        Integer type = params.getInteger("loginType");
        String thirdNum = params.getString("thirdNum");
        if (!ValidateUtil.isValid(type) || !ValidateUtil.isValid(thirdNum)) {
            return R.error(1006, "缺少参数");
        }
        TUserEntity user;
        user = tUserService.getUserByThird(type, thirdNum);
        if (!ValidateUtil.isValid(user)) {
            // 跳转绑定
            return R.error(1005, "未绑定账号");
        }
        result.put("userinfo", getbasicInfo(user));
        String token = String.valueOf(tokenService.createToken(user.getId()).get("token"));
        result.put("token", token);
        return result;
    }


    /**
     * 绑定手机号
     *
     * @param biz
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "bindMobile")
    @ResponseBody
    @IgnoreToken
    public R bindMobile(String biz) {
        R result = new R();
        log.debug("biz:" + biz);
        JSONObject params = FastJSONUtils.getJSONObject(biz);
        Integer type = params.getInteger("loginType");
        String thirdNum = params.getString("thirdNum");
        String mobile = params.getString("userName");
        Boolean register = params.getBoolean("isregister");

        TUserEntity user;
        user = tUserService.queryByUserName(mobile);
        TUserEntity thirdu = tUserService.getUserByThird(type, thirdNum);

        if (user == null) {
            String capacha = getCaptcha(params);
            if (capacha.equalsIgnoreCase(params.getString("captcha"))) {
                TUserEntity u = new TUserEntity();
                if (!ValidateUtil.isValid(inneruserService.queryList(params))) {
                    return result = R.error(1010, "非院内人员，不可注册");
                }
                if (!ValidateUtil.isValid(tUserService.queryByUserName(mobile))) {
                    u.setPassword(params.getString("password"));
                    u.setValid(1);
                    tUserService.setThirdNumByType(u, type, thirdNum);
                    u.setCreatetime(new Date());
                    u.setUserName(mobile);
                    u.setMobile(mobile);
                    tUserService.saveUser(u);
                    result.put("userinfo", getbasicInfo(u));
                } else {
                    return R.error(1007, "账号已注册");
                }
            } else {
                return R.error(1008, "验证码错误");
            }
        } else {
            if (ValidateUtil.isValid(user)) {
                if (ValidateUtil.isValid(thirdu)) {
                    // third not null
                    if (mobile.equalsIgnoreCase(thirdu.getUserName())) {
                        result.put("userinfo", getbasicInfo(user));
                    } else {
                        return R.error(1009, "该三方账号已绑定其他手机号");
                    }
                } else {
                    String t_thirdNum = tUserService.getThirdnumByType(user, type);
                    if (ValidateUtil.isValid(t_thirdNum)) {
                        // user bind not null
                        if (thirdNum.equalsIgnoreCase(t_thirdNum)) {
                            result.put("userinfo", getbasicInfo(user));
                        } else {
                            return R.error(1010, "该手机号已绑定其他账号");
                        }
                    } else {
                        // user bind null bind
                        tUserService.setThirdNumByType(user, type, thirdNum);
                        tUserService.update(user);
                        result.put("userinfo", getbasicInfo(user));
                    }
                }
            } else {
                return R.error(1011, "该手机号未注册");
            }

        }
        String token = String.valueOf(tokenService.createToken(user.getId()).get("token"));
        result.put("token", token);
        return result;
    }

    /**
     * 获取车辆类型列表
     *
     * @param biz
     * @return
     */
    @RequestMapping("/cartypelist")
    @ResponseBody
    @IgnoreToken
    public R cartypelist(String biz) {
        R result = new R();
        result.put("cartypelist", tCarCartypeService.queryList(new HashMap<String, Object>()));
        return result;
    }

    /**
     * 获取职位列表
     *
     * @param biz
     * @return
     */
    @RequestMapping("/positionlist")
    @ResponseBody
    @IgnoreToken
    public R positionlist(String biz) {
        R result = new R();
        result.put("positionlist", tUserPositionService.queryList(new HashMap<String, Object>()));
        return result;
    }


    /**
     * 申诉
     *
     * @param biz
     * @return
     */
    @RequestMapping("/appeal")
    @ResponseBody
    @IgnoreToken
    public R appeal(String biz) {
        log.debug(biz);
        TUserAppealEntity appeal = FastJSONUtils.getObject(biz, TUserAppealEntity.class);
        JSONObject params = new JSONObject();
        params.put("userName", appeal.getUserName());
        List<TUserAppealEntity> tappeal = tUserAppealService.queryList(params);
        if (ValidateUtil.isValid(tappeal) && 0 == tappeal.get(0).getStatus().intValue()) {
            return R.error(1012, "该账号已经提交申诉，等待审核，不可重复提交");
        }
        appeal.setStatus(0);
        tUserAppealService.save(appeal);
        return R.ok();
    }

    /**
     * 认证
     *
     * @param req
     * @return
     */
    @RequestMapping("/auth")
    @ResponseBody
    public R auth(HttpServletRequest req) {
        String biz = req.getParameter("biz");
        log.debug(biz);
        TUserAuthVO authVo = FastJSONUtils.getObject(biz, TUserAuthVO.class);
        TUserAuthEntity auth = authVo.getAuth();
        Integer uid = WebUtils.getUid(req);
        JSONObject params = new JSONObject();
        params.put("uid", uid);
        params.put("status", 3);
        List<TUserAuthEntity> nlist = tUserAuthService.queryList(params);
        if (ValidateUtil.isValid(nlist)) {
            for (TUserAuthEntity en : nlist) {
                tUserAuthService.delete(en.getId());
            }
        }
        params.put("status", 2);
        List<TUserAuthEntity> list = tUserAuthService.queryList(params);
        if (ValidateUtil.isValid(list)) {
            return R.error(1015, "该手机号已进行过实名认证");
        }
        params.put("status", 1);
        List<TUserAuthEntity> notauthlist = tUserAuthService.queryList(params);
        if (ValidateUtil.isValid(notauthlist)) {
            return R.error(1016, "该手机号正在审核中，不可重复提交");
        }
        TUserEntity user = tUserService.queryByUserName(auth.getMobile());
        if (!ValidateUtil.isValid(user)) {
            return R.error(1017, "该手机号尚未注册");
        } else {
            auth.setUid(user.getId());
            tUserAuthService.saveauth(authVo);
        }
        return R.ok();
    }



    /**
     * 获取来访人员填写地址
     *
     * @param req
     * @return
     * @throws ParseException
     */
    @RequestMapping("/getvisitaddr")
    public R getvisitaddr(HttpServletRequest req) throws ParseException {
        R result = new R();
        String biz = req.getParameter("biz");
        log.debug("biz:" + biz);
        Integer uid = WebUtils.getUid(req);
        JSONObject obj = FastJSONUtils.getJSONObject(biz);
        obj.put("uid", uid);
        Integer type;
        if (!ValidateUtil.isValid(type = obj.getInteger("type"))) {
            return R.error("请选择发送类型");
        }
        String url = createurl(req, obj);
        result.put("url", url);
        System.out.println("----url: " + url);
        if (1 == type) {
            if (!ValidateUtil.isValid(obj.getString("vmobile"))) {
                return R.error("请输入来访人手机号");
            }
            //短信
            String addr = "您需要通过以下链接来输入拜访信息，提交成功后,门卫审核即可通过：" + url;
            SmsUtils.getInstance().sendinfo(obj.getString("vmobile"), addr);
        } else {
            // weixin
        }
        return result;
    }

    private String createurl(HttpServletRequest req, JSONObject obj) {
        String basePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                + req.getContextPath() + "/";
        try {
            Properties properties = new Properties();
            InputStream inputStream = TUserController.class.getClassLoader().getResourceAsStream(
                    "db.properties");
            properties.load(inputStream);
            basePath = properties.getProperty("basePath") + "/";
            //basePath = "http://123.57.80.58:8030/zhihuihouqin-api/";
            VisiuserLogEntity visiuserLog = createlog(obj);
            visiuserLogService.save(visiuserLog);
            String baseurl = basePath + "appuser/user/getvisitinfo?";
            String url = "";
            url += "uid=" + visiuserLog.getUid()
                    + "&stamp=" + visiuserLog.getStamp();

            return baseurl + url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    private VisiuserLogEntity createlog(JSONObject obj) {
        VisiuserLogEntity log = new VisiuserLogEntity();
        log.setStatus(0);
        log.setStamp(String.valueOf(System.currentTimeMillis()));
        log.setUid(obj.getInteger("uid"));
        log.setVuid(-1);
        log.setPhonenum(obj.getString("phonenum"));
        log.setRealname(obj.getString("realname"));
        log.setRoomnum(obj.getString("roomnum"));
        log.setOrdercode(String.valueOf(new Date().getTime()));
        log.setChecked(0);
        log.setVisitchecked(0);
        return log;
    }


    /**
     * 校验验证码
     *
     * @param req
     * @return
     */
    @IgnoreToken
    @RequestMapping("/checkCaptcha")
    public R checkCaptcha(HttpServletRequest req) {
        String biz = req.getParameter("biz");
        log.debug("biz:" + biz);
        //查询列表数据
        JSONObject params = JSONObject.parseObject(biz);
        //添加uid到params
        Object uid = req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY);
        if (ValidateUtil.isValid(uid)) {
            params.put("uid", uid);
        } else {
            //return R.error(1,"未登录");
        }
        //传入参数
        String userName = params.getString("userName");
        if (ValidateUtil.isValid(params.getInteger("uid"))) {
            TUserEntity user = tUserService.queryObject(params.getInteger("uid"));
            String username = user.getUserName();
            if (!username.equals(userName)) {
                return R.error(1005, "非注册手机号");
            }
        }
        String captcha = params.getString("captcha");
        List<UserCaptchaEntity> list = userCaptchaService.queryList(params);
        if (ValidateUtil.isValid(list)) {
            UserCaptchaEntity captchaEntity = list.get(0);
            if (captchaEntity.getCaptcha().equalsIgnoreCase(captcha) && captchaEntity.getDeadline().getTime() >= new Date().getTime()) {
                return R.ok();
            } else {
                return R.error(1006, "验证码错误");
            }
        }
        return R.error(1007, "未发送验证码");
    }

    /**
     * 修改密码
     *
     * @param req
     * @return
     */
    @RequestMapping("/modifyPassword")
    public R modifyPassword(HttpServletRequest req) {
        String biz = req.getParameter("biz");
        //查询列表数据
        JSONObject params = JSONObject.parseObject(biz);
        //添加uid到params
        Object uid = req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY);
        if (ValidateUtil.isValid(uid)) {
            params.put("uid", uid);
        } else {
            //return R.error(1,"未登录");
        }
        //参数
        String password = params.getString("password");
        TUserEntity user = new TUserEntity();
        user.setId(params.getInteger("uid"));
        user.setPassword(password);
        tUserService.update(user);
        //清除当前用户信息
        return logout(req);
    }

    /**
     * 用户信息
     *
     * @param req
     * @return
     */
    @RequestMapping("/getUserinfo")
    public R getUserinfo(HttpServletRequest req) {
        String biz = req.getParameter("biz");
        //查询列表数据
        JSONObject params = JSONObject.parseObject(biz);
        //添加uid到params
        Object uid = req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY);
        if (ValidateUtil.isValid(uid)) {
            params.put("uid", uid);
        } else {
//            return R.error(1,"未登录");
        }
        TUserEntity user = tUserService.queryObject(params.getInteger("uid"));
        HashMap<String, Object> userMap = new HashMap<>();
        //List<PurchaseDTO> pubPurchaseList = purchaseAccountService.getPubPurchaseList(params.getInteger("uid"), null);
        userMap.put("hasBuyAuth", 1);
        userMap.put("photo", user.getPhoto() == null ? "" : user.getPhoto());
        userMap.put("nickname", user.getNickname() == null ? "" : user.getNickname());
        userMap.put("mobile", user.getMobile());
        userMap.put("roomnum", user.getRoomnum());
        userMap.put("tel", user.getTel());
        userMap.put("sex", user.getSex());
        userMap.put("position", user.getPosition());
        DepartEntity depart = departService.queryObject(user.getDepartid());
        userMap.put("depart", ValidateUtil.isValid(depart) ? depart.getName() : "");
       // OfficeEntity office = officeService.queryObject(user.getOfficeid());
        userMap.put("office",  "");
        //BuildingEntity build = buildingService.queryObject(Integer.valueOf(user.getBuildnum()));
        userMap.put("buildnum", user.getBuildnum());
        userMap.put("buildName", 1);
        userMap.put("authStatus", user.getAuthStatus() == null ? 0 : user.getAuthStatus());
        userMap.put("id", user.getId());
        userMap.put("realname", user.getRealname() == null ? "" : user.getRealname());
        userMap.put("nation", user.getNation());
        return R.ok().put("userinfo", userMap);
    }

    /**
     * 修改用户信息
     *
     * @param req
     * @return
     */
    @RequestMapping("/modifyUserinfo")
    public R modifyUserinfo(HttpServletRequest req) {
        String biz = req.getParameter("biz");
        //查询列表数据
        JSONObject params = JSONObject.parseObject(biz);
        TUserEntity user = JSONObject.parseObject(biz, TUserEntity.class);
        //添加uid到params
        Integer uid = WebUtils.getUid(req);
        if (ValidateUtil.isValid(uid)) {
            params.put("uid", uid);
        } else {
            return R.error(1, "未登录");
        }
        user.setId(uid);
        tUserService.update(user);
        return R.ok();
    }

    /**
     * 获取职位列表
     *
     * @param req
     * @return
     */
    @RequestMapping("/getPositionList")
    public R getPositionList(HttpServletRequest req) {
        String biz = req.getParameter("biz");
        //查询列表数据
        JSONObject params = JSONObject.parseObject(biz);
        //添加uid到params
        Object uid = req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY);
        if (ValidateUtil.isValid(uid)) {
            params.put("uid", uid);
        } else {
            //return R.error(1,"未登录");
        }
        List<Map<String, Object>> positionList = tUserService.queryPositionList();
        return R.ok().put("positionList", positionList);
    }

    /**
     * 获取职位列表
     *
     * @param req
     * @return
     */
    @RequestMapping("/getdepartList")
    public R getdepartList(HttpServletRequest req) {
        //查询列表数据
        List<DepartEntity> departList = departService.queryList(new HashMap<String, Object>());
        return R.ok().put("departList", departList);
    }

    /**
     * 获取职位列表
     *
     * @param req
     * @return
     */
    @RequestMapping("/getofficelist")
    public R getofficelist(HttpServletRequest req) {
        //查询列表数据
        String biz = req.getParameter("biz");
        JSONObject obj = FastJSONUtils.getJSONObject(biz);
        //List<OfficeEntity> officelist = officeService.queryList(obj);
        return R.ok().put("officelist", 1);
    }

    /**
     * 上传用户头像
     */
    @RequestMapping("/uploadPhoto")
    public R uploadPhoto(@RequestParam("file") MultipartFile file, HttpServletRequest req) throws Exception {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }
        //查询列表数据
        JSONObject params = new JSONObject();
        //添加uid到params
        params.put("uid", req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY));

        /*String fileName = file.getOriginalFilename();// 文件原名称
        String filePath = DateUtils.format(new Date())+"/"+System.currentTimeMillis()+fileName.substring(fileName.lastIndexOf("."), fileName.length());
        OSSUtils.uploadFile(filePath, file.getInputStream());
        String imgUrl = OSSConstantKey.OSS_BASE_URL+filePath;*/

        String pre = getPreByType(0);
        //上传文件
        JSONObject obj = WebUtils.uploadFile(file, null, pre, RedisConst.UPLOAD_IMG_FILTER);
        String imgUrl = obj.getString("data");
        
        TUserEntity user = new TUserEntity();
        user.setId(params.getInteger("uid"));
        user.setPhoto(imgUrl);
        tUserService.update(user);
        
        return R.ok().put("url", imgUrl);
    }

    private String getPreByType(int typeInt) {
        return RedisConst.getTypeimgmaps().get(typeInt) == null ? RedisConst.getTypeimgmaps().get(RedisConst.TYPE_OTHERS) :
                RedisConst.getTypeimgmaps().get(typeInt);
    }

    /**
     * 意见反馈
     *
     * @param req
     * @return
     */
    @RequestMapping("/saveAdvice")
    public R saveAdvice(HttpServletRequest req) {
        String biz = req.getParameter("biz");
        //查询列表数据
        JSONObject params = JSONObject.parseObject(biz);
        //添加uid到params
        Object uid = req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY);
        if (ValidateUtil.isValid(uid)) {
            params.put("uid", uid);
        } else {
            //return R.error(1,"未登录");
        }
        UserAdviceEntity userAdvice = JSONObject.parseObject(biz, UserAdviceEntity.class);
        userAdvice.setCreatetime(new Date());
        userAdvice.setUid(params.getInteger("uid"));
        userAdviceService.save(userAdvice);
        return R.ok();
    }

    /**
     * 获取用户认证信息
     *
     * @param req
     * @return
     */
    @RequestMapping("/getUserauth")
    public R getUserauth(HttpServletRequest req) {
        String biz = req.getParameter("biz");
        //查询列表数据
        JSONObject params = JSONObject.parseObject(biz);
        //添加uid到params
        Object uid = req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY);
        if (ValidateUtil.isValid(uid)) {
            params.put("uid", uid);
        } else {
            //return R.error(1,"未登录");
        }
        List<TUserAuthEntity> authList = tUserAuthService.queryList(params);
        if (ValidateUtil.isValid(authList)) {
            //
        }

        return R.ok();
    }

    /**
     * 楼栋列表
     */
    @RequestMapping("/buildings")
    public R list(HttpServletRequest req) {
        /*String biz = req.getParameter("biz");
    	//查询列表数据
    	JSONObject params = JSONObject.parseObject(biz);
        Query query = new Query(params);*/

        //List<BuildingEntity> buildingList = buildingService.queryList(new HashMap<String, Object>());
        return R.ok().put("data", 1);
    }

    private List<Integer> addTypeParams(JSONObject params) {
        int type = params.getInteger("type").intValue();
        List<Integer> statuslist = new ArrayList<Integer>();
        switch (type) {
            case 3:
                statuslist.add(3);
                statuslist.add(4);
                break;
            default:
                statuslist.add(type);
                break;
        }
        return statuslist;

    }
}
