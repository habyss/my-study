package com.hank.mystudy.excel.lock;

import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.handler.context.CellWriteHandlerContext;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFSheet;

import java.lang.reflect.Field;

public class LockCellWriteHandler implements CellWriteHandler, SheetWriteHandler {


    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        Sheet sheet = writeSheetHolder.getSheet();
        //锁定工作簿，设置保护密码
        sheet.protectSheet("1qaz!QAZ");
        // 锁定单元格不可选中(防止别人直接复制内容到其他excel修改)
        ((SXSSFSheet) writeSheetHolder.getSheet()).lockSelectLockedCells(true);
    }


    @Override
    public void afterCellDispose(CellWriteHandlerContext context) {
        WriteCellStyle writeCellStyle = context.getFirstCellData().getOrCreateStyle();
        String fieldName = context.getHeadData().getFieldName();
        //获取到前面定义的DetailReportViewDto的Class对象
        Class<?> clazz = context.getWriteSheetHolder().getClazz();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            //遍历判断字段名是否和表头的字段名相同
            if (field.getName().equals(fieldName)) {
                //相同则判断改字段是否被@UnLockCell注解修饰
                writeCellStyle.setLocked(field.isAnnotationPresent(LockCell.class));
            }
        }
    }
}