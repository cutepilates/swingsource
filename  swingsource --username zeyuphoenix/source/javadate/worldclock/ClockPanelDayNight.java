/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ClockPanel.java
 *
 * Created on 03.02.2010, 17:25:40
 */
package worldclock;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;

import javax.swing.JPanel;

import swinglayout.GroupLayout;
import swinglayout.LayoutStyle;

/**
 */
public class ClockPanelDayNight extends JPanel implements
		PropertyChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private City city = City.Berlin;
	private String cityName = City.Berlin.getName();
	private long offset = City.Berlin.getOffset();

	/** Creates new form ClockPanel */
	public ClockPanelDayNight() {
		initComponents();
		cityLabel.setText(cityName);
		clock.addPropertyChangeListener(this);
		setPreferredSize(new java.awt.Dimension(358, 74));
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
		this.cityName = city.getName();
		this.offset = city.getOffset();

		long localOffset = Calendar.getInstance().get(Calendar.ZONE_OFFSET);
		long diff = localOffset + offset;
		int minDiff = (int) (diff % 3600000);
		int hourDiff = (int) (diff / 3600000);

		this.clock.setTimeZoneOffsetHour(hourDiff);
		this.clock.setTimeZoneOffsetMinute(minDiff);

		cityLabel.setText(this.cityName);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		cityLabel = new TextLabel();
		dayLabel = new TextLabel();
		clock = new AnalogClockDayNight();

		setOpaque(false);
		setPreferredSize(new java.awt.Dimension(358, 100));
		setSize(new java.awt.Dimension(305, 100));

		cityLabel.setText("City");
		cityLabel.setFont(new java.awt.Font("Arial", 0, 36));
		cityLabel.setName("cityLabel"); // NOI18N

		dayLabel.setText("today");
		dayLabel.setFont(new java.awt.Font("Arial", 0, 18));
		dayLabel.setName("dayLabel"); // NOI18N

		clock.setName("clock"); // NOI18N
		clock.setPreferredSize(new java.awt.Dimension(74, 74));

		GroupLayout clockLayout = new GroupLayout(clock);
		clock.setLayout(clockLayout);
		clockLayout.setHorizontalGroup(clockLayout.createParallelGroup(
				GroupLayout.LEADING).add(0, 74, Short.MAX_VALUE));
		clockLayout.setVerticalGroup(clockLayout.createParallelGroup(
				GroupLayout.LEADING).add(0, 74, Short.MAX_VALUE));

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				GroupLayout.LEADING).add(
				layout.createSequentialGroup().add(clock,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE).add(18, 18, 18).add(
						layout.createParallelGroup(GroupLayout.LEADING).add(
								layout.createSequentialGroup().add(dayLabel,
										GroupLayout.PREFERRED_SIZE, 148,
										GroupLayout.PREFERRED_SIZE)
										.addContainerGap(132, Short.MAX_VALUE))
								.add(cityLabel, GroupLayout.DEFAULT_SIZE, 280,
										Short.MAX_VALUE))));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.LEADING)
				.add(
						layout.createSequentialGroup().add(cityLabel,
								GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE).addPreferredGap(
								LayoutStyle.RELATED).add(dayLabel,
								GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)).add(clock,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE));
	}// </editor-fold>//GEN-END:initComponents

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private TextLabel cityLabel;
	private AnalogClockDayNight clock;
	public TextLabel dayLabel;

	// End of variables declaration//GEN-END:variables

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getSource().equals(clock)) {
			if (event.getPropertyName().equals("dayOffset")) {
				switch ((Integer) event.getNewValue()) {
				case -1:
					dayLabel.setText("yesterday");
					break;
				case 0:
					dayLabel.setText("today");
					break;
				case 1:
					dayLabel.setText("tomorrow");
					break;
				default:
					dayLabel.setText("today");
					break;
				}
			}
		}
	}
}