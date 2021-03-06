package news.lxs.com.news.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;
/**
 * 解决在scrollview中只显示第一行数据的问题
 * @author lxs
 * 2016年7月25日
 */
public class MyGridView extends GridView {
	public MyGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyGridView(Context context) {
		super(context);
	}

	public MyGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
	
	
}
