package dyrda.fit.bstu.by.lab4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CompanyAdapter extends ArrayAdapter<Company> {
    private LayoutInflater inflater;
    private int layout;
    private List<Company> companies;

    public CompanyAdapter(Context context, int res, List<Company> companies) {
        super(context, res, companies);
        this.companies = companies;
        this.layout = res;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view=inflater.inflate(R.layout.list_item, parent, false);
        TextView nameView = (TextView) view.findViewById(R.id.Name);
        TextView locationView = (TextView) view.findViewById(R.id.Location);
        Company company = companies.get(position);
        nameView.setText(company.getName());
        locationView.setText(company.getLocation());
        return view;
    }
}