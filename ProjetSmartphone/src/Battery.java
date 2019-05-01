import com.sun.jna.Native;
import com.sun.jna.Structure;
import com.sun.jna.win32.StdCallLibrary;
import java.util.ArrayList;
import java.util.List;


public interface Battery extends StdCallLibrary {

        public Battery INSTANCE = (Battery) Native.loadLibrary("Kernel32", Battery.class);


        public class SYSTEM_POWER_STATUS extends Structure {
            public byte ACLineStatus;
            public byte BatteryLifePercent;


            @Override
            protected List<String> getFieldOrder() {
                ArrayList<String> fields = new ArrayList<String>();
                fields.add("ACLineStatus");
                fields.add("BatteryLifePercent");

                return fields;
            }

            /**
             * The AC power status
             */
            public String getACLineStatusString() {
                switch (ACLineStatus) {
                    case (0): return "Offline";
                    case (1): return "Online";
                    default: return "Unknown";
                }
            }


            /**
             * The percentage of full battery charge remaining
             */
            public String getBatteryLifePercent() {
                return (BatteryLifePercent == (byte) 255) ? "Unknown" : BatteryLifePercent + "%";
            }

            @Override
            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append("ACLineStatus: " + getACLineStatusString() + "\n");
                sb.append("BatteryLifePercent : " + getBatteryLifePercent());

                return sb.toString();
            }
        }

        /**
         * Fill the structure.
         */
          public int GetSystemPowerStatus(SYSTEM_POWER_STATUS result);
    }


class testBattery{
    public static void main(String args[]) {
        Battery.SYSTEM_POWER_STATUS batteryStatus = new Battery.SYSTEM_POWER_STATUS();
        Battery.INSTANCE.GetSystemPowerStatus(batteryStatus);
        System.out.println(batteryStatus);
    }
}

