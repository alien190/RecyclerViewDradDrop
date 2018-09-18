package com.example.alien.recyclerviewdraddrop.common;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class CustomLayoutManager extends RecyclerView.LayoutManager {

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {

        detachAndScrapAttachedViews(recycler);

        int pos = 0;
        int itemCount = getItemCount();
        //int overallHeight = 0;
        //int overallWidth = 0;
        int viewHeight;
        int viewWidth;

        int left;
        int top;
        int right;
        int bottom;

        int prevTop = 0;
        int prevRight = 0;
        int prevBottom = 0;

        while (pos < itemCount) {
            View view = recycler.getViewForPosition(pos);
            RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) view.getLayoutParams();
            addView(view);
            measureChildWithMargins(view, 0, 0);

            if (prevTop == 0) {
                prevTop = lp.topMargin;
            }
            viewWidth = getDecoratedMeasuredWidth(view);
            viewHeight = getDecoratedMeasuredHeight(view);

            if (prevRight + viewWidth + lp.leftMargin < getWidth()) {
                left = prevRight + lp.leftMargin;
                top = prevTop;
                right = left + viewWidth;
                bottom = top + viewHeight;
            } else {
                left = lp.leftMargin;
                top = prevBottom + lp.topMargin;
                right = left + viewWidth;
                bottom = top + viewHeight;
            }
            layoutDecorated(view, left, top, right, bottom);
            prevTop = top;
            prevRight = right;
            prevBottom = bottom;
            pos++;
        }
    }

//    @Override
//    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
//        detachAndScrapAttachedViews(recycler);
//        fillDown(recycler);
//    }
//
//    private void fillDown(RecyclerView.Recycler recycler) {
//        int pos = 0;
//        boolean fillDown = true;
//        int height = getHeight();
//        int viewTop = 0;
//        int itemCount = getItemCount();
//        int viewHeight = (int) (getHeight() * VIEW_HEIGHT_PERCENT);
//        final int widthSpec = View.MeasureSpec.makeMeasureSpec(getWidth(), View.MeasureSpec.EXACTLY);
//        final int heightSpec = View.MeasureSpec.makeMeasureSpec(getHeight(), View.MeasureSpec.EXACTLY);
//
//        while (fillDown && pos < itemCount){
//            View view = recycler.getViewForPosition(pos);
//            addView(view);
//            measureChildWithDecorationsAndMargin(view, widthSpec, heightSpec);
//            int decoratedMeasuredWidth = getDecoratedMeasuredWidth(view);
//            layoutDecorated(view, 0, viewTop, decoratedMeasuredWidth, viewTop + viewHeight);
//            viewTop = getDecoratedBottom(view);
//            fillDown = viewTop <= height;
//            pos++;
//        }
//    }

    private void measureChildWithDecorationsAndMargin(View child, int widthSpec, int heightSpec) {
        Rect decorRect = new Rect();
        calculateItemDecorationsForChild(child, decorRect);
        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) child.getLayoutParams();
        widthSpec = updateSpecWithExtra(widthSpec, lp.leftMargin + decorRect.left,
                lp.rightMargin + decorRect.right);
        heightSpec = updateSpecWithExtra(heightSpec, lp.topMargin + decorRect.top,
                lp.bottomMargin + decorRect.bottom);
        child.measure(widthSpec, heightSpec);
    }

    private int updateSpecWithExtra(int spec, int startInset, int endInset) {
        if (startInset == 0 && endInset == 0) {
            return spec;
        }
        final int mode = View.MeasureSpec.getMode(spec);
        if (mode == View.MeasureSpec.AT_MOST || mode == View.MeasureSpec.EXACTLY) {
            return View.MeasureSpec.makeMeasureSpec(
                    View.MeasureSpec.getSize(spec) - startInset - endInset, mode);
        }
        return spec;
    }

//    @Override
//    public boolean canScrollVertically() {
//        return true;
//    }
//
//
//    @Override
//    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
//        int delta = scrollVerticallyInternal(dy);
//        offsetChildrenVertical(-delta);
//        return delta;
//    }
//
//    private int scrollVerticallyInternal(int dy) {
//        int childCount = getChildCount();
//        int itemCount = getItemCount();
//        if (childCount == 0){
//            return 0;
//        }
//
//        final View topView = getChildAt(0);
//        final View bottomView = getChildAt(childCount - 1);
//
//        //Случай, когда все вьюшки поместились на экране
//        int viewSpan = getDecoratedBottom(bottomView) - getDecoratedTop(topView);
//        if (viewSpan <= getHeight()) {
//            return 0;
//        }
//
//        int delta = 0;
//        //если контент уезжает вниз
//        if (dy < 0){
//            View firstView = getChildAt(0);
//            int firstViewAdapterPos = getPosition(firstView);
//            if (firstViewAdapterPos > 0){ //если верхняя вюшка не самая первая в адаптере
//                delta = dy;
//            } else { //если верхняя вьюшка самая первая в адаптере и выше вьюшек больше быть не может
//                int viewTop = getDecoratedTop(firstView);
//                delta = Math.max(viewTop, dy);
//            }
//        } else if (dy > 0){ //если контент уезжает вверх
//            View lastView = getChildAt(childCount - 1);
//            int lastViewAdapterPos = getPosition(lastView);
//            if (lastViewAdapterPos < itemCount - 1){ //если нижняя вюшка не самая последняя в адаптере
//                delta = dy;
//            } else { //если нижняя вьюшка самая последняя в адаптере и ниже вьюшек больше быть не может
//                int viewBottom = getDecoratedBottom(lastView);
//                int parentBottom = getHeight();
//                delta = Math.min(viewBottom - parentBottom, dy);
//            }
//        }
//        return delta;
//    }
}
