package com.example.service;

import com.example.annotion.MatGroup;

/**
 * TODO 描述
 *
 * @author chaonan.xu
 * @version: 1.0
 * @date 2020/12/28 15:34
 */
public class AnonyImpl implements AnonymousClassInterface{
    
    @Override
    public String study() {
        return "";
    }
    
    @MatGroup(matGroup = "100")
    public String matGroup;
}
