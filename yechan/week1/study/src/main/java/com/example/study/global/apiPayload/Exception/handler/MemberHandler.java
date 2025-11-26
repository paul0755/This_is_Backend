package com.example.study.global.apiPayload.Exception.handler;

import com.example.study.global.apiPayload.Exception.GeneralException;
import com.example.study.global.apiPayload.code.BaseErrorCode;

public class MemberHandler extends GeneralException {

    public MemberHandler(BaseErrorCode errorCode){

        super(errorCode);
    }
}
