import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import frc.robot.Robot;

public class ClampTest {
  @Test
  public void testAllClamps() {
    for (int i = -100; i < 100; i++) {
        assertEquals(i, Robot.clamp(i, -100, 100));
    }
    for (int i = 100; i < 200; i++) {
        assertEquals(100, Robot.clamp(i, -100, 100));
    }
    for (int i = -200; i < -100; i++) {
        assertEquals(-100, Robot.clamp(i, -100, 100));
    }
  }
}
