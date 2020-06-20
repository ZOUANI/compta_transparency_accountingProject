package com.zsmart.accountingProject.service.util;

import org.hibernate.Hibernate;

public class Util {
    public static boolean instanceOf(Object object, Class<?> superclass) {
        return superclass.isAssignableFrom(Hibernate.getClass(object));
    }
}
