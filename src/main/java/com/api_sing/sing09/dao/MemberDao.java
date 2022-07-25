package com.api_sing.sing09.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberDao {

    List<Map> getMemberList(Map<String, Object> model);

    public int getLogin(Map<String, Object> model);

    public int getSignCheck(Map<String, Object> model);
    void getSign(Map<String, Object> model);

    void updatePassword(Map<String, Object> model);
}
