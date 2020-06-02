package info.christmann.recs.tor_master.webinterface.utils;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Resources {

	// File paths
	public static final String LOCAL_FIRMWARE_FILE_PATH = "/opt/RECS_Master/webapp/firmware/";
//	public static final String LOCAL_FIRMWARE_FILE_PATH = VaadinService.getCurrent() == null ? "/opt/RECS_Master/WebContent/VAADIN/firmware/" : VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/VAADIN/firmware/";
	public static final String LOCAL_FIRMWARE_FILE = LOCAL_FIRMWARE_FILE_PATH + "firmware.zip";
	public static final String LOCAL_TEMP_FIRMWARE_FILE = LOCAL_FIRMWARE_FILE_PATH + "_firmware.zip";
	public static final String LOCAL_ETH_SWITCH_CONFIG = "conf/eth_switch_conf.xml";


	// Color definitions
	public static final Color COLOR_BLACK = new Color(0, 0, 0);
	public static final Color COLOR_PURPLE = new Color(178, 80, 235);;
	public static final Color COLOR_PINK = new Color(154, 111, 218);
	public static final Color COLOR_DARK_CYAN = new Color(111, 172, 218);
	public static final Color COLOR_SOFT_GREEN = new Color(133, 217, 111);
	public static final Color COLOR_SOFT_BLUE = new Color(86, 138, 255);
	public static final Color COLOR_RED = new Color(198, 0, 0);
	public static final Color COLOR_GREEN = new Color(0, 158, 0);
	public static final Color COLOR_ORANGE = new Color(237, 194, 64);

	public static final Color COLOR_ON = COLOR_GREEN;
	public static final Color COLOR_WARNING = COLOR_ORANGE;
	public static final Color COLOR_CRITICAL = COLOR_RED;
	public static final Color COLOR_TOTAL = COLOR_SOFT_BLUE;
	public static final Color COLOR_TOTAL_POWER_USAGE = COLOR_SOFT_GREEN;
	public static final Color COLOR_NODE_POWER_USAGE = COLOR_PINK;
	public static final Color COLOR_PEG_POWER_USAGE = COLOR_DARK_CYAN;
	public static final Color COLOR_TEMP = COLOR_PURPLE;

	// Log Levels
	public static final List<String> LOG_LEVELS = Collections.unmodifiableList(Arrays.asList("TRACE", "DEBUG", "INFO", "WARN", "ERROR"));

	private Resources() {
		// Private constructor to prevent instantiating this utils class
	}

	//Displays hex representation of displayed color
	public static String toHex(Color color)
	{
		String hex = Integer.toHexString(color.getRGB() & 0xffffff);
		while(hex.length() < 6){
			hex = "0" + hex;
		}
		hex = "#" + hex;
		return hex;
	}


}
