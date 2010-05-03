package sweepmine;

import javax.swing.ImageIcon;

public interface CodeConstants {
	/**
	 * ����xx���Ѷȼ���
	 */
	public enum GameLevel {
		/** �� */
		EASY,
		/** ��ͨ */
		NORMAL,
		/** ���� */
		HARD,
		/** ��� */
		CRAZY;
	}

	/**
	 * �������ܻ��Ƶ�xx����Ŀ
	 */
	public enum AroundNumCount {
		ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE;
	}

	/**
	 * ����ʱ�����ֵ�ͼƬ
	 */
	public enum TimeNumberImage {
		/** ����0ͼƬ */
		IMAGE0(ImageManager.loadImage("/resources/timenumber/number0.gif")),
		/** ����1ͼƬ */
		IMAGE1(ImageManager.loadImage("/resources/timenumber/number1.gif")),
		/** ����2ͼƬ */
		IMAGE2(ImageManager.loadImage("/resources/timenumber/number2.gif")),
		/** ����3ͼƬ */
		IMAGE3(ImageManager.loadImage("/resources/timenumber/number3.gif")),
		/** ����4ͼƬ */
		IMAGE4(ImageManager.loadImage("/resources/timenumber/number4.gif")),
		/** ����5ͼƬ */
		IMAGE5(ImageManager.loadImage("/resources/timenumber/number5.gif")),
		/** ����6ͼƬ */
		IMAGE6(ImageManager.loadImage("/resources/timenumber/number6.gif")),
		/** ����7ͼƬ */
		IMAGE7(ImageManager.loadImage("/resources/timenumber/number7.gif")),
		/** ����8ͼƬ */
		IMAGE8(ImageManager.loadImage("/resources/timenumber/number8.gif")),
		/** ����9ͼƬ */
		IMAGE9(ImageManager.loadImage("/resources/timenumber/number9.gif"));

		private ImageIcon imageIcon = null;

		TimeNumberImage(ImageIcon imageIcon) {
			this.imageIcon = imageIcon;
		}

		public ImageIcon getImageIcon() {
			return this.imageIcon;
		}
	}
	
	/**
	 * ����xx���ֵ�ͼƬ
	 */
	public enum MineNumberImage {
		/** ����0ͼƬ */
		IMAGE0(ImageManager.loadImage("/resources/minenumber/number0.gif")),
		/** ����1ͼƬ */
		IMAGE1(ImageManager.loadImage("/resources/minenumber/number1.gif")),
		/** ����2ͼƬ */
		IMAGE2(ImageManager.loadImage("/resources/minenumber/number2.gif")),
		/** ����3ͼƬ */
		IMAGE3(ImageManager.loadImage("/resources/minenumber/number3.gif")),
		/** ����4ͼƬ */
		IMAGE4(ImageManager.loadImage("/resources/minenumber/number4.gif")),
		/** ����5ͼƬ */
		IMAGE5(ImageManager.loadImage("/resources/minenumber/number5.gif")),
		/** ����6ͼƬ */
		IMAGE6(ImageManager.loadImage("/resources/minenumber/number6.gif")),
		/** ����7ͼƬ */
		IMAGE7(ImageManager.loadImage("/resources/minenumber/number7.gif")),
		/** ����8ͼƬ */
		IMAGE8(ImageManager.loadImage("/resources/minenumber/number8.gif"));

		private ImageIcon imageIcon = null;

		MineNumberImage(ImageIcon imageIcon) {
			this.imageIcon = imageIcon;
		}

		public ImageIcon getImageIcon() {
			return this.imageIcon;
		}
	}
}
