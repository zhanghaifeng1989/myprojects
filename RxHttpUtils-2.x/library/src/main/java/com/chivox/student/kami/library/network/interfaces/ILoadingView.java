package com.chivox.student.kami.library.network.interfaces;

/**
 * <pre>
 *      desc    : 接口化处理loadingView，突破之前只能用dialog的局限
 * </pre>
 */
public interface ILoadingView {
    /**
     * 显示loadingView
     */
    void showLoadingView();

    /**
     * 隐藏loadingView
     */
    void hideLoadingView();
}
