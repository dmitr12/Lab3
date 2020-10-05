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
    private HashMap<Integer, Boolean> checks=new HashMap<>();

    public CompanyAdapter(Context context, int res, List<Company> companies) {
        super(context, res, companies);
        this.companies = companies;
        this.layout = res;
        this.inflater = LayoutInflater.from(context);
        for(int i=0;i<companies.size();i++)
            checks.put(i,false);
    }

    public Company getItem(int pos){
        return companies.get(pos);
    }

    void removeChecks(){
        for(int i=0;i<checks.size();i++)
            if(checks.get(i))
                checks.put(i,false);
    }

    void toggleChecked(int position){
        if(checks.get(position))
            checks.put(position,false);
        else
            checks.put(position,true);
        notifyDataSetChanged();
    }

    public List<Integer> getCheckedItemPositions(){
        List<Integer> checkedItemPositions=new ArrayList<>();
        for(int i=0;i<checks.size();i++){
            if(checks.get(i))
                (checkedItemPositions).add(i);
        }
        return checkedItemPositions;
    }

    List<Company> getCheckedItems(){
        List<Company> checkedCompanies=new ArrayList<>();
        for(int i=0;i<checks.size();i++){
            if(checks.get(i))
                (checkedCompanies).add(companies.get(i));
        }
        return checkedCompanies;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view=inflater.inflate(R.layout.list_item, parent, false);
        TextView nameView = (TextView) view.findViewById(R.id.Name);
        TextView locationView = (TextView) view.findViewById(R.id.Location);
        CheckedTextView checkedTextView=(CheckedTextView) view.findViewById(R.id.checkedTextView);
        Boolean checked=checks.get(position);
        if(checked!=null)
            checkedTextView.setChecked(checked);
        Company company = companies.get(position);
        nameView.setText(company.getName());
        locationView.setText(company.getLocation());
        return view;
    }
}