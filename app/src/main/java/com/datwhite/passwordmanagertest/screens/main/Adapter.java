package com.datwhite.passwordmanagertest.screens.main;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import com.datwhite.passwordmanagertest.R;
import com.datwhite.passwordmanagertest.model.Password;
import com.datwhite.passwordmanagertest.screens.details.PasswordDetailsActivity;
import com.datwhite.passwordmanagertest.toast.MakeToast;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import static android.content.Context.CLIPBOARD_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class Adapter extends RecyclerView.Adapter<Adapter.PasswordViewHolder> {

    private SortedList<Password> sortedList;

    public Adapter() {

        //Для отслеживания обновлений внутри себя
        //и передачи информации об обновлении recyclerview
        //для проигрывания анимации
        sortedList = new SortedList<>(Password.class, new SortedList.Callback<Password>() {
            @Override
            //Сравнение элементов списка для сортировки
            public int compare(Password o1, Password o2) {
                if (o2.getTimestamp() > o1.getTimestamp()) {
                    return 1;
                }
                if (o2.getTimestamp() < o1.getTimestamp()) {
                    return -1;
                }
                return 0;
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemRangeChanged(position, count);
            }

            //Сравнение на полную идентичность
            //(кроме содержания, одинаковые и id)
            @Override
            public boolean areContentsTheSame(Password oldItem, Password newItem) {
                return oldItem.equals(newItem);
            }

            //Сравнение на одинаковые id
            //(но содержимое может быть разным)
            @Override
            public boolean areItemsTheSame(Password item1, Password item2) {
                return item1.getUid() == item2.getUid();
            }

            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition, toPosition);
            }
        });
    }

    @NonNull
    @Override
    public PasswordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PasswordViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pass_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PasswordViewHolder holder, int position) {
        holder.bind(sortedList.get(position));
    }

    @Override
    public int getItemCount() {
        return sortedList.size();
    }

    //Функция для замены элементов
    public void setItems(List<Password> passwords) {
        sortedList.replaceAll(passwords);
    }

    //Класс для отдельного элемента
    static class PasswordViewHolder extends RecyclerView.ViewHolder {

        Password password;

        TextView website_item;
        TextView email_item;
        View copy;

        ClipboardManager clipboardManager;
        ClipData clipData;

        public PasswordViewHolder(@NonNull View itemView) {
            super(itemView);

            website_item = itemView.findViewById(R.id.website_item);
            email_item = itemView.findViewById(R.id.email_item);
            copy = itemView.findViewById(R.id.copy);

            //Вызов активити для редактирование заметки,
            //т.е. обработка нажатия на заметку
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PasswordDetailsActivity.start((Activity) itemView.getContext(), password);
                }
            });

            //Обработка нажатие на кнопку копирования
            final android.content.ClipboardManager clipboardManager = (android.content.ClipboardManager) itemView.getContext()
                    .getSystemService(Context.CLIPBOARD_SERVICE);
            copy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Snackbar.make(itemView, password.getText(), Snackbar.LENGTH_LONG)
//                            .show();

                    clipData = ClipData.newPlainText("text", password.getText());
                    clipboardManager.setPrimaryClip(clipData);

                    Toast.makeText(itemView.getContext(),"Copied ",Toast.LENGTH_SHORT).show();

//                    Snackbar.make(itemView, "Copied", Snackbar.LENGTH_LONG)
//                            .show();
                }
            });
        }

        //Фукнция, которая отображает значени полей разметки на view
        public void bind(Password password) {
            this.password = password;

            website_item.setText(password.getWebsite());
            email_item.setText(password.getEmail());
        }
    }
}
