package info.christmann.recs.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javax.sound.sampled.Line;
import info.christmann.recs.tor_master.webinterface.components.LineChart;

public class TimestampValueBean implements Externalizable {
	private static final long serialVersionUID = 5625174706050265744L;

	public double value;
	public long timestamp;
	
	public TimestampValueBean() {
		// empty constructor PMD
	}
	
	public TimestampValueBean(long timestamp, double value) {
		this.timestamp = timestamp;
		this.value = value;
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		// To nothing, serialization is done differently
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		// To nothing, serialization is done differently
	}

	@Override
	public String toString() {
		return "TimestampValueBean{" +
				"value=" + value +
				", timestamp=" + timestamp +
				", DateTime=" + LineChart.getFormattedString(timestamp) +
				'}';
	}
}