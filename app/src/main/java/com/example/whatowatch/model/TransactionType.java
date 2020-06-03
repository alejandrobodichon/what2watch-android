package com.example.whatowatch.model;

import com.example.whatowatch.R;

import java.util.ArrayList;
import java.util.List;

public enum TransactionType {
    EnterFromLeft,
    EnterFromRight,
    ExitToLeft,
    ExitToRight,
    EnterFromBottom,
    ExitToUp,
    EnterFromUp,
    ExitToBottom;

    public static int getAnim(TransactionType type){
        switch (type){
            case EnterFromLeft:
                return R.anim.enter_from_left;
            case EnterFromRight:
                return R.anim.enter_from_right;
            case ExitToLeft:
                return R.anim.exit_to_left;
            case ExitToRight:
                return R.anim.exit_to_right;
            case EnterFromBottom:
                return R.anim.enter_from_bottom;
            case ExitToUp:
                return R.anim.exit_to_up;
            case EnterFromUp:
                return R.anim.enter_from_up;
            case ExitToBottom:
                return R.anim.exit_to_bottom;
        }
        return 0;
    }

    public static List<Integer> getSlideUpAnimation() {
        List<Integer> lAnimations = new ArrayList<>();
        lAnimations.add(getAnim(EnterFromUp));
        lAnimations.add(getAnim(ExitToBottom));
        lAnimations.add(getAnim(EnterFromBottom));
        lAnimations.add(getAnim(ExitToUp));

        return lAnimations;
    }

    public static List<Integer> getSlideSideAnimation() {
        List<Integer> lAnimations = new ArrayList<>();
        lAnimations.add(getAnim(EnterFromRight));
        lAnimations.add(getAnim(ExitToLeft));
        lAnimations.add(getAnim(EnterFromLeft));
        lAnimations.add(getAnim(ExitToRight));

        return lAnimations;
    }
}
