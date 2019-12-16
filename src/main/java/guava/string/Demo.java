package guava.string;

/**
 * @author payno
 * @date 2019/12/12 16:09
 * @description
 */
public class Demo {
    public static void main(String[] args) {
        String str="abcAb12_";
        char[] chars=str.toCharArray();
        int digitCount=0;
        int lowerLetter=0;
        int upperLetter=0;
        int other=0;
        for(char c:chars){
            if(Character.isDigit(c)){
                digitCount++;
            }else if(Character.isLetter(c)){
                if(Character.isLowerCase(c)){
                    lowerLetter++;
                }else {
                    upperLetter++;
                }
            }else {
                other++;
            }
        }
        System.out.printf("digitCount : %d;lowerLetter : %d;upperLetter : %d;other : %d",
                digitCount,lowerLetter,upperLetter,other);
    }
}
