package com.api_sing.sing09.service;

import com.api_sing.sing09.domain.Member;
import com.api_sing.sing09.repository.MemberRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

@Transactional
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository repository;

    public List findAll() {
        return repository.findAll();
    }

    @SuppressWarnings("unchecked")
    public void callApi() {

        HttpURLConnection conn = null;
        JsonObject responseJson = null;

        try {
            //URL 설정
            URL url = new URL("https://codetest.3o3.co.kr/v1/scrap");

            conn = (HttpURLConnection) url.openConnection();

            // type의 경우 POST, GET, PUT, DELETE 가능
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json;charset=UTF-8");

            conn.setDoOutput(true);
            conn.setDoInput(true);

            String aa = "{"
                    + "\"name\" : \"홍길동\","
                    + "\"regNo\" : \"860824-1655068\""
                    + "}";
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
//            // JSON 형식의 데이터 셋팅
//            JSONObject jsonObject = new JSONObject();
//
//            jsonObject.put("name", "홍길동");
//            jsonObject.put("regNo", "860824-1655068");
//
//            // 데이터를 STRING으로 변경
//            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//            String jsonOutput = gson.toJson(jsonObject);
//
//            bw.write(jsonOutput);
//            bw.flush();
//            bw.close();

            try(OutputStream os = conn.getOutputStream()){
                byte[] input = aa.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // 보내고 결과값 받기
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                System.out.println("responseJson :: " + "한글");
                System.out.println("responseJson :: " + sb);
                // 응답 데이터
                System.out.println("responseJson :: " + responseJson);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
