import com.travel.utils.TimeUtils;
import org.junit.Test;

public class TestTime {
    @Test
    public void test() {
        String time="07:61:55";
        System.out.println(TimeUtils.isValidDate(time));
    }
}
