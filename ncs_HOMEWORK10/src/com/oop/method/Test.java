package com.oop.method;

public class Test {
	int a = 100;
    static int s = 99;
    
    public static void main(String[] args) {
        Test t = new Test();
        t.test1();
        //100
        //99
        t.test2();
        //100
        
        Test t3 = new Test();
        t.test3(t3);
        //10
        System.out.println(t3.a);
        //10
        
        
        t.test4(t); //쓸일이 많이 없다고 한다.
        //1000
        
        /* result
         * 100
         * 99
         * 100
         * 10
         * 10
         * 1000
         * */
    }
    public void test1() {
        int a = 1000;
        int s = 999;
        System.out.println(this.a); //100
        System.out.println(Test.s); //99
//        System.out.println(a); //1000
//        System.out.println(s); //999
    }
    public void test2() {
        Test t = new Test();
        t.a = 1000;
        System.out.println(a); //100
//        System.out.println(t.a); //1000
    }
    public void test3(Test t) {
        t.a = 10;
        this.a = 200;
        System.out.println(t.a); //10
//        System.out.println(a); //200
//        System.out.println(this.a); //200
    }
    public void test4(Test t) {
        t.a = 1000;
        System.out.println(this.a); //1000
    }
}
