package com.czy.product.util;

import com.czy.product.vo.ResultVO;

public class ResultVOUtil {

    public static ResultVO success(Object obj){
         ResultVO<Object> result = new ResultVO<>();
         result.setCode(0);
         result.setMsg("成功");
         result.setData(obj);

         return result;
    }
}
