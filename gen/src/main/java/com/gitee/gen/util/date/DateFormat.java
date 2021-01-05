package com.gitee.gen.util.date;

/**
 * @Description:
 * @Author: xwc1125
 * @Date: 2019-02-20 18:00
 * @Copyright Copyright@2019
 */
public enum DateFormat {
    /**
     * yyyy
     */
    YYYY {
        @Override
        public String getValue() {
            return "yyyy";
        }
    },
    /**
     * yyyy-MM
     */
    YYYY_MM {
        @Override
        public String getValue() {
            return "yyyy-MM";
        }
    },
    /**
     * yyyy/MM
     */
    YYYY_7MM {
        @Override
        public String getValue() {
            return "yyyy/MM";
        }
    },
    /**
     * yyyy.MM
     */
    YYYY_0MM {
        @Override
        public String getValue() {
            return "yyyy.MM";
        }
    },
    /**
     * yyyyMM
     */
    YYYYMM {
        @Override
        public String getValue() {
            return "yyyyMM";
        }
    },
    /**
     * yyyy-MM-dd
     */
    YYYY_MM_dd {
        @Override
        public String getValue() {
            return "yyyy-MM-dd";
        }
    },
    /**
     * yyyy/MM/dd
     */
    YYYY_7MM_7dd {
        @Override
        public String getValue() {
            return "yyyy/MM/dd";
        }
    },
    /**
     * yyyy.MM.dd
     */
    YYYY_0MM_0dd {
        @Override
        public String getValue() {
            return "yyyy.MM.dd";
        }
    },
    /**
     * yyyyMMdd
     */
    YYYYMMdd {
        @Override
        public String getValue() {
            return "yyyyMMdd";
        }
    },
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    YYYY_MM_dd8HH0mm {
        @Override
        public String getValue() {
            return "yyyy-MM-dd HH:mm";
        }
    },
    /**
     * yyyy/MM/dd HH:mm:ss
     */
    YYYY_7MM_7dd8HH0mm {
        @Override
        public String getValue() {
            return "yyyy/MM/dd HH:mm";
        }
    },
    /**
     * yyyy.MM.dd HH:mm:ss
     */
    YYYY_0MM_0dd8HH0mm {
        @Override
        public String getValue() {
            return "yyyy.MM.dd HH:mm";
        }
    },
    /**
     * yyyyMMddHHmmss
     */
    YYYYMMddHHmm {
        @Override
        public String getValue() {
            return "yyyyMMddHHmm";
        }
    },
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    YYYY_MM_dd8HH0mm0ss {
        @Override
        public String getValue() {
            return "yyyy-MM-dd HH:mm:ss";
        }
    },
    /**
     * yyyy/MM/dd HH:mm:ss
     */
    YYYY_7MM_7dd8HH0mm0ss {
        @Override
        public String getValue() {
            return "yyyy/MM/dd HH:mm:ss";
        }
    },
    /**
     * yyyy.MM.dd HH:mm:ss
     */
    YYYY_0MM_0dd8HH0mm0ss {
        @Override
        public String getValue() {
            return "yyyy.MM.dd HH:mm:ss";
        }
    },
    /**
     * yyyyMMddHHmmss
     */
    YYYYMMddHHmmss {
        @Override
        public String getValue() {
            return "yyyyMMddHHmmss";
        }
    },
    /**
     * HH:mm:ss
     */
    HH0mm0ss {
        @Override
        public String getValue() {
            return "HH:mm:ss";
        }
    },
    ;

    public abstract String getValue();
}
