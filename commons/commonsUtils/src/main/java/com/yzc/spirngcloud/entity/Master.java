package com.yzc.spirngcloud.entity;



import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;




@ControllerAdvice
public class Master {

    @ModelAttribute(value = "message")
    public String myData() {
        return "sbsbsbsb";
    }

}
