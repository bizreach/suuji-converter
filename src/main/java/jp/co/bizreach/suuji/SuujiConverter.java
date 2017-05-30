package jp.co.bizreach.suuji;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Convert a suuji (数字) string to a numeric value.
 *
 * 数字文字列を数値に変換します。
 */
public class SuujiConverter {

    public static long convert(String str){
        // 正規化
        str = replaceFullWidthNumber(removeFullWidthSeparator(str));

        // 最後の1文字
        char last = str.charAt(str.length() - 1);

        switch(last){
            case '億':
                return adjustUnit(str.substring(0, str.length() - 1)) * 100000000;
            case '万':
                return adjustUnit(str.substring(0, str.length() - 1)) * 10000;
            default:
                return adjustUnit(str);
        }
    }

    /**
     * 金銭区切りのカンマ（半角・全角）を取り除く。
     */
    private static String removeFullWidthSeparator(String str){
        return str.replaceAll("[,，]", "");
    }

    /**
     * 全角数字と漢数字を半角数字に置きかえる。
     */
    private static String replaceFullWidthNumber(String str){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(c == '一' || c == '１'){
                sb.append(1);
            } else if(c == '二' || c == '２'){
                sb.append(2);
            } else if(c == '三' || c == '３'){
                sb.append(3);
            } else if(c == '四' || c == '４'){
                sb.append(4);
            } else if(c == '五' || c == '５'){
                sb.append(5);
            } else if(c == '六' || c == '６'){
                sb.append(6);
            } else if(c == '七' || c == '７'){
                sb.append(7);
            } else if(c == '八' || c == '８'){
                sb.append(8);
            } else if(c == '九' || c == '９'){
                sb.append(9);
            } else if(c == '０'){
                sb.append(0);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private static Pattern NUM_UNIT_PATTERN = Pattern.compile("(([1-9]+[0-9\\.]*)[十百千万億]*)?");

    /**
     * 文字列中の単位を処理して数値に変換する。
     */
    private static long adjustUnit(String str){
        Matcher matcher = NUM_UNIT_PATTERN.matcher(str);
        long result = 0;
        while(matcher.find()){
            result = result + (long)(stringToDouble(matcher.group(2)) * convertUnit(matcher.group(1)));
        }
        return result;
    }

    /**
     * 単位数詞が含まれる場合はそれらを掛けあわせて数字とする。
     * 例えば「百万」は"100 * 10000"として、1000000に変換する。
     */
    private static double convertUnit(String str){
        if(str == null){
            return 1.0;
        } else {
            double result = 1.0;
            for(Double unit: getJapaneseUnitRate(str)) {
                result = result * unit.doubleValue();
            }
            return result;
        }
    }

    /**
     * 文字列に含まれる単位の倍率を大きいものから順にListに詰めて返す。
     */
    private static List<Double> getJapaneseUnitRate(String str){
        List<Double> units = new ArrayList<>();

        if(str.contains("億")){
            units.add(100000000.0);
        } else if(str.contains("万")){
            units.add(10000.0);
        } else if(str.contains("千")){
            units.add(1000.0);
        } else if(str.contains("百")){
            units.add(100.0);
        } else if(str.contains("十")){
            units.add(10.0);
        }

        return units;
    }

    private static Pattern DOUBLE_PATTERN = Pattern.compile("^([1-9][0-9]*)\\.([0-9]+)$");

    /**
     * 文字列をdoubleに変換する。"."が複数含まれる場合は、最後のもの以外は無視する
     */
    private static double stringToDouble(String str){
        if(str == null){
            return 0;
        } else {
            Matcher matcher = DOUBLE_PATTERN.matcher(str);
            if (matcher.matches()) {
                return Double.parseDouble(matcher.group(1).replaceAll("\\.", "") + "." + matcher.group(2));
            } else {
                return Double.parseDouble(str.replaceAll("\\.", ""));
            }
        }
    }

}
