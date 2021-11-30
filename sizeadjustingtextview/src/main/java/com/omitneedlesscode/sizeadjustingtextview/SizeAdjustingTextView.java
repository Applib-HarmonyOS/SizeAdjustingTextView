package com.omitneedlesscode.sizeadjustingtextview;

import ohos.agp.components.AttrSet;
import ohos.agp.components.Text;
import ohos.agp.render.Paint;
import ohos.agp.text.SimpleTextLayout;
import ohos.agp.utils.Rect;
import ohos.agp.utils.TextAlignment;
import ohos.app.Context;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

/**
 * SizeAdjustingTextView is a TextView that adjusts text size
 * depending if or if not it will fit in the view bounds.
 * Copyright (C) 2014 Elliott Chenger
 * <p>
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 * @author Elliott Chenger
 * @author Chase Colburn
 * @since Jan 18, 2014
 * @since Apr 4, 2011
 */
public class SizeAdjustingTextView extends Text implements Text.TextObserver {
    public static final HiLogLabel HI_LOG_LABEL = new HiLogLabel(0, 0, "SizeAdjustingTextView");

    Paint mTextPaint;

    SimpleTextLayout mTextLayout;

    // Minimum text size for this text view
    public static final float MIN_TEXT_SIZE = 20;

    // Text size that is set from code. This acts as a starting point for resizing
    private float mTextSize;

    // Temporary upper bounds on the starting text size
    private float mMaxTextSize = 0;

    // Lower bounds for text size
    private float mMinTextSize = MIN_TEXT_SIZE;

    // Default constructor override
    public SizeAdjustingTextView(Context context) {
        this(context, null);
        mTextSize = getTextSize();
        addTextObserver(this);
        init();
    }

    // Default constructor when inflating from XML file
    public SizeAdjustingTextView(Context context, AttrSet attrs) {
        this(context, attrs, "0");
        mTextSize = getTextSize();
        addTextObserver(this);
        init();
    }

    // Default constructor override
    public SizeAdjustingTextView(Context context, AttrSet attrs, String defStyle) {
        super(context, attrs, defStyle);
        mTextSize = getTextSize();
        addTextObserver(this);
        init();
    }

    @Override
    public void onTextUpdated(String s, int start, int before, int after) {
        // Since this view may be reused, it is good to reset the text size
        resetTextSize();
    }

    private void init() {
        mTextPaint = new Paint();
        mTextPaint.setMultipleLine(true);
        mTextPaint.setTextSize((int) mTextSize);
        mTextPaint.setTextAlign(TextAlignment.CENTER);
    }

    @Override
    public void setText(String text) {
        super.setText(text);
        resetTextSize();
        resizeText();
    }

    @Override
    public void setTextSize(int size) {
        super.setTextSize(size);
    }

    @Override
    public void setTextSize(int size, TextSizeType textSizeType) {
        super.setTextSize(size, textSizeType);
    }

    public void setMaxTextSize(float maxTextSize) {
        mMaxTextSize = maxTextSize;
        postLayout();
        invalidate();
    }

    public float getMaxTextSize() {
        return mMaxTextSize;
    }

    public void setMinTextSize(float minTextSize) {
        mMinTextSize = minTextSize;
        postLayout();
        invalidate();
    }

    public float getMinTextSize() {
        return mMinTextSize;
    }

    public void resetTextSize() {
        if (mTextSize > 0) {
            super.setTextSize((int) mTextSize, TextSizeType.PX);
            mMaxTextSize = mTextSize;
        }
    }

    private void resizeText() {
        int heightLimit = getHeight() - getPaddingBottom() - getPaddingTop();
        int widthLimit = getWidth() - getPaddingLeft() - getPaddingRight();
        resizeText(widthLimit, heightLimit);
    }

    public void resizeText(int width, int height) {
        CharSequence text = getText();
        if (text == null || text.length() == 0 || height <= 0 || width <= 0 || mTextSize == 0) {
            return;
        }
        float newTextSize = findNewTextSize(width, height, text);
        changeTextSize(newTextSize);
    }

    private void changeTextSize(float newTextSize) {
        setTextSize((int) newTextSize, TextSizeType.PX);
    }

    private float findNewTextSize(int width, int height, CharSequence text) {
        float targetTextSize = getTextSize();
        int textHeight = getTextHeight(text, width, height, targetTextSize);
        while (textHeight > height && targetTextSize > mMinTextSize) {
            targetTextSize = Math.max(targetTextSize - 1, mMinTextSize);
            textHeight = getTextHeight(text, width, height, targetTextSize);
        }
        return targetTextSize;
    }

    private int getTextHeight(CharSequence source, int width, int height, float textSize) {
        mTextPaint.setTextSize((int) textSize);
        mTextLayout = new SimpleTextLayout(source.toString(), mTextPaint,
                new Rect(0, 0, width, height), width);
        return mTextLayout.getHeight();
    }
}
