package com.github.fallblank.ganklast.util;

import android.content.Context;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.TextAppearanceSpan;

import com.github.fallblank.ganklast.R;
import com.github.fallblank.ganklast.data.entity.Gank;

/**
 * Created by fallb on 2016/4/23.
 */
public class StringStyleUtils {
    public static SpannableString format(Context context, String text, int style) {
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new TextAppearanceSpan(context, style), 0, text.length(), 0);
        return spannableString;
    }

    public static CharSequence getGankInfoSequence(Context context, Gank gank) {
        SpannableStringBuilder builder = new SpannableStringBuilder(gank.desc).append(
                StringStyleUtils.format(context, " (via. " + gank.who + ")", R.style.ViaTextAppearance));
        return builder.subSequence(0, builder.length());
    }
}
