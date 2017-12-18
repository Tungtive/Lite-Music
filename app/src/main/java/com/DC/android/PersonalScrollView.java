package com.DC.android;
import android.content.Context;
import android.graphics.Rect;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ScrollView;

/**
 * 自定义ScrollView
 * 
 * @author jia
 * 
 */
public class PersonalScrollView extends NestedScrollView {

	private final String TAG = PersonalScrollView.class.getSimpleName();

	private View inner;// 孩子View

	private float touchY;// 点击时Y坐标

	private float deltaY;// Y轴滑动的距离

	private float initTouchY;// 首次点击的Y坐标

	private boolean shutTouch = false;// 是否关闭ScrollView的滑动.

	private Rect normal = new Rect();// 矩形(这里只是个形式，只是用于判断是否需要动画.)

	private boolean isMoveing = false;// 是否开始移动.

	private ImageView imageView;// 背景图控件.
	private View line_up;// 上线
	private int line_up_top;// 上线的top
	private int line_up_bottom;// 上线的bottom

	private int initTop, initBottom;// 初始高度

	private int current_Top, current_Bottom;// 拖动时时高度。

	private int lineUp_current_Top, lineUp_current_Bottom;// 上线

	private onTurnListener turnListener;

	private ImageView imageHeader;

	public void setImageHeader(ImageView imageHeader) {
		this.imageHeader = imageHeader;
	}

	// 状态：上部，下部，默认
	private enum State {
		UP, DOWN, NOMAL
	};

	// 默认状态
	private State state = State.NOMAL;

	public void setTurnListener(onTurnListener turnListener) {
		this.turnListener = turnListener;
	}

	public void setLine_up(View line_up) {
		this.line_up = line_up;
	}

	
	/***
	 * 构造方法
	 * 
	 * @param context
	 * @param attrs
	 */
	public PersonalScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/***
	 * 根据 XML 生成视图工作完成.该函数在生成视图的最后调用，在所有子视图添加完之后. 即使子类覆盖了 onFinishInflate
	 * 方法，也应该调用父类的方法，使该方法得以执行.
	 */
	@Override
	protected void onFinishInflate() {
		if (getChildCount() > 0) {
			inner = getChildAt(0);
		}
	}

	/** touch 事件处理 **/
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (inner != null) {
			commOnTouchEvent(ev);
		}
        // ture：禁止控件本身的滑动.
		if (shutTouch)
			return true;
		else
			return super.onTouchEvent(ev);

	}

	/***
	 * 触摸事件
	 * 
	 * @param ev
	 */
	public void commOnTouchEvent(MotionEvent ev) {
		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			initTouchY = ev.getY();
			current_Top = initTop = imageView.getTop();
			current_Bottom = initBottom = imageView.getBottom();
			if (line_up_top == 0) {
				lineUp_current_Top = line_up_top = line_up.getTop();
				lineUp_current_Bottom = line_up_bottom = line_up.getBottom();
			}
			break;
		case MotionEvent.ACTION_UP:
			/** 回缩动画 **/
			if (isNeedAnimation()) {
				animation();
			}

			if (getScrollY() == 0) {
				state = State.NOMAL;
			}

			isMoveing = false;
			touchY = 0;
			shutTouch = false;
			break;

		/***
		 * 排除出第一次移动计算，因为第一次无法得知deltaY的高度， 然而我们也要进行初始化，就是第一次移动的时候让滑动距离归0.
		 * 之后记录准确了就正常执行.
		 */
		case MotionEvent.ACTION_MOVE:

			touchY = ev.getY();  //点击时y的坐标
			deltaY = touchY - initTouchY;// 滑动距离

			/** 对于首次Touch操作要判断方位：UP OR DOWN **/
			if (deltaY < 0 ) {
				state = State.UP;
			} else if (deltaY > 0 ) {
				state = State.DOWN;
			}

			if (state == State.UP) {
				deltaY = deltaY < 0 ? deltaY : 0;
				isMoveing = false;
				shutTouch = false;

				/** line_up **/
				lineUp_current_Top = (int) (line_up_top - getScrollY());
				lineUp_current_Bottom = (int) (line_up_bottom - getScrollY());

				Log.e(TAG, "top=" + getScrollY());

				line_up.layout(line_up.getLeft(), lineUp_current_Top,
						line_up.getRight(), lineUp_current_Bottom);

			} else if (state == state.DOWN) {
				if (getScrollY() <= deltaY) {
					shutTouch = true;
					isMoveing = true;
				}
				deltaY = deltaY < 0 ? 0 : deltaY;
			}

			if (isMoveing) {
				// 初始化头部矩形
				if (normal.isEmpty()) {
					// 保存正常的布局位置
					normal.set(inner.getLeft(), inner.getTop(),
							inner.getRight(), inner.getBottom());
				}
				// 移动布局(手势移动的1/3)
				float inner_move_H = deltaY / 3;

				inner.layout(normal.left, (int) (normal.top + inner_move_H),
						normal.right, (int) (normal.bottom + inner_move_H));

				/** image_bg **/
				float image_move_H = deltaY / 10;
				current_Top = (int) (initTop + image_move_H);
				current_Bottom = (int) (initBottom + image_move_H);
				imageView.layout(imageView.getLeft(), current_Top,
						imageView.getRight(), current_Bottom);

				/** line_up **/
				lineUp_current_Top = (int) (line_up_top + inner_move_H);
				lineUp_current_Bottom = (int) (line_up_bottom + inner_move_H);
				line_up.layout(line_up.getLeft(), lineUp_current_Top,
						line_up.getRight(), lineUp_current_Bottom);
			}
			break;

		default:
			break;

		}
	}

	/***
	 * 回缩动画
	 */
	public void animation() {

		TranslateAnimation image_Anim = new TranslateAnimation(0, 0,
				Math.abs(initTop - current_Top), 0);
		image_Anim.setDuration(200);
		imageView.startAnimation(image_Anim);

		imageView.layout(imageView.getLeft(), (int) initTop,
				imageView.getRight(), (int) initBottom);

		// 开启移动动画
		TranslateAnimation inner_Anim = new TranslateAnimation(0, 0,
				inner.getTop(), normal.top);
		inner_Anim.setDuration(200);
		inner.startAnimation(inner_Anim);
		inner.layout(normal.left, normal.top, normal.right, normal.bottom);

		/** line_up **/
		TranslateAnimation line_up_Anim = new TranslateAnimation(0, 0,
				Math.abs(line_up_top - lineUp_current_Top), 0);
		line_up_Anim.setDuration(200);
		line_up.startAnimation(line_up_Anim);
		line_up.layout(line_up.getLeft(), line_up_top, line_up.getRight(),
				line_up_bottom);

		normal.setEmpty();

		/** 动画执行 **/
		if (current_Top > initTop + 50 && turnListener != null)
			turnListener.onTurn();

	}

	/** 是否需要开启动画 **/
	public boolean isNeedAnimation() {
		return !normal.isEmpty();
	}

	/***
	 * 执行翻转
	 * 
	 * @author jia
	 * 
	 */
	public interface onTurnListener {

		/** 必须达到一定程度才执行 **/
		void onTurn();
	}
	// 注入背景图
		public void setImageView(ImageView imageView) {
			this.imageView = imageView;
		}

}
