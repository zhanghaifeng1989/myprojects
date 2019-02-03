package com.example.javatest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2019/2/2.
 */

//public class HashTest {
//    private int i;
//
//    public int getI() {
//        return i;
//    }
//
//    public void setI(int i) {
//        this.i = i;
//    }
//
//
//        @Override
//    public int hashCode() {
//        return i % 10;
//    }
//    public final static void main(String[] args) {
//        HashTest a = new HashTest();
//        HashTest b = new HashTest();
//        a.setI(1);
//        b.setI(1);
//        Set<HashTest> set = new HashSet<HashTest>();
//        set.add(a);
//        set.add(b);
//        System.out.println(a.hashCode() == b.hashCode());
//        System.out.println(a.equals(b));
//        System.out.println(set);
//    }
//
//}
public class HashTest {
    private int i;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public boolean equals(Object o) {
        if(((HashTest)o).getI() == i){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        return i%10;
    }

    public final static void main(String[] args) {
        HashTest a = new HashTest();
        HashTest b = new HashTest();
        HashMap<HashTest,Integer> map = new HashMap<>();
        map.put(a,1);
        System.out.println(map.get(b));
        System.out.println(a.hashCode() == b.hashCode());
        System.out.println(a.equals(b));
        System.out.println(map);
    }

}