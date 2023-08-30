package utils;

import constants.CharacterConstant;

public class StringFormatUtil {

    public static  String replaceWhilesSpaces(String value){
        return value.replaceAll("\\s+", CharacterConstant.SPACE_STRING);
    }

    public static String[] splitStrings(String value){
        return replaceWhilesSpaces(value).split(CharacterConstant.SPACE_STRING);
    }

    public static int removeDuplicateNumber(int a[], int n)
    {
        if (n == 0 || n == 1) {
            return n;
        }
        // creating another array for only storing
        // the unique elements
        int[] temp = new int[n];
        int j = 0;

        for (int i = 0; i < n - 1; i++) {
            if (a[i] != a[i + 1]) {
                temp[j++] = a[i];
            }
        }

        temp[j++] = a[n - 1];
        // Changing the original array
        if (j >= 0) System.arraycopy(temp, 0, a, 0, j);
        return j;
    }



}
