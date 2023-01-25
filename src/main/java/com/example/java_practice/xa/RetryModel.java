package com.example.java_practice.xa;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RetryModel {
    private Long id;
    private String name;
    private String status;
    private String remark;
    private boolean async;
    private Integer maxRetry;
    private long retryTime;
    private String notifyConfig;
    private long nextExecuteTime;
    private int hasRetry;
    private String className;
    private String methodName;
    private String methodParam;
}
