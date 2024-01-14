package com.engx.engxserver.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AIController {
    @Value("${application.azure.endpoint}")
    private String endPoint;

    @Value("${application.azure.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    @PostMapping
    @ResponseBody
    public JSONObject getGPT(@RequestBody JSONObject requestBody) {
        String ai_model = requestBody.get("model").toString();
        Map<String, ArrayList> mapping = new HashMap<>();
        mapping.put("messages", (ArrayList) requestBody.get("messages"));
        JSONObject body = new JSONObject(mapping);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", apiKey);
        HttpEntity<JSONObject> entity = new HttpEntity<>(requestBody, headers);

        return restTemplate
                .exchange(
                        endPoint + "openai/deployments/" + ai_model + "/chat/completions?api-version=2023-05-15",
                        HttpMethod.POST,
                        entity,
                        JSONObject.class)
                .getBody();
    }
}
