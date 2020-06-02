package info.christmann.recs.tor_master.webinterface.components;

import java.awt.Color;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.github.appreciated.apexcharts.ApexCharts;
import com.github.appreciated.apexcharts.config.builder.ChartBuilder;
import com.github.appreciated.apexcharts.config.builder.DataLabelsBuilder;
import com.github.appreciated.apexcharts.config.builder.LegendBuilder;
import com.github.appreciated.apexcharts.config.builder.PlotOptionsBuilder;
import com.github.appreciated.apexcharts.config.builder.ResponsiveBuilder;
import com.github.appreciated.apexcharts.config.builder.StrokeBuilder;
import com.github.appreciated.apexcharts.config.builder.TooltipBuilder;
import com.github.appreciated.apexcharts.config.builder.XAxisBuilder;
import com.github.appreciated.apexcharts.config.builder.YAxisBuilder;
import com.github.appreciated.apexcharts.config.chart.Type;
import com.github.appreciated.apexcharts.config.chart.animations.Easing;
import com.github.appreciated.apexcharts.config.chart.animations.builder.AnimateGraduallyBuilder;
import com.github.appreciated.apexcharts.config.chart.animations.builder.DynamicAnimationBuilder;
import com.github.appreciated.apexcharts.config.chart.builder.AnimationsBuilder;
import com.github.appreciated.apexcharts.config.chart.builder.BrushBuilder;
import com.github.appreciated.apexcharts.config.chart.builder.ToolbarBuilder;
import com.github.appreciated.apexcharts.config.chart.builder.ZoomBuilder;
import com.github.appreciated.apexcharts.config.chart.zoom.ZoomType;
import com.github.appreciated.apexcharts.config.chart.zoom.ZoomedArea;
import com.github.appreciated.apexcharts.config.chart.zoom.builder.ZoomedAreaBuilder;
import com.github.appreciated.apexcharts.config.legend.HorizontalAlign;
import com.github.appreciated.apexcharts.config.legend.Position;
import com.github.appreciated.apexcharts.config.plotoptions.builder.BarBuilder;
import com.github.appreciated.apexcharts.config.stroke.Curve;
import com.github.appreciated.apexcharts.config.xaxis.XAxisType;
import com.github.appreciated.apexcharts.config.xaxis.labels.builder.DatetimeFormatterBuilder;
import com.github.appreciated.apexcharts.config.yaxis.builder.CrosshairsBuilder;
import com.github.appreciated.apexcharts.config.yaxis.builder.AxisTicksBuilder;
import com.github.appreciated.apexcharts.config.xaxis.builder.LabelsBuilder;
import com.github.appreciated.apexcharts.config.xaxis.builder.TitleBuilder;
import com.github.appreciated.apexcharts.helper.Coordinate;
import com.github.appreciated.apexcharts.helper.Series;

import info.christmann.recs.model.TimestampValueBean;

public class LineChart extends ApexCharts {
	private static final long serialVersionUID = -8628051811914152541L;
	public static final int SIZE_HISTORY_VALUES = 61;
	private static Logger mLog = LogManager.getLogger();

	public LineChart(String width, String height) {
		super();
		setWidth(width);
		setHeight(height);
	}

	public LineChart() {
		new LineChart("300px", "200px");
	}

	static public ApexCharts getLineChart(String width, String height) {
		ApexCharts c = new ApexCharts();
		c.setChart(ChartBuilder.get()
				.withType(Type.line)
				// Toolbar is enabled by default. Disable it!
				.withToolbar(ToolbarBuilder.get()
						.withShow(false)
						.build())
				// Zoom defaults to ZoomType.x. Disable it
				.withZoom(ZoomBuilder.get()
						.withEnabled(false)
						.build())
				.withAnimations(AnimationsBuilder.get()
						.withDynamicAnimation(DynamicAnimationBuilder.get()
								.withSpeed(500)
								.withEnabled(true)
								.build())
						.withAnimateGradually(AnimateGraduallyBuilder.get()
								.withDelay(500)
								.withEnabled(true)
								.build())
						.withEasing(Easing.linear)
						.withSpeed(500.0)
						.withEnabled(true)
						.build())
				// moves each chart down from top of element
				.withOffsetY(0.0)
				// shifts charts right
				.withOffsetX(0.0)
				.build());
		c.setDataLabels(DataLabelsBuilder.get()
				.withEnabled(false)
				.withOffsetX(10.0)
				.withOffsetY(10.0)
//				.withEnabledOnSeries()
				.build());
		c.setStroke(StrokeBuilder.get().withCurve(Curve.smooth).build());
		c.setXaxis(XAxisBuilder.get()
				.withType(XAxisType.datetime)
//				.withLabels(LabelsBuilder.get()
//				 		.withDatetimeFormatter(DatetimeFormatterBuilder.get()
//								.withDay("dd")
//								.withMinute("mm")
//								.build()).build())
//				.withAxisTicks(com.github.appreciated.apexcharts.config.xaxis.builder.AxisTicksBuilder.get()
//						.withHeight(5.0)
//						.withColor("#000000")
//						.withShow(false)
//						.build())
				.withTitle(TitleBuilder.get().withText("Time").build())
				// if set to 1000.0 disrupts chart and control of app
//				.withTickAmount(5.0)
				.build());
		c.setYaxis(YAxisBuilder.get()
				.withTooltip(TooltipBuilder.get()
						.withEnabled(true)
						.build())
				.withOpposite(true)
				.withForceNiceScale(true)
				.withCrosshairs(CrosshairsBuilder.get()
						.withStroke(com.github.appreciated.apexcharts.config.yaxis.builder.StrokeBuilder.get()
								.withColor("#000000")
								.withWidth(5)
								.withDashArray(5)
								.build())
						.withShow(false)
						.build())
				.withAxisTicks(AxisTicksBuilder.get()
						.withColor("#122522")
						.withWidth(100.0)
						.withShow(true)
						.build())
				.withTickAmount(20.0)
				.build());
		c.setLegend(LegendBuilder.get()
				.withPosition(Position.right)
				.withHorizontalAlign(HorizontalAlign.left)
//				.withFloating(true)
//				.withOffsetX(20.0)
//				.withOffsetY(10.0)
				.withShowForSingleSeries(true)
				.withWidth(150.0)
				.build());
		c.setWidth(width);
		c.setHeight(height);
		return c;
	}

	static public Series<Coordinate> getApexChartsCoordinateSeries(String name, List<TimestampValueBean> timestampValues, Color color) {
		Double[] apexChartsData = getApexChartsSeries(name, timestampValues, color).getData();
		String[] apexChartsLabels = getApexChartsLabels(name, timestampValues, color);
		Coordinate[] coordinates = new Coordinate[apexChartsData.length];
		Series<Coordinate> coordinateSeries = new Series<>(name);
		for (int i = 0; i < apexChartsLabels.length; i++) {
			coordinates[i] = new Coordinate(apexChartsLabels[i],apexChartsData[i]);
		}
//		mLog.info("********************** NAME {} *****************",name );
//		for (Coordinate c : coordinates)
//			mLog.info("Time {} to Value: {}", c.getX(), c.getY());
		coordinateSeries.setData(coordinates);
		return coordinateSeries;
	}

	static public Series<Double> getApexChartsSeries(String name, List<TimestampValueBean> timestampValues, Color color) {
		List<Double> values = new LinkedList<Double>();
		int deltaPos = SIZE_HISTORY_VALUES - timestampValues.size();
		if (deltaPos > 0) {
			// take the first initial value from the input list
			TimestampValueBean bean = timestampValues.get(0);
			double value = Double.valueOf(Math.round(bean.value * 10.0) / 10.0);
			// and fill the output list with index 0 to missing -1
			for (int i = 0; i < deltaPos; i++) {
				values.add(value);
			}
		} // else go on

		for (TimestampValueBean bean : timestampValues) {
			values.add(Double.valueOf(Math.round(bean.value * 10.0) / 10.0));
		}
		Series<Double> s = new Series<Double>(name, values.toArray(new Double[values.size()]));
		return s;
	}

	static public String[] getApexChartsLabels(String name, List<TimestampValueBean> timestampValues, Color color) {
		List<String> values = new LinkedList<>();
		int deltaPos = SIZE_HISTORY_VALUES - timestampValues.size();
		if (deltaPos > 0) {
			// take the first initial values and average the timeStamp difference and extrapolate to the past
			long timeStampDiff = averageTimestampDiffs(timestampValues);
			TimestampValueBean bean = timestampValues.get(0);
			// and fill the output list from index 0 to deltaPos with extrapolated timestamp
			for (int i = 0; i < deltaPos; i++) {
				values.add(0, getISOString(bean.timestamp - (timeStampDiff * i)));
			}
		} // else go on
		for (TimestampValueBean bean : timestampValues) {
//			values.add(LocalDate.ofEpochDay(bean.timestamp/1000).toString());
//			values.add(Utils.getTimeFromEpoch(bean.timestamp));
//			values.add(getISOString(bean.timestamp));
			values.add(getISOString(bean.timestamp));
		}
		return values.toArray(new String[values.size()]);
	}

	private static long averageTimestampDiffs(List<TimestampValueBean> timestampValues) {
		List<Long> timestampList = timestampValues.stream().map(b -> b.timestamp).collect(Collectors.toList());
		LongStream differences =
				LongStream.range(0, timestampList.size() - 1)
						.map(i -> (int) (timestampList.get((int) (i + 1)) - timestampList.get((int) i)));
		LongSummaryStatistics stats = differences.summaryStatistics();
		return Math.round(stats.getAverage());
	}

	private static String getISOString(long l) {
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(l), ZoneId.systemDefault()).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	}

	public static String getFormattedString(long l) {
		ZonedDateTime zdt = Instant.ofEpochMilli(l).atZone(ZoneId.systemDefault());
		LocalDateTime ldt = LocalDateTime.of(zdt.getYear(), zdt.getMonth(), zdt.getDayOfMonth(), zdt.getHour(), zdt.getMinute(), zdt.getSecond(), 0);
		return ldt.toString();
	}

}