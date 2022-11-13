package com.baen;

import java.util.Objects;

public class Code {
    private String code;
   private String name;

    public Code() {
    }

    public Code(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Code code1 = (Code) o;
        return Objects.equals(code, code1.code) && Objects.equals(name, code1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
