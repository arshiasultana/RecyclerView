package com.example.recyclerview;

import android.provider.BaseColumns;

public class RegistrationContract {

    private RegistrationContract(){}

    public static final class RegistrationEntry implements BaseColumns {
        public static final String TABLE_NAME = "courseRegistration";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_COURSE = "course";
        public static final String COLUMN_YEAR = "year";
        public static final String COLUMN_TIMESTAMP = "timestamp";

    }
}
