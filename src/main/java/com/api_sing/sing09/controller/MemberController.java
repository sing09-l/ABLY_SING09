package com.api_sing.sing09.controller;

import com.api_sing.sing09.service.MemberService;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MemberController {

    private Logger logger = LoggerFactory.getLogger(MemberController.class);
    @Autowired
    private MemberService service;

    @RequestMapping("/")
    public String main() {
        return "redirect:login";
    }

    /**
     * @ login 페이지 view
     * */
    @RequestMapping("/login")
    public String login(){ return "login_view"; }

    /**
     * @ login 기능 수행
     * */
    @ResponseBody
    @RequestMapping(value="/login/join",  method = {RequestMethod.GET, RequestMethod.POST})
    public String login2(@RequestParam Map<String, Object> map, Model model) throws Exception {
        String rst = "";
        String m = "";
        m = service.getLogin(map);
        if("Y".equals(m)) {
            rst = "Y";
        }else {
            rst = "N";
        }
        return rst;
    }

    /**
     * @ 로그인정보 view
     * @@ 조회조건 별
     * */
    @RequestMapping("/member")
    public ModelAndView member(@RequestParam Map<String, Object> map) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("member_view"); //뷰의 이름
        mv.addObject("data", service.getMemberList(map));
        return mv;
    }

    /**
     * @ 회원가입 view
     * */
    @RequestMapping("/sign")
    public String sign(){

        String rst = "sign_view";
        return rst;
    }

    /**
     * @ 비밀번호 찾기 view
     * */
    @RequestMapping("/find")
    public String find(){

        String rst = "find_view";
        return rst;
    }

    /**
     * @ 회원가입 기능 수행
     * */
    @RequestMapping(value ="/sign_add",  method = {RequestMethod.GET, RequestMethod.POST})
    public String sign_add(@RequestParam Map<String, Object> map) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        m = service.getSign(map);

        return "login_view";
    }

    /**
     * @ 비밀번호 변경 수행
     * */
    @RequestMapping(value ="/password_change",  method = {RequestMethod.GET, RequestMethod.POST})
    public String password_change(@RequestParam Map<String, Object> map) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        m = service.updatePassword(map);

        return "login_view";
    }

    /**
     * @ sms 문자 발송 기능
     * */
    @RequestMapping("/check/sendSMS")
    public @ResponseBody String sendSMS(@RequestParam(value="to") String to) throws CoolsmsException {
        return service.PhoneNumberCheck(to);
    }

    /**
     * @ 전화번호 체크
     * */
    @RequestMapping("/check/signNo")
    public @ResponseBody String signNumberCheck(@RequestParam(value="phoneNo") String phoneNo) throws Exception {
        return service.signNumberCheck(phoneNo);
    }
}
