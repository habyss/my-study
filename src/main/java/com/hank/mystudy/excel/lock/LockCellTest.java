package com.hank.mystudy.excel.lock;

import com.alibaba.excel.EasyExcel;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

public class LockCellTest {
    private static List<DemoData> data() {
        LocalDate now = LocalDate.now();
        return IntStream.rangeClosed(1, 50000)
                .mapToObj(i -> {
                    DemoData demoData = new DemoData();
                    demoData.setTitle("字符串" + i);
                    demoData.setDate(now);
                    demoData.setDoubleData(0.56);
                    demoData.setIgnore("ignore" + i);
                    return demoData;
                })
                .toList();
    }

    public static void main(String[] args) {
        String fileName = "C:\\dev\\simpleWrite" + System.currentTimeMillis() + ".xlsx";
        // 分页查询数据
        EasyExcel.write(fileName, DemoData.class)
                .registerWriteHandler(new LockCellWriteHandler())
                .sheet("模板")
                .doWrite(data());
    }
}
