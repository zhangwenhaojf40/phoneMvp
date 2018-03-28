package yy.hao.com.testphonemvp.present;

import android.net.IpPrefix;

import com.trello.rxlifecycle2.LifecycleProvider;

import yy.hao.com.testphonemvp.base.IBaseView;

/**
 * Created by Administrator
 * on 2018/3/20 0020.
 */

public abstract class BasePresent<iView extends IBaseView,T> implements IPresent<iView,T>{
    public iView mView;
    public LifecycleProvider<T> provider;
    @Override
    public void attachView( iView mView,LifecycleProvider<T> provider) {
        this.mView =  mView;
        this.provider = provider;
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView = null;
        }
    }
}
