package cn.starnine.chat;

import java.util.List;
import java.util.Timer;

import android.R.layout;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TextAdapter extends BaseAdapter {
	private List<ListData> lists;
	private Context mContext;

	private RelativeLayout layout;

	public TextAdapter(List<ListData> lists, Context mContext) {
		super();
		this.lists = lists;
		this.mContext = mContext;

	}

	public int getCount() {
		// TODO Auto-generated method stub
		return lists == null ? 0 : lists.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return lists.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = LayoutInflater.from(mContext);
		if (lists.get(position).getFlag() == ListData.RECEIVER) {
			layout = (RelativeLayout) inflater.inflate(
					R.layout.activity_leftitem, null);
		}
		if (lists.get(position).getFlag() == ListData.SEND) {
			layout = (RelativeLayout) inflater.inflate(
					R.layout.activity_rightitem, null);
		}
		TextView time = (TextView) layout.findViewById(R.id.tvtime);
		TextView tv = (TextView) layout.findViewById(R.id.tv);
		tv.setText(lists.get(position).getContent());
		time.setText(lists.get(position).getTime());

		return layout;
	}

}
