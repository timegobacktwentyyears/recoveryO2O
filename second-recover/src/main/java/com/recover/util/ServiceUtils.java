package com.recover.util;

import com.recover.exception.MineException;

public class ServiceUtils {
    public static void checkNull(Object object, String name) {
        if (object == null) {
            throw new MineException(name + "不能为空");
        }
    }
}
