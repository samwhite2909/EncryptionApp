package com.swhite.encryptionapp.db;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.swhite.encryptionapp.R;
import com.swhite.encryptionapp.di.EncryptionApplication;
import com.swhite.encryptionapp.models.Operation;

import java.util.List;

public class OperationsAdapter extends RecyclerView.Adapter<OperationsAdapter.ViewHolder> {

    private List<Operation> operations;


    public OperationsAdapter(List<Operation> operations) {
        EncryptionApplication.get().encryptionComponent.inject(this);
        this.operations = operations;
    }

    //Creates the view using the pre-defined layout.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item_row, parent,
                false);
        return new OperationsAdapter.ViewHolder(view);
    }

    //Sets the data to the relevant text view to be displayed.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.dateTime.setText(operations.get(position).getDateTime());
        holder.operation.setText(operations.get(position).getOperationType());
        holder.method.setText(operations.get(position).getEncryptionMethod());
        holder.input.setText(operations.get(position).getInput());
        holder.output.setText(operations.get(position).getOutput());
    }

    @Override
    public int getItemCount() {
        return operations.size();
    }

    //Creates the view holder and assigns the text views to the view.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView input, output, dateTime, method, operation;
        public ViewHolder(@NonNull View OperationView) {
            super(OperationView);
            dateTime = OperationView.findViewById(R.id.date_time_text_view);
            operation = OperationView.findViewById(R.id.operation_text_view);
            method = OperationView.findViewById(R.id.encryption_method_text_view);
            input = OperationView.findViewById(R.id.input_text_view);
            output = OperationView.findViewById(R.id.output_text_view);
        }
    }
}
