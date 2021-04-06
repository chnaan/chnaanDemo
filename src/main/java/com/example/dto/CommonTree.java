package com.example.dto;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO 描述
 *
 * @author chaonan.xu
 * @version: 1.0
 * @date 2021/01/13 14:52
 */
@Data
public class  CommonTree<T> {
   T data;
   Integer parentNodeCode;
   Integer nodeCode;
   Boolean isRoot;
   List<CommonTree> childNodes;

   public List<CommonTree<T>> getTree(List<CommonTree<T>> treeList){
      List<CommonTree<T>> rootNodeList = treeList.stream().filter(CommonTree<T>::getIsRoot).collect(Collectors.toList());

      for (CommonTree<T> i : treeList){
         for (CommonTree<T> j : treeList){
            if (i==j){
               continue;
            }
            if (i.getNodeCode().equals(j.getParentNodeCode()) &&!i.getChildNodes().contains(j)){
               i.getChildNodes().add(j);
            }
         }
      }
      return null;
   }
}
