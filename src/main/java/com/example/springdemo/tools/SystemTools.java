package com.example.springdemo.tools;

import com.example.springdemo.entity.HsUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Slf4j
@Component
public class SystemTools {


    /**
     *  1.使用者
     *  2.要判斷的字串
     *  3.判斷的內容
     *
     *  判斷字串是否為空字串
     *
     * @author Lime
     * @param hsUser, s, remark
     * @return java.lang.Boolean
     */
    public Boolean isNullStringTools(HsUser hsUser,String s,String remark){
        System.out.println(hsUser.getUsername()+remark+s);
        if (s.trim() == "" && s.equals("") && s == null){
            log.error("[ERROR] USER : {} 命中isNull : {}",hsUser.getUsername(),remark);
            return true;
        };
        return  false;
    }

    /**
     *  使用UUID 產生TOKEN
     *
     * @author Lime
     * @param []
     * @return java.lang.String
     */

    public String uuidToken(){
        UUID randomUUID =UUID.randomUUID();
        String id =  randomUUID.toString();
        String token = id.replaceAll("-","");

        return token;
    }

}
