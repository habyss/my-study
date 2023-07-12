package com.hank.mystudy.excel.lock;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class DemoData implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ExcelProperty("字符串标题")
    @LockCell
    private String title;

    @ExcelProperty("日期标题")
    private LocalDate date;

    @ExcelProperty("数字标题")
    private Double doubleData;

    @ExcelIgnore
    private String ignore;
}
