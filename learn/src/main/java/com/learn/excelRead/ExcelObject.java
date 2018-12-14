package com.learn.excelRead;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author gonghe.hogan
 */
@Data
@Builder
public class ExcelObject {
    int num;
    int messageNum;
    List<String> oldInterfaces;
    List<String> newInterfaces;
    String changeDesc;
    String mark;
}
