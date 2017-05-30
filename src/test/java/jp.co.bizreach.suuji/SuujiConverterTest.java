package jp.co.bizreach.suuji;

import org.junit.Test;
import static org.junit.Assert.*;

public class SuujiConverterTest {

    @Test
    public void test(){
        long value1 = SuujiConverter.convert("1万2千5百四十");
        assertEquals(12540, value1);

        long value2 = SuujiConverter.convert("1万2000");
        assertEquals(12000, value2);

        long value3 = SuujiConverter.convert("2千5百万");
        assertEquals(25000000, value3);

        long value4 = SuujiConverter.convert("2千500万");
        assertEquals(25000000, value4);

        long value5 = SuujiConverter.convert("2000万");
        assertEquals(20000000, value5);

        long value6 = SuujiConverter.convert("2千万");
        assertEquals(20000000, value6);
    }

}
