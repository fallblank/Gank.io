package com.github.fallblank.ganklast.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by fallb on 2016/4/26.
 */
public class CopyBoardUtils {
    public static void copyToClipBoard(Context context, String copyContent, String toast) {
        ClipData clipData = ClipData.newPlainText("gank.io_copy", copyContent);
        ClipboardManager manager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        manager.setPrimaryClip(clipData);
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
    }
}
