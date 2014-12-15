package sea.goethe.sportpas.lib;

import sea.goethe.sportpas.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NavDrawerListAdapter extends BaseAdapter{
    private Context context;
    private String[] titleItem;

    public NavDrawerListAdapter(Context context, String[] titleItem){
        this.context = context;
        this.titleItem = titleItem;
    }

    @Override
    public int getCount() {
        return titleItem.length;
    }

    @Override
    public Object getItem(int position) {
        return titleItem[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams") @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.drawer_list_item, null);
        }

        TextView txtTitle = (TextView) convertView.findViewById(R.id.titleSidebar);

        txtTitle.setText(titleItem[position]);

        return convertView;
    }
}

