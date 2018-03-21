package com.msgqa.eventengine.testcases;

public enum ReportTest {
    PASS, 
    FAIL, 
    FATAL, 
    ERROR, 
    WARNING, 
    INFO, 
    SKIP,
    UNKNOWN;
    
    @Override
    public String toString() {
        switch (this) {
            case PASS: return "pass";
            case FAIL: return "fail";
            case FATAL: return "fatal";
            case ERROR: return "error";
            case WARNING: return "warning";
            case INFO: return "info";
            case SKIP: return "skip";
            default: return "unknown";
        }
    }
}
