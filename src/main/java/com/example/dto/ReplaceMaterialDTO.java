package com.example.dto;


import com.example.annotion.MatGroup;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * TODO 描述
 *
 * @author chaonan.xu
 * @version: 1.0
 * @date 2020/11/17 15:17
 */
@Setter
@Getter
public class ReplaceMaterialDTO {
    @MatGroup(matGroup = "120")
    String materialCode;

    String materialDesc;
    String productFactory;
    String replaceGroup;
    Long userPercent;
    String test;


}
