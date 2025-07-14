package com.example.demo.utils;

import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;

    @Service("googleSpeechService")
    public class SpeechVoice  {

//        private final SpeechClient speechClient;
//
//        public SpeechVoice() throws IOException {
//            this.speechClient = SpeechClient.create();
//        }
//
//        @Override
//        public String recognize(String filePath) {
//            try {
//                RecognitionConfig config = RecognitionConfig.newBuilder()
//                        .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
//                        .setSampleRateHertz(16000)
//                        .setLanguageCode("zh-CN")
//                        .build();
//
//                RecognitionAudio audio = RecognitionAudio.newBuilder()
//                        .setContent(ByteString.readFrom(new FileInputStream(filePath)))
//                        .build();
//
//                RecognizeResponse response = speechClient.recognize(config, audio);
//                return response.getResultsList().stream()
//                        .map(result -> result.getAlternatives(0).getTranscript())
//                        .findFirst()
//                        .orElse("未识别到内容");
//
//            } catch (IOException e) {
//                return "Google语音识别失败：" + e.getMessage();
//            }
//        }

//        public static void main(String[] args) throws  IOException{
//            SpeechVoice speechService = new SpeechVoice();
//            String result = speechService.recognize("C:\\Users\\86182\\Desktop\\uml\\67tool\\777.wav");
//            System.out.println(result);
//        }

}
