package com.wuhao.tips.stream;/**
 *
 */

import java.util.*;
import java.util.stream.Collectors;

/**
 *@ClassName DataTransform
 *@Description TODO
 *@Author wuhao51
 *@Date 2024/4/29 15:26
 *@Version 1.0
 **/
public class DataTransform {
    public static void main(String[] args) {
        // 生成hashset
//        tbApplyEntityList.stream().map(TbApplyEntity::getApplyId).collect(Collectors.toCollection(HashSet::new));

        // 取出工号重复的人员列表
//        list.stream()
//                .collect(Collectors.groupingBy(PersonVo::getJobNo))
//                .entrySet().stream()
//                .filter(entry -> entry.getValue().size() > 1)
//                .map(entry -> entry.getValue().get(0))
//                .collect(Collectors.toList());

        // optional过滤转成hashset
//        HashSet<String> outerPersonSet = Optional.ofNullable(cemsPersonGroupQueryResDto)
//                .map(CemsPersonGroupQueryResDto::getRows)
//                .filter(rowsDTOS -> !rowsDTOS.isEmpty())
//                .map(rowsDTOS -> rowsDTOS.get(0))
//                .map(CemsPersonGroupQueryResDto.RowsDTO::getPersonDetails)
//                .orElse(Collections.emptyList())
//                .stream()
//                .map(s -> s.split(",", 2)[1].trim())
//                .collect(Collectors.toCollection(HashSet::new));

        // 转成map
//        Map<Integer, List<String>> adminLevelUserNameListMap = tbAdminDaoService.queryListByCompanyId(tbCompanyAdminRelEntity.getId())
//                .stream()
//                .collect(Collectors.groupingBy(
//                        TbAdminEntity::getAdminLevel,
//                        Collectors.mapping(
//                                TbAdminEntity::getAdminUserName,
//                                Collectors.toList()
//                        )
//                ));

        // 转成map并且如果有重复value则覆盖之前的
//        toJudgeList.stream().collect(Collectors.toMap(TbFreezeEntity::getJobNo, o -> o));

    }




}
