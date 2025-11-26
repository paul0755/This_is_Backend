package com.example.study.global.apiPayload.Exception.handler;

import com.example.study.global.apiPayload.Exception.GeneralException;
import com.example.study.global.apiPayload.code.BaseErrorCode;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode){

        super(errorCode);
        // 해당 코드로 GeneralException 클래스에서
        // @AllArgsConstructor를 통해 만들어진 생성자 호출

    }
}
