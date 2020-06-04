/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author Дима Никитин
 * 
 * Добрый день. Программа реализована в одном классе - Calculator.java. В этом
 * классе определены все действия и операции (как для арабских, так и для рим-
 * ских чисел. Я счел нецелесообразным выносить каждое из действий в отдельный 
 * класс, поскольку они весьма коротки. В случае более "тяжелых" операторов их,
 * разумеется, необходимо разделить в отдельные классы программы - деление, 
 * умножение и т.д.
 * 
 * 
 */

public class Calculator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String asb;
        Scanner sr=new Scanner(System.in);
        double a,b,res;
        String alpha,beta,gamma;
        char operation;
        System.out.println("Введите выражение вида А operation B, разделяя операторы пробелом:");
        StringReader str;
        //str = new StringReader(new InputStream());
        
        String operation2=new String();
        operation2=sr.nextLine();
        String[] subStr;
        String delimiter=" ";
        subStr=operation2.split(delimiter);
        
        
        
        if(subStr[0].contains("I")||subStr[0].contains("V")||subStr[0].contains("X")){
            
            if(subStr[1].contains("+"))
                System.out.println(arabicToRoman(romanToArabic(subStr[0])+romanToArabic(subStr[2])));
            if(subStr[1].contains("-"))
                System.out.println(arabicToRoman(romanToArabic(subStr[0])-romanToArabic(subStr[2])));
            if(subStr[1].contains("*"))
                System.out.println(arabicToRoman(romanToArabic(subStr[0])*romanToArabic(subStr[2])));
            if(subStr[1].contains("/"))
                System.out.println(arabicToRoman(romanToArabic(subStr[0])/romanToArabic(subStr[2])));
                
                
        }
        else{
            if(subStr[1].contains("+"))
                System.out.println(Add(Double.valueOf(subStr[0]),Double.valueOf(subStr[2])));
            if(subStr[1].contains("-"))
                System.out.println(Sub(Double.valueOf(subStr[0]),Double.valueOf(subStr[2])));
            if(subStr[1].contains("*"))
                System.out.println(Mult(Double.valueOf(subStr[0]),Double.valueOf(subStr[2])));
            if(subStr[1].contains("/"))
                System.out.println(Div(Double.valueOf(subStr[0]),Double.valueOf(subStr[2])));
            
        }
    }
    
    public static double Add(double a,double b){
        return (a+b);
    }
    public static String Add(String a,String b){
        return a.concat(b);
    }
    public static double Sub(double a,double b){
        return (a-b);
    }
    public static double Mult(double a,double b){
        return (a*b);
    }
    public static double Div(double a,double b){
        if(b!=0)
            return a/b;
        else 
            return 0;
    }
    
    public static void RomanCalculation(char alpha,char beta,char gamma){
        
    }
    public static char Add(char a,char b){
        char I=1;
        char II=2;
        return (char)((char)a+(char)b);
    }
    public static char I=1;
    public static char II=2;
    
    public static char III=3;
    public static char IV=4;
    public static char V=5;
    public static char VI=6;
    public static char VII=7;
    public static char VIII=8;
    public static char IX=9;
    public static char X=10;
    
    enum RomanNumeral {
        I(1), IV(4), V(5), IX(9), X(10),
        XL(40), L(50), XC(90), C(100),
        CD(400), D(500), CM(900), M(1000);
        
        private int value;
        
        RomanNumeral(int value){
            this.value=value;
        }
        public int getValue(){
            return value;
        }
        public static List<RomanNumeral> getReverseSortedValues(){
            return Arrays.stream(values())
                .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
                .collect(Collectors.toList());
        }
    }
    public static int romanToArabic(String input) {
        String romanNumeral = input.toUpperCase();
        int result = 0;

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException(input + " cannot be converted to a Roman Numeral");
        }

        return result;
    }
    public static String arabicToRoman(int number){
        if((number<=0)||(number>4000)){
            System.out.println("Упс! При вычитании получилось отрицательное число.");
            throw new IllegalArgumentException(number + " не попадает в интервал от 0 до 40000");
        }
        List<RomanNumeral> romanNumerals=RomanNumeral.getReverseSortedValues();
        
        int i=0;
        StringBuilder sb=new StringBuilder();
        
        while((number>0)&&(i<romanNumerals.size())){
            RomanNumeral currentSymbol=romanNumerals.get(i);
            if(currentSymbol.getValue()<=number){
                sb.append(currentSymbol.name());
                number-=currentSymbol.getValue();
            }else{
                i++;
            }
        }
        return sb.toString();
    }
    
}
