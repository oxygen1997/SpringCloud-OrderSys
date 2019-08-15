package com.czy.order.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

     private static ObjectMapper objectMapper = new ObjectMapper();

     //对象转换为json字符串
     public  static String toJson(Object object){
          try {
              return objectMapper.writeValueAsString(object);
          } catch (JsonProcessingException e) {
               e.printStackTrace();
          }
          return null;
     }

     //json字符串转换为对象
     public static Object fromJson(String string,Class classType){
          try {
               return objectMapper.readValue(string,classType);
          }catch (JsonParseException e) {
               e.printStackTrace();
          } catch (JsonMappingException e) {
               e.printStackTrace();
          } catch (IOException e) {
               e.printStackTrace();
          }
          return null;
     }
}
