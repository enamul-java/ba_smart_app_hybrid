package eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import eraapps.bankasia.bdinternetbanking.apps.R;
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.CodeDesOptions;

public class AutoCompleteAdapter extends ArrayAdapter<CodeDesOptions> {
    private final Context context;

    private final List<CodeDesOptions> items;
    private final List<CodeDesOptions> tempItems;
    private final List<CodeDesOptions> suggestions;

    public AutoCompleteAdapter(@NonNull Context context, ArrayList<CodeDesOptions> items) {
        super(context, R.layout.row_drop_down_auto_complet, items);
        this.items = items;
        this.context = context;
        // this.resourceId = resourceId;
        tempItems = new ArrayList<>(items);
        suggestions = new ArrayList<>();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        try {
            if (convertView == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                view = inflater.inflate(R.layout.row_drop_down_auto_complet, parent, false);
            }
            CodeDesOptions fruit = getItem(position);
            TextView name = (TextView) view.findViewById(R.id.tv_dropdown_name);

            assert fruit != null;
            name.setText(fruit.getCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert view != null;
        return view;
    }

    @Nullable
    @Override
    public CodeDesOptions getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return fruitFilter;
    }

    private final Filter fruitFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            CodeDesOptions fruit = (CodeDesOptions) resultValue;
            return fruit.getCode();
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            if (charSequence != null) {
                suggestions.clear();
                for (CodeDesOptions fruit : tempItems) {
//                    if (fruit.desc.toLowerCase().startsWith(charSequence.toString().toLowerCase())) {
//                        suggestions.add(fruit);
//                    }

                    if (Objects.requireNonNull(fruit.getCode()).toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        suggestions.add(fruit);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            ArrayList<CodeDesOptions> tempValues = (ArrayList<CodeDesOptions>) filterResults.values;
            if (filterResults.count > 0) {
                clear();
                for (CodeDesOptions fruitObj : tempValues) {
                    add(fruitObj);
                }
                notifyDataSetChanged();
            } else {
                clear();
                notifyDataSetChanged();
            }
        }
    };
}