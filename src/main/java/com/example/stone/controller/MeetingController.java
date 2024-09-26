package com.example.stone.controller;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://127.0.0.1:5000", "http://172.30.152.67:5000"})
public class MeetingController {

    // 텍스트 요약 함수 (예시로만 구현)
    public String summarizeText(String text) {
        // 여기에 Google API 요청 코드 작성 필요
        return "요약된 텍스트";
    }

    // 명사 추출 함수 (Java에서 NLP 라이브러리 사용 필요)
    public String extractNouns(String text) {
        return "명사1, 명사2, 명사3";  // 실제 명사 추출 구현 필요
    }

    // 감정 분석 함수 (Java에서 감정 분석 라이브러리 사용 필요)
    public Map<String, Object> analyzeSentiment(String text) {
        Map<String, Object> result = new HashMap<>();
        result.put("sentiment", "긍정적");
        result.put("sentiment_score", 0.85);
        return result;
    }

    // 요약 API
    @PostMapping("/summarize")
    public Map<String, Object> summarize(@RequestBody Map<String, String> request) {
        String rawText = request.get("text");
        String summaryText = summarizeText(rawText);
        String keywords = extractNouns(summaryText);

        Map<String, Object> sentimentData = analyzeSentiment(rawText);

        // 결과 반환
        Map<String, Object> response = new HashMap<>();
        response.put("summary", summaryText);
        response.put("keywords", keywords);
        response.put("sentiment", sentimentData.get("sentiment"));
        response.put("sentiment_score", sentimentData.get("sentiment_score"));
        response.put("image_path", "/images/" + getImageFilename((String)sentimentData.get("sentiment")));
        return response;
    }

    private String getImageFilename(String sentiment) {
        switch (sentiment) {
            case "긍정적":
                return "positive_img.png";
            case "부정적":
                return "negative_img.png";
            default:
                return "neutral_img.png";
        }
    }
}
