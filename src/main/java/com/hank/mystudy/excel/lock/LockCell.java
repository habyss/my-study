package com.hank.mystudy.excel.lock;

import java.lang.annotation.*;

/**
 * 用于标记锁定哪些列需要锁定
 */
@Target(value = {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LockCell {

}