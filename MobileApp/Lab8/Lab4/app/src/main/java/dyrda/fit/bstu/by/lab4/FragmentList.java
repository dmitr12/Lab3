package dyrda.fit.bstu.by.lab4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.concurrent.ExecutionException;

public class FragmentList extends Fragment {

    ListView listView;
    AsyncSelectAll asyncSelectAll;
    Context context;
    CompanyAdapter adapter;
    private OnFragmentInteractionListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnFragmentInteractionListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_list,container,false);
        listView = view.findViewById(R.id.listCompanies);
        listView.setOnItemClickListener(itemListener);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Company company = (Company) adapterView.getItemAtPosition(i);
                itemPopupMenu(view, company, adapterView);
                return true;
            }
        });
        return view;
    }

    private void itemPopupMenu(final View view, final Company company, final AdapterView<?> adapterView) {
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.inflate(R.menu.popoutmenu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent;
                switch (menuItem.getItemId()) {
                    case R.id.itemDelete:
                        AsyncDelete dlt=new AsyncDelete(context);
                        dlt.execute(company.getId());
                        AsyncSelectAll s=new AsyncSelectAll();
                        s.execute(context);
                        try {
                            adapter=new CompanyAdapter(context, R.layout.list_item, s.get());
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        listView.setAdapter(adapter);
                        break;
                    case R.id.itemEdit:
                        intent=new Intent(context,EditActivity.class);
                        intent.putExtra(Company.class.getSimpleName(), company);
                        startActivity(intent);
                        break;
                    case R.id.itemAddFavourite:
                        company.setIsChosen(1);
                        AsyncEdit edt=new AsyncEdit(context);
                        edt.equals(company);
                        break;
                }
                return true;
            }
        });
        popupMenu.show();
    }

    AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            Company company = (Company) parent.getItemAtPosition(position);
            getInfo(company);
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        asyncSelectAll=new AsyncSelectAll();
        asyncSelectAll.execute(context);
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            adapter=new CompanyAdapter(context, R.layout.list_item, asyncSelectAll.get());
            listView.setAdapter(adapter);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Company company);
    }

    private void getInfo(Company company){
        mListener.onFragmentInteraction(company);
    }
}
