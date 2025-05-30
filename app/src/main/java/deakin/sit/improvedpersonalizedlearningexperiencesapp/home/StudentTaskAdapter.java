package deakin.sit.improvedpersonalizedlearningexperiencesapp.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import deakin.sit.improvedpersonalizedlearningexperiencesapp.R;
import deakin.sit.improvedpersonalizedlearningexperiencesapp.account.AccountViewActivity;
import deakin.sit.improvedpersonalizedlearningexperiencesapp.database.StudentTask;

public class StudentTaskAdapter extends RecyclerView.Adapter<StudentTaskAdapter.ViewHolder> {
    List<StudentTask> studentTasks;
    HomeActivity homeActivity;
    AccountViewActivity accountViewActivity;
    public StudentTaskAdapter(List<StudentTask> studentTasks, HomeActivity homeActivity) {
        this.studentTasks = studentTasks;
        this.homeActivity = homeActivity;
        this.accountViewActivity = null;
    }

    public StudentTaskAdapter(List<StudentTask> studentTasks, AccountViewActivity accountViewActivity) {
        this.studentTasks = studentTasks;
        this.homeActivity = null;
        this.accountViewActivity = accountViewActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StudentTask studentTask = studentTasks.get(position);

        holder.taskTitleTextView.setText(studentTask.getTitle());
        holder.taskDescriptionTextView.setText(studentTask.getDescription());

        if (accountViewActivity!=null) {
            holder.beginTaskButton.setText("View task");
            holder.beginTaskButton.setOnClickListener(view -> {
                accountViewActivity.viewTask(studentTask);
            });
        } else if (homeActivity!=null) {
            holder.beginTaskButton.setOnClickListener(view -> {
                homeActivity.beginTask(studentTask);
            });
        }
    }

    @Override
    public int getItemCount() {
        return studentTasks.size();
    }

    public void updateTaskList(List<StudentTask> taskList) {
        this.studentTasks = taskList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView taskTitleTextView, taskDescriptionTextView;
        Button beginTaskButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            taskTitleTextView = itemView.findViewById(R.id.taskTitleTextView);
            taskDescriptionTextView = itemView.findViewById(R.id.taskDescriptionTextView);
            beginTaskButton = itemView.findViewById(R.id.beginTaskButton);
        }
    }
}
