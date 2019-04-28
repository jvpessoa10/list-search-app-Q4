package com.joao.listsearch.utils;

public class FilterUtils {

    public static  Boolean checkTypos(String correct, String wrong){
        correct = correct.toLowerCase().trim();
        wrong = wrong.toLowerCase().trim();

        int MAX_TYPOS = 1
                ,lenA = correct.length()
                ,lenB = wrong.length();


        if(lenA - lenB >1 || lenB - lenA > 1) return false;

        int aIndex = 0, bIndex = 0, typos = 0;

        while(aIndex < lenA && bIndex< lenB){
            char aChar = correct.charAt(aIndex), bChar = wrong.charAt(bIndex);
            if(aChar == bChar) {
                aIndex++;
                bIndex++;

            }else{
                if(typos == 1) return false;

                if (lenA > lenB) aIndex++;
                else if (lenA<lenB)bIndex++;
                else{
                    aIndex++;
                    bIndex++;
                }


                typos++;
            }

        }

        return typos<= MAX_TYPOS ;

    }

    public static boolean checkJumbled(String correct, String  wrong){

        correct = correct.toLowerCase().trim();
        wrong = wrong.toLowerCase().trim();


        int mainLen = correct.length();


        if(mainLen != wrong.length()
                || correct.charAt(0) != wrong.charAt(0))
            return false;



        int moveCount = 0;
        for (int i = 0; i < mainLen; i++) {
            int j = wrong.indexOf(correct.charAt(i));

            if(j == -1) return false;

            if(i != j) moveCount ++;



        }
        if(mainLen> 3){
            if(moveCount < mainLen * (2f/3f) ) return true;
        }else{
            return moveCount >= 0;
        }

        return false;
    }
}
