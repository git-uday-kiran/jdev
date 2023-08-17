package com.jdev;

import com.jdev.beans.BeanTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppTest {

    public BeanTest beanTest;

    @Autowired
    public void setBeanTest(BeanTest beanTest) {
        this.beanTest = beanTest;
    }
}
