package com.msg.suites.pom.util;

public enum rpt {
	
	separator; 

    
    @Override
    public String toString() {
        switch (this) {
            case separator: return "|";
            default: return "unknown";
        }
    }

}
