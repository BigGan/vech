package com.hdu.model;

import java.util.List;
import java.util.Map;

public interface Message <T> extends ObjectModel<T> {
    Message setCode(int var1);

    Message setId(String var1);

    Message setSuccess(boolean var1);

    Message setMessage(String var1);

    Message setObj(T var1);

    Message setErrors(List<Map> var1);

    Message setErrors(String var1, String var2);
}
