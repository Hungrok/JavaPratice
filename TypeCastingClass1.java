package com.my.pratice ;


public class TypeCastingClass1 extends TypeCastingClass2 {

    public void method1(){} ;
}

class TypeCastingClass2 extends TypeCastingAbsClass1 {
    public void method2(){} ;
    @Override
    public void method4() {} ;
}

abstract class TypeCastingAbsClass1 implements TypeCastingIf1{
    public void method3(){} ;
}


interface TypeCastingIf1 {
    public void method4() ; // abstract method

}

interface TypeCastingIf2 {

}



