package vimmone.blog.utils;

import android.content.Context;
import android.text.InputType;
import android.widget.TextView;
import android.widget.Toast;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

/**
 * @autor Vimmone
 */

public class Dialog {
    public static void showDialog(final Context context, String title, final TextView textView) {
        final String[] modify = new String[1];
        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(context);
        builder.setTitle(title)
                .setPlaceholder("在此输入修改内容")
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        CharSequence text = builder.getEditText().getText();

                        if (text != null && text.length() > 0) {
                            Toast.makeText(context, "修改成功,请保存修改" , Toast.LENGTH_SHORT).show();
                            textView.setText(text);
                            dialog.dismiss();
//

                        } else {
                            Toast.makeText(context, "修改失败" , Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .show();
    }

}
