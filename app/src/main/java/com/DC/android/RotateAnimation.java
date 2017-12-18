package com.DC.android;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * 
 */
public class RotateAnimation extends Animation {
	private final String TAG = RotateAnimation.class.getSimpleName();

	// ���������ȡ� */
	public static final float DEPTH_Z = 200.0f;
	//  ������ʾʱ���� */
	public static final long DURATION = 1000;

	private float centerX;// ��������
	private float centerY;// ��������
	private Camera mCamera;
	/** ���ڼ����������ȡ���ֵ����ʱ�����txtNumber�����ݡ� */
	private InterpolatedTimeListener listener;

	public void setInterpolatedTimeListener(InterpolatedTimeListener listener) {
		this.listener = listener;
	}

	/** ����һ���ص���������AnimationĿ��View�Ĵ�С����(������Ҫ���пռ�Ŀ�Ⱥ͸߶�.) **/
	public void initialize(int width, int height, int parentWidth,
			int parentHeight) {
		// �ڹ��캯��֮��getTransformation()֮ǰ���ñ�������
		super.initialize(width, height, parentWidth, parentHeight);

		centerX = width / 2;
		centerY = height / 2;
		setDuration(DURATION);
		mCamera = new Camera();
	}

	@Override
	public boolean getTransformation(long currentTime,
			Transformation outTransformation) {
		return super.getTransformation(currentTime, outTransformation);
	}

	/***
	 * �������Ҫ��д,�ڻ��ƶ�����ʱ��ᷴ��ִ��. interpolatedTime���ò�����0�� ��Ϊ1. 0:��ʾ������ʼִ��. 0.5��ʾ�м�ֵ.
	 * 1:��ʾ��������. Transformation:��ȡ�仯�ľ���.matrix
	 */
	protected void applyTransformation(float interpolatedTime,
			Transformation transformation) {
		float from = 0.0f, to = 180.0f;
		float degree = from + (to - from) * interpolatedTime;// ��ת�ĽǶ�
		if (degree == 180)
			return;

		if (interpolatedTime > 0.5f) {
			// ��ת���������£�Ϊ��֤��ʾ���ݲ����־���Ч�����ǶԳ���״����Ҫ���Ƕ��������.
			degree = degree - 180;
		}

		float depth = (0.5f - Math.abs(interpolatedTime - 0.5f)) * DEPTH_Z;// ���

		final Matrix matrix = transformation.getMatrix();
		mCamera.save();
		mCamera.translate(0.0f, 0.0f, depth);
		mCamera.rotateY(degree);
		mCamera.getMatrix(matrix);
		mCamera.restore();

		// ȷ��ͼƬ�ķ�ת����һֱ������������ĵ�λ��
		matrix.preTranslate(-centerX, -centerY);
		matrix.postTranslate(centerX, centerY);

		// interpolatedTime:��������ֵ����ΧΪ[0.0f,1.0f]
		if (listener != null) {
			listener.interpolatedTime(interpolatedTime);
		}

	}

	/** �������ȼ������� */
	public static interface InterpolatedTimeListener {
		public void interpolatedTime(float interpolatedTime);
	}
}