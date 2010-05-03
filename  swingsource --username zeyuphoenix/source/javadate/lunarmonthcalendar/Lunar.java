package lunarmonthcalendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Lunar {
	private int year;
	private int month;
	private int day;
	private boolean leap;

	private static SimpleDateFormat chineseDateFormat = new SimpleDateFormat(
			"yyyy��MM��dd��");

	/**
	 * ����y��m��d�ն�Ӧ��ũ��.
	 */
	public Lunar(Calendar calendar) {
		int monCyl;
		int leapMonth = 0;
		Date baseDate = null;
		try {
			baseDate = chineseDateFormat.parse("1900��1��31��");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// �����1900��1��31����������
		int offset = (int) ((calendar.getTime().getTime() - baseDate.getTime()) / 86400000L);
		monCyl = 14;

		// ��offset��ȥÿũ���������
		// ���㵱����ũ���ڼ���
		// i���ս����ũ�������
		// offset�ǵ���ĵڼ���
		int iYear, daysOfYear = 0;
		for (iYear = 1900; iYear < 2050 && offset > 0; iYear++) {
			daysOfYear = yearDaysCount(iYear);
			offset -= daysOfYear;
			monCyl += 12;
		}
		if (offset < 0) {
			offset += daysOfYear;
			iYear--;
			monCyl -= 12;
		}
		// ũ�����
		year = iYear;

		// ���ĸ���,1-12
		leapMonth = verificationLeapMonth(iYear);
		leap = false;

		// �õ��������offset,�����ȥÿ�£�ũ��������������������Ǳ��µĵڼ���
		int iMonth, daysOfMonth = 0;
		for (iMonth = 1; iMonth < 13 && offset > 0; iMonth++) {
			// ����
			if (leapMonth > 0 && iMonth == (leapMonth + 1) && !leap) {
				--iMonth;
				leap = true;
				daysOfMonth = countLeapDays(year);
			} else
				daysOfMonth = monthDays(year, iMonth);

			offset -= daysOfMonth;
			// �������
			if (leap && iMonth == (leapMonth + 1))
				leap = false;
			if (!leap)
				monCyl++;
		}
		// offsetΪ0ʱ�����Ҹղż�����·������£�ҪУ��
		if (offset == 0 && leapMonth > 0 && iMonth == leapMonth + 1) {
			if (leap) {
				leap = false;
			} else {
				leap = true;
				--iMonth;
				--monCyl;
			}
		}
		// offsetС��0ʱ��ҲҪУ��
		if (offset < 0) {
			offset += daysOfMonth;
			--iMonth;
			--monCyl;
		}
		month = iMonth;
		day = offset + 1;
	}

	// ȡ��ũ�� year���������
	private final int yearDaysCount(int year) {
		int i = 0;
		// �Ȱ�С�µõ���С������ 12 * 29 = 348.
		int sum = 348;
		// �ж�12�����Ǹ��Ǵ���,�������һ��.
		for (i = 0x8000; i > 0x8; i >>= 1) {
			if ((CodeConstants.LUNAR_INFO[year - 1900] & i) != 0) {
				sum += 1;
			}
		}
		// �������µ�����,�õ�һ���������
		return (sum + countLeapDays(year));
	}

	// ȡ��ũ�� year�����µ�����
	private final int countLeapDays(int year) {
		// ����֤�Ƿ��������,�������򷵻�0
		if (verificationLeapMonth(year) != 0) {
			// �ж������Ǵ��»���С��
			if ((CodeConstants.LUNAR_INFO[year - 1900] & 0x10000) != 0) {
				return 30;
			} else {
				return 29;
			}
		}
		return 0;
	}

	// ��֤ũ�� year�����ĸ��� 1-12 , û���´��� 0
	private final int verificationLeapMonth(int year) {
		// ��������Ϣ�ĺ�4λ��0x1111���������,�������򷵻�.
		return (int) (CodeConstants.LUNAR_INFO[year - 1900] & 0xf);
	}

	// ȡ��ũ�� year��month�µ�������
	private final int monthDays(int year, int month) {
		// �ƶ�ָ����λ���������������ظ����Ǵ��»���С��
		if ((CodeConstants.LUNAR_INFO[year - 1900] & (0x10000 >> month)) == 0) {
			return 29;
		} else {
			return 30;
		}
	}

	// ȡ��ũ�� year�����Ф
	public String yearZodiac(int year) {
		return CodeConstants.CHINESE_ZODIAC[(year - 4) % 12];
	}

	// ȡ��ũ�� year�����ɺ͵�֧ 0Ϊ����
	public String cyclical(int year) {
		int num = year - 1900 + 36;
		return (CodeConstants.CHINESE_TIANGAN[num % 10] + CodeConstants.CHINESE_DIZHI[num % 12]);
	}

	// ���ݸ�������ó�������ʾ.
	private final String getChinaDayString(int day) {
		int n = day % 10 == 0 ? 9 : day % 10 - 1;
		if (day > 30) {
			return "";
		}
		if (day == 10) {
			day = 0;
		}
		return CodeConstants.CHINESE_DAY_NUMBER[day / 10 + 10]
				+ CodeConstants.CHINESE_DAY_NUMBER[n];
	}

	@Override
	public String toString() {
		return (leap ? "��" : "")
				+ CodeConstants.CHINESE_MONTH_NUMBER[month - 1]
				+ getChinaDayString(day);
	}

	// ���ذ�����������ʽ����������
	public String getNumericMD() {
		String temp_mon = month < 10 ? "0" + month : "" + month;
		String temp_day = day < 10 ? "0" + day : "" + day;

		return temp_mon + temp_day;
	}

	// ������������
	public String getYear() {
		return cyclical(year) + "��";
	}

	// �����������·�
	public String getMonth() {
		return CodeConstants.CHINESE_MONTH_NUMBER[month - 1];
	}

	// ������������
	public String getDay() {
		return getChinaDayString(day);
	}

	public static void main(String[] args) throws ParseException {
		Calendar today = Calendar.getInstance(Locale.CHINA);
		// today.setTime(new Date());// ���ص�ǰ����
		today.set(2009, 6, 5);
		Lunar lunar = new Lunar(today);
		System.out.println(lunar.getYear());// ��������������
		System.out.println(lunar.toString());// ���������������
		System.out.println(lunar.yearZodiac(today.get(Calendar.YEAR)));// �����������
		System.out.println(new SimpleDateFormat("yyyy��MM��dd��").format(today
				.getTime()));// �����������
		System.out.println(CodeConstants.CHINESE_WEEK[today
				.get(Calendar.DAY_OF_WEEK) - 1]);// ����������ڼ�
	}
}
