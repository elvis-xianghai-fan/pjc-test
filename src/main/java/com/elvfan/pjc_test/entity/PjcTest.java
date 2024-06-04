package com.elvfan.pjc_test.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PjcTest {

    @Id
    private String input;

    private String encrypted;

    public String getEncrypted() {
        return encrypted;
    }

    public void setEncrypted(String testId) {
        this.encrypted = testId;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}