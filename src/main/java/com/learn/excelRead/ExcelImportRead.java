package com.learn.excelRead;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author gonghe.hogan
 */
@Slf4j
public class ExcelImportRead {

    public static void main(String[] args) throws IOException, InvalidFormatException {
        String path = "E:\\test\\excel导入测试.xlsx";
        File file = new File(path);
        Workbook workbook = WorkbookFactory.create(file);
        final Sheet sheet = workbook.getSheetAt(0);
        final int lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i < lastRowNum + 1; i++){
            if (i != 1 && i != 2){
                final Row row = sheet.getRow(i);
                final int num = (int)row.getCell(0).getNumericCellValue();
                final int messageNum = (int)row.getCell(1).getNumericCellValue();
                final String oldInterfaces = row.getCell(2).getStringCellValue();
                final String newInterfaces = row.getCell(3).getStringCellValue();
                final String changeDesc = row.getCell(4).getStringCellValue();
                final String mark = row.getCell(5).getStringCellValue();

                ExcelObject excelObject = ExcelObject.builder()
                        .num(Integer.valueOf(num))
                        .messageNum(Integer.valueOf(messageNum))
                        .oldInterfaces(new ArrayList<>())
                        .newInterfaces(new ArrayList<>())
                        .changeDesc(changeDesc)
                        .mark(mark)
                        .build();
                log.info(excelObject.toString());
            }

        }
        log.info(sheet.toString());
    }
}
