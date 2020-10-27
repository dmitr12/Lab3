package dyrda.fit.bstu.by.lab4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentInfo extends Fragment {

    Context context;
    public Company Company=new Company();
    TextView name,email,phone,location,link;



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_info, container,false);
        name=view.findViewById(R.id.inpName);
        email=view.findViewById(R.id.inpEmail);
        phone=view.findViewById(R.id.inpPhoneNumber);
        location=view.findViewById(R.id.inpLocation);
        link=view.findViewById(R.id.inpLink);
        setCompany(Company);
        return view;
    }

    public void setCompany(Company company){
        name.setText(company.getName());
        email.setText(company.getEmail());
        phone.setText(company.getPhoneNumber());
        location.setText(company.getLocation());
        link.setText(company.getLink());
    }
}
