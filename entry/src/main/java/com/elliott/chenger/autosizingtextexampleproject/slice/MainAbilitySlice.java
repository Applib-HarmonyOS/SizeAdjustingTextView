/*
 * Copyright (C) 2020-21 Application Library Engineering Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.elliott.chenger.autosizingtextexampleproject.slice;

import com.elliott.chenger.autosizingtextexampleproject.ResourceTable;
import com.elliott.chenger.autosizingtextexampleproject.utils.TextMover;
import com.omitneedlesscode.sizeadjustingtextview.SizeAdjustingTextView;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Text;
import ohos.agp.components.TextField;
import ohos.miscservices.inputmethod.EditorAttribute;

public class MainAbilitySlice extends AbilitySlice {
    private TextField mMessageEditText;
    private SizeAdjustingTextView mTopMessageBox,
            mMiddleLeftBox, mMiddleRightBox, mBottomLeftBox,
            mBottomMiddleBox, mBottomRightBox;

    Text.EditorActionListener actionListener = new Text.EditorActionListener() {
        @Override
        public boolean onTextEditorAction(int actionId) {
            boolean handled = false;
            String incomingText = mMessageEditText.getText();
            if (actionId == EditorAttribute.ENTER_KEY_TYPE_SEND && !incomingText.isEmpty()) {
                handled = true;
                moveText();
                clearEditText();
            }
            return handled;
        }
    };

    private void moveText() {
        TextMover.moveTextFromCellToCell(mBottomMiddleBox, mBottomRightBox);
        TextMover.moveTextFromCellToCell(mBottomLeftBox, mBottomMiddleBox);
        TextMover.moveTextFromCellToCell(mMiddleRightBox, mBottomLeftBox);
        TextMover.moveTextFromCellToCell(mMiddleLeftBox, mMiddleRightBox);
        TextMover.moveTextFromCellToCell(mTopMessageBox, mMiddleLeftBox);
        String incomingText = mMessageEditText.getText().toString();
        TextMover.moveNewTextIntoCell(mTopMessageBox, incomingText);
    }

    private void clearEditText(){
        mMessageEditText.setText("");
    }

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        mMessageEditText= (TextField) findComponentById(ResourceTable.Id_text_input);
        mMessageEditText.setEditorActionListener(actionListener);
        mTopMessageBox = (SizeAdjustingTextView) findComponentById(ResourceTable.Id_topBox);
        mMiddleLeftBox = (SizeAdjustingTextView) findComponentById(ResourceTable.Id_middleLeftBox);
        mMiddleRightBox = (SizeAdjustingTextView) findComponentById(ResourceTable.Id_middleRightBox);
        mBottomLeftBox = (SizeAdjustingTextView) findComponentById(ResourceTable.Id_bottomLeftBox);
        mBottomMiddleBox = (SizeAdjustingTextView) findComponentById(ResourceTable.Id_bottomMiddleBox);
        mBottomRightBox = (SizeAdjustingTextView) findComponentById(ResourceTable.Id_bottomRightBox);
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

}
