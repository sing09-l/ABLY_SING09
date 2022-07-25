package com.api_sing.sing09.service;

import com.api_sing.sing09.dao.MemberDao;
import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberDao MemberDao;

    @Override
    public List<Map> getMemberList(Map<String, Object> model) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId" , (String)model.get("userId"));
        map.put("loginGb" , (String)model.get("loginGb"));

        System.out.println("userId" + (String)model.get("userId"));
        System.out.println("loginGb" + (String)model.get("loginGb"));
        return MemberDao.getMemberList(map);
    }

    @Override
    public String getLogin(Map<String, Object> model) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        String loginReturn = "";
        map.put("userId" , (String)model.get("userId"));
        map.put("password"  , (String)model.get("password"));
        map.put("loginGb"  , (String)model.get("loginGb"));

        int result = MemberDao.getLogin(map);

        if(result == 1) {
            loginReturn = "Y";
        }else{
            loginReturn = "N";
        }

        return loginReturn;
    }

    @Override
    public Map<String, Object> getSign(Map<String, Object> model) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("name" , (String)model.get("name"));
        map.put("password"  , (String)model.get("password"));
        map.put("phoneNo" , (String)model.get("phoneNo"));
        map.put("email" , (String)model.get("email"));
        map.put("userId" , (String)model.get("userId"));

        MemberDao.getSign(map);

        return map;
    }

    @Override
    public Map<String, Object> updatePassword(Map<String, Object> model) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("password"  , (String)model.get("password"));
        map.put("phoneNo" , (String)model.get("phoneNo"));

        MemberDao.updatePassword(map);

        return map;
    }

    public String PhoneNumberCheck(String to) throws CoolsmsException {

        String api_key = "NCSTT14TQMGIWT4C";
        String api_secret = "CSH4PD0WJAYI54UITZO9YMOVPNHBDD3O";
        Message coolsms = new Message(api_key, api_secret);

        Random rand  = new Random();
        String numStr = "";
        for(int i=0; i<4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr+=ran;
        }

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", to);    // 수신전화번호 (ajax로 view 화면에서 받아온 값으로 넘김)
        params.put("from", "01086970929");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "sms");
        params.put("text", "인증번호는 [" + numStr + "] 입니다.");

        coolsms.send(params); // 메시지 전송

        return numStr;

    }

    public String signNumberCheck(String phoneNo) throws Exception {

        String check = "";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("phoneNo", phoneNo);
        int result = MemberDao.getSignCheck(map);

        if(result > 0) {
            check = "Y";
        }else{
            check = "N";
        }
        return check;
    }
}
