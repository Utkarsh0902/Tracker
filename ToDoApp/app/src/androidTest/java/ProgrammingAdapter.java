import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.R;

public class ProgrammingAdapter extends RecyclerView.Adapter<ProgrammingAdapter.ProgrammingVieHolder> {

    @NonNull
    private  String[] data;
    public ProgrammingAdapter(String[] data){
        this.data = data;
    }
    public ProgrammingVieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator = LayoutInflater.from(parent.getContext());
        View view = inflator.inflate(R.layout.list_item_layout,parent, false);

        return new ProgrammingVieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgrammingVieHolder holder, int position) {
        String title = data[position];
        holder.txtTitle.setText(title);

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ProgrammingVieHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        public ProgrammingVieHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
        }
    }

}
