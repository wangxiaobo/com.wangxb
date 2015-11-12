package com.xst.utils;

import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQLDialect;

public class BlobMySQLDialect extends MySQLDialect  {

	public BlobMySQLDialect () {  
        super(); 
       // registerColumnType(Types.LONGVARBINARY, Hibernate.BYTE.getName());  
        registerHibernateType(Types.LONGVARBINARY, Hibernate.BLOB.getName());
        registerHibernateType(Types.LONGVARCHAR, Hibernate.TEXT.getName());
    }  
}
