import com.tw.merchant.roman.IntergalacticToRomanConverter;
import junit.framework.TestCase;

public class TestSamples extends TestCase {
    IntergalacticToRomanConverter irc;

    protected void setup() {
        irc = new IntergalacticToRomanConverter();
        irc.setCommodityCost(20);
        irc.setCommodityName("Gold");
        irc.getIntergalacticUnits().add("MM");
        irc.getIntergalacticUnits().add("LL");
        irc.getIntergalacticUnits().add("VV");
        irc.getRomanDenomination().add("M");
        irc.getRomanDenomination().add("L");
        irc.getRomanDenomination().add("V");

    }


    public void testNumberConversion() {
        setup();
        assertTrue(irc.getDecimalValue() == 1055);

    }
}
