package de.nitri.slidingtoggleswitch;

import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.view.View.OnClickListener;

;

public class SlidingToggleSwitchView extends FrameLayout implements
		OnClickListener {

	private Button btnMovable;
	private Button btnLeft;
	private Button btnRight;

	private OnToggleListener listener;

	public static int LEFT_SELECTED = 0;
	public static int RIGHT_SELECTED = 1;

	private int POSITION = LEFT_SELECTED;
	private String leftButtonText;
	private String rightButtonText;
	private int textColor;
	private Drawable sliderBackground;
	private Drawable buttonBackground;

	public SlidingToggleSwitchView(Context context) {
		this(context, null);
	}

	public SlidingToggleSwitchView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT));

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(R.layout.sliding_toggle_switch, this, true);

		btnMovable = (Button) v.findViewById(R.id.movable_button);
		btnLeft = (Button) v.findViewById(R.id.button_left);
		btnRight = (Button) v.findViewById(R.id.button_right);

		btnLeft.setOnClickListener(this);
		btnRight.setOnClickListener(this);

		listener = (OnToggleListener) context;

		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.SlidingToggleSwitch, 0, 0);

		leftButtonText = a
				.getString(R.styleable.SlidingToggleSwitch_leftButtonText);
		rightButtonText = a
				.getString(R.styleable.SlidingToggleSwitch_rightButtonText);
		textColor = a.getColor(
				R.styleable.SlidingToggleSwitch_android_textColor,
				android.R.color.holo_blue_dark);
		sliderBackground = a
				.getDrawable(R.styleable.SlidingToggleSwitch_sliderBackground);
		buttonBackground = a
				.getDrawable(R.styleable.SlidingToggleSwitch_buttonBackground);

		if (!TextUtils.isEmpty(leftButtonText))
			btnLeft.setText(leftButtonText);
		if (!TextUtils.isEmpty(rightButtonText))
			btnRight.setText(rightButtonText);
		btnLeft.setTextColor(textColor);
		btnRight.setTextColor(textColor);

		if (sliderBackground == null)
			this.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.toggle_frame));
		else
			this.setBackgroundDrawable(sliderBackground);

		if (buttonBackground != null)
			btnMovable.setBackgroundDrawable(buttonBackground);

	}

	public SlidingToggleSwitchView(Context context, AttributeSet attrs,
			int defStyle) {
		this(context, attrs);
	}

	@Override
	public void onClick(View v) {
		if (POSITION == RIGHT_SELECTED) {
			POSITION = LEFT_SELECTED;
			listener.onToggle(LEFT_SELECTED);
			float destX = ViewHelper.getX(btnLeft);
			ObjectAnimator shiftLeft = ObjectAnimator.ofFloat(btnMovable, "x",
					destX);
			shiftLeft.start();
		} else {
			POSITION = RIGHT_SELECTED;
			listener.onToggle(RIGHT_SELECTED);
			float destX = ViewHelper.getX(btnRight);
			ObjectAnimator shiftRight = ObjectAnimator.ofFloat(btnMovable, "x",
					destX);
			shiftRight.start();
		}

	}

}
