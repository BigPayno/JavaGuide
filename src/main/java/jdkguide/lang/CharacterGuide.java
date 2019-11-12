package jdkguide.lang;


import java.util.ArrayList;
import java.util.List;

/**
 * @author payno
 * @date 2019/11/6 14:34
 * @description
 */
public class CharacterGuide {
    private static final String BLANK=" ";
    private static final String NULL="";
    private static final int LENGTH=20;
    private String f1(String s,int n){
        StringBuffer stringBuffer=new StringBuffer();
        for (int i = 0; i < n-1; i++) {
            stringBuffer.append(s).append(",");
        }
        stringBuffer.append(s);
        return stringBuffer.toString();
    }

    private String[] f2(Character c,int n){
        String[] strings=new String[n];
        int index=(int)c.charValue();
        for(int i=0;i<n;i++){
            char var=(char)(i+index);
            strings[i]=Character.valueOf(var).toString();
        }
        return strings;
    }

    private void f3(Character c,int n){
        StringBuffer stringBuffer=new StringBuffer();
        StringBuffer blankBuffer=new StringBuffer();
        List<String> stringList=new ArrayList<>();
        List<String> blankList=new ArrayList<>();
        for (int i = 0; i <LENGTH; i++) {
            stringBuffer.append(c.toString());
            blankBuffer.append(BLANK);
            stringList.add(stringBuffer.toString());
            blankList.add(blankBuffer.toString());
        }
        for (int i = n; i >0 ; i--) {
            int num=(n-i+1+NULL).length();
            System.out.printf("%d%s%s%n",n-i+1,blankList.get(LENGTH-num-i),stringList.get(i-1));
        }
    }

    public static void main(String[] args) {
        CharacterGuide characterGuide=new CharacterGuide();
        characterGuide.f3('*',13);
    }
}
