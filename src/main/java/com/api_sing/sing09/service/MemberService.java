package com.api_sing.sing09.service;

import net.nurigo.java_sdk.exceptions.CoolsmsException;

import java.util.List;
import java.util.Map;


public interface MemberService {

    public String getLogin(Map<String, Object> model) throws Exception;
    public List<Map> getMemberList(Map<String, Object> model) throws Exception;
    public Map<String, Object> getSign(Map<String, Object> model)  throws Exception;

    public Map<String, Object> updatePassword(Map<String, Object> model)  throws Exception;

    public String PhoneNumberCheck(String to) throws CoolsmsException;

    public String signNumberCheck(String phoneNo) throws Exception;
}
