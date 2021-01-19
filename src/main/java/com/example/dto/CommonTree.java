package com.example.dto;

import java.util.List;

/**
 * TODO 描述
 *
 * @author chaonan.xu
 * @version: 1.0
 * @date 2021/01/13 14:52
 */
public class  CommonTree<T> {
   T data;
   Integer parent;
   Integer i;
   List<CommonTree> childNodes;
}
