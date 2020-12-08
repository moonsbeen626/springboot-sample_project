package com.example.myhome.validator;

import com.example.myhome.model.Board;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component //di하기 위해 필요
public class BoardValidator implements Validator { //데이터 유효한지 검증

    @Override
    public boolean supports(Class<?> clazz) {
        return Board.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors e) {
        Board b = (Board) obj;
        if(StringUtils.isEmpty(b.getContent())){
            e.rejectValue("content", "key", "내용을 입력하세요");
        }
    }
}
