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

    public static void callApi(JsonObject params, String type) {

        HttpURLConnection conn = null;
        JsonObject responseJson = null;

        try {
            //URL 설정
            URL url = new URL("https://codetest.3o3.co.kr/v1/scrap");

            conn = (HttpURLConnection) url.openConnection();

            // type의 경우 POST, GET, PUT, DELETE 가능
            conn.setRequestMethod(type);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Transfer-Encoding", "chunked");
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setDoOutput(true);


            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            // JSON 형식의 데이터 셋팅
            JsonObject commands = new JsonObject();
            JsonArray jsonArray = new JsonArray();
            params.addProperty("name", "홍길동");
            params.addProperty("regNo", 860824-1655068);

            //commands.add("userInfo", params);
            // JSON 형식의 데이터 셋팅 끝

            // 데이터를 STRING으로 변경
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonOutput = gson.toJson(params);

            bw.write(params.toString());
            bw.flush();
            bw.close();

            // 보내고 결과값 받기
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

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
