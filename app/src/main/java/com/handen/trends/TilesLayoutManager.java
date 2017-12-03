package com.handen.trends;

import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

import java.util.HashMap;

/**
 * Created by Vanya on 26.11.2017.
 */

public class TilesLayoutManager extends RecyclerView.LayoutManager {

    private static final float VIEW_HEIGHT_PERCENT = 0.75f;
    private SparseArray<View> viewCache = new SparseArray<>();

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        detachAndScrapAttachedViews(recycler);
        fillDown(recycler);
    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }

    private void fillDown(RecyclerView.Recycler recycler) {
        int pos = 0;
        boolean fillDown = true;
        int height = getHeight();
        int viewTop = 0;
        int itemCount = getItemCount();
        int viewHeight = (int) (getHeight() * VIEW_HEIGHT_PERCENT);
        final int widthSpec = View.MeasureSpec.makeMeasureSpec(getWidth(), View.MeasureSpec.EXACTLY);
        final int heightSpec = View.MeasureSpec.makeMeasureSpec(getHeight(), View.MeasureSpec.EXACTLY);

        while (fillDown && pos < itemCount){
            View view = recycler.getViewForPosition(pos);
            addView(view);
            measureChildWithDecorationsAndMargin(view, widthSpec, heightSpec);
            int decoratedMeasuredWidth = getDecoratedMeasuredWidth(view);
            layoutDecorated(view, 0, viewTop, decoratedMeasuredWidth, viewTop + viewHeight);
            viewTop = getDecoratedBottom(view);
            fillDown = viewTop <= height;
            pos++;
        }
    }

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

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int delta = scrollVerticallyInternal(dy);
        offsetChildrenVertical(-delta);
        return delta;
    }

    private int scrollVerticallyInternal(int dy) {
        int childCount = getChildCount();
        int itemCount = getItemCount();
        if (childCount == 0){
            return 0;
        }

        final View topView = getChildAt(0);
        final View bottomView = getChildAt(childCount - 1);

        //Случай, когда все вьюшки поместились на экране
        int viewSpan = getDecoratedBottom(bottomView) - getDecoratedTop(topView);
        if (viewSpan <= getHeight()) {
            return 0;
        }

        int delta = 0;
        //если контент уезжает вниз
        if (dy < 0){
            View firstView = getChildAt(0);
            int firstViewAdapterPos = getPosition(firstView);
            if (firstViewAdapterPos > 0){ //если верхняя вюшка не самая первая в адаптере
                delta = dy;
            } else { //если верхняя вьюшка самая первая в адаптере и выше вьюшек больше быть не может
                int viewTop = getDecoratedTop(firstView);
                delta = Math.max(viewTop, dy);
            }
        } else if (dy > 0){ //если контент уезжает вверх
            View lastView = getChildAt(childCount - 1);
            int lastViewAdapterPos = getPosition(lastView);
            if (lastViewAdapterPos < itemCount - 1){ //если нижняя вюшка не самая последняя в адаптере
                delta = dy;
            } else { //если нижняя вьюшка самая последняя в адаптере и ниже вьюшек больше быть не может
                int viewBottom = getDecoratedBottom(lastView);
                int parentBottom = getHeight();
                delta = Math.min(viewBottom - parentBottom, dy);
            }
        }
        return delta;
    }

    private void fill(RecyclerView.Recycler recycler) {

        View anchorView = getAnchorView();
        viewCache.clear();

        //Помещаем вьюшки в кэш и...
        for (int i = 0, cnt = getChildCount(); i < cnt; i++) {
            View view = getChildAt(i);
            int pos = getPosition(view);
            viewCache.put(pos, view);
        }

        //... и удалям из лэйаута
        for (int i = 0; i < viewCache.size(); i++) {
            detachView(viewCache.valueAt(i));
        }

        fillUp(anchorView, recycler);
        fillDown(anchorView, recycler);

        //отправляем в корзину всё, что не потребовалось в этом цикле лэйаута
        //эти вьюшки или ушли за экран или не понадобились, потому что соответствующие элементы
        //удалились из адаптера
        for (int i=0; i < viewCache.size(); i++) {
            recycler.recycleView(viewCache.valueAt(i));
        }

    }

    private void fillUp(@Nullable View anchorView, RecyclerView.Recycler recycler) {
        int anchorPos = 0;
        int anchorTop = 0;
        if (anchorView != null){
            anchorPos = getPosition(anchorView);
            anchorTop = getDecoratedTop(anchorView);
        }

        boolean fillUp = true;
        int pos = anchorPos - 1;
        int viewBottom = anchorTop; //нижняя граница следующей вьюшки будет начитаться от верхней границы предыдущей
        int viewHeight = (int) (getHeight() * VIEW_HEIGHT_PERCENT);
        final int widthSpec = View.MeasureSpec.makeMeasureSpec(getWidth(), View.MeasureSpec.EXACTLY);
        final int heightSpec = View.MeasureSpec.makeMeasureSpec(viewHeight, View.MeasureSpec.EXACTLY);
        while (fillUp && pos >= 0){
            View view = viewCache.get(pos); //проверяем кэш
            if (view == null){
                //если вьюшки нет в кэше - просим у recycler новую, измеряем и лэйаутим её
                view = recycler.getViewForPosition(pos);
                addView(view, 0);
                measureChildWithDecorationsAndMargin(view, widthSpec, heightSpec);
                int decoratedMeasuredWidth = getDecoratedMeasuredWidth(view);
                layoutDecorated(view, 0, viewBottom - viewHeight, decoratedMeasuredWidth, viewBottom);
            } else {
                //если вьюшка есть в кэше - просто аттачим её обратно
                //нет необходимости проводить measure/layout цикл.
                attachView(view);
                viewCache.remove(pos);
            }
            viewBottom = getDecoratedTop(view);
            fillUp = (viewBottom > 0);
            pos--;
        }
    }

    private void fillDown(@Nullable View anchorView, RecyclerView.Recycler recycler) {
        int anchorPos = 0;
        int anchorTop = 0;
        if (anchorView != null){
            anchorPos = getPosition(anchorView);
            anchorTop = getDecoratedTop(anchorView);
        }

        int pos = anchorPos;
        boolean fillDown = true;
        int height = getHeight();
        int viewTop = anchorTop;
        int itemCount = getItemCount();
        int viewHeight = (int) (getHeight() * VIEW_HEIGHT_PERCENT);
        final int widthSpec = View.MeasureSpec.makeMeasureSpec(getWidth(), View.MeasureSpec.EXACTLY);
        final int heightSpec = View.MeasureSpec.makeMeasureSpec(viewHeight, View.MeasureSpec.EXACTLY);

        while (fillDown && pos < itemCount){
            View view = viewCache.get(pos);
            if (view == null){
                view = recycler.getViewForPosition(pos);
                addView(view);
                measureChildWithDecorationsAndMargin(view, widthSpec, heightSpec);
                int decoratedMeasuredWidth = getDecoratedMeasuredWidth(view);
                layoutDecorated(view, 0, viewTop, decoratedMeasuredWidth, viewTop + viewHeight);
            } else {
                attachView(view);
                viewCache.remove(pos);
            }
            viewTop = getDecoratedBottom(view);
            fillDown = viewTop <= height;
            pos++;
        }
    }

    //метод вернет вьюшку с максимальной видимой площадью
    private View getAnchorView() {
        int childCount = getChildCount();
        HashMap<Integer, View> viewsOnScreen = new HashMap<>();
        Rect mainRect = new Rect(0, 0, getWidth(), getHeight());
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            int top = getDecoratedTop(view);
            int bottom = getDecoratedBottom(view);
            int left = getDecoratedLeft(view);
            int right = getDecoratedRight(view);
            Rect viewRect = new Rect(left, top, right, bottom);
            boolean intersect = viewRect.intersect(mainRect);
            if (intersect){
                int square = viewRect.width() * viewRect.height();
                viewsOnScreen.put(square, view);
            }
        }
        if (viewsOnScreen.isEmpty()){
            return null;
        }
        Integer maxSquare = null;
        for (Integer square : viewsOnScreen.keySet()) {
            if (maxSquare == null){
                maxSquare = square;
            } else {
                maxSquare = Math.max(maxSquare, square);
            }
        }
        return viewsOnScreen.get(maxSquare);
    }





}
