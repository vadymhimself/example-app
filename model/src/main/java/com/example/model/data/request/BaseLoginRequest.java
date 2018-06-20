package com.example.model.data.request;

import java.io.Serializable;

public class BaseLoginRequest implements Serializable {
    public String inn;

    public BaseLoginRequest(String inn) {
        this.inn = inn;
    }
}
